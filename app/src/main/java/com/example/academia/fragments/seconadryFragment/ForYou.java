package com.example.academia.fragments.seconadryFragment;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.academia.Fire_Models.More_model;
import com.example.academia.Fire_Models.Reconmmended_model;
import com.example.academia.R;
import com.example.academia.firebase.More_Adapter;
import com.example.academia.firebase.Recomended_Adapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class ForYou extends Fragment {


    private  RecyclerView recyclerView_rm_Hr, more_recycler_Vh;
    private Recomended_Adapter adapter;
    private More_Adapter more_adapter;
    private DatabaseReference mbase_hr;
    private DatabaseReference mbase_vh;
    private ArrayList<Reconmmended_model> recon_listed;
    private ArrayList<More_model> more_models_list;
    private TextView text_recon22, text_more22;
    @SuppressLint("NotifyDataSetChanged")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.fragment_foryou, container, false);
        text_more22 = view.findViewById(R.id.more_text);
        text_recon22 = view.findViewById(R.id.recon_text);
        /// for horizonayl recylcer view
        recyclerView_rm_Hr = view.findViewById(R.id.recommended_recycler);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext() , LinearLayoutManager.HORIZONTAL , false);
        recyclerView_rm_Hr.setLayoutManager(linearLayoutManager);
        recyclerView_rm_Hr.setHasFixedSize(true);
        mbase_hr = FirebaseDatabase.getInstance().getReference();
        recon_listed = new ArrayList<>();

        ///
        SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(recyclerView_rm_Hr);
        ///
        GetDataFromFirebase();


        recyclerView_rm_Hr.setNestedScrollingEnabled(false);

        //// for the vertical recycler view
        more_recycler_Vh = view.findViewById(R.id.more_recycler);
        more_recycler_Vh.setLayoutManager(new LinearLayoutManager(getContext() , LinearLayoutManager.VERTICAL , false));
        more_recycler_Vh.setHasFixedSize(true);
        mbase_vh = FirebaseDatabase.getInstance().getReference();
        more_models_list = new ArrayList<>();

        ////


        /////
        GetData_More_VR();




        return  view;
    }

    private void GetData_More_VR() {
        Query query = mbase_vh.child("Recon").child("List");
        query.addValueEventListener(new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {



                if (datasnapshot.exists()){
                    more_models_list.clear();
                    text_more22.setVisibility(View.VISIBLE);


                    for (DataSnapshot snapshot:datasnapshot.getChildren() ) {
                        More_model more = new More_model();

                        more.setTitle(snapshot.child("title").getValue().toString());
                        more.setImage(snapshot.child("image").getValue().toString());
                        more.setDes(snapshot.child("des").getValue().toString());

                        more_models_list.add(more); }

                   // Collections.shuffle(more_models_list);

                    more_adapter = new More_Adapter(getContext(), more_models_list);
                    more_recycler_Vh.setAdapter(more_adapter);
                    adapter.notifyDataSetChanged();
                }else {
                    Toast.makeText(getContext(), "data not found", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

    }

    private void GetDataFromFirebase() {
        Query query = mbase_hr.child("Recon").child("List");
        query.addValueEventListener(new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {


                if (datasnapshot.exists()){
                    recon_listed.clear();
                    text_recon22.setVisibility(View.VISIBLE);


                    for (DataSnapshot snapshot:datasnapshot.getChildren() ) {
                        Reconmmended_model message = new Reconmmended_model();

                        message.setTitle(snapshot.child("title").getValue().toString());
                        message.setImage(snapshot.child("image").getValue().toString());
                        message.setDes(snapshot.child("des").getValue().toString());

                        recon_listed.add(message); }

                    //Collections.shuffle(recon_listed);

                    adapter = new Recomended_Adapter(getContext(), recon_listed);
                    recyclerView_rm_Hr.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                }else {
                    Toast.makeText(getContext(), "data not found", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });


    }




}