package com.example.coursemanagement.Adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coursemanagement.API.API;
import com.example.coursemanagement.API.BaseURL;
import com.example.coursemanagement.Entity.Exam;
import com.example.coursemanagement.R;

import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class ViewExamsForTeacherAdapter extends RecyclerView.Adapter<ViewExamsForTeacherAdapter.ViewHolder> {

    private String URL;
    API api;
    private List<Exam> examList;
    private Context context;

    public ViewExamsForTeacherAdapter(Context context, List<Exam> examList) {
        this.context = context;
        this.examList = examList;

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
    public ViewExamsForTeacherAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(this.context);
        View view = layoutInflater.inflate(R.layout.exam_teacher_layout, parent, false);

        return new ViewExamsForTeacherAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewExamsForTeacherAdapter.ViewHolder holder, int position) {
        Exam exam = examList.get(position);
        holder.id.setText("Exam ID : " + exam.getExamID());
        holder.name.setText(exam.getExamName());
        holder.type.setText(exam.getExamType());
        holder.description.setText(exam.getExamDescription());
    }

    @Override
    public int getItemCount() {
        return examList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView id, name, type, description;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            id = itemView.findViewById(R.id.manage_teacher_exam_examID);
            name = itemView.findViewById(R.id.manage_teacher_exam_examName);
            type = itemView.findViewById(R.id.manage_teacher_exam_examType);
            description = itemView.findViewById(R.id.manage_teacher_exam_examDescription);
        }
    }
}
