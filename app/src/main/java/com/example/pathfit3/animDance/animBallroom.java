package com.example.pathfit3.animDance;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pathfit3.R;
import com.example.pathfit3.VideoAdapter;
import com.example.pathfit3.VideoItem;

import java.util.Arrays;
import java.util.List;

public class animBallroom extends AppCompatActivity {
    private RecyclerView recyclerView;
    private VideoAdapter videoAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_anim_ballroom);


        recyclerView = findViewById(R.id.recyclerBallroom);


        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);

        // Sample video items
        List<VideoItem> videoItems = Arrays.asList(
                new VideoItem("android.resource://" + getPackageName() + "/" + R.raw.ball1, "Ballroom Step1", "Description for Step 1."),
                new VideoItem("android.resource://" + getPackageName() + "/" + R.raw.ball2, "Ballroom Step2", "Description for Step 2."),
                new VideoItem("android.resource://" + getPackageName() + "/" + R.raw.ball3, "Ballroom Step3", "Description for Step 3."),
                new VideoItem("android.resource://" + getPackageName() + "/" + R.raw.ball4, "Ballroom Step4", "Description for Step 4."),
                new VideoItem("android.resource://" + getPackageName() + "/" + R.raw.ball5, "Ballroom Step5", "Description for Step 5."),
                new VideoItem("android.resource://" + getPackageName() + "/" + R.raw.ball6, "Ballroom Step6", "Description for Step 6."),
                new VideoItem("android.resource://" + getPackageName() + "/" + R.raw.ball7, "Ballroom Step7", "Description for Step 7."),
                new VideoItem("android.resource://" + getPackageName() + "/" + R.raw.ball8, "Ballroom Step8", "Description for Step 8."),
                new VideoItem("android.resource://" + getPackageName() + "/" + R.raw.ball9, "Ballroom Step9", "Description for Step 9.")
        );

        videoAdapter = new VideoAdapter(this, videoItems);
        recyclerView.setAdapter(videoAdapter);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        videoAdapter.releaseMediaPlayer();
    }
}