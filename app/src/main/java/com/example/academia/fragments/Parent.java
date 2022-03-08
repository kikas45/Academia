package com.example.academia.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.academia.R;
import com.example.academia.adapter.ViewPagerAdapter;
import com.example.academia.fragments.seconadryFragment.Chemistry;
import com.example.academia.fragments.seconadryFragment.Mathematics;
import com.example.academia.fragments.seconadryFragment.Sciences;
import com.google.android.material.tabs.TabLayout;


public class Parent extends Fragment {

  TabLayout tabLayout;
  ViewPager viewPager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_parent, container, false);
        addFragment(view);

        return view;
    }

    private void addFragment(View view) {
        tabLayout = view.findViewById(R.id.tabs2);
        viewPager = view.findViewById(R.id.fr_custom_viewpager3);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager());
        adapter.addFragment(new Home(), "For you");
        adapter.addFragment(new Chemistry(), "Chemistry");
        adapter.addFragment(new Mathematics(), "Mathematics");
        //adapter.addFragment(new Sciences(), "Sciences");
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        viewPager.setOffscreenPageLimit(6);


    }
}