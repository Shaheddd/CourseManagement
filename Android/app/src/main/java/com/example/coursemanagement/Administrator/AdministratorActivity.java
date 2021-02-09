package com.example.coursemanagement.Administrator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.coursemanagement.LoginActivity;
import com.example.coursemanagement.ManageAdministratorActivity;
import com.example.coursemanagement.ManageUsersActivity;
import com.example.coursemanagement.R;
import com.example.coursemanagement.RegisterAdministratorActivity;

public class AdministratorActivity extends AppCompatActivity {

    Button listAllUsersButton, addNewAdministratorButton, listAllAdministratorsButton, logoutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administrator);

        listAllUsersButton = findViewById(R.id.listAllUsers_button);
        addNewAdministratorButton = findViewById(R.id.addNewAdmin_button);
        listAllAdministratorsButton = findViewById(R.id.listAllAdministrators_button);
        logoutButton = findViewById(R.id.logout_button);

        listAllUsersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), ManageUsersActivity.class);
                startActivity(intent);
            }
        });

        addNewAdministratorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), RegisterAdministratorActivity.class);
                startActivity(intent);
            }
        });

        listAllAdministratorsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), ManageAdministratorActivity.class);
                startActivity(intent);
            }
        });

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdministratorActivity.this, LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            }
        });

    }
}