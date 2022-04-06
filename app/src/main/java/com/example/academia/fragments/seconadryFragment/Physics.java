package com.example.academia.fragments.seconadryFragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.academia.Fire_Models.Message;
import com.example.academia.R;
import com.example.academia.firebase.RecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;


public class Physics extends Fragment {


    //Global varoables
    private RecyclerView recyclerView;
    private DatabaseReference myRef;
    private ArrayList<Message> messagesList;
    private RecyclerAdapter adapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.fragment_physics, container, false);
        recyclerView = view.findViewById(R.id.recyclerview);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);

        myRef = FirebaseDatabase.getInstance().getReference();

        //ArrayList
        messagesList = new ArrayList<>();

        //clear Arralist

        //Get Data Method
        GetDataFromFirebase();

        //// END
        return  view;
    }



    private void GetDataFromFirebase() {

        Query query = myRef.child("David").child("Employee");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {

                if (datasnapshot.exists()){
                    messagesList.clear();


                    for (DataSnapshot snapshot:datasnapshot.getChildren() ){
                        Message message = new Message();

                      message.setDate(snapshot.child("date").getValue().toString());
                      message.setLen(snapshot.child("len").getValue().toString());
                       message.setDes(snapshot.child("des").getValue().toString());
                         message.setIcon(snapshot.child("icon").getValue().toString());
                        message.setImage(snapshot.child("image").getValue().toString());
                        message.setTitle(snapshot.child("title").getValue().toString());
                        message.setType(snapshot.child("type").getValue().toString());

                        messagesList.add(message);


                /*    Message message1 = snapshot.getValue(Message.class);
                    messagesList.add(message1);
*/
                    }

                    Collections.shuffle(messagesList);



                    adapter = new RecyclerAdapter(getContext(),messagesList);
                    recyclerView.setAdapter(adapter);
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

    public static class Utils {
        private static FirebaseDatabase mDatabase;

        public static FirebaseDatabase getDatabase() {
            if (mDatabase == null) {
                mDatabase = FirebaseDatabase.getInstance();
                mDatabase.setPersistenceEnabled(true);
            }
            return mDatabase;
        }

    }



}