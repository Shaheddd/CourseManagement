package com.example.coursemanagement;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import com.example.coursemanagement.API.API;
import com.example.coursemanagement.API.BaseURL;
import com.example.coursemanagement.Adapter.ViewMarksForStudentAdapter;
import com.example.coursemanagement.Entity.Exam;
import com.example.coursemanagement.Entity.Mark;
import com.example.coursemanagement.Entity.Student;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ManageMarksForStudentActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private String URL;
    API api;
    private int account_ID;
    private List<Mark> markList;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_marks_for_student);

        BaseURL baseURL = new BaseURL();
        URL = baseURL.APIBaseURL();
        recyclerView = findViewById(R.id.manage_mark_student_recycler);

        sharedPreferences = getSharedPreferences("MyData", Context.MODE_PRIVATE);
        account_ID = sharedPreferences.getInt("accountID", 0);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        api = retrofit.create(API.class);

        ViewMarksForStudent();
    }

    private void ViewMarksForStudent() {
        try {
            Call<List<Mark>> call = api.viewMarksForStudent(account_ID);

            call.enqueue(new Callback<List<Mark>>() {
                @Override
                public void onResponse(Call<List<Mark>> call, Response<List<Mark>> response) {
                    markList = response.body();
                    ViewMarksForStudentAdapter viewMarksForStudentAdapter = new ViewMarksForStudentAdapter(ManageMarksForStudentActivity.this, markList);
                    recyclerView.setAdapter(viewMarksForStudentAdapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(ManageMarksForStudentActivity.this));
                }

                @Override
                public void onFailure(Call<List<Mark>> call, Throwable t) {
                    Toast.makeText(ManageMarksForStudentActivity.this, "An Error Has Occurred!", Toast.LENGTH_LONG).show();
                }
            });

        } catch (Exception e) {
            Toast.makeText(ManageMarksForStudentActivity.this, "An Error Has Occurred!", Toast.LENGTH_LONG).show();
        }
    }
}