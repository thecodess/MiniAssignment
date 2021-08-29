package com.apps.foodorderapp;

import androidx.annotation.NonNull;

import com.apps.foodorderapp.interfaces.SearchService;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Retrofit;

import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import io.reactivex.Observable;

public class SearchClient {

    private static final String BASE_URL = "https://food.uclass.com.ng/";

    private static SearchClient instance;
    private SearchService searchService;

    private SearchClient() {
        final Gson gson =
                new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();
        final Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        searchService = retrofit.create(SearchService.class);
    }
    public static SearchClient getInstance() {
        if (instance == null) {
            instance = new SearchClient();
        }
        return instance;
    }

    public Observable<List<Ingredient>> getIngredients(@NonNull String ing) {
        return searchService.getIngredients(ing);
    }
}