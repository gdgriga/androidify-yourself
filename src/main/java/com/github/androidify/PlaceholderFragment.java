package com.github.androidify;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class PlaceholderFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        final ViewPager viewPagerHead = (ViewPager) rootView.findViewById(R.id.viewPagerHead);
        final ViewPager viewPagerBody = (ViewPager) rootView.findViewById(R.id.viewPagerBody);
        final ViewPager viewPagerLegs = (ViewPager) rootView.findViewById(R.id.viewPagerLegs);

        FragmentManager fm = getActivity().getSupportFragmentManager();
        viewPagerHead.setAdapter(new AndroidifyViewPagerAdapter(fm, AndroidDrawables.getHeads()));
        viewPagerBody.setAdapter(new AndroidifyViewPagerAdapter(fm, AndroidDrawables.getBodies()));
        viewPagerLegs.setAdapter(new AndroidifyViewPagerAdapter(fm, AndroidDrawables.getLegs()));

        View shareButton = rootView.findViewById(R.id.button_share);
        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer head = AndroidDrawables.getHeads().get(viewPagerHead.getCurrentItem());
                Integer body = AndroidDrawables.getBodies().get(viewPagerBody.getCurrentItem());
                Integer legs = AndroidDrawables.getLegs().get(viewPagerLegs.getCurrentItem());

                Bitmap bitmap = BitmapUtils.combineDrawables(getResources(), head, body, legs);

                String imagePath = MediaStore.Images.Media.insertImage(getActivity().getContentResolver(), bitmap, "Android Avatar", null);
                Uri imageURI = Uri.parse(imagePath);

                startShareActivity(imageURI);
            }
        });


        return rootView;
    }

    private void startShareActivity(Uri imageURI) {
        final Intent shareIntent = new Intent(Intent.ACTION_SEND);

        shareIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        shareIntent.putExtra(Intent.EXTRA_STREAM, imageURI);
        shareIntent.setType("image/png");

        startActivity(shareIntent);
    }
}