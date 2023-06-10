package com.aeroponics.user.activities;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.aeroponics.user.R;
import com.aeroponics.user.activities.models.Users;
import com.aeroponics.user.databinding.ActivityLoginBinding;
import com.aeroponics.user.impl.ButtonImpl;
import com.aeroponics.user.interfaces.ActivityListener;
import com.aeroponics.user.preference.UserPref;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    ActivityLoginBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.btnLogin.setOnClickListener(LoginActivity.this);
        binding.txtCreateAccount.setOnClickListener(LoginActivity.this);
        binding.btnGoogleSignIn.setOnClickListener(LoginActivity.this);
        Users users = new UserPref(LoginActivity.this).getUsers();
        if (users != null) {
            if (!users.getDocumentID().equals("")) {
                Intent intent = new Intent(LoginActivity.this, MainFormActivity.class);
                startActivity(intent);
                finish();
            }
        }
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
            case R.id.btnGoogleSignIn:
                ButtonImpl.handleGoogleSignin(LoginActivity.this);
                break;
            default:
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Users users = new UserPref(LoginActivity.this).getUsers();
        if (users != null) {
            if (!users.getDocumentID().equals("")) {
                Toast.makeText(this, "Login Successfully", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(LoginActivity.this, MainFormActivity.class);
                startActivity(intent);
                finish();
            }
        }
    }
}
