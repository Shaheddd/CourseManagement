package com.example.coursemanagement;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.coursemanagement.API.API;
import com.example.coursemanagement.API.BaseURL;
import com.example.coursemanagement.Adapter.BatchViewAdapter;
import com.example.coursemanagement.Entity.Batch;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ManageBatchActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    API api;
    private String URL;
    private List<Batch> batchList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_batch);

        BaseURL baseURL = new BaseURL();
        URL = baseURL.APIBaseURL();
        recyclerView = findViewById(R.id.manage_batch_recycler);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        api = retrofit.create(API.class);

        ListBatches();

    }

    private void ListBatches() {
        try {
            Call<List<Batch>> call = api.getAllBatches();

            call.enqueue(new Callback<List<Batch>>() {
                @Override
                public void onResponse(Call<List<Batch>> call, Response<List<Batch>> response) {
                    batchList = response.body();
                    BatchViewAdapter batchViewAdapter = new BatchViewAdapter(ManageBatchActivity.this, batchList);
                    recyclerView.setAdapter(batchViewAdapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(ManageBatchActivity.this));
                }

                @Override
                public void onFailure(Call<List<Batch>> call, Throwable t) {
                    Toast.makeText(ManageBatchActivity.this, "An Error Has Occurred!", Toast.LENGTH_LONG).show();
                }
            });
        } catch (Exception e) {
            Toast.makeText(ManageBatchActivity.this, "An Error Has Occurred!", Toast.LENGTH_LONG).show();
        }
    }
}