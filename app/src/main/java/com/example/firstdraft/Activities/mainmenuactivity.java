package com.example.firstdraft.Activities;
import com.example.firstdraft.storage.SharedPrefManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import android.content.SharedPreferences;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.firstdraft.Adapters.CustomAdapter;
import com.example.firstdraft.R;

public class mainmenuactivity extends AppCompatActivity {
    GridView simpleGrid;
    int logos[] = {R.drawable.routinelogo, R.drawable.exerciseslogo, R.drawable.weighttrackerlogo, R.drawable.gallerylogo,
            R.drawable.forumlogo, R.drawable.settingslogo};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainmenuactivity);
        //Save user's settings when logged in
//        PreferenceManager.setDefaultValues(this, R.xml.root_preferences, false);
//        SharedPreferences sharedPref =
//                PreferenceManager.getDefaultSharedPreferences(this);
//        Boolean switchPref = sharedPref.getBoolean
//                (SettingsActivity.KEY_PREF_EXAMPLE_SWITCH, false);

        simpleGrid = (GridView) findViewById(R.id.simpleGridView); // init GridView
        // Create an object of CustomAdapter and set Adapter to GridView
        CustomAdapter customAdapter = new CustomAdapter(getApplicationContext(), logos);
        simpleGrid.setAdapter(customAdapter);

        // implement setOnItemClickListener event on GridView
        /*simpleGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // set an Intent to Another Activity
                Intent intent = new Intent(mainmenuactivity.this, customAdapter.class);
                intent.putExtra("image", logos[position]); // put image data in Intent
                startActivity(intent); // start Intent
            }
        };*/



        simpleGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position,
                                    long arg3) {
                if (position == 5) {
                    Intent intent = new Intent(mainmenuactivity.this, SettingsActivity.class);
                    startActivity(intent);
                } else if (position == 1) {
                    Intent intent = new Intent(getApplicationContext(), DisplayPage.class);
                    startActivity(intent);
                } else if (position == 4) {
                    Intent intent = new Intent(getApplicationContext(), MyFeedActivity_linker.class);
                    startActivity(intent);
                } else if (position == 3) {
                    Intent intent = new Intent(getApplicationContext(), GalleryActivity.class);
                    startActivity(intent);
                } else if (position == 2) {
                    Intent intent = new Intent(getApplicationContext(), weightandcalorietrackerActivity.class);
                    startActivity(intent);
                } else if (position == 0) {
                    Intent intent = new Intent(getApplicationContext(), MyRoutineActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

//        if (!SharedPrefManager.getInstance(this).isLoggedIn()) {
//            Intent intent = new Intent(this, MainActivity.class);
//            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//            startActivity(intent);
//        }
    }
}