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

public class animCheerDance extends AppCompatActivity {

    private RecyclerView recyclerView;
    private VideoAdapter videoAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_anim_cheer_dance);

        recyclerView = findViewById(R.id.recyclerCheerDance);


        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);

        // Sample video items
        List<VideoItem> videoItems = Arrays.asList(
                new VideoItem("android.resource://" + getPackageName() + "/" + R.raw.cheerdance1, "Cheer Dance Step1"),
                new VideoItem("android.resource://" + getPackageName() + "/" + R.raw.cheerdance3, "Cheer Dance Step2"),
                new VideoItem("android.resource://" + getPackageName() + "/" + R.raw.cheerdance4, "Cheer Dance Step3")

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