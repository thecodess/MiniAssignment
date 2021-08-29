package com.apps.foodorderapp.interfaces;

import com.apps.foodorderapp.Ingredient;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface MainService {
    @GET("food-data?q=main")
    Observable<List<Ingredient>> getIngredients();
}