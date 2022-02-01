package com.example.academia

import android.app.AlertDialog
import android.content.ContentValues
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log

import android.widget.Toast
import androidx.viewpager.widget.ViewPager
import com.example.academia.adapter.ViewPagerAdapter
import com.example.academia.fragments.DashBoard
import com.example.academia.fragments.Home
import com.google.android.gms.tasks.OnCompleteListener

import com.google.android.material.tabs.TabLayout
import com.google.firebase.messaging.FirebaseMessaging

class MainActivity : AppCompatActivity() {

    private var doubleBackToExitPressedOnce = false
    private var backPressToast: Toast? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_Academia)

        setContentView(R.layout.activity_main)

      supportActionBar?.hide()

        setuptab()



        ////




    }

    private fun setuptab() {
        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(Home(), title = "")
        adapter.addFragment(DashBoard(), title = "")



        //////

        //val i = Intent(this, TRY::class.java)
        //val putExtra = i.putExtra("FirstTab", 4)


        //val i = intent
        //val tabToOpen = i.getIntExtra("FirstTab", -1)
        //if (tabToOpen != -1) {
        // Open the right tab
        //}

        ////

        val viewPager = findViewById<ViewPager>(R.id.viewpager)
        viewPager.adapter = adapter

        viewPager.offscreenPageLimit = 3



        val tabs = findViewById<TabLayout>(R.id.tabs)
        tabs.setupWithViewPager(viewPager)




        ////add the titles

        tabs.getTabAt(0)!!.setIcon(R.drawable.home)
        tabs.getTabAt(1)!!.setIcon(R.drawable.dasboard)



        ////Notification

        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.w(ContentValues.TAG, "Fetching FCM registration token failed", task.exception)
                return@OnCompleteListener
            }

            // Get new FCM registration token
            val token = task.result

            // Log and toast
            FirebaseMessaging.getInstance().getToken()
            Log.d(ContentValues.TAG, "Token")

            //Toast.makeText(baseContext, "Token registered", Toast.LENGTH_SHORT).show()
        })







    }

    override fun onBackPressed() {

            val builder = AlertDialog.Builder(this)
            builder.setMessage("Are you sure you want to Exit?")
                .setNegativeButton("No", null)
                .setPositiveButton(
                    "Yes"
                ) { dialogInterface, i -> finishAffinity() }.show()



       /// if (doubleBackToExitPressedOnce) {
          //  backPressToast?.cancel()
        //    super.onBackPressed()
           // return
      //  }
     //   doubleBackToExitPressedOnce = true
     //   backPressToast = Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT)
      //  with(backPressToast) { this?.show() }
       // Handler().postDelayed({ doubleBackToExitPressedOnce = false }, 2000)
    }
}

