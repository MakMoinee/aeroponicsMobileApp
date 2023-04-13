package com.aeroponics.user.activities;


import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.aeroponics.user.R;
import com.aeroponics.user.databinding.ActivityLoginBinding;
import com.aeroponics.user.impl.ButtonImpl;
import com.aeroponics.user.interfaces.ActivityListener;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    ActivityLoginBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.btnLogin.setOnClickListener(LoginActivity.this);
        binding.txtCreateAccount.setOnClickListener(LoginActivity.this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnLogin:
                ButtonImpl.handleLogin(LoginActivity.this, binding.editUsername.getText().toString(), binding.editPW.getText().toString(), new ActivityListener() {
                    @Override
                    public void finishActivity() {
                        finish();
                    }
                });
                break;
            case R.id.txtCreateAccount:
                ButtonImpl.handleCreateAccount(LoginActivity.this);
                break;
            default:
                break;
        }
    }
}
