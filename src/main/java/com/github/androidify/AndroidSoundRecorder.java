package com.github.androidify;


import android.media.MediaRecorder;
import android.os.Environment;
import android.util.Log;

import java.io.IOException;

public class AndroidSoundRecorder {

    private MediaRecorder mediaRecorder = null;
    private String mFileName;

    public AndroidSoundRecorder(String mFileName) {
        this.mFileName = Environment.getExternalStorageDirectory().
                getAbsolutePath() + "/" + mFileName;
    }

    public void startRecording() {
        if (mediaRecorder != null) stopRecording();

        mediaRecorder = new MediaRecorder();
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
        mediaRecorder.setOutputFile(mFileName);
        mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AAC);
        mediaRecorder.setAudioSamplingRate(44100);
        mediaRecorder.setAudioEncodingBitRate(16);

        try {
            mediaRecorder.prepare();
        } catch (IOException e) {
            Log.e("HELLO", "prepare() failed");
        }

        mediaRecorder.start();

    }

    public void stopRecording() {
        if (mediaRecorder == null) return;

        mediaRecorder.stop();
        mediaRecorder.release();
        mediaRecorder = null;
    }

    public boolean isRecording() {
        return mediaRecorder != null;
    }

}
