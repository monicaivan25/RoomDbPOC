package com.monicaivan.roomdbpoc;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public class UserAdapter extends RecyclerView.Adapter<UserAdapter.MyViewHolder> {
    private static final String TAG = "UserAdapter";
    private List<User> userList;

    UserAdapter(List<User> dataset) {
        this.userList = dataset;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.my_text_view, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.id.setText(String.valueOf(userList.get(position).uid));
        Log.d(TAG, "onBindViewHolder: "+ userList.get(position).lastName);
        holder.name.setText(userList.get(position).lastName);
        holder.surname.setText(userList.get(position).firstName);


    }

    User getUser(int position){
        return userList.get(position);
    }


    @Override
    public int getItemCount() {
        return userList.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView id;
        TextView name;
        TextView surname;


        MyViewHolder(@NonNull View itemView){
            super(itemView);
            id = itemView.findViewById(R.id.row_id);
            name = itemView.findViewById(R.id.row_name);
            surname = itemView.findViewById(R.id.row_surname);
        }
    }


}
