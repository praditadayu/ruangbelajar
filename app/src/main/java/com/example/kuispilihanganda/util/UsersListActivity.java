package com.example.kuispilihanganda.util;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.LinearLayoutManager;

import androidx.recyclerview.widget.RecyclerView;

import com.example.kuispilihanganda.DashboardActivity;
import com.example.kuispilihanganda.R;

import java.util.ArrayList;
import java.util.List;

public class UsersListActivity extends AppCompatActivity {

    private ImageButton move;
    private AppCompatActivity activity = UsersListActivity.this;
    private AppCompatTextView textViewName;
    private List<User> listUsers;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kelas);
        getSupportActionBar().setTitle("");

        move = (ImageButton) findViewById(R.id.satu);
        move.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UsersListActivity.this, DashboardActivity.class);
                startActivity(intent);
            }
        });
    }

    /**
     * This method is to initialize views
     */

    private void getDataFromSQLite() {
        // AsyncTask is used that SQLite operation not blocks the UI Thread.
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                listUsers.clear();
                listUsers.addAll(databaseHelper.getAllUser());

                return null;
            }
        }.execute();
    }
}