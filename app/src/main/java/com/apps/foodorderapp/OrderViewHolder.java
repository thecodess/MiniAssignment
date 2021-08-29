package com.apps.foodorderapp;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.apps.foodorderapp.R;

public class OrderViewHolder extends RecyclerView.ViewHolder {
    TextView id, created_at, numItems, acceptText;
RelativeLayout accept;
RecyclerView rv;
    ProgressBar progressBar;
    public OrderViewHolder(@NonNull View itemView) {
        super(itemView);
acceptText=itemView.findViewById(R.id.acceptText);
        id = itemView.findViewById(R.id.id);
        created_at = itemView.findViewById(R.id.created_at);
        numItems = itemView.findViewById(R.id.numItems);
        accept=itemView.findViewById(R.id.accept);
        rv=itemView.findViewById(R.id.rv);

        progressBar=itemView.findViewById(R.id.progressBar);
    }
}
