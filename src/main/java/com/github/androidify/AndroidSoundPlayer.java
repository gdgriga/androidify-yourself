package com.github.androidify;


import android.media.MediaPlayer;
import android.os.Environment;
import android.util.Log;

import java.io.IOException;

public class AndroidSoundPlayer {

    private final String fileName;
    private MediaPlayer mediaPlayer = null;

    public AndroidSoundPlayer(String fileName) {
        this.fileName = Environment.getExternalStorageDirectory().
                getAbsolutePath() + "/" + fileName;
    }

    public void startPlaying() {
        if (mediaPlayer != null) stopPlaying();

        mediaPlayer = new MediaPlayer();
        try {
            mediaPlayer.setDataSource(fileName);
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (IOException e) {
            Log.e("SoundPlayer", "prepare() failed");
        }
    }

    public void stopPlaying() {
        if (mediaPlayer == null) return;
        mediaPlayer.release();
        mediaPlayer = null;
    }

    public boolean isPlaying() {
        return mediaPlayer != null && mediaPlayer.isPlaying();
    }
}
