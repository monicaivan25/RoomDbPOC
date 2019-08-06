package com.monicaivan.roomdbpoc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class UsersRecyclerView extends AppCompatActivity {
    private static final String TAG = "UsersRecyclerView";
    private RecyclerView recyclerView;
    private RecyclerView.Adapter<UserAdapter.MyViewHolder> mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private List<User> userList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_recycler_view);

        recyclerView = findViewById(R.id.my_recycler_view);

        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        userList = (ArrayList<User>) getIntent().getSerializableExtra("userList");
        mAdapter = new UserAdapter(userList);
        recyclerView.setAdapter(mAdapter);
    }
}
