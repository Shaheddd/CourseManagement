package com.example.coursemanagement.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coursemanagement.API.API;
import com.example.coursemanagement.API.BaseURL;
import com.example.coursemanagement.DialogBox.DialogBox;
import com.example.coursemanagement.EditBatchActivity;
import com.example.coursemanagement.EditCourseActivity;
import com.example.coursemanagement.Entity.Batch;
import com.example.coursemanagement.R;

import org.w3c.dom.Text;

import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class BatchViewAdapter extends RecyclerView.Adapter<BatchViewAdapter.ViewHolder> {

    private Context context;
    private List<Batch> batchList;
    API api;
    private String URL;

    public BatchViewAdapter(Context context, List<Batch> batchList) {
        this.context = context;
        this.batchList = batchList;

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
    }

    @NonNull
    @Override
    public BatchViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(this.context);
        View view = layoutInflater.inflate(R.layout.batch_layout, parent, false);

        return new BatchViewAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BatchViewAdapter.ViewHolder holder, int position) {
        Batch batch = batchList.get(position);
        holder.id.setText("Batch ID : " + batch.getBatchID());
        holder.code.setText("Batch Code : " + batch.getBatchCode());

        holder.editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent intent = new Intent(context, EditBatchActivity.class);
                intent.putExtra("batch_ID", Integer.toString(batch.getBatchID()));
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return batchList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView id, code;
        Button editButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            id = itemView.findViewById(R.id.manage_batch_ID);
            code = itemView.findViewById(R.id.manage_batch_batchCode);
            editButton = itemView.findViewById(R.id.edit_batch);
        }
    }
}
