package com.iiex.videocommunity.activities;

import static com.google.android.material.bottomsheet.BottomSheetBehavior.STATE_EXPANDED;
import static com.google.android.material.bottomsheet.BottomSheetBehavior.STATE_HIDDEN;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.iiex.videocommunity.R;
import com.iiex.videocommunity.databinding.ActivityViewBinding;
import com.iiex.videocommunity.fragments.VideoListFragment;
import com.iiex.videocommunity.model.Video;
import com.iiex.videocommunity.viewmodels.ViewViewModel;


public class ViewActivity extends AppCompatActivity {

    private ViewViewModel viewModel;
    private VideoView videoView;
    private ActivityViewBinding binding; // DataBinding
    private GridView gridView;

    private ImageButton back;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_view); // Liên kết DataBinding

        // Khởi tạo ViewViewModel
        viewModel =  new ViewModelProvider(this).get(ViewViewModel.class);
        binding.setViewModel(viewModel);
        videoView = binding.videoView;

        // Lấy dữ liệu từ Intent
        Intent intent = getIntent();
        Video video = (Video) intent.getSerializableExtra("video");

        // Đặt dữ liệu vào ViewViewModel

        if (video != null) {
            viewModel.setVideo(video, videoView);
            viewModel.togglePlayPause();
        }


        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, new VideoListFragment());
        transaction.addToBackStack(null);
        transaction.commit();

        back = findViewById(R.id.videoView_go_back);



        BottomSheetBehavior bottomSheetBehavior =  BottomSheetBehavior.from(binding.designBottomSheet);
        bottomSheetBehavior.setState(STATE_HIDDEN);
        binding.btnShowMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomSheetBehavior.setState(STATE_EXPANDED);
            }
        });
        binding.btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomSheetBehavior.setState(STATE_HIDDEN);
            }
        });
        setBack();


    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        viewModel.releaseMediaPlayer();
    }


    private void setBack(){
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( getBaseContext(),MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
