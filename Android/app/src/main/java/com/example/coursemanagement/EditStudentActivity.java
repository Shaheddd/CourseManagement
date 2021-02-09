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

import org.w3c.dom.Text;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class EditStudentActivity extends AppCompatActivity {

    API api;
    private String URL;
    private int user_ID;
    private EditText studentFirstName, studentLastName, studentAddress, studentPhoneNumber;
    private TextView id;
    private Button update;
    private User myUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_student);

        id = findViewById(R.id.view_user_ID);
        studentFirstName = findViewById(R.id.edit_student_firstName);
        studentLastName = findViewById(R.id.edit_student_lastName);
        studentAddress = findViewById(R.id.edit_student_address);
        studentPhoneNumber = findViewById(R.id.edit_student_phone_number);
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

        LoadStudent();

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(studentFirstName.getText().toString()) || TextUtils.isEmpty(studentLastName.getText().toString())
                || TextUtils.isEmpty(studentAddress.getText().toString()) || TextUtils.isEmpty(studentPhoneNumber.getText().toString())) {
                    new DialogBox(EditStudentActivity.this, "Please Fill In All Empty Fields!");
                } else {
                    updateStudent();
                }
            }
        });
    }

    private void LoadStudent() {
        try {
            Call<User> call = api.getStudent(user_ID);

            call.enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    User user = response.body();
                    myUser = user;
                    id.setText(Integer.toString(user.getUserID()));
                    studentFirstName.setText(user.getFirstName());
                    studentLastName.setText(user.getLastName());
                    studentAddress.setText(user.getAddress());
                    studentPhoneNumber.setText(user.getPhoneNumber());
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    new DialogBox(EditStudentActivity.this, "An Error Has Occurred!");
                }
            });
        } catch (Exception e) {
            new DialogBox(EditStudentActivity.this, "An Error Has Occurred!");
        }
    }

    private void updateStudent() {
        try {
            myUser.setUserID(Integer.parseInt(id.getText().toString()));
            myUser.setFirstName(studentFirstName.getText().toString());
            myUser.setLastName(studentLastName.getText().toString());
            myUser.setAddress(studentAddress.getText().toString());
            myUser.setPhoneNumber(studentPhoneNumber.getText().toString());
            Call<String> call = api.updateStudent(myUser);

            call.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    new DialogBox(EditStudentActivity.this, "Student Updated!");
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    new DialogBox(EditStudentActivity.this, "An Error Has Occurred!");
                }
            });
        } catch (Exception e) {
            new DialogBox(EditStudentActivity.this, "An Error Has Occurred!");
        }
    }
}