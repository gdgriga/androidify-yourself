package com.github.androidify;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class AndroidifyPartFragment extends Fragment {

    private int imgId;

    public AndroidifyPartFragment(int imgId) {
        this.imgId = imgId;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ImageView imageView = (ImageView) inflater.inflate(R.layout.androidify_part, container);
        imageView.setImageResource(imgId);
        return imageView;
    }
}
