package com.example.coursemanagement.Administrator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.coursemanagement.LoginActivity;
import com.example.coursemanagement.ManageTeacherActivity;
import com.example.coursemanagement.R;
import com.example.coursemanagement.RegisterTeacherActivity;

public class AdministratorTeacherActivity extends AppCompatActivity {

    Button registerTeacherButton, viewTeachersButton, logoutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administrator_teacher);

        registerTeacherButton = findViewById(R.id.registerTeacher_button);
        viewTeachersButton = findViewById(R.id.viewTeachers_button);
        logoutButton = findViewById(R.id.logout_button);

        registerTeacherButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), RegisterTeacherActivity.class);
                startActivity(intent);
            }
        });

        viewTeachersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), ManageTeacherActivity.class);
                startActivity(intent);
            }
        });

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdministratorTeacherActivity.this, LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            }
        });
    }
}