package com.example.coursemanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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

public class EditTeacherActivity extends AppCompatActivity {

    API api;
    private String URL;
    private int user_ID;
    private EditText teacherFirstName, teacherLastName, teacherAddress, teacherPhoneNumber;
    private TextView id;
    private Button update;
    private User myUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_teacher);

        id = findViewById(R.id.view_user_ID);
        teacherFirstName = findViewById(R.id.edit_teacher_firstName);
        teacherLastName = findViewById(R.id.edit_teacher_lastName);
        teacherAddress = findViewById(R.id.edit_teacher_address);
        teacherPhoneNumber = findViewById(R.id.edit_teacher_phone_number);
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

        LoadTeacher();

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(teacherFirstName.getText().toString()) || TextUtils.isEmpty(teacherLastName.getText().toString())
                || TextUtils.isEmpty(teacherAddress.getText().toString()) || TextUtils.isEmpty(teacherPhoneNumber.getText().toString())) {
                    new DialogBox(EditTeacherActivity.this, "Please Fill In All Empty Fields!");
                } else {
                    updateTeacher();
                }
            }
        });
    }

    private void LoadTeacher() {
        try {
            Call<User> call = api.getTeacher(user_ID);

            call.enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    User user = response.body();
                    myUser = user;
                    id.setText(Integer.toString(user.getUserID()));
                    teacherFirstName.setText(user.getFirstName());
                    teacherLastName.setText(user.getLastName());
                    teacherAddress.setText(user.getAddress());
                    teacherPhoneNumber.setText(user.getPhoneNumber());
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    new DialogBox(EditTeacherActivity.this, "An Error Has Occurred!");
                }
            });
        } catch (Exception e) {
            new DialogBox(EditTeacherActivity.this, "An Error Has Occurred!");
        }
    }

    private void updateTeacher() {
        try {
            myUser.setUserID(Integer.parseInt(id.getText().toString()));
            myUser.setFirstName(teacherFirstName.getText().toString());
            myUser.setLastName(teacherLastName.getText().toString());
            myUser.setAddress(teacherAddress.getText().toString());
            myUser.setPhoneNumber(teacherPhoneNumber.getText().toString());
            Call<String> call = api.updateTeacher(myUser);

            call.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    new DialogBox(EditTeacherActivity.this, "Teacher Updated!");
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    new DialogBox(EditTeacherActivity.this, "An Error Has Occurred!");
                }
            });
        } catch (Exception e) {
            new DialogBox(EditTeacherActivity.this, "An Error Has Occurred!");
        }
    }
}