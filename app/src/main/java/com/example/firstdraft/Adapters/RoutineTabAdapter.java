package com.example.firstdraft.Adapters;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.firstdraft.Activities.myRoutineTab.Fri;
import com.example.firstdraft.Activities.myRoutineTab.Mon;
import com.example.firstdraft.Activities.myRoutineTab.Sat;
import com.example.firstdraft.Activities.myRoutineTab.Sun;
import com.example.firstdraft.Activities.myRoutineTab.Thurs;
import com.example.firstdraft.Activities.myRoutineTab.Tues;
import com.example.firstdraft.Activities.myRoutineTab.Wed;

public class RoutineTabAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;
    public RoutineTabAdapter(FragmentManager fm, int NoofTabs){
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
                Mon Mon = new Mon();
                return Mon;
            case 1:
                Tues Tues = new Tues();
                return Tues;
            case 2:
                Wed wed = new Wed();
                return wed;
            case 3:
                Thurs Thurs = new Thurs();
                return Thurs;
            case 4:
                Fri Fri = new Fri();
                return Fri;
            case 5:
                Sat Sat = new Sat();
                return Sat;
            case 6:
                Sun Sun = new Sun();
                return Sun;
            default:
                return null;
        }
    }
}
