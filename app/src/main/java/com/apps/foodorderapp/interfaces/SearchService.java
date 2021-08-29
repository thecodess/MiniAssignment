package com.apps.foodorderapp.interfaces;

import com.apps.foodorderapp.Ingredient;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;



    public interface SearchService {
        @GET("food-data/{result}")
        Observable<List<Ingredient>> getIngredients(@Path("result")String result);
}
