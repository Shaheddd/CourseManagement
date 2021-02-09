package com.example.coursemanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.coursemanagement.Administrator.AdministratorActivity;

public class TeacherHomeActivity extends AppCompatActivity {

    private Button listMyBatchesButton, manageMyExamsButton, logoutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_home);

        listMyBatchesButton = findViewById(R.id.listMyBatches_button);
        manageMyExamsButton = findViewById(R.id.manageMyExams_button);
        logoutButton = findViewById(R.id.logout_button);

        listMyBatchesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), ManageBatchesForTeacherActivity.class);
                startActivity(intent);
            }
        });

        manageMyExamsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), ManageExamsForTeacherActivity.class);
                startActivity(intent);
            }
        });

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TeacherHomeActivity.this, LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            }
        });

    }
}