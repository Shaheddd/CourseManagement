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
import com.example.coursemanagement.EditHeadmasterActivity;
import com.example.coursemanagement.EditStudentActivity;
import com.example.coursemanagement.Entity.Student;
import com.example.coursemanagement.R;

import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class StudentViewAdapter extends RecyclerView.Adapter<StudentViewAdapter.ViewHolder> {

    private Context context;
    private List<Student> studentList;
    API api;
    private String URL;

    public StudentViewAdapter(Context context, List<Student> studentList) {
        this.context = context;
        this.studentList = studentList;

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
    public StudentViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(this.context);
        View view = inflater.inflate(R.layout.student_layout, parent, false);

        return new StudentViewAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentViewAdapter.ViewHolder holder, int position) {
        Student student = studentList.get(position);
        holder.id.setText("Student ID : " + student.getStudentID());
        holder.name.setText(student.getStudentFirstName() + " " + student.getStudentLastName());
        holder.address.setText(student.getStudentAddress());
        holder.phoneNumber.setText(student.getStudentPhoneNumber());
        holder.type.setText("Student");

        holder.editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent intent = new Intent(context, EditStudentActivity.class);
                intent.putExtra("user_ID", Integer.toString(student.getUserID()));
                context.startActivity(intent);
            }
        });

        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Call<String> call = api.deleteStudent(student.getUserID());

                    call.enqueue(new Callback<String>() {
                        @SuppressLint("ResourceAsColor")
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            String message = response.body();
                            new DialogBox(context, message);
                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {
                            new DialogBox(context, "An Error Has Occurred!");
                        }
                    });
                } catch (Exception e) {
                    new DialogBox(context, "An Error Has Occurred!");
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return studentList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView id, name, address, phoneNumber, type;
        Button editButton, deleteButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.manage_student_id);
            name = itemView.findViewById(R.id.manage_student_name);
            address = itemView.findViewById(R.id.manage_student_address);
            phoneNumber = itemView.findViewById(R.id.manage_student_phone_number);
            type = itemView.findViewById(R.id.manage_student_type);
            editButton = itemView.findViewById(R.id.edit_student);
            deleteButton = itemView.findViewById(R.id.delete_student);
        }
    }
}
