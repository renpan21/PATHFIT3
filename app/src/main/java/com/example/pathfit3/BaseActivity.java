package com.example.pathfit3;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class BaseActivity extends AppCompatActivity {
    private static int actCounter = 0;
    private static boolean isMusicServiceStarted = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!isMusicServiceStarted) {
            startMusicService();
            isMusicServiceStarted = true;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        actCounter++;
        resumeMusic();
    }

    @Override
    protected void onStop() {
        super.onStop();
        actCounter--;


        if (actCounter == 0) {
            stopMusicService();
            isMusicServiceStarted = false;
        }
    }

    private void startMusicService() {
        Intent intent = new Intent(this, musicPlayer.class);
        startService(intent);
    }

    private void stopMusicService() {
        Intent intent = new Intent(this, musicPlayer.class);
        stopService(intent);
    }

    private void resumeMusic() {
        Intent intent = new Intent(this, musicPlayer.class);
        startService(intent);
    }

}
