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

public class animHipHop extends AppCompatActivity {
    private RecyclerView recyclerView;
    private VideoAdapter videoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_anim_hip_hop);


        recyclerView = findViewById(R.id.recyclerHipHop);


        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);


        List<VideoItem> videoItems = Arrays.asList(
                new VideoItem("android.resource://" + getPackageName() + "/" + R.raw.hh1, "Hip-Hop Step1", "Description for Step 1."),
                new VideoItem("android.resource://" + getPackageName() + "/" + R.raw.hh2, "Hip-Hop Step2", "Description for Step 2."),
                new VideoItem("android.resource://" + getPackageName() + "/" + R.raw.hh3, "Hip-Hop Step3", "Description for Step 3."),
                new VideoItem("android.resource://" + getPackageName() + "/" + R.raw.hh4, "Hip-Hop Step4", "Description for Step 4."),
                new VideoItem("android.resource://" + getPackageName() + "/" + R.raw.hh5, "Hip-Hop Step5", "Description for Step 5."),
                new VideoItem("android.resource://" + getPackageName() + "/" + R.raw.hh6, "Hip-Hop Step6", "Description for Step 6.")

        );

        videoAdapter = new VideoAdapter(this, videoItems);
        recyclerView.setAdapter(videoAdapter);


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}