package com.example.firstdraft.Activities.myFeedTab;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.content.Intent;

import com.example.firstdraft.Activities.NewpostActivity;
import com.example.firstdraft.Adapters.CustomListAdapter;
import com.example.firstdraft.Adapters.Card;
import com.example.firstdraft.R;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private static final String TAG = "myfeedactivity";
    private ListView mListView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_feed, viewGroup, false);

        mListView = (ListView) rootView.findViewById(R.id.listView);
        ArrayList<Card> list = new ArrayList<>();

        list.add(new Card("drawable://" + R.drawable.myfeed_eg1, " I finally did it!!", "drawable://" + R.drawable.rsz_download, "Aherlisi"));
        list.add(new Card("drawable://" + R.drawable.myfeed_eg2, " Thanks for all the encouragements!", "drawable://" + R.drawable.rsz_download, "RedSosa"));
        list.add(new Card("drawable://" + R.drawable.myfeed_eg3, " What a journey!", "drawable://" + R.drawable.rsz_download, "Tagzhodden"));
        list.add(new Card("drawable://" + R.drawable.myfeed_eg4, " #EZ", "drawable://" + R.drawable.rsz_download, "Listereras"));
        list.add(new Card("drawable://" + R.drawable.myfeed_eg5, " I made it guys","drawable://" + R.drawable.rsz_download, "Smootheity"));
        list.add(new Card("drawable://" + R.drawable.myfeed_eg6, " Am i finally fit?","drawable://" + R.drawable.rsz_download, "NephewTen"));
        list.add(new Card("drawable://" + R.drawable.myfeed_eg7, " My progression!", "drawable://" + R.drawable.rsz_download, "JuzBel"));
        list.add(new Card("drawable://" + R.drawable.myfeed_eg8, " Can't believe this is actually me", "drawable://" + R.drawable.rsz_download, "Mrmuscle"));

        CustomListAdapter adapter = new CustomListAdapter(getContext(), R.layout.cardview, list);
        mListView.setAdapter(adapter);


        return rootView;
    }
}


