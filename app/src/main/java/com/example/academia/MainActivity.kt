package com.example.academia

import android.app.AlertDialog
import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

import android.widget.Toast
import androidx.drawerlayout.widget.DrawerLayout
import androidx.viewpager.widget.ViewPager
import com.example.academia.adapter.ViewPagerAdapter
import com.example.academia.fragments.DashBoard
import com.example.academia.fragments.Parent
import com.example.academia.fragments.seconadryFragment.Physics
import com.google.android.gms.tasks.OnCompleteListener

import com.google.android.material.tabs.TabLayout
import com.google.firebase.messaging.FirebaseMessaging

class MainActivity : AppCompatActivity() {

    private var doubleBackToExitPressedOnce = false
    private var backPressToast: Toast? = null
    private lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_Academia)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        Physics.Utils.getDatabase()

        setuptab()
        fireBaseNotification()


    }


    private fun setuptab() {
        val adapter = ViewPagerAdapter(supportFragmentManager)

        /// add titles
        adapter.addFragment(Parent(), title = "")
        adapter.addFragment(DashBoard(), title = "")


        //// settig up the View pager
        val com_viewPager = findViewById<ViewPager>(R.id.custom_viewpager)
        com_viewPager.adapter = adapter

        com_viewPager.offscreenPageLimit = 4

        val tabs = findViewById<TabLayout>(R.id.tabs)
        tabs.setupWithViewPager(com_viewPager)

        ////add the icons
        tabs.getTabAt(0)!!.setIcon(R.drawable.home)
        tabs.getTabAt(1)!!.setIcon(R.drawable.dasboard)


    }



    private fun fireBaseNotification() {
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
            ) { dialogInterface, i -> finishAffinity() }.show();
    }


}


