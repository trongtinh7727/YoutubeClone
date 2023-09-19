package com.iiex.videocommunity.activities;

import static com.google.android.material.bottomsheet.BottomSheetBehavior.STATE_EXPANDED;
import static com.google.android.material.bottomsheet.BottomSheetBehavior.STATE_HIDDEN;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
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
    private LinearLayout one,two,three,four;
    boolean isOpen = true;


    private ImageButton ibtnBack, ibtnToggle;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_view); // Liên kết DataBinding

        // Khởi tạo ViewViewModel
        viewModel =  new ViewModelProvider(this).get(ViewViewModel.class);
        binding.setViewModel(viewModel);
        videoView = binding.videoView;

        // tham chiếu đến custom controls
        one = findViewById(R.id.videoView_one_layout);
        two = findViewById(R.id.videoView_three_layout);
        three = findViewById(R.id.videoView_four_layout);
        four = findViewById(R.id.video_five_layout);
        ibtnBack = findViewById(R.id.videoView_go_back);
        ibtnToggle = findViewById(R.id.videoView_play_pause_btn);

        // Lấy dữ liệu từ Intent
        Intent intent = getIntent();
        Video video = (Video) intent.getSerializableExtra("video");

        // Đặt dữ liệu vào ViewViewModel
        if (video != null) {
            viewModel.setVideo(video, videoView);
            viewModel.togglePlayPause();
        }

        // set sự kiện ẩn control
        binding.zoomLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isOpen){
                    hideDefaultControls();
                    isOpen = false;
                }else {
                    showDefaultControls();
                    isOpen = true;
                }
            }
        });


        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, new VideoListFragment());
        transaction.addToBackStack(null);
        transaction.commit();





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

        ibtnToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.togglePlayPause();
                if (viewModel.getIsPlaying().getValue()) {
                    ibtnToggle.setBackgroundResource(R.drawable.ic_pause);
                }
                else{
                    ibtnToggle.setBackgroundResource(R.drawable.ic_play);
                }
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
        ibtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( getBaseContext(),MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void hideDefaultControls(){

        one.setVisibility(View.GONE);
        two.setVisibility(View.GONE);
        three.setVisibility(View.GONE);
        four.setVisibility(View.GONE);
        final Window window = this.getWindow();
        if (window == null){
            return;
        }
        window.clearFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        final View decorView = window.getDecorView();
        if (decorView!=null){
            int uiOption = decorView.getSystemUiVisibility();
            if (Build.VERSION.SDK_INT >= 14) {
                uiOption |= View.SYSTEM_UI_FLAG_LOW_PROFILE;
            }
            if (Build.VERSION.SDK_INT >= 16) {
                uiOption |= View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
            }
            if (Build.VERSION.SDK_INT >= 19) {
                uiOption |= View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
            }
            decorView.setSystemUiVisibility(uiOption);
        }
    }

    private void showDefaultControls(){
        one.setVisibility(View.VISIBLE);
        two.setVisibility(View.VISIBLE);
        three.setVisibility(View.VISIBLE);
        four.setVisibility(View.VISIBLE);
        final Window window = this.getWindow();
        if (window == null){
            return;
        }
        window.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        window.addFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
        final View decorView = window.getDecorView();
        if (decorView!=null){
            int uiOption = decorView.getSystemUiVisibility();
            if (Build.VERSION.SDK_INT >= 14) {
                uiOption &= ~View.SYSTEM_UI_FLAG_LOW_PROFILE;
            }
            if (Build.VERSION.SDK_INT >= 16) {
                uiOption &= ~View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
            }
            if (Build.VERSION.SDK_INT >= 19) {
                uiOption &= ~View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
            }
            decorView.setSystemUiVisibility(uiOption);
        }
    }
}
