package com.example.academia.firebase;

import android.os.Bundle;

import com.example.academia.Fire_Models.Employee;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.HashMap;

public class DAOEmployee {
    private DatabaseReference databaseReference;
    Bundle bundle;
    public DAOEmployee()
    {
        FirebaseDatabase db =FirebaseDatabase.getInstance();
        // databaseReference = db.getReference(Employee.class.getSimpleName());
        databaseReference = db.getReference().child("Recon").child("List");


    }


    /// addding items using time snap on the firebase database

    public Task<Void> add(Employee emp)
    {
        return databaseReference.push().setValue(emp);
    }



    public Task<Void> update(String key, HashMap<String ,Object> hashMap)
    {
        return databaseReference.child(key).updateChildren(hashMap);
    }





    public Task<Void> remove(String key)
    {
        return databaseReference.child(key).removeValue();
    }




    public Query get(String key)
    {
        if(key == null)
        {
            return databaseReference.orderByKey().limitToFirst(4);
        }
        return databaseReference.orderByKey().startAfter(key).limitToFirst(4);
    }





    public Query get()
    {
        return databaseReference;
    }
}
