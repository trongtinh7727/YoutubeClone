package com.iiex.videocommunity.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.iiex.videocommunity.model.Video;
import com.iiex.videocommunity.repositorys.VideoRepository;

import java.util.List;

public class VideoViewModel extends ViewModel {
    private VideoRepository videoRepository;
    private MutableLiveData<List<Video>> videoLiveData;

    public VideoViewModel() {
        videoRepository = new VideoRepository();
        videoLiveData = new MutableLiveData<List<Video>>();
        loadSuggestedVideos();
    }

    public LiveData<List<Video>> getSuggestedVideos() {
        return videoLiveData;
    }

    public void loadSuggestedVideos() {
        // Call a method from the repository to get the list of suggested videos
        List<Video> suggestedVideos = videoRepository.getSuggestedVideos();
        videoLiveData.setValue(suggestedVideos);
    }
}
