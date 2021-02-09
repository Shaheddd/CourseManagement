package com.example.coursemanagement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.BiometricManager;
import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.coursemanagement.API.API;
import com.example.coursemanagement.API.BaseURL;
import com.example.coursemanagement.DialogBox.DialogBox;
import com.example.coursemanagement.Entity.Login;

import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class LoginActivity extends AppCompatActivity {

    Button login, loginWithBiometrics;
    EditText username, password;
    API api;
    private String URL;
    public SharedPreferences sharedPreferences;
    ImageView fingerprint;
//    CheckBox checkBox;

    String rememberMePreferences = "rememberMePreferences";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login = findViewById(R.id.login_button);
        username = findViewById(R.id.login_username);
        password = findViewById(R.id.login_password_input);
        loginWithBiometrics = findViewById(R.id.loginWithAuthentication_button);
//        checkBox = findViewById(R.id.remember);

        sharedPreferences = getSharedPreferences("MyData", Context.MODE_PRIVATE);

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

        BiometricManager biometricManager = BiometricManager.from(this);
        switch (biometricManager.canAuthenticate()) {

            case BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE:
                fingerprint.setVisibility(View.GONE);
                break;

            case BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE:
                fingerprint.setVisibility(View.GONE);
                break;

            case BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED:
                fingerprint.setVisibility(View.GONE);
                break;
        }

//        sharedPreferences = getSharedPreferences(rememberMePreferences, 0);
//        boolean rememberMe = sharedPreferences.getBoolean("rememberMe", false);
//
//        if(rememberMe == true) {
//            String login = sharedPreferences.getString("login", null);
//            String userPassword = sharedPreferences.getString("password", null);
//
//            if(login != null && userPassword != null) {
//                rememberLogin(login,userPassword);
//            }
//        }

        Executor executor = ContextCompat.getMainExecutor(this);

        BiometricPrompt biometricPrompt = new BiometricPrompt(LoginActivity.this, executor,
                new BiometricPrompt.AuthenticationCallback() {
                    @Override
                    public void onAuthenticationError(int errorCode, @NonNull CharSequence errString) {
                        super.onAuthenticationError(errorCode, errString);
                    }

                    @Override
                    public void onAuthenticationSucceeded(@NonNull BiometricPrompt.AuthenticationResult result) {
                        super.onAuthenticationSucceeded(result);
                        Login();
                    }

                    @Override
                    public void onAuthenticationFailed() {
                        super.onAuthenticationFailed();
                    }
                });

        loginWithBiometrics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BiometricPrompt.PromptInfo promptInfo = new BiometricPrompt.PromptInfo.Builder()
                        .setTitle("Access")
                        .setDescription("Use Fingerprint to Access")
                        .setDeviceCredentialAllowed(true)
                        .build();
                biometricPrompt.authenticate(promptInfo);

                if (TextUtils.isEmpty(username.getText().toString()) || TextUtils.isEmpty(password.getText().toString())) {
                    new DialogBox(LoginActivity.this, "Please Enter In The Required Fields!");
                }
                    else if(biometricPrompt.equals("true")) {
                        Login();
                }
            }
        });


        login.setOnClickListener(new View.OnClickListener() {
            @Override
           public void onClick(View v) {

//                boolean isChecked =  checkBox.isChecked();
                if (TextUtils.isEmpty(username.getText().toString()) || TextUtils.isEmpty(password.getText().toString())) {
                    new DialogBox(LoginActivity.this, "Please Enter In The Required Fields!");
                } else {
//                    if(isChecked) {
//                        saveLoginDetails();
//                    } else {
//                        deleteLoginDetails();
//                    }
                       Login();
                    }
          }
        });
    }

    private void rememberLogin( String username,String password) {

        try {
            com.example.coursemanagement.Entity.Login login = new Login(username, password);

            Call<String> call = api.userLogin(login);

            call.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    String check = response.body();

                    String[] data = check.split(" ");

                    SharedPreferences.Editor editor = sharedPreferences.edit();

                    if (data[0].equals("Invalid")) {
                        new DialogBox(LoginActivity.this, "Login Credentials Are Incorrect!");
                    } else if (data[0].equals("Administrator")) {
                        editor.putInt("accountID", Integer.parseInt(data[1]));
                        editor.putString("role", data[0]);
                        editor.putString("username", data[2]);
                        editor.commit();
                        Intent intent = new Intent(getBaseContext(), AdministratorHomeActivity.class);
                        startActivity(intent);
                    } else if (data[0].equals("Headmaster")) {
                        editor.putInt("accountID", Integer.parseInt(data[1]));
                        editor.putString("role", data[0]);
                        editor.putString("username", data[2]);
                        editor.commit();
                        Intent intent = new Intent(getBaseContext(), HeadmasterHomeActivity.class);
                        startActivity(intent);
                    } else if (data[0].equals("Teacher")) {
                        editor.putInt("accountID", Integer.parseInt(data[1]));
                        editor.putString("role", data[0]);
                        editor.putString("username", data[2]);
                        editor.commit();
                        Intent intent = new Intent(getBaseContext(), TeacherHomeActivity.class);
                        startActivity(intent);
                    } else if (data[0].equals("Student")) {
                        editor.putInt("accountID", Integer.parseInt(data[1]));
                        editor.putString("role", data[0]);
                        editor.putString("username", data[2]);
                        editor.commit();
                        Intent intent = new Intent(getBaseContext(), StudentHomeActivity.class);
                        startActivity(intent);
                    }
                }
                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    new DialogBox(LoginActivity.this, "An Error Has Occurred!");
                }
            });
        } catch (Exception e) {
            new DialogBox(LoginActivity.this, "An Error Has Occurred!");
        }
    }

    private void saveLoginDetails() {
        username = findViewById(R.id.login_username);
        password = findViewById(R.id.login_password_input);
        String login = username.getText().toString();
        String userPassword = password.getText().toString();

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("rememberMe", true);
        editor.putString("login", login);
        editor.putString("password", userPassword);
        editor.commit();
    }

    private void deleteLoginDetails() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("rememberMe", false);
        editor.putString("login","");
        editor.putString("password","");
        editor.commit();
    }

    private void Login() {
        try {
            com.example.coursemanagement.Entity.Login login = new Login(username.getText().toString().trim(), password.getText().toString().trim());

            Call<String> call = api.userLogin(login);

            call.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    String check = response.body();

                    String[] data = check.split(" ");

                    SharedPreferences.Editor editor = sharedPreferences.edit();

                    if (data[0].equals("Invalid")) {
                        new DialogBox(LoginActivity.this, "Login Credentials Are Incorrect!");
                    } else if (data[0].equals("Administrator")) {
                        editor.putInt("accountID", Integer.parseInt(data[1]));
                        editor.putString("role", data[0]);
                        editor.putString("username", data[2]);
                        editor.commit();
                        Intent intent = new Intent(getBaseContext(), AdministratorHomeActivity.class);
                        startActivity(intent);
                        } else if (data[0].equals("Headmaster")) {
                            editor.putInt("accountID", Integer.parseInt(data[1]));
                            editor.putString("role", data[0]);
                            editor.putString("username", data[2]);
                            editor.commit();
                            Intent intent = new Intent(getBaseContext(), HeadmasterHomeActivity.class);
                            startActivity(intent);
                        } else if (data[0].equals("Teacher")) {
                            editor.putInt("accountID", Integer.parseInt(data[1]));
                            editor.putString("role", data[0]);
                            editor.putString("username", data[2]);
                            editor.commit();
                            Intent intent = new Intent(getBaseContext(), TeacherHomeActivity.class);
                            startActivity(intent);
                        } else if (data[0].equals("Student")) {
                            editor.putInt("accountID", Integer.parseInt(data[1]));
                            editor.putString("role", data[0]);
                            editor.putString("username", data[2]);
                            editor.commit();
                            Intent intent = new Intent(getBaseContext(), StudentHomeActivity.class);
                            startActivity(intent);
                    } else {
                        new DialogBox(LoginActivity.this, "An Error Has Occurred!");
                    }
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    new DialogBox(LoginActivity.this, "An Error Has Occurred!");
                }
            });
        } catch (Exception e) {
            new DialogBox(LoginActivity.this, "An Error Has Occurred!");
        }
    }
}

