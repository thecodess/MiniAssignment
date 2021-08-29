package com.apps.foodorderapp.interfaces;

import com.apps.foodorderapp.Order;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface OrderService {
    @GET("food-info")
    Observable<List<Order>> getOrders();
}