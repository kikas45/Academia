package com.example.academia

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class Reciever_Slider : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.reciver_slider)
        // val bundle: Bundle?
        // bundle = intent.extras
        // val web = bundle!!.getString("URL")
        //Toast.makeText(this, "This is item in position " + intent, Toast.LENGTH_SHORT).show();


        GetIntent()
    }


    @SuppressLint("SetTextI18n")
    private fun GetIntent() {

        val intent = intent.getStringExtra("key" )
        val t1 = findViewById<TextView>(R.id.textView)
        t1.setText("welcome" + intent)


    }


}