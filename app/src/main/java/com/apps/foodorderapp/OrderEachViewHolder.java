package com.apps.foodorderapp;

import android.view.View;

import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.apps.foodorderapp.R;

public class OrderEachViewHolder extends RecyclerView.ViewHolder {
    TextView foodTitle, foodTag, addonTitle;

    public OrderEachViewHolder(@NonNull View itemView) {
        super(itemView);

        foodTag = itemView.findViewById(R.id.foodTag);
       foodTitle = itemView.findViewById(R.id.foodTitle);
        addonTitle = itemView.findViewById(R.id.addonTitle);

    }
}
