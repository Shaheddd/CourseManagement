package com.example.coursemanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.coursemanagement.API.API;
import com.example.coursemanagement.API.BaseURL;
import com.example.coursemanagement.DialogBox.DialogBox;
import com.example.coursemanagement.Entity.Course;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class EditCourseActivity extends AppCompatActivity {

    API api;
    private String URL;
    private int course_ID;
    private EditText courseName, courseDescription, courseType;
    private TextView id;
    private Button update;
    private Course myCourse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_course);

        id = findViewById(R.id.view_course_ID);
        courseName = findViewById(R.id.edit_course_name);
        courseDescription = findViewById(R.id.edit_course_description);
        courseType = findViewById(R.id.edit_course_type);
        update = findViewById(R.id.update_course_btn);

        BaseURL baseURL = new BaseURL();
        URL = baseURL.APIBaseURL();
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .client(httpClient.build())
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        api = retrofit.create(API.class);

        course_ID = Integer.parseInt(getIntent().getStringExtra("course_ID"));

        LoadCourse();

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(courseName.getText().toString()) || TextUtils.isEmpty(courseDescription.getText().toString())
                || TextUtils.isEmpty(courseType.getText().toString())) {
                    new DialogBox(EditCourseActivity.this, "Please Fill In All Required Fields!");
                } else {
                    updateCourse();
                }
            }
        });
    }

    private void LoadCourse() {
        try {
            Call<Course> courseCall = api.getCourse(course_ID);

            courseCall.enqueue(new Callback<Course>() {
                @Override
                public void onResponse(Call<Course> call, Response<Course> response) {
                    Course course = response.body();
                    myCourse = course;
                    id.setText(Integer.toString(course.getCourseID()));
                    courseName.setText(course.getCourseName());
                    courseDescription.setText(course.getCourseDescription());
                    courseType.setText(course.getCourseType());
                }

                @Override
                public void onFailure(Call<Course> call, Throwable t) {
                    new DialogBox(EditCourseActivity.this, "An Error Has Occurred");
                }
            });
        } catch (Exception e) {
            Toast.makeText(EditCourseActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private void updateCourse() {
        try {
            myCourse.setCourseID(Integer.parseInt(id.getText().toString()));
            myCourse.setCourseName(courseName.getText().toString());
            myCourse.setCourseDescription(courseDescription.getText().toString());
            myCourse.setCourseType(courseType.getText().toString());
            Call<String> call = api.updateCourse(myCourse);

            call.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    new DialogBox(EditCourseActivity.this, "Course Updated!");
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    new DialogBox(EditCourseActivity.this, "An Error Has Occurred!");
                }
            });
        } catch (Exception e) {
            new DialogBox(EditCourseActivity.this, "An Error Has Occurred!");
        }
    }
}