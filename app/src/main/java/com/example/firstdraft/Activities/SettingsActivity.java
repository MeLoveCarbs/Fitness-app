package com.example.firstdraft.Activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.firstdraft.Api.RetrofitClient;
import com.example.firstdraft.R;
import com.example.firstdraft.Responses.DefaultResponse;
import com.example.firstdraft.Responses.User;
import com.example.firstdraft.storage.SharedPrefManager;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.EditTextPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SettingsActivity extends AppCompatActivity implements View.OnClickListener {

    public static String KEY_PREF_EXAMPLE_SWITCH = "example_switch";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);

        findViewById(R.id.deletebutton).setOnClickListener(this);
        findViewById(R.id.logoutbutton).setOnClickListener(this);
        findViewById(R.id.changepasswordbutton).setOnClickListener(this);
    }

    private void logout() {
        SharedPrefManager.getInstance(SettingsActivity.this).clear();
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    private void deleteUser() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Are you sure?");
        builder.setMessage("This action is irreversible!");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                User user = SharedPrefManager.getInstance(SettingsActivity.this).getUser();
                Call<DefaultResponse> call = RetrofitClient.getInstance().getApi().deleteUser(user.getId());

                call.enqueue(new Callback<DefaultResponse>() {
                    @Override
                    public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {

                        if (!response.body().isErr()) {
                            SharedPrefManager.getInstance(SettingsActivity.this).clear();
                            SharedPrefManager.getInstance(SettingsActivity.this).clear();
                            Intent intent = new Intent(SettingsActivity.this, MainActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);
                        }

                        Toast.makeText(SettingsActivity.this, response.body().getMsg(), Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onFailure(Call<DefaultResponse> call, Throwable t) {

                    }
                });

            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        AlertDialog ad = builder.create();
        ad.show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.changepasswordbutton:
                startActivity(new Intent(this, changepasswordActivity.class));
                break;
            case R.id.deletebutton:
                deleteUser();
                break;
            case R.id.logoutbutton:
                logout();
                break;
        }
    }

}