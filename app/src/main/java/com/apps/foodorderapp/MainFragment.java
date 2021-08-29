package com.apps.foodorderapp;



import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.apps.foodorderapp.interfaces.MainService;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;


public class MainFragment extends Fragment {
    RecyclerView mainRecycler;
    MainService mainService;
    List<Ingredient> ingredientList;
    Retrofit retrofit;
    CompositeDisposable compositeDisposable;
public MainFragment(){

}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View root= inflater.inflate(R.layout.fragment_main, container, false);
        mainRecycler=root.findViewById(R.id.mainRecycler);
        mainRecycler.setHasFixedSize(true);
        mainRecycler.setLayoutManager(new LinearLayoutManager(getContext()));

        compositeDisposable = new CompositeDisposable();
        retrofit = RetrofitClient.getInstance();
        mainService = retrofit.create(MainService.class);

        fetchData();
        return root;
    }
    private void fetchData() {
        compositeDisposable.add(mainService.getIngredients()
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
        mainRecycler.setAdapter(adapter);

    }

    @Override
    public void onStop() {
        compositeDisposable.clear();
        super.onStop();
    }
}