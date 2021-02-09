package com.example.coursemanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.coursemanagement.API.API;
import com.example.coursemanagement.API.BaseURL;
import com.example.coursemanagement.DataTransferObject.BatchRegistration;
import com.example.coursemanagement.DialogBox.DialogBox;
import com.example.coursemanagement.Entity.Batch;

import org.w3c.dom.Text;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RegisterBatchActivity extends AppCompatActivity {

    Button register_batch_button;
    TextView batchCode, teacherFirstName, teacherLastName, teacherID, courseID, courseName, courseDescription, courseType;
    API api;
    private String URL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_batch);

        teacherFirstName = findViewById(R.id.register_first_name_input);
        teacherLastName = findViewById(R.id.register_last_name_input);
        teacherID = findViewById(R.id.register_teacher_id_input);
        courseID = findViewById(R.id.register_course_id_input);
        courseName = findViewById(R.id.register_courseName_input);
        courseDescription = findViewById(R.id.register_courseDescription_input);
        courseType = findViewById(R.id.register_courseType_input);
        batchCode = findViewById(R.id.register_batch_code_input);
        register_batch_button = findViewById(R.id.register_button);

        BaseURL baseURL = new BaseURL();
        URL = baseURL.APIBaseURL();
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder()
                .callTimeout(2, TimeUnit.MINUTES)
                .connectTimeout(20, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .client(httpClient.build())
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        api = retrofit.create(API.class);

        register_batch_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(teacherFirstName.getText().toString()) || TextUtils.isEmpty(teacherLastName.getText().toString())
                        || TextUtils.isEmpty(teacherID.getText().toString()) || TextUtils.isEmpty(courseID.getText().toString())
                        || TextUtils.isEmpty(courseName.getText().toString()) || TextUtils.isEmpty(courseDescription.getText().toString())
                        || TextUtils.isEmpty(courseType.getText().toString()) || TextUtils.isEmpty(batchCode.getText().toString())) {
                    new DialogBox(RegisterBatchActivity.this, "Please Enter All Required Fields!");
                } else {
                    registerBatch();
                }
            }
        });
    }

    private void registerBatch() {
        try {
            BatchRegistration batchRegistration = new BatchRegistration();
            batchRegistration.setTeacherFirstName(teacherFirstName.getText().toString().trim());
            batchRegistration.setTeacherLastName(teacherLastName.getText().toString().trim());
            batchRegistration.setTeacherID(Integer.parseInt(teacherID.getText().toString().trim()));
            batchRegistration.setCourseID(Integer.parseInt(courseID.getText().toString().trim()));
            batchRegistration.setCourseName(courseName.getText().toString().trim());
            batchRegistration.setCourseDescription(courseDescription.getText().toString().trim());
            batchRegistration.setCourseType(courseType.getText().toString().trim());
            batchRegistration.setBatchCode(batchCode.getText().toString().trim());
            Call<String> call = api.registerBatch(batchRegistration);

            call.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    String check = response.body();
                    new DialogBox(RegisterBatchActivity.this, check);
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    new DialogBox(RegisterBatchActivity.this, "An Error Has Occurred!");
                }
            });
        } catch (Exception e) {
            new DialogBox(RegisterBatchActivity.this, "An Error Has Occurred");
        }
    }

}