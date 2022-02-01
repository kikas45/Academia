package com.example.academia

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ActionMode
import android.widget.TextView
import android.widget.Toast
import com.example.academia.adapter.SliderData

class MainActivity2 : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        // val bundle: Bundle?
        // bundle = intent.extras
        // val web = bundle!!.getString("URL")
        //Toast.makeText(this, "This is item in position " + intent, Toast.LENGTH_SHORT).show();


        GetIntent()
    }


    private fun GetIntent() {

        val intent = intent.getStringExtra("key" )
        val t1 = findViewById<TextView>(R.id.textView)
        t1.setText("welcome + intent")


    }


}