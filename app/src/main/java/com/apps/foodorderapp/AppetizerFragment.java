package com.apps.foodorderapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.apps.foodorderapp.interfaces.AppetizerService;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;


public class AppetizerFragment extends Fragment {
    RecyclerView appetizerRecycler;
    AppetizerService appetizerService;
    List<Ingredient> ingredientList;
    Retrofit retrofit;
    CompositeDisposable compositeDisposable;
    public AppetizerFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root=inflater.inflate(R.layout.fragment_appetizer, container, false);
        appetizerRecycler=root.findViewById(R.id.appetizerRecycler);
        appetizerRecycler.setHasFixedSize(true);
       appetizerRecycler.setLayoutManager(new LinearLayoutManager(getContext()));


        compositeDisposable = new CompositeDisposable();
        retrofit = RetrofitClient.getInstance();
        appetizerService = retrofit.create(AppetizerService.class);

        fetchData();
        return root;
    }
    private void fetchData() {
        compositeDisposable.add(appetizerService.getIngredients()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Ingredient>>() {

                    @Override
                    public void accept(List<Ingredient> ingredients) throws Exception {
                        displayData(ingredients);
                    }
                }));
    }

    private void displayData(List<Ingredient> ingredients) {
       IngredientAdapter adapter = new IngredientAdapter(getContext(),ingredients);
        appetizerRecycler.setAdapter(adapter);

    }

    @Override
    public void onStop() {
        compositeDisposable.clear();
        super.onStop();
    }
}