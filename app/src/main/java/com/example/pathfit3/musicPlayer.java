package com.example.pathfit3;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.widget.Toast;

public class musicPlayer extends Service implements MediaPlayer.OnErrorListener {

    private final IBinder mBinder = new ServiceBinder();
    private MediaPlayer mPlayer;

    private float currentVolume = 1.0f;

    public musicPlayer() { }

    public class ServiceBinder extends Binder {
        public musicPlayer getService() {
            return musicPlayer.this;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mPlayer = MediaPlayer.create(this, R.raw.bg_music);
        mPlayer.setOnErrorListener(this);
        mPlayer.setLooping(true);
        mPlayer.setVolume(currentVolume, currentVolume);
        mPlayer.setOnCompletionListener(mp -> mp.seekTo(0)); // Loop
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (mPlayer != null && !mPlayer.isPlaying()) {
            mPlayer.start();
        }
        return START_STICKY;
    }

    public void stopMusic() {
        if (mPlayer != null) {
            mPlayer.stop();
            mPlayer.release();
            mPlayer = null;
        }
    }

    public void setVolume(float leftVolume, float rightVolume) {
        if (mPlayer != null) {
            currentVolume = leftVolume;
            mPlayer.setVolume(leftVolume, rightVolume);
        }
    }

    public float getVolume() {
        return currentVolume;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        stopMusic();
    }

    @Override
    public boolean onError(MediaPlayer mp, int what, int extra) {
        Toast.makeText(this, "Music player failed", Toast.LENGTH_SHORT).show();
        stopMusic(); // Handle error/stop music
        return true;
    }
}
