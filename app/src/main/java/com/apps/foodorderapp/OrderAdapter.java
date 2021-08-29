package com.apps.foodorderapp;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Handler;
import android.provider.Settings;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class OrderAdapter extends RecyclerView.Adapter<OrderViewHolder> {
    Context context;
    List<Order> orderList;
Handler handler= new Handler();
    public int CurrentProgress=0;
    private RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
    public OrderAdapter(Context context, List<Order> orderList) {
        this.context = context;
        this.orderList = orderList;

    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_order,parent,false);
        return new OrderViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull final OrderViewHolder holder, int position) {
        holder.id.setText("# "+String.valueOf(orderList.get(position).id));
        holder.numItems.setText(getItemCount()+" items");

holder.accept.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        orderList.remove(holder.getAdapterPosition());
        notifyItemRemoved(holder.getAdapterPosition());
        notifyDataSetChanged();
    }
});
        OrderEachAdapter orderEachAdapter
                = new OrderEachAdapter(context.getApplicationContext(), orderList);
        holder.rv.setLayoutManager(new LinearLayoutManager(context.getApplicationContext()));
        holder.rv.setAdapter(orderEachAdapter);
        holder.rv.setRecycledViewPool(viewPool);
        //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.ENGLISH);
       // LocalDate localDate = LocalDate.parse(orderList.get(position).created_at, formatter);
     //  Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
      // holder.created_at.setText("at "+ String.valueOf(date.getHours())+" : "+String.valueOf(date.getMinutes()));
        SimpleDateFormat inputFormat=new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        String dateStr=String.valueOf(orderList.get(position).created_at);
        String dateStr2=String.valueOf(orderList.get(position).alerted_at);
        String dateStr0 =String.valueOf(orderList.get(0).alerted_at);
        String dateStr3 = String.valueOf(orderList.get(position).expired_at);
        try {
            Date date = inputFormat.parse(dateStr);
            Date date2 =inputFormat.parse(dateStr2);
Date date0=inputFormat.parse(dateStr0);
Date expired_time=inputFormat.parse(dateStr3);

            MediaPlayer player = MediaPlayer.create(context.getApplicationContext(), Settings.System.DEFAULT_RINGTONE_URI);
            assert date != null;
            final String[] niceDateStr = {String.valueOf(DateUtils.getRelativeTimeSpanString(date.getTime(), Calendar.getInstance().getTimeInMillis(), DateUtils.MINUTE_IN_MILLIS))};
            holder.created_at.setText("at " + String.valueOf(date.getHours())+" : "+ String.valueOf(date.getMinutes()));
            new Thread(new Runnable() {
                public void run() {

                    while (CurrentProgress < 100) {
                        CurrentProgress += 1;

                        handler.post(new Runnable() {
                            public void run() {
                                holder.progressBar.setProgress(CurrentProgress);
                                // holder.textView.setText(CurrentProgress +"/"+holder.progressBar.getMax());
                            }
                        });
                        try {
                            // Sleep for 200 milliseconds.
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();
            Date currentDate = new Date();
            if( String.valueOf(date2.getMinutes()).equals(String.valueOf(currentDate.getMinutes()))){
                player.start();
            }
            if(String.valueOf(expired_time.getMinutes()).equals(String.valueOf(currentDate.getMinutes()))){
                holder.acceptText.setText("Expired");
                player.stop();
               // Toast.makeText(context.getApplicationContext(), "Expired", Toast.LENGTH_SHORT).show();
            }
            assert date2 != null;
            assert date0 != null;

        } catch (ParseException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
       /* handler = new Handler();
        handler.postDelayed(updateTimerThread, 0);

        holder.progressBar.setProgress(CurrentProgress);
        holder.progressBar.setMax(100);*/
      /*  Runnable runnable=new Runnable() {
            @Override
            public void run() {

                try {
                    Date date0 = inputFormat.parse(dateStr0);
                    Date currentDate = new Date();
                    if(String.valueOf(date0.getHours())== String.valueOf(currentDate.getHours())) {
                        MediaPlayer player = MediaPlayer.create(context.getApplicationContext(), Settings.System.DEFAULT_RINGTONE_URI);
                        player.start();
                        holder.created_at.setText("Time don reach o!");
                    }
                } catch (ParseException e) {
                    e.printStackTrace();

                }

            }
        };
        Thread thread = new Thread(runnable);
        thread.start();*/
    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }

/*private Runnable updateTimerThread = new Runnable() {
    @Override
    public void run() {
        CurrentProgress = CurrentProgress + 10;

        handler.postDelayed(this,1000);
    }
};*/

}