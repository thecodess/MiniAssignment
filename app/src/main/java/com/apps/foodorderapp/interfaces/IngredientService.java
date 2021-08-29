package com.apps.foodorderapp.interfaces;

import com.apps.foodorderapp.Ingredient;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface IngredientService {
    @GET("food-data")
    Observable<List<Ingredient>> getIngredients();

}
