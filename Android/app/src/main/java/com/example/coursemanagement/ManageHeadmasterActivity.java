package com.example.coursemanagement;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.HeaderViewListAdapter;
import android.widget.Toast;

import com.example.coursemanagement.API.API;
import com.example.coursemanagement.API.BaseURL;
import com.example.coursemanagement.Adapter.AdministratorViewAdapter;
import com.example.coursemanagement.Adapter.HeadmasterViewAdapter;
import com.example.coursemanagement.Entity.Administrator;
import com.example.coursemanagement.Entity.Headmaster;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ManageHeadmasterActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private String URL;
    API api;
    private Button listHeadmasters;
    private List<Headmaster> headmasterList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_headmaster);
        BaseURL baseURL = new BaseURL();
        URL = baseURL.APIBaseURL();
        recyclerView = findViewById(R.id.manage_headmaster_recycler);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        api = retrofit.create(API.class);

        ListHeadmasters();
    }

    private void ListHeadmasters() {
        try{
            Call<List<Headmaster>> call = api.getAllHeadmasters();

            call.enqueue(new Callback<List<Headmaster>>(){
                @Override
                public void onResponse(Call<List<Headmaster>> call, Response<List<Headmaster>> response) {

                    headmasterList = response.body();
                    HeadmasterViewAdapter headmasterViewAdapter = new HeadmasterViewAdapter(ManageHeadmasterActivity.this, headmasterList);
                    recyclerView.setAdapter(headmasterViewAdapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(ManageHeadmasterActivity.this));
                }

                @Override
                public void onFailure(Call<List<Headmaster>> call, Throwable t) {
                    Toast.makeText(ManageHeadmasterActivity.this, "ERROR", Toast.LENGTH_LONG).show();
                }
            });}
        catch (Exception e)
        {
            Toast.makeText(ManageHeadmasterActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}