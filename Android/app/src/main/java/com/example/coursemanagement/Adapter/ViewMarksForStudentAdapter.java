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
import com.example.coursemanagement.Entity.Mark;
import com.example.coursemanagement.R;

import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class ViewMarksForStudentAdapter extends RecyclerView.Adapter<ViewMarksForStudentAdapter.ViewHolder> {

    private Context context;
    private List<Mark> markList;
    API api;
    private String URL;

    public ViewMarksForStudentAdapter(Context context, List<Mark> markList) {
        this.context = context;
        this.markList = markList;

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
    public ViewMarksForStudentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = android.view.LayoutInflater.from(this.context);
        View view = layoutInflater.inflate(R.layout.mark_student_layout, parent, false);

        return new ViewMarksForStudentAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewMarksForStudentAdapter.ViewHolder holder, int position) {
        Mark mark = markList.get(position);
        holder.id.setText("Mark ID : " + mark.getMarkID());
        holder.mark.setText("Marks : " + mark.getMarks());
        holder.grade.setText(mark.getGrade());
    }

    @Override
    public int getItemCount() {
        return markList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView id, mark, grade;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            id = itemView.findViewById(R.id.manage_mark_student_markID);
            mark = itemView.findViewById(R.id.manage_mark_student_marks);
            grade = itemView.findViewById(R.id.manage_mark_student_grade);
        }
    }
}
