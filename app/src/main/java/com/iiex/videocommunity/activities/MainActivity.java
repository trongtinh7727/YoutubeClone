package com.iiex.videocommunity.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import com.iiex.videocommunity.R;
import com.iiex.videocommunity.fragments.HomeFragment;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Thay thế Fragment mặc định bằng HomeFragment khi khởi động ứng dụng
        if (savedInstanceState == null) {
            loadFragment(new HomeFragment());
        }
    }

    // Phương thức để thay thế Fragment
    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}