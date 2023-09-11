package com.iiex.videocommunity.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;


import com.iiex.videocommunity.activities.VideoPlayerActivity;
import com.iiex.videocommunity.adapters.VideoAdapter;
import com.iiex.videocommunity.databinding.FragmentHomeBinding;
import com.iiex.videocommunity.model.Video;
import com.iiex.videocommunity.viewmodels.VideoViewModel;

import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {

    private VideoViewModel videoViewModel;
    private VideoAdapter videoAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        videoViewModel = new ViewModelProvider(this).get(VideoViewModel.class);
        videoAdapter = new VideoAdapter(new ArrayList<>());
        videoAdapter.setOnItemClickListener(new VideoAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Video video) {
                openVideoPlayer(video.getVideoUrl());
            }
        });
    }

    private void openVideoPlayer(String videoUrl) {
        Intent intent = new Intent(getActivity(), VideoPlayerActivity.class);
        intent.putExtra("videoUrl", videoUrl);
        startActivity(intent);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FragmentHomeBinding binding = FragmentHomeBinding.inflate(inflater, container, false);

        RecyclerView recyclerView = binding.recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(videoAdapter);

        videoViewModel.getVideosLiveData().observe(getViewLifecycleOwner(), new Observer<List<Video>>() {
            @Override
            public void onChanged(List<Video> videos) {
                videoAdapter.setVideos(videos);
            }
        });

        return binding.getRoot();
    }
}

