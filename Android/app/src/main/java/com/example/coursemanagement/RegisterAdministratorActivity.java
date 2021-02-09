package com.example.coursemanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.coursemanagement.API.API;
import com.example.coursemanagement.API.BaseURL;
import com.example.coursemanagement.DataTransferObject.AdministratorRegistration;
import com.example.coursemanagement.DialogBox.DialogBox;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RegisterAdministratorActivity extends AppCompatActivity {

    Button register_administrator_button;
    TextView administratorFirstName, administratorLastName, username, password, administratorAddress, administratorPhoneNumber;
    API api;
    private String URL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_administrator);

        administratorFirstName = findViewById(R.id.register_first_name_input);
        administratorLastName = findViewById(R.id.register_last_name_input);
        username = findViewById(R.id.register_username_input);
        password = findViewById(R.id.register_password_input);
        administratorPhoneNumber = findViewById(R.id.register_phone_number_input);
        administratorAddress = findViewById(R.id.register_address_input);
        register_administrator_button = findViewById(R.id.register_button);

        BaseURL base = new BaseURL();
        URL = base.APIBaseURL();
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder()
                .callTimeout(2, TimeUnit.MINUTES)
                .connectTimeout(20, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .client(httpClient.build())
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        api = retrofit.create(API.class);

        register_administrator_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (TextUtils.isEmpty(administratorFirstName.getText().toString()) || TextUtils.isEmpty(administratorLastName.getText().toString()) || TextUtils.isEmpty(username.getText().toString()) || TextUtils.isEmpty(password.getText().toString())) {
                    new DialogBox(RegisterAdministratorActivity.this, "Please Fill In All Empty Fields!");
                } else {
                    registerAdministrator();
                }
            }
        });
    }

    private void registerAdministrator() {
        try {

            AdministratorRegistration administratorRegistration = new AdministratorRegistration();
            administratorRegistration.setFirstName(administratorFirstName.getText().toString().trim());
            administratorRegistration.setLastName(administratorLastName.getText().toString().trim());
            administratorRegistration.setAddress(administratorAddress.getText().toString().trim());
            administratorRegistration.setPhoneNumber(administratorPhoneNumber.getText().toString().trim());
            administratorRegistration.setLastName(administratorLastName.getText().toString().trim());
            administratorRegistration.setUsername(username.getText().toString().trim());
            administratorRegistration.setPassword(password.getText().toString().trim());

            Call<String> call = api.registerAdministrator(administratorRegistration);

            call.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {

                    String check = response.body();
                    new DialogBox(RegisterAdministratorActivity.this, check);
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {

                    new DialogBox(RegisterAdministratorActivity.this, "An Error Has Occurred!");
                }
            });
        }
        catch (Exception e)
        {
            new DialogBox(RegisterAdministratorActivity.this, "An Error Has Cccurred!");
        }
    }
}