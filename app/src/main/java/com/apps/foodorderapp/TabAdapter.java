package com.apps.foodorderapp;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.apps.foodorderapp.AppetizerFragment;
import com.apps.foodorderapp.BentoFragment;
import com.apps.foodorderapp.MainFragment;

public class TabAdapter extends FragmentPagerAdapter {
    Context context;
    int totalTabs;
    public TabAdapter(Context c, FragmentManager fm, int totalTabs){
        super(fm);
        context=c;
        this.totalTabs=totalTabs;
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch(position){
            case 0:
                BentoFragment bentoFragment = new BentoFragment();
                return bentoFragment;
            case 1:
                MainFragment mainFragment= new MainFragment();
                return mainFragment;
            case 2:
                AppetizerFragment appetizerFragment=new AppetizerFragment();
                return appetizerFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return totalTabs;
    }
}
