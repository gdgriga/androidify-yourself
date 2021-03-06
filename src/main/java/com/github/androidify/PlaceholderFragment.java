package com.github.androidify;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

public class PlaceholderFragment extends Fragment {

    private final String mySound = "my_recorded_sound";
    private AndroidSoundRecorder soundRecorder;
    private AndroidSoundPlayer soundPlayer;
    private ViewPager mViewPagerHead;
    private ViewPager mViewPagerBody;
    private ViewPager mViewPagerLegs;

    private static final int WRITE_EXTERNAL_STORAGE = 1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        mViewPagerHead = (ViewPager) rootView.findViewById(R.id.viewPagerHead);
        mViewPagerBody = (ViewPager) rootView.findViewById(R.id.viewPagerBody);
        mViewPagerLegs = (ViewPager) rootView.findViewById(R.id.viewPagerLegs);

        FragmentManager fm = getActivity().getSupportFragmentManager();
        mViewPagerHead.setAdapter(new AndroidifyViewPagerAdapter(fm, AndroidDrawables.getHeads()));
        mViewPagerBody.setAdapter(new AndroidifyViewPagerAdapter(fm, AndroidDrawables.getBodies()));
        mViewPagerLegs.setAdapter(new AndroidifyViewPagerAdapter(fm, AndroidDrawables.getLegs()));

        initShareButton(rootView);
        initPlayButton(rootView);
        initRecordButton(rootView);

        return rootView;
    }

    private void initPlayButton(View rootView) {
        soundPlayer = new AndroidSoundPlayer(mySound);

        View playButton = rootView.findViewById(R.id.button_play_sound);
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (soundPlayer.isPlaying()) {
                    soundPlayer.stopPlaying();
                } else {
                    soundPlayer.startPlaying();
                }
            }
        });
    }

    private void initRecordButton(View rootView) {
        soundRecorder = new AndroidSoundRecorder(mySound);

        View recordButton = rootView.findViewById(R.id.button_record_sound);
        recordButton.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                ImageButton recordButton = (ImageButton) view;

                if (MotionEvent.ACTION_UP == motionEvent.getAction()) {
                    if (soundRecorder.isRecording()) {
                        soundRecorder.stopRecording();
                        recordButton.setImageDrawable(getResources().getDrawable(R.drawable.record_off));
                    } else {
                        soundRecorder.startRecording();
                        recordButton.setImageDrawable(getResources().getDrawable(R.drawable.record_on));
                    }
                }

                return false;
            }
        });
    }

    private void initShareButton(View rootView) {
        View shareButton = rootView.findViewById(R.id.button_share);
        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int permissionCheck = ContextCompat.checkSelfPermission(getActivity(),
                        android.Manifest.permission.WRITE_EXTERNAL_STORAGE);

                if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(
                            new String[] { android.Manifest.permission.WRITE_EXTERNAL_STORAGE },
                            WRITE_EXTERNAL_STORAGE);
                } else {
                    share();
                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {

            case WRITE_EXTERNAL_STORAGE:
                if ((grantResults.length > 0) && (grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    share();
                }
                break;

            default:
                break;
        }
    }

    private void share() {
        Integer head = AndroidDrawables.getHeads().get(mViewPagerHead.getCurrentItem());
        Integer body = AndroidDrawables.getBodies().get(mViewPagerBody.getCurrentItem());
        Integer legs = AndroidDrawables.getLegs().get(mViewPagerLegs.getCurrentItem());

        Bitmap bitmap = BitmapUtils.combineDrawables(getResources(), head, body, legs);

        String imagePath = MediaStore.Images.Media.insertImage(
                getActivity().getContentResolver(), bitmap,
                getResources().getString(R.string.android_avatar), null);
        Uri imageURI = Uri.parse(imagePath);
        startShareActivity(imageURI);
    }

    private void startShareActivity(Uri imageURI) {
        final Intent shareIntent = new Intent(Intent.ACTION_SEND);

        shareIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        shareIntent.putExtra(Intent.EXTRA_STREAM, imageURI);
        shareIntent.setType("image/png");

        startActivity(shareIntent);
    }

    @Override
    public void onPause() {
        super.onPause();
        soundRecorder.stopRecording();
        soundPlayer.stopPlaying();
    }
}