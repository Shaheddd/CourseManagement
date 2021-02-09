package com.example.coursemanagement.Headmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.coursemanagement.Administrator.AdministratorActivity;
import com.example.coursemanagement.LoginActivity;
import com.example.coursemanagement.ManageCourseActivity;
import com.example.coursemanagement.R;
import com.example.coursemanagement.RegisterCourseActivity;

public class HeadmasterActivity extends AppCompatActivity {

    Button registerCourse, viewAllCoursesButton, logoutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_headmaster);

        registerCourse = findViewById(R.id.registerCourse_button);
        viewAllCoursesButton = findViewById(R.id.viewCourses_button);
        logoutButton = findViewById(R.id.logout_button);

        registerCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), RegisterCourseActivity.class);
                startActivity(intent);
            }
        });

        viewAllCoursesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), ManageCourseActivity.class);
                startActivity(intent);
            }
        });

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HeadmasterActivity.this, LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            }
        });
    }
}