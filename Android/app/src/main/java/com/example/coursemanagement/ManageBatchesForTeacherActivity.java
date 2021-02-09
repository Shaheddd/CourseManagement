package com.example.coursemanagement;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.coursemanagement.API.API;
import com.example.coursemanagement.API.BaseURL;
import com.example.coursemanagement.Adapter.ViewBatchesForTeacherAdapter;
import com.example.coursemanagement.Entity.Batch;
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

public class ManageBatchesForTeacherActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private String URL;
    API api;
    private int account_ID;
    private List<Batch> batchList;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_batches_for_teacher);

        BaseURL baseURL = new BaseURL();
        URL = baseURL.APIBaseURL();
        recyclerView = findViewById(R.id.manage_teacher_batch_recycler);

        sharedPreferences = getSharedPreferences("MyData", Context.MODE_PRIVATE);
        account_ID = sharedPreferences.getInt("accountID", 0);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        api = retrofit.create(API.class);

        ViewBatchesForTeacher();
    }

    private void ViewBatchesForTeacher() {
        try {
            Call<List<Batch>> call = api.viewBatchesForTeacher(account_ID);

            call.enqueue(new Callback<List<Batch>>() {
                @Override
                public void onResponse(Call<List<Batch>> call, Response<List<Batch>> response) {
                    batchList = response.body();
                    ViewBatchesForTeacherAdapter viewBatchesForTeacherAdapter = new ViewBatchesForTeacherAdapter(ManageBatchesForTeacherActivity.this, batchList);
                    recyclerView.setAdapter(viewBatchesForTeacherAdapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(ManageBatchesForTeacherActivity.this));
                }

                @Override
                public void onFailure(Call<List<Batch>> call, Throwable t) {
                    Toast.makeText(ManageBatchesForTeacherActivity.this, "Big Yikes From Me, Dawg", Toast.LENGTH_LONG).show();
                }
            });
        } catch (Exception e) {
            Toast.makeText(ManageBatchesForTeacherActivity.this, "An Error Has Occurred!", Toast.LENGTH_LONG).show();
        }
    }
}