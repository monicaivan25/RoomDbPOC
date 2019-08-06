package com.monicaivan.roomdbpoc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private EditText mName;
    private EditText mSurname;

    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "my-database")
                .allowMainThreadQueries()
                .build();

        mName = findViewById(R.id.name);
        mSurname = findViewById(R.id.surname);
    }


    public void addToDatabase(View v) {
        User user = new User();
        user.firstName = mName.getText().toString();
        user.lastName = mSurname.getText().toString();

        db.userDao().insertAll(user);
    }

    public void getFromDatabase(View v) {
        ArrayList<User> users = (ArrayList<User>) db.userDao().getAll();
        Intent intent = new Intent(getBaseContext(), UsersRecyclerView.class);
        intent.putExtra("userList", users);
        startActivity(intent);
    }
}
