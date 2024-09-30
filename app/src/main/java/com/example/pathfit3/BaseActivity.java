package com.example.pathfit3;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import androidx.appcompat.app.AppCompatActivity;

public class BaseActivity extends AppCompatActivity {

    protected musicPlayer mMusicPlayer;
    private boolean isBound = false;

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            musicPlayer.ServiceBinder binder = (musicPlayer.ServiceBinder) service;
            mMusicPlayer = binder.getService();
            isBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mMusicPlayer = null;
            isBound = false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bindMusicService();
    }

    private void bindMusicService() {
        Intent intent = new Intent(this, musicPlayer.class);
        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (isBound) {
            playMusic(); // Optionally play music when the activity starts
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (isBound) {
            pauseMusic(); // Pause music if the app goes to the background
            unbindService(serviceConnection);
            isBound = false;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (isBound) {
            unbindService(serviceConnection);
            isBound = false;
        }
    }

    public void playMusic() {
        if (mMusicPlayer != null) {
            mMusicPlayer.resumeMusic();
        }
    }

    public void pauseMusic() {
        if (mMusicPlayer != null) {
            mMusicPlayer.pauseMusic();
        }
    }

    public void stopMusic() {
        if (mMusicPlayer != null) {
            mMusicPlayer.stopMusic();
        }
    }
}
