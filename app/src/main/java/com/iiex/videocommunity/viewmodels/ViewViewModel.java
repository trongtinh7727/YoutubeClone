package com.iiex.videocommunity.viewmodels;

import android.media.MediaPlayer;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.iiex.videocommunity.model.Video;

import android.media.PlaybackParams;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.widget.SeekBar;
import android.widget.VideoView;

import java.io.IOException;

public class ViewViewModel extends ViewModel {

    private MediaPlayer mediaPlayer;
    private MutableLiveData<Boolean> isPlaying = new MutableLiveData<>();

    private MutableLiveData<Boolean> isSeeking = new MutableLiveData<>();
    private MutableLiveData<Integer> currentPosition = new MutableLiveData<>();
    private MutableLiveData<Integer> duration = new MutableLiveData<>();
    private MutableLiveData<Float> playbackSpeed = new MutableLiveData<>();
    private Handler handler;
    private Runnable updateProgress;

    public ViewViewModel() {
        mediaPlayer = new MediaPlayer();
        handler = new Handler(Looper.getMainLooper());

        // Thiết lập bắt sự kiện thay đổi vị trí
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                isPlaying.setValue(false);
            }
        });

        // Cập nhật thời gian hiện tại của video mỗi giây
        updateProgress = new Runnable() {
            @Override
            public void run() {
                if (mediaPlayer.isPlaying()) {
                    currentPosition.setValue(mediaPlayer.getCurrentPosition());
                }
                handler.postDelayed(this, 1000);
            }
        };
        handler.postDelayed(updateProgress, 1000);
    }

    public LiveData<Boolean> getIsSeeking() {
        return isSeeking;
    }

    public void onStartSeek() {
        isSeeking.setValue(true);
    }

    public void onStopSeek() {
        isSeeking.setValue(false);
    }

    public LiveData<Boolean> getIsPlaying() {
        return isPlaying;
    }

    public LiveData<Integer> getCurrentPosition() {
        return currentPosition;
    }

    public LiveData<Integer> getDuration() {
        return duration;
    }

    public LiveData<Float> getPlaybackSpeed() {
        return playbackSpeed;
    }


    public void setVideo(Video video, VideoView videoView) {

        try {
            mediaPlayer.setDataSource(video.getVideoUrl());
            mediaPlayer.setDisplay(videoView.getHolder());
            mediaPlayer.prepare();
            duration.setValue(mediaPlayer.getDuration());
            isPlaying.setValue(false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void togglePlayPause() {
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
        } else {
            mediaPlayer.start();
        }
        isPlaying.setValue(mediaPlayer.isPlaying());
    }



    public void seekTo(int position) {
        mediaPlayer.seekTo(position);
        currentPosition.setValue(position);
    }

    public void setPlaybackSpeed(float speed) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            PlaybackParams params = mediaPlayer.getPlaybackParams();
            params.setSpeed(speed);
            mediaPlayer.setPlaybackParams(params);
            playbackSpeed.setValue(speed);
        }
    }


    public void releaseMediaPlayer() {
        if (mediaPlayer != null) {
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.stop();
            }
            mediaPlayer.setDisplay(null);
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}
