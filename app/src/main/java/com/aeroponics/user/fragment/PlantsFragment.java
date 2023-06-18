package com.aeroponics.user.fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.aeroponics.user.activities.models.Plants;
import com.aeroponics.user.activities.models.ServerRequestBody;
import com.aeroponics.user.databinding.FragmentPlantsBinding;
import com.aeroponics.user.interfaces.RequestListener;
import com.aeroponics.user.services.ServerRequest;

import org.json.JSONObject;

import java.util.List;

public class PlantsFragment extends Fragment {

    FragmentPlantsBinding binding;

    Context mContext;
    ServerRequest request;

    public PlantsFragment(Context mContext) {
        this.mContext = mContext;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentPlantsBinding.inflate(inflater, container, false);
        request = new ServerRequest(mContext);
        loadData();
        return binding.getRoot();
    }

    private void loadData() {
        ServerRequestBody body = new ServerRequestBody.ServerRequestBodyBuilder()
                .setUrl(String.format("%s%s", "http://192.168.1.0:8443", ServerRequest.PLANTS_PATH))
                .build();

        request.getAll(body, new RequestListener() {
            @Override
            public <T> void onSuccess(T any) {
                if (any instanceof JSONObject) {
                    
                }
            }

            @Override
            public void onError(Error error) {
                if (error != null) {
                    Log.e("loadData_err", error.getLocalizedMessage());
                }
                Toast.makeText(mContext, "There are no plants added yet", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
