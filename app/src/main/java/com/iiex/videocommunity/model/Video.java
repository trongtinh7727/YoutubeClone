package com.iiex.videocommunity.model;

public class Video {
    private String title;
    private String videoUrl;

    public Video(String title, String videoUrl) {
        this.title = title;
        this.videoUrl = videoUrl;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getTitle() {
        return title;
    }

    public String getVideoUrl() {
        return videoUrl;
    }
}
