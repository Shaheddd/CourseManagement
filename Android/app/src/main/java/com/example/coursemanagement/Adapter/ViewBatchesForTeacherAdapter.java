package com.example.coursemanagement.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coursemanagement.API.API;
import com.example.coursemanagement.API.BaseURL;
import com.example.coursemanagement.Entity.Batch;
import com.example.coursemanagement.R;

import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class ViewBatchesForTeacherAdapter extends RecyclerView.Adapter<ViewBatchesForTeacherAdapter.ViewHolder> {

    private Context context;
    private List<Batch> batchList;
    API api;
    private String URL;

    public ViewBatchesForTeacherAdapter(Context context, List<Batch> batchList) {
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
    public ViewBatchesForTeacherAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(this.context);
        View view = layoutInflater.inflate(R.layout.batch_teacher_layout, parent, false);

        return new ViewBatchesForTeacherAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewBatchesForTeacherAdapter.ViewHolder holder, int position) {
        Batch batch = batchList.get(position);
        holder.id.setText("Batch ID :" + batch.getBatchID());
//        holder.name.setText(batch.getTeacher().getTeacherFirstName() + " " + batch.getTeacher().getTeacherLastName());
//        holder.course.setText(batch.getCourse().toString());
        holder.code.setText(batch.getBatchCode());
    }

    @Override
    public int getItemCount() {
        return batchList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView id, name, course, code;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.manage_teacher_batch_batch_id);
//            name = itemView.findViewById(R.id.manage_teacher_batch_name);
//            course = itemView.findViewById(R.id.manage_teacher_batch_course);
            code = itemView.findViewById(R.id.manage_teacher_batch_batch_code);
        }
    }
}
