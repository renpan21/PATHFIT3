package com.example.pathfit3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class startupPanel extends BaseActivity {
    private Button btnGetStarted;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_startup_panel);
        startService(new Intent(this, musicPlayer.class));

        // Just for testing
//        if (mMusicPlayer != null) {
//            mMusicPlayer.resumeMusic();
//        }
        initViews();
        startService(new Intent(this, musicPlayer.class));
        btnGetStarted.setOnClickListener(v -> {
            Intent intent = new Intent(startupPanel.this, homeModule.class);
            startActivity(intent);
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    public void initViews(){
        btnGetStarted = findViewById(R.id.btnGetStarted);

    }
}