package com.aeroponics.user.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.aeroponics.user.databinding.FragmentPlantsBinding;

public class PlantsFragment extends Fragment {

    FragmentPlantsBinding binding;

    Context mContext;

    public PlantsFragment(Context mContext) {
        this.mContext = mContext;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentPlantsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
}
