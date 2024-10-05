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
    private int length = 0; // To keep track of the current position

    public musicPlayer() { }

    public class ServiceBinder extends Binder {
        public musicPlayer getService() {
            return musicPlayer.this;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder; // Binding not needed for this use case
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mPlayer = MediaPlayer.create(this, R.raw.bg_music); // Replace with your actual music file
        mPlayer.setOnErrorListener(this);
        mPlayer.setLooping(true);
        mPlayer.setVolume(1.0f, 1.0f);
        mPlayer.setOnCompletionListener(mp -> mp.seekTo(0)); // Looping behavior
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (mPlayer != null && !mPlayer.isPlaying()) {
            mPlayer.start(); // Start playing music if it's not already playing
        }
        return START_STICKY; // Service will continue until explicitly stopped
    }

    public void pauseMusic() {
        if (mPlayer != null && mPlayer.isPlaying()) {
            mPlayer.pause();
            length = mPlayer.getCurrentPosition(); // Save the current position
        }
    }

    public void resumeMusic() {
        if (mPlayer != null && !mPlayer.isPlaying()) {
            mPlayer.seekTo(length); // Resume from the saved position
            mPlayer.start();
        }
    }

    public void stopMusic() {
        if (mPlayer != null) {
            mPlayer.stop();
            mPlayer.release(); // Release resources
            mPlayer = null; // Set to null for garbage collection
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        stopMusic(); // Stop and release the player when the service is destroyed
    }

    @Override
    public boolean onError(MediaPlayer mp, int what, int extra) {
        Toast.makeText(this, "Music player failed", Toast.LENGTH_SHORT).show();
        stopMusic(); // Handle error and stop the music
        return true; // Error handled
    }
}
