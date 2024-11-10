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

public class animFolk extends AppCompatActivity {
    private RecyclerView recyclerView;
    private VideoAdapter videoAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_anim_folk);


        recyclerView = findViewById(R.id.recyclerFolk);


        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);


        List<VideoItem> videoItems = Arrays.asList(
                new VideoItem("android.resource://" + getPackageName() + "/" + R.raw.folk1, "Folk Dance Step1"),
                new VideoItem("android.resource://" + getPackageName() + "/" + R.raw.folk2, "Folk Dance Step2"),
                new VideoItem("android.resource://" + getPackageName() + "/" + R.raw.folk3, "Folk Dance Step3"),
                new VideoItem("android.resource://" + getPackageName() + "/" + R.raw.folk4, "Folk Dance Step4")

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