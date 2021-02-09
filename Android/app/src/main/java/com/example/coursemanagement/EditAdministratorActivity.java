package com.example.coursemanagement;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.coursemanagement.API.API;
import com.example.coursemanagement.API.BaseURL;
import com.example.coursemanagement.DialogBox.DialogBox;
import com.example.coursemanagement.Entity.User;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class EditAdministratorActivity extends AppCompatActivity {

    API api;
    private String URL;
    private int user_ID;
    private EditText administratorFirstName, administratorLastName, administratorAddress, administratorPhoneNumber;
    private TextView id, status;
    private Button update;
//    private ImageView image;
//    private CardView card;
    private User myUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_administrator);

        id = findViewById(R.id.view_user_ID);
        administratorFirstName = findViewById(R.id.edit_administrator_firstName);
        administratorLastName = findViewById(R.id.edit_administrator_lastName);
        administratorAddress = findViewById(R.id.edit_administrator_address);
        administratorPhoneNumber = findViewById(R.id.edit_administrator_phone_number);
        update = findViewById(R.id.update_user_btn);

        BaseURL baseURL = new BaseURL();
        URL = baseURL.APIBaseURL();
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .client(httpClient.build())
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        api = retrofit.create(API.class);

        user_ID = Integer.parseInt(getIntent().getStringExtra("user_ID"));

        LoadAdministrator();

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(administratorFirstName.getText().toString()) || TextUtils.isEmpty(administratorLastName.getText().toString())
                || TextUtils.isEmpty(administratorAddress.getText().toString()) || TextUtils.isEmpty(administratorPhoneNumber.getText().toString())) {
                    new DialogBox(EditAdministratorActivity.this,"Please Fill In All The Empty Fields!");
                } else {
                    updateAdministrator();
                }
            }
        });
    }

    private void LoadAdministrator() {
        try {
            Call<User> call = api.getUser(user_ID);

            call.enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    User user = response.body();
                    myUser = user;
                    id.setText(Integer.toString(user.getUserID()));
                    administratorFirstName.setText(user.getFirstName());
                    administratorLastName.setText(user.getLastName());
                    administratorAddress.setText(user.getAddress());
                    administratorPhoneNumber.setText(user.getPhoneNumber());
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    new DialogBox(EditAdministratorActivity.this, "An Error Has Occurred!");
                }
            });
        } catch (Exception e) {
            Toast.makeText(EditAdministratorActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private void updateAdministrator() {
        try {
            myUser.setUserID(Integer.parseInt(id.getText().toString()));
            myUser.setFirstName(administratorFirstName.getText().toString());
            myUser.setLastName(administratorLastName.getText().toString());
            myUser.setAddress(administratorAddress.getText().toString());
            myUser.setPhoneNumber(administratorPhoneNumber.getText().toString());
            Call<String> call = api.updateUser(myUser);

            call.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    new DialogBox(EditAdministratorActivity.this, "Updated");
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    new DialogBox(EditAdministratorActivity.this, "An Error Has Occurred!");
                }
            });
        } catch (Exception e) {
            new DialogBox(EditAdministratorActivity.this, "An Error Has Occurred!");
        }
    }
}