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
    private List<User> mDataset;

    public UserAdapter(List<User> dataset) {
        this.mDataset = dataset;
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
        holder.id.setText(String.valueOf(mDataset.get(position).uid));
        Log.d(TAG, "onBindViewHolder: "+mDataset.get(position).lastName);
        holder.name.setText(mDataset.get(position).lastName);
        holder.surname.setText(mDataset.get(position).firstName);


    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView id;
        public TextView name;
        public TextView surname;


        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            id = itemView.findViewById(R.id.row_id);
            name = itemView.findViewById(R.id.row_name);
            surname = itemView.findViewById(R.id.row_surname);
        }
    }

}
