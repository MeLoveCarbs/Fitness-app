package com.example.firstdraft.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import com.example.firstdraft.Adapters.ViewPagerAdapter;
import com.example.firstdraft.R;
import com.example.firstdraft.Responses.User;
import com.example.firstdraft.storage.SharedPrefManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

public class GalleryActivity extends AppCompatActivity {
    ImageButton newpostButton;
    private TextView tvName, tvDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(this);
        viewPager.setAdapter(viewPagerAdapter);

        newpostButton = findViewById(R.id.newpostButton);
        newpostButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GalleryActivity.this, NewpostActivity.class);
                startActivity(intent);
            }
        });


        User user = SharedPrefManager.getInstance(GalleryActivity.this).getUser();
        tvName = findViewById(R.id.tvName);
        tvName.setText(user.getUsername());
        tvDescription = findViewById(R.id.tvDescription);
        tvDescription.setText(user.getName());
    }

}
