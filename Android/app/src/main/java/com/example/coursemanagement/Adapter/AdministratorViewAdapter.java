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
import com.example.coursemanagement.EditAdministratorActivity;
import com.example.coursemanagement.Entity.Administrator;
import com.example.coursemanagement.R;

import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class AdministratorViewAdapter extends RecyclerView.Adapter<AdministratorViewAdapter.ViewHolder> {

    private Context context;
    private List<Administrator> administratorList;
    API api;
    private String URL;

    public AdministratorViewAdapter(Context context, List<Administrator> administratorList) {

        this.context = context;
        this.administratorList = administratorList;

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
    public AdministratorViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(this.context);
        View view = inflater.inflate(R.layout.adminstrator_layout, parent, false);

        return new AdministratorViewAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdministratorViewAdapter.ViewHolder holder, int position) {

        Administrator administrator = administratorList.get(position);
        holder.id.setText("ID : " + administrator.getAdministratorID());
        holder.name.setText(administrator.getAdministratorFirstName() + " " + administrator.getAdministratorLastName());
        holder.address.setText(administrator.getAdministratorAddress());
        holder.phoneNumber.setText(administrator.getAdministratorPhoneNumber());
        holder.type.setText("Administrator");

        holder.editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent intent = new Intent(context, EditAdministratorActivity.class);
                intent.putExtra("user_ID", Integer.toString(administrator.getUserID()));
                context.startActivity(intent);
            }
        });

        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Call<String> call = api.deleteUser(administrator.getUserID());

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
        return administratorList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView id, name, address, phoneNumber, type;
        Button editButton, deleteButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.manage_admin_id);
            name = itemView.findViewById(R.id.manage_admin_name);
            address = itemView.findViewById(R.id.manage_admin_address);
            phoneNumber = itemView.findViewById(R.id.manage_admin_phone_number);
            type = itemView.findViewById(R.id.manage_admin_type);
            editButton = itemView.findViewById(R.id.edit_administrator);
            deleteButton = itemView.findViewById(R.id.delete_administrator);
        }
    }
}