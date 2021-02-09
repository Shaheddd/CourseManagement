package com.example.coursemanagement;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import com.example.coursemanagement.API.API;
import com.example.coursemanagement.API.BaseURL;
import com.example.coursemanagement.Adapter.StudentViewAdapter;
import com.example.coursemanagement.Adapter.TeacherViewAdapter;
import com.example.coursemanagement.Entity.Student;
import com.example.coursemanagement.Entity.Teacher;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ManageStudentActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private String URL;
    API api;
    private List<Student> studentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_student);
        BaseURL baseURL = new BaseURL();
        URL = baseURL.APIBaseURL();
        recyclerView = findViewById(R.id.manage_student_recycler);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        api = retrofit.create(API.class);

        ListStudents();
    }

    private void ListStudents() {
        try{
            Call<List<Student>> call = api.getAllStudents();

            call.enqueue(new Callback<List<Student>>(){
                @Override
                public void onResponse(Call<List<Student>> call, Response<List<Student>> response) {

                    studentList = response.body();
                    StudentViewAdapter studentViewAdapter = new StudentViewAdapter(ManageStudentActivity.this, studentList);
                    recyclerView.setAdapter(studentViewAdapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(ManageStudentActivity.this));
                }

                @Override
                public void onFailure(Call<List<Student>> call, Throwable t) {
                    Toast.makeText(ManageStudentActivity.this, "ERROR", Toast.LENGTH_LONG).show();
                }
            });}
        catch (Exception e)
        {
            Toast.makeText(ManageStudentActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}