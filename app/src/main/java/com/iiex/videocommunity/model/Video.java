package com.iiex.videocommunity.model;

import java.io.Serializable;

public class Video implements Serializable {
    private String title;
    private String channelName;

    private Long views;
    private String publishedTime;

    private String channelPicture;

    private String thumbnail;
    private String videoUrl;

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public Video(){

    }

    public Video(String title, String channelName, Long views, String publishedTime,
                 String channelPicture, String thumbnail, String videoUrl) {
        this.title = title;
        this.channelName = channelName;
        this.views = views;
        this.publishedTime = publishedTime;
        this.channelPicture = channelPicture;
        this.thumbnail = thumbnail;
        this.videoUrl = videoUrl;
    }

    public String getChannelPicture() {
        return channelPicture;
    }

    public void setChannelPicture(String channelPicture) {
        this.channelPicture = channelPicture;
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

