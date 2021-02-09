package com.example.coursemanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.coursemanagement.API.API;
import com.example.coursemanagement.API.BaseURL;
import com.example.coursemanagement.DataTransferObject.StudentRegistration;
import com.example.coursemanagement.DialogBox.DialogBox;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RegisterStudentActivity extends AppCompatActivity {

    Button register_student_button;
    TextView studentFirstName, studentLastName, studentAddress, studentPhoneNumber, username, password;
    API api;
    private String URL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_student);

        studentFirstName = findViewById(R.id.register_first_name_input);
        studentLastName = findViewById(R.id.register_last_name_input);
        studentAddress = findViewById(R.id.register_address_input);
        studentPhoneNumber = findViewById(R.id.register_phone_number_input);
        username = findViewById(R.id.register_username_input);
        password = findViewById(R.id.register_password_input);
        register_student_button = findViewById(R.id.register_button);

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

        register_student_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(studentFirstName.getText().toString()) || TextUtils.isEmpty(studentLastName.getText().toString())
                || TextUtils.isEmpty(studentAddress.getText().toString()) || TextUtils.isEmpty(studentPhoneNumber.getText().toString())
                || TextUtils.isEmpty(username.getText().toString()) || TextUtils.isEmpty(password.getText().toString())) {
                    new DialogBox(RegisterStudentActivity.this, "Please Fill In All Empty Fields!");
                } else {
                    registerStudent();
                }
            }
        });
    }

    private void registerStudent() {
        try {
            StudentRegistration studentRegistration = new StudentRegistration();
            studentRegistration.setFirstName(studentFirstName.getText().toString().trim());
            studentRegistration.setLastName(studentLastName.getText().toString().trim());
            studentRegistration.setAddress(studentAddress.getText().toString().trim());
            studentRegistration.setPhoneNumber(studentPhoneNumber.getText().toString().trim());
            studentRegistration.setUsername(username.getText().toString().trim());
            studentRegistration.setPassword(password.getText().toString().trim());

            Call<String> call = api.registerStudent(studentRegistration);

            call.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    String check = response.body();
                    new DialogBox(RegisterStudentActivity.this, check);
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    new DialogBox(RegisterStudentActivity.this, "An Error Has Occurred!");
                }
            });
        } catch (Exception e) {
            new DialogBox(RegisterStudentActivity.this, "An Error Has Occurred!");
        }
    }
}