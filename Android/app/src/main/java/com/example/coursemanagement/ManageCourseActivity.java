package com.example.coursemanagement;

import android.os.Bundle;

import com.example.coursemanagement.API.API;
import com.example.coursemanagement.API.BaseURL;
import com.example.coursemanagement.Adapter.CourseViewAdapter;
import com.example.coursemanagement.Entity.Course;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ManageCourseActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private String URL;
    API api;
    private List<Course> courseList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_course);

        BaseURL baseURL = new BaseURL();
        URL = baseURL.APIBaseURL();
        recyclerView = findViewById(R.id.manage_course_recycler);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        api = retrofit.create(API.class);

        ListCourses();
    }

    private void ListCourses() {
        try {
            Call<List<Course>> call = api.getAllCourses();

            call.enqueue(new Callback<List<Course>>() {
                @Override
                public void onResponse(Call<List<Course>> call, Response<List<Course>> response) {
                    courseList = response.body();
                    CourseViewAdapter courseViewAdapter = new CourseViewAdapter(ManageCourseActivity.this, courseList);
                    recyclerView.setAdapter(courseViewAdapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(ManageCourseActivity.this));
                }

                @Override
                public void onFailure(Call<List<Course>> call, Throwable t) {
                    Toast.makeText(ManageCourseActivity.this, "An Error Has Occurred!", Toast.LENGTH_LONG).show();
                }
            });
        } catch (Exception e) {
            Toast.makeText(ManageCourseActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}