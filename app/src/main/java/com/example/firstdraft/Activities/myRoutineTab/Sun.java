package com.example.firstdraft.Activities.myRoutineTab;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;

import com.example.firstdraft.Activities.DisplayPage;
import com.example.firstdraft.R;

public class Sun extends Fragment {
    SearchView searchView;
    ImageButton newpostButton;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle savedInstanceState){
        return inflater.inflate(R.layout.routine_sun, viewGroup, false);

    }
}