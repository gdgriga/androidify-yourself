package com.github.androidify;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class AndroidifyViewPagerItemFragment extends Fragment {

    private int imgId;

    public AndroidifyViewPagerItemFragment(int imgId) {
        this.imgId = imgId;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.androidify_part, container, false);
        ImageView imageView = (ImageView) rootView.findViewById(R.id.android_part);
        imageView.setImageResource(imgId);
        return rootView;
    }
}
