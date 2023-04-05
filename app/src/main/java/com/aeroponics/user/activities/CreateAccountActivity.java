package com.aeroponics.user.activities;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.aeroponics.user.R;
import com.aeroponics.user.databinding.ActivityCreateAccountBinding;
import com.aeroponics.user.fragment.DatePickerFragment;
import com.aeroponics.user.impl.ButtonImpl;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CreateAccountActivity extends AppCompatActivity implements View.OnClickListener {

    ActivityCreateAccountBinding binding;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCreateAccountBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.btnCreateAccount.setOnClickListener(CreateAccountActivity.this);
        binding.layoutDatePick.setEndIconOnClickListener(v -> ButtonImpl.handleDatePicker(getSupportFragmentManager(), (view, year, month, dayOfMonth) -> {
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, month);
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
            binding.editBirthDate.setText(dateFormat.format(calendar.getTime()));
        }));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnCreateAccount:
                break;
            default:
                break;
        }
    }
}
