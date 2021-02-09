package com.example.coursemanagement.Administrator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.coursemanagement.LoginActivity;
import com.example.coursemanagement.ManageStudentActivity;
import com.example.coursemanagement.R;
import com.example.coursemanagement.RegisterStudentActivity;

public class AdministratorStudentActivity extends AppCompatActivity {

    Button registerStudentButton, viewStudentsButton, logoutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administrator_student);

        registerStudentButton = findViewById(R.id.registerStudent_button);
        viewStudentsButton = findViewById(R.id.viewStudents_button);
        logoutButton = findViewById(R.id.logout_button);

        registerStudentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), RegisterStudentActivity.class);
                startActivity(intent);
            }
        });

        viewStudentsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), ManageStudentActivity.class);
                startActivity(intent);
            }
        });

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdministratorStudentActivity.this, LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            }
        });
    }
}