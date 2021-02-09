package com.example.coursemanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.coursemanagement.API.API;
import com.example.coursemanagement.API.BaseURL;
import com.example.coursemanagement.DialogBox.DialogBox;
import com.example.coursemanagement.Entity.User;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class EditHeadmasterActivity extends AppCompatActivity {

    API api;
    private String URL;
    private int user_ID;
    private EditText headmasterFirstName, headmasterLastName, headmasterAddress, headmasterPhoneNumber;
    private TextView id;
    private Button update;
    private User myUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_headmaster);

        id = findViewById(R.id.view_user_ID);
        headmasterFirstName = findViewById(R.id.edit_headmaster_firstName);
        headmasterLastName = findViewById(R.id.edit_headmaster_lastName);
        headmasterAddress = findViewById(R.id.edit_headmaster_address);
        headmasterPhoneNumber = findViewById(R.id.edit_headmaster_phone_number);
        update = findViewById(R.id.update_user_btn);

        BaseURL baseURL = new BaseURL();
        URL = baseURL.APIBaseURL();
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .client(httpClient.build())
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        api = retrofit.create(API.class);

        user_ID = Integer.parseInt(getIntent().getStringExtra("user_ID"));

        LoadHeadmaster();

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(headmasterFirstName.getText().toString()) || TextUtils.isEmpty(headmasterLastName.getText().toString())
                || TextUtils.isEmpty(headmasterAddress.getText().toString()) || TextUtils.isEmpty(headmasterPhoneNumber.getText().toString())) {
                    new DialogBox(EditHeadmasterActivity.this, "Please Fill In All Empty Fields!");
                } else {
                    updateHeadmaster();
                }
            }
        });
    }

    private void LoadHeadmaster() {
        try {
        Call<User> call = api.getHeadmaster(user_ID);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User user = response.body();
                myUser = user;
                id.setText(Integer.toString(user.getUserID()));
                headmasterFirstName.setText(user.getFirstName());
                headmasterLastName.setText(user.getLastName());
                headmasterAddress.setText(user.getAddress());
                headmasterPhoneNumber.setText(user.getPhoneNumber());
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                new DialogBox(EditHeadmasterActivity.this, "An Error Has Occurred!");
            }
        });
    } catch (Exception e) {
            Toast.makeText(EditHeadmasterActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private void updateHeadmaster() {
        try {
            myUser.setUserID(Integer.parseInt(id.getText().toString()));
            myUser.setFirstName(headmasterFirstName.getText().toString());
            myUser.setLastName(headmasterLastName.getText().toString());
            myUser.setAddress(headmasterAddress.getText().toString());
            myUser.setPhoneNumber(headmasterPhoneNumber.getText().toString());
            Call<String> call = api.updateHeadmaster(myUser);

            call.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    new DialogBox(EditHeadmasterActivity.this, "Headmaster Updated!");
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    new DialogBox(EditHeadmasterActivity.this, "An Error Has Occurred!");
                }
            });
        } catch (Exception e) {
            new DialogBox(EditHeadmasterActivity.this, "An Error Has Occurred!");
        }
    }
}