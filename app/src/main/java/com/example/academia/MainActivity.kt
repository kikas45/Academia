package com.example.academia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import com.example.academia.adapter.ViewPagerAdapter
import com.example.academia.fragments.DashBoard
import com.example.academia.fragments.Home

import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_Academia)

        setContentView(R.layout.activity_main)

        supportActionBar?.hide()

        setuptab()



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








    }




}