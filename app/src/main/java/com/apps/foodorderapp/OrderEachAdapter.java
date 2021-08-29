package com.apps.foodorderapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class OrderEachAdapter extends RecyclerView.Adapter<OrderEachViewHolder> {
    Context context;
    List<Order> orderList;
List<AddOn> addOnList;
    public OrderEachAdapter(Context context, List<Order> orderList) {
        this.context = context;
        this.orderList = orderList;

    }

    @NonNull
    @Override
    public OrderEachViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_each_order,parent,false);
        return new OrderEachViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final OrderEachViewHolder holder, int position) {
    //   AddOn addOn=new AddOn();
        holder.foodTitle.setText(String.valueOf(orderList.get(position).getTitle()));
        holder.foodTag.setText("# "+ String.valueOf(orderList.get(position).getTag()));
//        holder.addonTitle.setText(String.valueOf(addOnList.get(position).quantity)+" "+ String.valueOf(addOnList.get(position).title));



    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }



}