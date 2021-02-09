
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
import com.example.coursemanagement.Adapter.ViewExamsForTeacherAdapter;
import com.example.coursemanagement.Entity.Exam;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ManageExamsForTeacherActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private String URL;
    API api;
    private int account_ID;
    private List<Exam> examList;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_exams_for_teacher);

        BaseURL baseURL = new BaseURL();
        URL = baseURL.APIBaseURL();
        recyclerView = findViewById(R.id.manage_teacher_exam_recycler);

        sharedPreferences = getSharedPreferences("MyData", Context.MODE_PRIVATE);
        account_ID = sharedPreferences.getInt("accountID", 0);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        api = retrofit.create(API.class);

        ViewExamsForTeacher();
    }

    private void ViewExamsForTeacher() {
        try {
            Call<List<Exam>> call = api.viewExamsForTeacher(account_ID);

            call.enqueue(new Callback<List<Exam>>() {
                @Override
                public void onResponse(Call<List<Exam>> call, Response<List<Exam>> response) {
                    examList = response.body();
                    ViewExamsForTeacherAdapter viewExamsForTeacherAdapter = new ViewExamsForTeacherAdapter(ManageExamsForTeacherActivity.this, examList);
                    recyclerView.setAdapter(viewExamsForTeacherAdapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(ManageExamsForTeacherActivity.this));
                }

                @Override
                public void onFailure(Call<List<Exam>> call, Throwable t) {
                    Toast.makeText(ManageExamsForTeacherActivity.this, "An Error Has Occurred!", Toast.LENGTH_LONG).show();
                }
            });
        } catch (Exception e) {
            Toast.makeText(ManageExamsForTeacherActivity.this, "An Error Has Occurred!", Toast.LENGTH_LONG).show();
        }
    }
}