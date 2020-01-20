package com.example.firstdraft.Activities;
import com.example.firstdraft.Responses.LoginResponse;
import com.example.firstdraft.Api.RetrofitClient;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.firstdraft.storage.SharedPrefManager;
import com.example.firstdraft.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private EditText editTextUsername;
    private EditText editTextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextUsername = findViewById(R.id.usernameedittext);
        editTextPassword = findViewById(R.id.passwordedittext);

        findViewById(R.id.loginbutton).setOnClickListener(this);
        findViewById(R.id.newuserbutton).setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();

//        if (SharedPrefManager.getInstance(this).isLoggedIn()) {
//            Intent intent = new Intent(this, mainmenuactivity.class);
//            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//            startActivity(intent);
//        }
    }

    private void userLogin() {

        final String username = editTextUsername.getText().toString().trim();
        final String password = editTextPassword.getText().toString().trim();

        if (username.isEmpty()) {
            editTextUsername.setError("Username is required");
            editTextUsername.requestFocus();
            return;
        }


        if (password.isEmpty()) {
            editTextPassword.setError("Password required");
            editTextPassword.requestFocus();
            return;
        }

        if (password.length() < 6) {
            editTextPassword.setError("Password should be at least 6 character long");
            editTextPassword.requestFocus();
            return;
        }

//        Below code is for back-end ready account database

        Call<LoginResponse> call = RetrofitClient
                .getInstance().getApi().userLogin(username, password);

        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                LoginResponse loginResponse = response.body();

                //assert loginResponse != null;
                if (username.equals("admin") && password.equals("password")) { //!loginResponse.isError()

                    SharedPrefManager.getInstance(MainActivity.this)
                            .saveUser(loginResponse.getUser());

                    Intent intent = new Intent(MainActivity.this, mainmenuactivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);


                } else {
                    Toast.makeText(MainActivity.this, loginResponse.getMessage(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {

            }
        });

    }

    @Override
    public void onClick(View v) {
//        Intent intent = new Intent(MainActivity.this, mainmenuactivity.class);
//        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//        startActivity(intent);
        switch (v.getId()) {
            case R.id.loginbutton:
                startActivity(new Intent(this, mainmenuactivity.class));
                break;
            case R.id.newuserbutton:
                startActivity(new Intent(this, newuseractivity.class));
                break;
        }
    }
}