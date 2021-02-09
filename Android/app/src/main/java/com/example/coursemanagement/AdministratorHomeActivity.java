package com.example.coursemanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.coursemanagement.Administrator.AdministratorActivity;
import com.example.coursemanagement.Administrator.AdministratorHeadmasterActivity;
import com.example.coursemanagement.Administrator.AdministratorStudentActivity;
import com.example.coursemanagement.Administrator.AdministratorTeacherActivity;

public class AdministratorHomeActivity extends AppCompatActivity {

    Button manageUsersButton, manageHeadmastersButton, manageStudentsButton, manageTeachersButton, logoutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administrator_home);

        manageUsersButton = findViewById(R.id.manageUsers_button);
        manageHeadmastersButton = findViewById(R.id.manageHeadmasters_button);
        manageStudentsButton = findViewById(R.id.manageStudents_button);
        manageTeachersButton = findViewById(R.id.manageTeachers_button);
        logoutButton = findViewById(R.id.logout_button);

        manageUsersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), AdministratorActivity.class);
                startActivity(intent);
            }
        });

        manageHeadmastersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), AdministratorHeadmasterActivity.class);
                startActivity(intent);
            }
        });

        manageStudentsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), AdministratorStudentActivity.class);
                startActivity(intent);
            }
        });

        manageTeachersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), AdministratorTeacherActivity.class);
                startActivity(intent);
            }
        });

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdministratorHomeActivity.this, LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            }
        });
    }
}