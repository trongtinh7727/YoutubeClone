package com.iiex.videocommunity.repositorys;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.iiex.videocommunity.model.Video;

import java.util.ArrayList;
import java.util.List;

public class VideoRepository {
    private DatabaseReference databaseReference;

    public VideoRepository() {
        databaseReference = FirebaseDatabase.getInstance().getReference("videos");
    }

    public LiveData<List<Video>> getVideos() {
        MutableLiveData<List<Video>> videosLiveData = new MutableLiveData<>();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<Video> videos = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Video video = snapshot.getValue(Video.class);
                    videos.add(video);
                }
                videosLiveData.setValue(videos);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Xử lý lỗi
            }
        });

        return videosLiveData;
    }
}


