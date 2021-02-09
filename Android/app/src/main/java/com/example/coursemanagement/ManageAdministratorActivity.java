package com.example.coursemanagement;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.example.coursemanagement.API.API;
import com.example.coursemanagement.API.BaseURL;
import com.example.coursemanagement.Adapter.AdministratorViewAdapter;
import com.example.coursemanagement.Entity.Administrator;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ManageAdministratorActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private String URL;
    API api;
    private Button listAdministrators;
    private List<Administrator> administratorList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_administrator);
        BaseURL baseURL = new BaseURL();
        URL = baseURL.APIBaseURL();
        recyclerView = findViewById(R.id.manage_administrator_recycler);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        api = retrofit.create(API.class);

        ListAdministrators();

    }

    private void ListAdministrators() {
        try{
            Call<List<Administrator>> call = api.getAllAdministrators();

            call.enqueue(new Callback<List<Administrator>>(){
                @Override
                public void onResponse(Call<List<Administrator>> call, Response<List<Administrator>> response) {

                    administratorList = response.body();
                    AdministratorViewAdapter administratorViewAdapter = new AdministratorViewAdapter(ManageAdministratorActivity.this, administratorList);
                    recyclerView.setAdapter(administratorViewAdapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(ManageAdministratorActivity.this));
                }

                @Override
                public void onFailure(Call<List<Administrator>> call, Throwable t) {
                    Toast.makeText(ManageAdministratorActivity.this, "ERROR", Toast.LENGTH_LONG).show();
                }
            });}
        catch (Exception e)
        {
            Toast.makeText(ManageAdministratorActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
        }

    }
}