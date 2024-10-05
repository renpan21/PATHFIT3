package com.example.pathfit3;

public class VideoItem {
    private String url;
    private String title;
    private String description;

    public VideoItem(String url, String title, String description) {
        this.url = url;
        this.title = title;
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
}

