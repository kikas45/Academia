package com.example.academia.fragments.seconadryFragment;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.academia.Fire_Models.Employee;
import com.example.academia.R;
import com.example.academia.firebase.DAOEmployee;
import com.example.academia.firebase.RVAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;


public class ForYou extends Fragment {
    ProgressBar progressBar;
    SwipeRefreshLayout swipeRefreshLayout;
    RecyclerView recyclerView;
    RVAdapter adapter;
    DAOEmployee dao;
    boolean isLoading=false;
    String key =null;
    @SuppressLint("NotifyDataSetChanged")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.fragment_foryou, container, false);
        swipeRefreshLayout = view.findViewById(R.id.swip);
        progressBar = view.findViewById(R.id.progressBar);
        recyclerView = view.findViewById(R.id.rv);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(manager);
        adapter= new RVAdapter(getContext());
        recyclerView.setAdapter(adapter);
        dao = new DAOEmployee();
        loadData();

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener()
        {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                int totalItem = linearLayoutManager.getItemCount();
                int lastVisible = linearLayoutManager.findLastCompletelyVisibleItemPosition();
                if(totalItem< lastVisible+3)
                {
                    if(isLoading == false)
                    {
                        isLoading=true;
                        loadData();
                        progressBar.setVisibility(View.GONE);

                    }
                }
            }
        });





        return  view;
    }

    private void loadData() {

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onRefresh() {
                if (isLoading){
                    ArrayList<Employee> emps = new ArrayList<>();
                    swipeRefreshLayout.setRefreshing(true);
                    loadData();
                    adapter.notifyDataSetChanged();
                    isLoading =true;
                }else {
                    swipeRefreshLayout.setRefreshing(false);
                }

            }
        });

        progressBar.setVisibility(View.VISIBLE);
        dao.get(key).addListenerForSingleValueEvent(new ValueEventListener()
        {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot)
            {


                if (snapshot.exists()){

                    progressBar.setVisibility(View.GONE);

                }

                ArrayList<Employee> emps = new ArrayList<>();
                for (DataSnapshot data : snapshot.getChildren())
                {
                    Employee emp = data.getValue(Employee.class);
                    emp.setKey(data.getKey());
                    emps.add(emp);
                    key = data.getKey();
                }
                Collections.shuffle(emps);
                adapter.setItems(emps);
                adapter.notifyDataSetChanged();
                isLoading =false;
                progressBar.setVisibility(View.GONE);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error)
            {

            }
        });
    }

    }
