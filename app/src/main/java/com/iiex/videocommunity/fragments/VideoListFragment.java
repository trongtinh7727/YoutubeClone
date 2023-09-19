package com.iiex.videocommunity.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;


import com.iiex.videocommunity.activities.ViewActivity;
import com.iiex.videocommunity.adapters.VideoAdapter;
import com.iiex.videocommunity.databinding.FragmentVideoListBinding;
import com.iiex.videocommunity.model.Video;
import com.iiex.videocommunity.viewmodels.VideoViewModel;

import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;


public class VideoListFragment extends Fragment {

    protected  VideoViewModel videoViewModel;
    protected  VideoAdapter videoAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        videoViewModel = new ViewModelProvider(this).get(VideoViewModel.class);
        videoAdapter = new VideoAdapter(new ArrayList<>());
        videoAdapter.setOnItemClickListener(new VideoAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Video video) {
                openVideoPlayer(video);
            }
        });
    }

    private void openVideoPlayer(Video video) {
        Intent intent = new Intent(getActivity(), ViewActivity.class);

        intent.putExtra("video",  video);
        startActivity(intent);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FragmentVideoListBinding binding = FragmentVideoListBinding.inflate(inflater, container, false);

        RecyclerView recyclerView = binding.recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(videoAdapter);
        fillData();
        return binding.getRoot();
    }

    public void fillData(){
        videoViewModel.getVideosLiveData().observe(getViewLifecycleOwner(), new Observer<List<Video>>() {
            @Override
            public void onChanged(List<Video> videos) {
                videoAdapter.setVideos(videos);
            }
        });
    }
}

