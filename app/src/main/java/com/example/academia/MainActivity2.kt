package com.example.academia

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)


       /// val getPosition = intent.getIntExtra("key", -1)


        //Fetching data which is passed from previous activity into this activity
        GetIntent()
    }


    private fun GetIntent() {
        val intent = intent.getStringExtra("key")

    }
}