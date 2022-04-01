package com.example.academia.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import com.example.academia.R
import androidx.viewpager.widget.ViewPager
import com.example.academia.About
import com.example.academia.adapter.ViewPagerAdapter
import com.example.academia.fragments.seconadryFragment.Mathematics
import com.example.academia.fragments.seconadryFragment.Physics
import com.example.academia.fragments.seconadryFragment.Sciences

import com.google.android.material.tabs.TabLayout


class Parent : Fragment() {

    var tabLayout: TabLayout? = null
    var viewPager: ViewPager? = null
    private var toolbar: Toolbar? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_parent, container, false);



        val adapter = ViewPagerAdapter(childFragmentManager)

        adapter.addFragment(Physics(), "FOR YOU");
        adapter.addFragment(Home(), "MORE");
        adapter.addFragment( Mathematics(), "EXPLORE");

        val com_viewPager = view?.findViewById<ViewPager>(R.id.fr_custom_viewpager3)
        com_viewPager?.adapter = adapter
        com_viewPager?.offscreenPageLimit = 6

        val tabs = view?.findViewById<TabLayout>(R.id.tabs2)
        tabs?.setupWithViewPager(com_viewPager)

        //setting up the toolbar
        toolbar = view.findViewById(R.id.toolbar_parent)
        toolbar?.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.action_about -> {
                    val you = Intent(context, About::class.java)
                    startActivity(you)
                    true
                }
                R.id.search -> {
                    // Handle search icon press
                    true
                }
                else -> false
            }
        }



        ///


        return view;

    }

}