package com.github.androidify;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class PlaceholderFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        ViewPager viewPagerHead = (ViewPager) rootView.findViewById(R.id.viewPagerHead);
        ViewPager viewPagerBody = (ViewPager) rootView.findViewById(R.id.viewPagerBody);
        ViewPager viewPagerLegs = (ViewPager) rootView.findViewById(R.id.viewPagerLegs);

        FragmentManager fm = getActivity().getSupportFragmentManager();
        viewPagerHead.setAdapter(new AndroidifyViewPagerAdapter(fm, new ArrayList<Integer>()));
        viewPagerBody.setAdapter(new AndroidifyViewPagerAdapter(fm, new ArrayList<Integer>()));
        viewPagerLegs.setAdapter(new AndroidifyViewPagerAdapter(fm, new ArrayList<Integer>()));

        return rootView;
    }
}