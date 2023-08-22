package com.iiex.videocommunity.model;

public class Video {
    private String title;
    private String channelName;

    private Long views;
    private String publishedTime;

    private String thumbnail;

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public Video(){

    }

    public Video(String title, String channelName, Long views, String publishedTime, String thumbnail) {
        this.title = title;
        this.channelName = channelName;
        this.views = views;
        this.publishedTime = publishedTime;
        this.thumbnail = thumbnail;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public Long getViews() {
        return views;
    }

    public void setViews(Long views) {
        this.views = views;
    }

    public String getPublishedTime() {
        return publishedTime;
    }

    public void setPublishedTime(String publishedTime) {
        this.publishedTime = publishedTime;
    }

}

