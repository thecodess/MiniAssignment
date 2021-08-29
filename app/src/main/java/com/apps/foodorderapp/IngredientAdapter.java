package com.apps.foodorderapp;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class IngredientAdapter extends RecyclerView.Adapter<IngredientViewHolder>  {
    Context context;
    List<Ingredient> ingredientList;




    public IngredientAdapter(Context context, List<Ingredient> ingredientList) {
        this.context = context;
        this.ingredientList = ingredientList;

    }

    @NonNull
    @Override
    public IngredientViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_ingredient,parent,false);
        return new IngredientViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientViewHolder holder, int position) {
        holder.title.setText(String.valueOf(ingredientList.get(position).title));
        holder.quantity.setText(String.valueOf(ingredientList.get(position).quantity));
        Picasso.get().load(ingredientList.get(position).image).into(holder.ingimg);

    }

    @Override
    public int getItemCount() {
        return ingredientList.size();
    }



}