package com.example.coursemanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.coursemanagement.API.API;
import com.example.coursemanagement.API.BaseURL;
import com.example.coursemanagement.DataTransferObject.CourseRegistration;
import com.example.coursemanagement.DialogBox.DialogBox;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RegisterCourseActivity extends AppCompatActivity {

    Button register_course_button;
    TextView courseName, courseDescription, courseType;
    API api;
    private String URL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_course);

        courseName = findViewById(R.id.register_course_name_input);
        courseDescription = findViewById(R.id.register_course_description_input);
        courseType = findViewById(R.id.register_course_type_input);
        register_course_button = findViewById(R.id.register_course_button);

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

        register_course_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(courseName.getText().toString()) || TextUtils.isEmpty(courseDescription.getText().toString())
                || TextUtils.isEmpty(courseType.getText().toString())) {
                    new DialogBox(RegisterCourseActivity.this, "Please Fill In All Required Fields!");
                } else {
                    registerCourse();
                }
            }
        });
    }

    private void registerCourse() {
        try {
            CourseRegistration courseRegistration = new CourseRegistration();
            courseRegistration.setCourseName(courseName.getText().toString().trim());
            courseRegistration.setCourseDescription(courseDescription.getText().toString().trim());
            courseRegistration.setCourseType(courseType.getText().toString().trim());

            Call<String> call = api.registerCourse(courseRegistration);

            call.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    String check = response.body();
                    new DialogBox(RegisterCourseActivity.this, check);
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    new DialogBox(RegisterCourseActivity.this, "An Error Has Occurred!");
                }
            });
        } catch (Exception e) {
            new DialogBox(RegisterCourseActivity.this, "An Error Has Occurred!");
        }
    }
}