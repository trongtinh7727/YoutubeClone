package com.iiex.videocommunity.fragments;

import androidx.lifecycle.Observer;

import com.iiex.videocommunity.model.Video;

import java.util.List;

public class HomeFragment extends VideoListFragment{
    public HomeFragment(){
        super();
    }
    @Override
    public void fillData() {
        videoViewModel.getVideosLiveData().observe(getViewLifecycleOwner(), new Observer<List<Video>>() {
            @Override
            public void onChanged(List<Video> videos) {
                videoAdapter.setVideos(videos);
            }
        });
    }
}
