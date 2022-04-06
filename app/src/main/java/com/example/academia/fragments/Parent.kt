package com.example.academia.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.example.academia.About
import com.example.academia.R
import com.example.academia.SerachActivity
import com.example.academia.adapter.ViewPagerAdapter
import com.example.academia.fragments.seconadryFragment.Explore
import com.example.academia.fragments.seconadryFragment.ForYou
import com.example.academia.fragments.seconadryFragment.Physics
import com.google.android.material.tabs.TabLayout
import com.google.firebase.ktx.Firebase


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

        adapter.addFragment(ForYou(), "FOR YOU");
        adapter.addFragment(Physics(), "MORE");
        adapter.addFragment(Explore(), "EXPLORE");

        val parent_viewPager = view?.findViewById<ViewPager>(R.id.fr_real_viewpager3)
        parent_viewPager?.adapter = adapter
        parent_viewPager?.offscreenPageLimit = 6

        val tabs = view?.findViewById<TabLayout>(R.id.tabs2)
        tabs?.setupWithViewPager(parent_viewPager)
        tabs?.setTabRippleColorResource(android.R.color.transparent)


        ///
        //


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
                    val search = Intent(context, SerachActivity::class.java)
                    startActivity(search)
                    true
                }
                else -> false
            }
        }



        ///


        return view;

    }
    private var _hasLoadedOnce = false // your boolean field

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)

        // we check that the fragment is becoming visible
        if (isVisibleToUser && !_hasLoadedOnce) {


            _hasLoadedOnce = true
        }
    }

}