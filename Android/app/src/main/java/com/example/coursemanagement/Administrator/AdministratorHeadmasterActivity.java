package com.example.coursemanagement.Administrator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.coursemanagement.AdministratorHomeActivity;
import com.example.coursemanagement.LoginActivity;
import com.example.coursemanagement.ManageHeadmasterActivity;
import com.example.coursemanagement.R;
import com.example.coursemanagement.RegisterHeadmasterActivity;

public class AdministratorHeadmasterActivity extends AppCompatActivity {

    Button registerHeadmasterButton, viewHeadmasterButton, logoutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administrator_headmaster);

        registerHeadmasterButton = findViewById(R.id.registerHeadmaster_button);
        viewHeadmasterButton = findViewById(R.id.viewHeadmasters_button);
        logoutButton = findViewById(R.id.logout_button);

        registerHeadmasterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), RegisterHeadmasterActivity.class);
                startActivity(intent);
            }
        });

        viewHeadmasterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), ManageHeadmasterActivity.class);
                startActivity(intent);
            }
        });

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdministratorHeadmasterActivity.this, LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            }
        });
    }
}