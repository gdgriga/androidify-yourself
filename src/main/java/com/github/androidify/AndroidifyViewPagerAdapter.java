package com.github.androidify;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

public class AndroidifyViewPagerAdapter extends FragmentPagerAdapter {

    private List<Integer> imgIds;

    public AndroidifyViewPagerAdapter(FragmentManager fm, List<Integer> imgIds) {
        super(fm);
        this.imgIds = imgIds;
    }

    @Override
    public Fragment getItem(int position) {
        AndroidifyViewPagerItemFragment fragment = new AndroidifyViewPagerItemFragment();
        fragment.setImgId(imgIds.get(position));
        return fragment;
    }

    @Override
    public int getCount() {
        return imgIds.size();
    }
}
