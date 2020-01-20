package com.example.firstdraft.Adapters;

import android.content.res.Resources;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.firstdraft.R;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {


    Context context;
    int logos[];
    String[] menu = {"MyRoutine", "Exercises", "Weight and Calorie Tracker", "Gallery", "My Feed", "Settings"};
    LayoutInflater inflter;
    public CustomAdapter(Context applicationContext, int[] logos) {
        this.context = applicationContext;
        this.logos = logos;
        inflter = (LayoutInflater.from(applicationContext));
    }
    @Override
    public int getCount() {
        return logos.length;
    }
    @Override
    public Object getItem(int i) {
        return null;
    }
    @Override
    public long getItemId(int i) {
        return 0;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        view = inflter.inflate(R.layout.mainmenugridview, null); // inflate the layout
        ImageView icon = (ImageView) view.findViewById(R.id.icon); // get the reference of ImageView
        icon.setImageResource(logos[i]); // set logo images
        TextView textView = (TextView) view.findViewById(R.id.infotextview);
        textView.setText(menu[i]);
        return view;
    }
}
