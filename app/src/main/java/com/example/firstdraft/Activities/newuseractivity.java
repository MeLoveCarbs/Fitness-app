package com.example.firstdraft.Activities;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.firstdraft.Api.RetrofitClient;
import com.example.firstdraft.R;
import com.example.firstdraft.Responses.DefaultResponse;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;




public class newuseractivity extends AppCompatActivity implements View.OnClickListener{

    private EditText editTextEmail, editTextPassword, editTextName, editTextUsername;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newuseractivity);

        editTextEmail = findViewById(R.id.emailedititext);
        editTextPassword = findViewById(R.id.passwordedittext);
        editTextName = findViewById(R.id.fullnameedittext);
        editTextUsername = findViewById(R.id.usernameedittext);

        findViewById(R.id.signupbutton).setOnClickListener(this);

    }

    private boolean userSignUp() {
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        String name = editTextName.getText().toString().trim();
        String username = editTextUsername.getText().toString().trim();

        if (email.isEmpty()) {
            editTextEmail.setError("Email is required");
            editTextEmail.requestFocus();
            return false;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editTextEmail.setError("Enter a valid email");
            editTextEmail.requestFocus();
            return false;
        }

        if (password.isEmpty()) {
            editTextPassword.setError("Password required");
            editTextPassword.requestFocus();
            return false;
        }

        if (password.length() < 6) {
            editTextPassword.setError("Password should be atleast 6 character long");
            editTextPassword.requestFocus();
            return false;
        }

        if (name.isEmpty()) {
            editTextName.setError("Name required");
            editTextName.requestFocus();
            return false;
        }

        if (username.isEmpty()) {
            editTextUsername.setError("Username required");
            editTextUsername.requestFocus();
            return false;
        }

        /*registering user using API calls*/



        Call<DefaultResponse>call = RetrofitClient
                .getInstance()
                .getApi()
                .createUser(email, password, name, username);


        call.enqueue(new Callback<DefaultResponse>() {
            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                if (response.code() == 201) {

                    DefaultResponse dr = response.body();
                    Toast.makeText(newuseractivity.this, dr.getMsg(), Toast.LENGTH_LONG).show();

                } else if (response.code() == 422) {
                    Toast.makeText(newuseractivity.this, "User already exist", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<DefaultResponse> call, Throwable t) {

                Toast.makeText(newuseractivity.this, t.getMessage(), Toast.LENGTH_LONG).show();

            }
        });

        return true;
    }


            @Override
    public void onClick(View v) {
        if(v.getId() == R.id.signupbutton){
            if(userSignUp())
                startActivity(new Intent(this, mainmenuactivity.class));
        }
    }
}
