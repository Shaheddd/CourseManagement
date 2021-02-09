package com.example.coursemanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.coursemanagement.API.API;
import com.example.coursemanagement.API.BaseURL;
import com.example.coursemanagement.DataTransferObject.HeadmasterRegistration;
import com.example.coursemanagement.DialogBox.DialogBox;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RegisterHeadmasterActivity extends AppCompatActivity {

    Button register_headmaster_button;
    TextView headmasterFirstName, headmasterLastName, headmasterAddress, headmasterPhoneNumber, username, password;
    API api;
    private String URL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_headmaster);

        headmasterFirstName = findViewById(R.id.register_first_name_input);
        headmasterLastName = findViewById(R.id.register_last_name_input);
        headmasterAddress = findViewById(R.id.register_address_input);
        headmasterPhoneNumber = findViewById(R.id.register_phone_number_input);
        username = findViewById(R.id.register_username_input);
        password = findViewById(R.id.register_password_input);
        register_headmaster_button = findViewById(R.id.register_button);

        BaseURL baseURL = new BaseURL();
        URL = baseURL.APIBaseURL();
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

        register_headmaster_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(headmasterFirstName.getText().toString()) || TextUtils.isEmpty(headmasterLastName.getText().toString())
                || TextUtils.isEmpty(headmasterAddress.getText().toString()) || TextUtils.isEmpty(headmasterPhoneNumber.getText().toString())
                || TextUtils.isEmpty(username.getText().toString()) || TextUtils.isEmpty(password.getText().toString())) {
                    new DialogBox(RegisterHeadmasterActivity.this, "Please Fill In All Empty Fields!");
                } else {
                    registerHeadmaster();
                }
                }
            });
        }

        private void registerHeadmaster() {
        try {
            HeadmasterRegistration headmasterRegistration = new HeadmasterRegistration();
            headmasterRegistration.setFirstName(headmasterFirstName.getText().toString().trim());
            headmasterRegistration.setLastName(headmasterLastName.getText().toString().trim());
            headmasterRegistration.setAddress(headmasterAddress.getText().toString().trim());
            headmasterRegistration.setPhoneNumber(headmasterPhoneNumber.getText().toString().trim());
            headmasterRegistration.setUsername(username.getText().toString().trim());
            headmasterRegistration.setPassword(password.getText().toString().trim());

            Call<String> call = api.registerHeadmaster(headmasterRegistration);

            call.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    String check = response.body();
                    new DialogBox(RegisterHeadmasterActivity.this, check);
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    new DialogBox(RegisterHeadmasterActivity.this, "An Error Has Occurred!");
                }
            });
        } catch (Exception e) {
            new DialogBox(RegisterHeadmasterActivity.this, "An Error Has Occurred");
        }
    }
}