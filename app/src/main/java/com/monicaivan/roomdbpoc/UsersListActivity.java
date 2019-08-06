package com.monicaivan.roomdbpoc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class UsersListActivity extends AppCompatActivity {
    private static final String TAG = "UsersListActivity";
    private RecyclerView recyclerView;
    private RecyclerView.Adapter<UserAdapter.MyViewHolder> mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_recycler_view);

        recyclerView = findViewById(R.id.my_recycler_view);

        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        db = AppDatabase.getInstance(getBaseContext());
        mAdapter = new UserAdapter(new ArrayList<User>());
        recyclerView.setAdapter(mAdapter);

        db.userDao().getAll().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                mAdapter = new UserAdapter(users);
                recyclerView.setAdapter(mAdapter);
            }
        });

        setItemTouchHelper();


    }

    private static class deleteWordAsyncTask extends AsyncTask<User, Void, Void> {
        private UserDao mAsyncTaskDao;

        deleteWordAsyncTask(UserDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final User... params) {
            mAsyncTaskDao.delete(params[0]);
            return null;
        }
    }

    private void deleteUser(User user) {
        new deleteWordAsyncTask(db.userDao()).execute(user);
    }


    private void setItemTouchHelper() {
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP | ItemTouchHelper.DOWN,
                ItemTouchHelper.RIGHT) {

            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                User user = ((UserAdapter) mAdapter).getUser(position);
                deleteUser(user);
            }
        }).attachToRecyclerView(recyclerView);
    }

}
