package com.example.firstdraft.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.example.firstdraft.Api.Api;
import com.example.firstdraft.Api.RetrofitClient;
import com.example.firstdraft.R;
import com.example.firstdraft.Responses.DefaultResponse;
import com.example.firstdraft.Responses.User;
import com.example.firstdraft.storage.SharedPrefManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class changepasswordActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText editTextCurrentPassword, editTextNewPassword , editTextemail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changepassword);

        editTextCurrentPassword = findViewById(R.id.currentpasswordeditText);
        editTextNewPassword = findViewById(R.id.newpasswordeditText);
        findViewById(R.id.updateButton).setOnClickListener(this);
    }


    private void updatePassword() {
        String currentpassword = editTextCurrentPassword.getText().toString().trim();
        String newpassword = editTextNewPassword.getText().toString().trim();

        if (currentpassword.isEmpty()) {
            editTextCurrentPassword.setError("Password required");
            editTextCurrentPassword.requestFocus();
            return;
        }

        if (newpassword.length() < 6) {
            editTextNewPassword.setError("New password should be at least 6 character long");
            editTextNewPassword.requestFocus();
            return;
        }

        if (newpassword.isEmpty()) {
            editTextNewPassword.setError("Enter new password");
            editTextNewPassword.requestFocus();
            return;
        }


        User user = SharedPrefManager.getInstance(changepasswordActivity.this).getUser();

        Call<DefaultResponse> call = RetrofitClient.getInstance().getApi()
                .updatePassword(currentpassword, newpassword, user.getEmail());

        call.enqueue(new Callback<DefaultResponse>() {
            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {

                Toast.makeText(changepasswordActivity.this, response.body().getMsg(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<DefaultResponse> call, Throwable t) {

            }
        });
    }




    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.updateButton:
                updatePassword();
                break;

        }
    }
}
