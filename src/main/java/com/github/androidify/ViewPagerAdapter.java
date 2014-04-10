package com.github.androidify;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    private List<Integer> imgIds;

    public ViewPagerAdapter(FragmentManager fm, List<Integer> imgIds) {
        super(fm);
        this.imgIds = imgIds;
    }

    @Override
    public Fragment getItem(int position) {
        return new AndroidifyPartFragment(imgIds.get(position));
    }

    @Override
    public int getCount() {
        return imgIds.size();
    }
}
