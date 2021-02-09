package com.example.coursemanagement;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.example.coursemanagement.API.API;
import com.example.coursemanagement.API.BaseURL;
import com.example.coursemanagement.Adapter.UserViewAdapter;
import com.example.coursemanagement.Entity.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ManageUsersActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private String URL;
    API api;
    private Button listUsers;
    private List<User> userList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_users);
        BaseURL baseURL = new BaseURL();
        URL = baseURL.APIBaseURL();
        recyclerView = findViewById(R.id.manage_user_recycler);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        api = retrofit.create(API.class);

        ListUsers();
    }

    private void ListUsers() {
        try {
            Call<List<User>> call = api.getAllUsers();

            call.enqueue(new Callback<List<User>>() {
                @Override
                public void onResponse(Call<List<User>> call, Response<List<User>> response) {

                    userList = response.body();
                    UserViewAdapter userViewAdapter = new UserViewAdapter(ManageUsersActivity.this, userList);
                    recyclerView.setAdapter(userViewAdapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(ManageUsersActivity.this));
                }

                @Override
                public void onFailure(Call<List<User>> call, Throwable t) {
                    Toast.makeText(ManageUsersActivity.this, "An Error Has Occurred!", Toast.LENGTH_LONG).show();
                }
            });
        } catch (Exception e) {
            Toast.makeText(ManageUsersActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}