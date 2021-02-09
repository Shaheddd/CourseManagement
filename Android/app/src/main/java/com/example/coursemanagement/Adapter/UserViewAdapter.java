package com.example.coursemanagement.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coursemanagement.Entity.User;
import com.example.coursemanagement.R;

import java.util.List;

public class UserViewAdapter extends RecyclerView.Adapter<UserViewAdapter.ViewHolder> {

    private Context context;
    private List<User> userList;

    public UserViewAdapter(Context context, List<User> userList) {
        this.context = context;
        this.userList = userList;
    }


    @NonNull
    @Override
    public UserViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(this.context);
        View view = layoutInflater.inflate(R.layout.user_layout, parent, false);

        return new UserViewAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewAdapter.ViewHolder holder, int position) {

        User user = userList.get(position);
        holder.id.setText("User ID : " + user.getUserID());
        holder.name.setText(user.getFirstName() + " " + user.getLastName());
        holder.address.setText(user.getAddress());
        holder.phoneNumber.setText(user.getPhoneNumber());
        holder.type.setText("User");
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView id, name, address, phoneNumber, type;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.manage_user_id);
            name = itemView.findViewById(R.id.manage_user_name);
            address = itemView.findViewById(R.id.manage_user_address);
            phoneNumber = itemView.findViewById(R.id.manage_user_phoneNumber);
            type = itemView.findViewById(R.id.manage_user_type);
        }
    }
}

