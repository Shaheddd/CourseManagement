package com.example.coursemanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.coursemanagement.API.API;
import com.example.coursemanagement.API.BaseURL;
import com.example.coursemanagement.DataTransferObject.TeacherRegistration;
import com.example.coursemanagement.DialogBox.DialogBox;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RegisterTeacherActivity extends AppCompatActivity {

    Button register_teacher_button;
    TextView teacherFirstName, teacherLastName, teacherAddress, teacherPhoneNumber, username, password;
    API api;
    private String URL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_teacher);

        teacherFirstName = findViewById(R.id.register_first_name_input);
        teacherLastName = findViewById(R.id.register_last_name_input);
        teacherAddress = findViewById(R.id.register_address_input);
        teacherPhoneNumber = findViewById(R.id.register_phone_number_input);
        username = findViewById(R.id.register_username_input);
        password = findViewById(R.id.register_password_input);
        register_teacher_button = findViewById(R.id.register_button);

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

        register_teacher_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(teacherFirstName.getText().toString()) || TextUtils.isEmpty(teacherLastName.getText().toString())
                || TextUtils.isEmpty(teacherAddress.getText().toString()) || TextUtils.isEmpty(teacherPhoneNumber.getText().toString())
                || TextUtils.isEmpty(username.getText().toString()) || TextUtils.isEmpty(password.getText().toString())) {
                    new DialogBox(RegisterTeacherActivity.this, "Please Fill In All Empty Fields!");
                } else {
                    registerTeacher();
                }
            }
        });
    }

    private void registerTeacher() {
        try {
            TeacherRegistration teacherRegistration = new TeacherRegistration();
            teacherRegistration.setFirstName(teacherFirstName.getText().toString().trim());
            teacherRegistration.setLastName(teacherLastName.getText().toString().trim());
            teacherRegistration.setAddress(teacherAddress.getText().toString().trim());
            teacherRegistration.setPhoneNumber(teacherPhoneNumber.getText().toString().trim());
            teacherRegistration.setUsername(username.getText().toString().trim());
            teacherRegistration.setPassword(password.getText().toString().trim());

            Call<String> call = api.registerTeacher(teacherRegistration);

            call.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    String check = response.body();
                    new DialogBox(RegisterTeacherActivity.this, check);
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    new DialogBox(RegisterTeacherActivity.this, "An Error Has Occurred!");

                }
            });
        } catch (Exception e) {
            new DialogBox(RegisterTeacherActivity.this, "An Error Has Occurred!");
        }
    }
}