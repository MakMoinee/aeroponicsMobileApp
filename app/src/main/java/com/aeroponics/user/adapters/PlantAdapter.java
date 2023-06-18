package com.aeroponics.user.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aeroponics.user.R;
import com.aeroponics.user.activities.models.Plants;
import com.aeroponics.user.interfaces.AdapterListener;

import java.util.List;

public class PlantAdapter extends RecyclerView.Adapter<PlantAdapter.ViewHolder> {

    Context mContext;
    List<Plants> plantsList;
    AdapterListener listener;

    public PlantAdapter(Context mContext, List<Plants> plantsList, AdapterListener listener) {
        this.mContext = mContext;
        this.plantsList = plantsList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public PlantAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(mContext).inflate(R.layout.item_plants, parent, false);
        return new ViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull PlantAdapter.ViewHolder holder, int position) {
        Plants plants = plantsList.get(position);
        holder.txtPlantType.setText(String.format("Plant Type: %s", plants.getPlantType()));
        holder.itemView.setOnClickListener(v -> listener.onClick(holder.getAdapterPosition()));
    }

    @Override
    public int getItemCount() {
        return plantsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtPlantType;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtPlantType = itemView.findViewById(R.id.txtPlantType);
        }
    }
}
