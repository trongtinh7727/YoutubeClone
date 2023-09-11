package com.iiex.videocommunity.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;
import androidx.appcompat.app.AppCompatActivity;

import com.iiex.videocommunity.R;

public class VideoPlayerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_player);

        VideoView videoView = findViewById(R.id.videoView);

        // Lấy URL video từ Intent
        String videoUrl = getIntent().getStringExtra("videoUrl");

        // Thiết lập MediaController để điều khiển video
        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(videoView);
        videoView.setMediaController(mediaController);

        // Thiết lập video URL cho VideoView
        Uri uri = Uri.parse(videoUrl);
        videoView.setVideoURI(uri);
        videoView.start();
    }
}
