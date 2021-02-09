package com.example.coursemanagement.Headmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.coursemanagement.Administrator.AdministratorActivity;
import com.example.coursemanagement.LoginActivity;
import com.example.coursemanagement.ManageBatchActivity;
import com.example.coursemanagement.ManageStudentActivity;
import com.example.coursemanagement.R;
import com.example.coursemanagement.RegisterBatchActivity;

public class HeadmasterBatchActivity extends AppCompatActivity {

    Button registerBatchButton, viewAllBatchesButton, logoutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_headmaster_batch);

        registerBatchButton = findViewById(R.id.registerBatch_button);
        viewAllBatchesButton = findViewById(R.id.viewBatches_button);
        logoutButton = findViewById(R.id.logout_button);

        registerBatchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), RegisterBatchActivity.class);
                startActivity(intent);
            }
        });

        viewAllBatchesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), ManageBatchActivity.class);
                startActivity(intent);
            }
        });

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HeadmasterBatchActivity.this, LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            }
        });
    }
}