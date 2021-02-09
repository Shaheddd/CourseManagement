package com.example.coursemanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.coursemanagement.Headmaster.HeadmasterActivity;
import com.example.coursemanagement.Headmaster.HeadmasterBatchActivity;

public class HeadmasterHomeActivity extends AppCompatActivity {

    private Button listAllCoursesButton, listAllTeachersButton, listAllStudentsButton, listAllBatchesButton, logoutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_headmaster_home);

        listAllCoursesButton = findViewById(R.id.listAllCourses_button);
        listAllTeachersButton = findViewById(R.id.listAllTeachers_button);
        listAllStudentsButton = findViewById(R.id.listAllStudents_button);
        listAllBatchesButton = findViewById(R.id.listAllBatches_button);
        logoutButton = findViewById(R.id.logout_button);

        listAllCoursesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), HeadmasterActivity.class);
                startActivity(intent);
            }
        });

        listAllTeachersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), ManageTeacherActivity.class);
                startActivity(intent);
            }
        });

        listAllStudentsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), ManageStudentActivity.class);
                startActivity(intent);
            }
        });

        listAllBatchesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), HeadmasterBatchActivity.class);
                startActivity(intent);
            }
        });

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HeadmasterHomeActivity.this, LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            }
        });

    }
}