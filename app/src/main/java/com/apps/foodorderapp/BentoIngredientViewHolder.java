package com.apps.foodorderapp;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.apps.foodorderapp.R;

public class BentoIngredientViewHolder extends RecyclerView.ViewHolder {
    TextView title, quantity, tag;
    ImageView ingimg;
    LinearLayout greyRound, greyBorder;

    public BentoIngredientViewHolder(@NonNull View itemView) {
        super(itemView);
tag=itemView.findViewById(R.id.tag);
        title = itemView.findViewById(R.id.titlee);
        quantity = itemView.findViewById(R.id.quantity);
        ingimg = itemView.findViewById(R.id.ingredientimage);
        greyBorder=itemView.findViewById(R.id.greyBorder);
        greyRound=itemView.findViewById(R.id.greyRound);
    }
}