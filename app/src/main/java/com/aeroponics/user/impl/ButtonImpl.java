package com.aeroponics.user.impl;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.widget.DatePicker;
import android.widget.Toast;

import androidx.fragment.app.FragmentManager;

import com.aeroponics.user.activities.CreateAccountActivity;
import com.aeroponics.user.fragment.DatePickerFragment;
import com.aeroponics.user.interfaces.ButtonsListener;

public class ButtonImpl {
    public static void handleLogin(Context mContext, String userName, String password) {
        if (userName.equals("") || password.equals("")) {
            Toast.makeText(mContext, "Please Don't Leave Empty Fields", Toast.LENGTH_SHORT).show();
        }
    }

    public static void handleCreateAccount(Context mContext) {
        Intent intent = new Intent(mContext, CreateAccountActivity.class);
        mContext.startActivity(intent);
    }

    public static void handleDatePicker(FragmentManager fm, DatePickerDialog.OnDateSetListener dateListener) {
        DatePickerFragment datePickerFragment = new DatePickerFragment(dateListener);
        datePickerFragment.show(fm, "DATE_PICK");
    }
}
