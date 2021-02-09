package com.example.coursemanagement;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.example.coursemanagement.API.API;
import com.example.coursemanagement.API.BaseURL;
import com.example.coursemanagement.Adapter.HeadmasterViewAdapter;
import com.example.coursemanagement.Adapter.TeacherViewAdapter;
import com.example.coursemanagement.Entity.Headmaster;
import com.example.coursemanagement.Entity.Teacher;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ManageTeacherActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private String URL;
    API api;
    private Button listTeachers;
    private List<Teacher> teacherList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_teacher);
        BaseURL baseURL = new BaseURL();
        URL = baseURL.APIBaseURL();
        recyclerView = findViewById(R.id.manage_teacher_recycler);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        api = retrofit.create(API.class);

        ListTeachers();
    }

    private void ListTeachers() {
        try{
            Call<List<Teacher>> call = api.getAllTeachers();

            call.enqueue(new Callback<List<Teacher>>(){
                @Override
                public void onResponse(Call<List<Teacher>> call, Response<List<Teacher>> response) {

                    teacherList = response.body();
                    TeacherViewAdapter teacherViewAdapter = new TeacherViewAdapter(ManageTeacherActivity.this, teacherList);
                    recyclerView.setAdapter(teacherViewAdapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(ManageTeacherActivity.this));
                }

                @Override
                public void onFailure(Call<List<Teacher>> call, Throwable t) {
                    Toast.makeText(ManageTeacherActivity.this, "ERROR", Toast.LENGTH_LONG).show();
                }
            });}
        catch (Exception e)
        {
            Toast.makeText(ManageTeacherActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}