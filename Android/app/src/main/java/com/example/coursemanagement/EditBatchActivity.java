package com.example.coursemanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.coursemanagement.API.API;
import com.example.coursemanagement.API.BaseURL;
import com.example.coursemanagement.DialogBox.DialogBox;
import com.example.coursemanagement.Entity.Batch;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class EditBatchActivity extends AppCompatActivity {

    API api;
    private String URL;
    private int batch_ID;
    private EditText batchCode;
    private TextView id;
    private Button update;
    private Batch myBatch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_batch);

        id = findViewById(R.id.view_batch_ID);
        batchCode = findViewById(R.id.edit_batch_code);
        update = findViewById(R.id.update_batch_btn);

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

        batch_ID = Integer.parseInt(getIntent().getStringExtra("batch_ID"));

        LoadBatch();

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(batchCode.getText().toString())) {
                    new DialogBox(EditBatchActivity.this, "Please Fill In All Required Fields!");
                } else {
                    updateBatch();
                }
            }
        });
    }

    private void LoadBatch() {
        try {
            Call<Batch> call = api.viewBatch(batch_ID);

            call.enqueue(new Callback<Batch>() {
                @Override
                public void onResponse(Call<Batch> call, Response<Batch> response) {
                    Batch batch = response.body();
                    myBatch = batch;
                    id.setText(Integer.toString(batch.getBatchID()));
                    batchCode.setText(batch.getBatchCode());
                }

                @Override
                public void onFailure(Call<Batch> call, Throwable t) {
                    new DialogBox(EditBatchActivity.this, "An Error Has Occurred!");
                }
            });
        } catch (Exception e) {
            Toast.makeText(EditBatchActivity.this, "An Error Has Occurred!", Toast.LENGTH_LONG).show();
        }
    }

    private void updateBatch() {
        try {
            myBatch.setBatchID(Integer.parseInt(id.getText().toString().trim()));
            myBatch.setBatchCode(batchCode.getText().toString().trim());
            Call<String> call = api.updateBatch(myBatch);

            call.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    new DialogBox(EditBatchActivity.this, "Batch Updated!");
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    new DialogBox(EditBatchActivity.this, "An Error Has Occurred!");
                }
            });

        } catch (Exception e) {
            new DialogBox(EditBatchActivity.this, "An Error Has Occurred!");
        }
    }
}