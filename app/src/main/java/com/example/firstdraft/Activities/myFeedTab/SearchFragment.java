package com.example.firstdraft.Activities.myFeedTab;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;

import com.example.firstdraft.R;

public class SearchFragment extends Fragment {
    SearchView searchView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle savedInstanceState){
        return inflater.inflate(R.layout.fragment_search_tab, viewGroup, false);
    }
}

