package com.iiex.videocommunity.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.iiex.videocommunity.R;

public class SflagActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sflag);

        Handler handler = new Handler();
        handler.postDelayed(
                new Runnable() {
                    @Override
                    public void run() {
                        nextActivity();
                    }
                }, 2000
        );
    }

    private void nextActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
//        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//        if (user == null) {
//            //not logged in
//        }else {
//            Intent intent = new Intent(this, MainActivity.class);
//            startActivity(intent);
//        }
    }
}