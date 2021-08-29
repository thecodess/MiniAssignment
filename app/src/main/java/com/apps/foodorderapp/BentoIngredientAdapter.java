package com.apps.foodorderapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class BentoIngredientAdapter extends RecyclerView.Adapter<BentoIngredientViewHolder> {
    Context context;
    List<Ingredient> ingredientList= new ArrayList<>();

    public BentoIngredientAdapter(Context context, List<Ingredient> ingredientList) {
        this.context = context;
        this.ingredientList = ingredientList;
    }

    @Override public long getItemId(int position) {
        return position;
    }
    @NonNull
    @Override
    public BentoIngredientViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_ingredient,parent,false);
        return new BentoIngredientViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BentoIngredientViewHolder holder, int position) {
        holder.title.setText(String.valueOf(ingredientList.get(position).title));
        holder.quantity.setText(String.valueOf(ingredientList.get(position).quantity));
       // holder.tag.setText(String.valueOf(ingredientList.get(position).quantity));
        Picasso.get().load(ingredientList.get(position).image).into(holder.ingimg);


}

    @Override
    public int getItemCount() {
        return ingredientList.size();
    }
    public void setIngredients(@Nullable List<Ingredient> ing) {
        if (ing == null) {
            return;
        }
        ing.clear();
        ingredientList.addAll(ing);
        notifyDataSetChanged();
    }

    public Ingredient getItem(int position){
        if (position < 0 || position >= ingredientList.size()) {
            return null;
        } else {
            return ingredientList.get(position);
        }
    }

}