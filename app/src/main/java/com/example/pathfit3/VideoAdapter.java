package com.example.pathfit3;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.VideoViewHolder> {
    private List<VideoItem> videoItems;
    private Context context;
    private MediaPlayer mediaPlayer;

    public VideoAdapter(Context context, List<VideoItem> videoItems) {
        this.context = context;
        this.videoItems = videoItems;
        mediaPlayer = new MediaPlayer();
    }

    @NonNull
    @Override
    public VideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.vid_layout, parent, false);
        return new VideoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VideoViewHolder holder, int position) {
        VideoItem item = videoItems.get(position);
        holder.videoTitle.setText(item.getTitle());


        // Set up the VideoView
        holder.videoView.setVideoURI(Uri.parse(item.getUrl())); // Use the URL from VideoItem
        MediaController mediaController = new MediaController(context);
        mediaController.setAnchorView(holder.videoView);
        holder.videoView.setMediaController(mediaController);

        holder.videoView.setOnPreparedListener(mp -> {
            // Optionally start the video here or let it start on click
        });

        holder.itemView.setOnClickListener(v -> {
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.stop();
                mediaPlayer.reset();
            }
            try {
                mediaPlayer.setDataSource(context, Uri.parse(item.getUrl()));
                mediaPlayer.prepare();
                mediaPlayer.start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public int getItemCount() {
        return videoItems.size();
    }

    public void releaseMediaPlayer() {
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    static class VideoViewHolder extends RecyclerView.ViewHolder {
        VideoView videoView;
        TextView videoTitle;


        VideoViewHolder(@NonNull View itemView) {
            super(itemView);
            videoView = itemView.findViewById(R.id.video_view);
            videoTitle = itemView.findViewById(R.id.video_title);

        }
    }
}
