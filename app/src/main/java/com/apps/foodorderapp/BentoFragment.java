package com.apps.foodorderapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.apps.foodorderapp.interfaces.BentoService;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class BentoFragment extends Fragment {
RecyclerView bentoRecycler;
    BentoService bentoService;
    List<Ingredient> ingredientList;
    Retrofit retrofit;
    CompositeDisposable compositeDisposable;

    public BentoFragment() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View root= inflater.inflate(R.layout.fragment_bento, container, false);
        bentoRecycler=root.findViewById(R.id.bentoRecycler);

        bentoRecycler.setHasFixedSize(true);
        bentoRecycler.setLayoutManager(new LinearLayoutManager(getContext()));


        compositeDisposable = new CompositeDisposable();
        retrofit = RetrofitClient.getInstance();
        bentoService = retrofit.create(BentoService.class);

        fetchData();

        return root;
    }

    private void fetchData() {
        compositeDisposable.add(bentoService.getIngredients()
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
        BentoIngredientAdapter adapter = new BentoIngredientAdapter(getContext(),ingredients);
       bentoRecycler.setAdapter(adapter);

    }

    @Override
    public void onStop() {
        compositeDisposable.clear();
        super.onStop();
    }
}