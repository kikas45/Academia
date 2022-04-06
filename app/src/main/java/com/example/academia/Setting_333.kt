package com.example.academia

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CompoundButton
import android.widget.ImageView
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.SwitchCompat

class Setting_333 : AppCompatActivity() {

    var imageView: ImageView? = null
    var switchCompat: SwitchCompat? = null
    var sharedPreferences: SharedPreferences? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting333)

        loadit()

    }

    private fun loadit() {
        imageView = findViewById(R.id.imageView)
        switchCompat = findViewById(R.id.switchCompat)
        sharedPreferences = getSharedPreferences("night", 0)
        val booleanValue = sharedPreferences?.getBoolean("night_mode", true)
        if (booleanValue == true) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            switchCompat?.setChecked(true)
            // imageView.setImageResource(R.drawable.night);
        }
        switchCompat?.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                switchCompat?.setChecked(true)
                //  imageView.setImageResource(R.drawable.night);
                val editor = sharedPreferences?.edit()
                editor?.putBoolean("night_mode", true)
                editor?.commit()
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                switchCompat?.setChecked(false)
                //imageView.setImageResource(R.drawable.night);
                val editor = sharedPreferences?.edit()
                editor?.putBoolean("night_mode", false)
                editor?.commit()
            }
        })
    }
}

