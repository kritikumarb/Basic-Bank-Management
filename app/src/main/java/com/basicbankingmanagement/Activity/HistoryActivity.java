package com.basicbankingmanagement.Activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;

import com.basicbankingmanagement.Adapter.HistoryAdapter;
import com.basicbankingmanagement.DBHelper;
import com.basicbankingmanagement.Model.HistoryModel;
import com.basicbankingmanagement.R;

import java.util.ArrayList;
import java.util.Objects;

public class HistoryActivity extends AppCompatActivity {
    ArrayList<HistoryModel> history = new ArrayList<>();
    RecyclerView historyrecycler;
    DBHelper dbHelper;
    HistoryAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("history1"," Inside history activity");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        Objects.requireNonNull(getSupportActionBar()).setTitle(" Transaction History ");
        historyrecycler = findViewById(R.id.historyrecycler);
        historyrecycler.setLayoutManager(new LinearLayoutManager(this));
        dbHelper = new DBHelper(this);
        showData();
    }

    private void showData() {
        Cursor cursor = new DBHelper(this).readHistory();
        while(cursor.moveToNext()){
            String date = cursor.getString(0);
            String u1 = cursor.getString(1);
            String u2 = cursor.getString(2);
            String s = cursor.getString(3);
            String balance = cursor.getString(4);
            history.add(new HistoryModel(date , u1 , u2 , s , balance));
        }
        adapter = new HistoryAdapter(this , history);
        historyrecycler.setAdapter(adapter);

    }
}