package com.apps.foodorderapp;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.apps.foodorderapp.interfaces.OrderService;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class OrderScreen extends Activity {
RecyclerView recycleV;
    OrderService orderService;
    List<Order> orderList;
    ImageView imageView;
    CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_screen);
        recycleV=findViewById(R.id.recyclerV);
        imageView=findViewById(R.id.ingredient);
        ConnectivityManager mgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = mgr.getActiveNetworkInfo();
        if(netInfo!=null){
            if(netInfo.isConnected()){

            }else{
                Toast.makeText(getApplicationContext(), "Looks like you are not connected to the internet. Please try again later", Toast.LENGTH_SHORT).show();
                finish();
            }
        }else{
            Toast.makeText(getApplicationContext(), "Looks like you are not connected to the internet. Please try again later", Toast.LENGTH_SHORT).show();
            finish();
        }
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(OrderScreen.this, IngredientScreen.class));
            }
        });
        Retrofit retrofit = RetrofitClient.getInstance();
       orderService = retrofit.create(OrderService.class);


        recycleV.setHasFixedSize(true);
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(
                        recycleV
                        .getContext(),
                LinearLayoutManager.HORIZONTAL,
                false);

recycleV.setLayoutManager(layoutManager);
        fetchData();

    }
    private void fetchData() {
        compositeDisposable.add(orderService.getOrders()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Order>>() {

                    @Override
                    public void accept(List<Order> orders) throws Exception {
                        OrderScreen.this.displayData(orders);
                    }
                }));
    }

    private void displayData(List<Order> orders) {
        OrderAdapter adapter = new OrderAdapter(this,orders);
        recycleV.setAdapter(adapter);
    }

    @Override
    protected void onStop() {
        compositeDisposable.clear();
        super.onStop();

    }

}