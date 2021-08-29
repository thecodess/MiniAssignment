package com.apps.foodorderapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.MyViewHolder> implements Filterable {

    private ArrayList<Ingredient> ingredientList;
    private ArrayList<Ingredient> filteredList;
    private Context context;

    public SearchAdapter(Context context,ArrayList<Ingredient> ingredientList) {
        this.context = context;
        this.ingredientList = ingredientList;
        this.filteredList = ingredientList;

    }

    @Override
    public SearchAdapter.MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_ingredient, viewGroup, false);
        final MyViewHolder myViewHolder = new MyViewHolder(view);

        return myViewHolder;
    }
    @Override
    public void onBindViewHolder(SearchAdapter.MyViewHolder viewHolder, int position) {

        viewHolder.title.setText(filteredList.get(position).title);
        viewHolder.quantity.setText(filteredList.get(position).quantity);
        Picasso.get().load(filteredList.get(position).image).into(viewHolder.ingimg);
    }

    @Override
    public int getItemCount() {
        return filteredList.size();
    }

    @Override
    public Filter getFilter() {

        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {

                String searchString = charSequence.toString();

                if (searchString.isEmpty()) {

                    filteredList = ingredientList;

                } else {
                    ArrayList<Ingredient> tempFilteredList = new ArrayList<>();

                    for (Ingredient ingredient : ingredientList) {

                        // search for user title
                        if( ingredient.title.toLowerCase().contains(searchString)) {

                            tempFilteredList.add(ingredient);
                        }
                    }

                    filteredList = tempFilteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = filteredList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                filteredList = (ArrayList<Ingredient>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
     ImageView ingimg;
         TextView title;
   TextView quantity;

        public MyViewHolder(View view) {
            super(view);
            ingimg= (ImageView)view.findViewById(R.id.ingredientimage);
            title = (TextView)view.findViewById(R.id.titlee);
            quantity = (TextView)view.findViewById(R.id.quantity);

        }
    }
                }