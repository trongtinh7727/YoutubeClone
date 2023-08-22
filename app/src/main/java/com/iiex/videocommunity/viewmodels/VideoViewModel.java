package com.iiex.videocommunity.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.iiex.videocommunity.model.Video;
import com.iiex.videocommunity.repositorys.VideoRepository;

import java.util.ArrayList;
import java.util.List;

public class VideoViewModel extends ViewModel {
    private VideoRepository videoRepository;
    private LiveData<List<Video>> videosLiveData;

    public VideoViewModel() {
        videoRepository = new VideoRepository();
        videosLiveData = videoRepository.getVideos();
    }

    public LiveData<List<Video>> getVideosLiveData() {
        return videosLiveData;
    }
}

