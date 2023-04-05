package com.aeroponics.user.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.aeroponics.user.databinding.ActivitySplashBinding;

public class SplashActivity extends AppCompatActivity {

    ActivitySplashBinding binding;
    private int progressStatus = 0;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySplashBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setPBProgress();
    }

    private void setPBProgress() {
        new Thread(() -> {
            while (progressStatus < 200) {
                progressStatus += 20;
                // Update the progress bar and display the
                //current value in the text view
                handler.post(() -> binding.progress.setProgress(progressStatus));
                try {
                    // Sleep for 200 milliseconds.
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    Log.e("WELCOME_ACTIVITY_ERR", e.getMessage());
                }

                if (progressStatus == 200) {
                    Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        }).start();
    }
}
