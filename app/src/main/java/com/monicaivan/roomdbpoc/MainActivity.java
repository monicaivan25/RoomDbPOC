package com.monicaivan.roomdbpoc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

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
        db = AppDatabase.getInstance(getBaseContext());

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

        Intent intent = new Intent(getBaseContext(), UsersListActivity.class);
        startActivity(intent);
    }
}
