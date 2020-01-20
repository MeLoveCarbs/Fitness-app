package com.example.firstdraft.Adapters;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.firstdraft.Activities.myFeedTab.HomeFragment;
import com.example.firstdraft.Activities.myFeedTab.SearchFragment;

public class MyfeedTabAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;
    public MyfeedTabAdapter(FragmentManager fm, int NoofTabs){
        super(fm);
        this.mNumOfTabs = NoofTabs;
    }
    @Override
    public int getCount() {
        return mNumOfTabs;
    }
    @Override
    public Fragment getItem(int position){
        switch (position){
            case 0:
                HomeFragment home = new HomeFragment();
                return home;
            case 1:
                SearchFragment search = new SearchFragment();
                return search;
            default:
                return null;
        }
    }
}
