package com.apps.foodorderapp;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.apps.foodorderapp.interfaces.IngredientService;
import com.apps.foodorderapp.interfaces.SearchService;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

import rx.Subscription;


public class IngredientScreen extends FragmentActivity {
RecyclerView recyclerView;
EditText editText;
    IngredientService ingredientService;
    SearchService searchService;
   List<Ingredient> ingredientList;
   TabLayout tabLayout;
   ViewPager viewPager;
   Toolbar toolbar;
    private Subscription subscription;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    Button search;
public static String input;

    private ProgressDialog progressDialog;

    private SearchAdapter searchAdapter;
    private ArrayList<Ingredient> ingredientsList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredient_screen);
        recyclerView = findViewById(R.id.recyclerView);
        editText=findViewById(R.id.editText);
        tabLayout=findViewById(R.id.tabLayout);
        viewPager=findViewById(R.id.viewPager);

       // search = findViewById(R.id.search);
        tabLayout.addTab(tabLayout.newTab().setText("Bento"));
        tabLayout.addTab(tabLayout.newTab().setText("Main"));
        tabLayout.addTab(tabLayout.newTab().setText("Appetizer"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        final TabAdapter tabAdapter = new TabAdapter(this, getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(tabAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        toolbar=findViewById(R.id.toolbar);
        this.toolbar.setTitle("Ingredient Screen");
        Retrofit retrofit = RetrofitClient.getInstance();
        ingredientService = retrofit.create(IngredientService.class);

       // recyclerView.setHasFixedSize(true);
       // recyclerView.setLayoutManager(new LinearLayoutManager(this));
         input = editText.getText().toString();

      //  fetchData();
search.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
       // fetchData();
        getIngredients(input);
        tabLayout.setVisibility(View.GONE);
        viewPager.setVisibility(View.GONE);
    }
});
        String st=editText.getText().toString();
        if(TextUtils.isEmpty(st)){
            recyclerView.setVisibility(View.GONE);
            tabLayout.setVisibility(View.VISIBLE);
            viewPager.setVisibility(View.VISIBLE);
        }
    }

  /*  private void fetchData() {
        compositeDisposable.add(searchService.getIngredients(input)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Ingredient>>() {

                    @Override
                    public void accept(List<Ingredient> ingredients) throws Exception {
                        IngredientScreen.this.displayData(ingredients);
                    }
                }));
    }*/
    private void getIngredients(String ing) {
        SearchClient.getInstance()
                .getIngredients(ing)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Ingredient>>() {
                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull List<Ingredient> ingredients) {
                        displayData(ingredients);
                    }
                });

    }

    private void displayData(List<Ingredient> ingredients) {
        IngredientAdapter adapter = new IngredientAdapter(this,ingredients);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onStop() {
        compositeDisposable.clear();
        super.onStop();
    }

}