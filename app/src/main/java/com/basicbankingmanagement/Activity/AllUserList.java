package com.basicbankingmanagement.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.basicbankingmanagement.Adapter.AllUserDataAdapter;
import com.basicbankingmanagement.DBHelper;
import com.basicbankingmanagement.Model.UserModel;
import com.basicbankingmanagement.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Objects;

public class AllUserList extends AppCompatActivity {
    ArrayList<UserModel> userData = new ArrayList<>();
    RecyclerView userlistRecycler;
    DBHelper dbhelper;
    AllUserDataAdapter allUserDataAdapter;
    FloatingActionButton history;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.userlistcard);
        history = findViewById(R.id.historyButton);
        Objects.requireNonNull(getSupportActionBar()).hide();
        dbhelper = new DBHelper(this);
        userlistRecycler = findViewById(R.id.userlist);
        userlistRecycler.setLayoutManager(new LinearLayoutManager(this));

        showData();
        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(), HistoryActivity.class));
            }
        });

    }
    private void showData() {
        Cursor cursor = new DBHelper(this).readAllData();

        while (cursor.moveToNext()){
            String account=cursor.getString(0);
            String name=cursor.getString(1);
            String mobile=cursor.getString(2);
            String balance=cursor.getString(3);
            UserModel model = new UserModel(name,account,balance,mobile);
            userData.add(model);
        }

        allUserDataAdapter=new AllUserDataAdapter(this,userData);
        userlistRecycler.setAdapter(allUserDataAdapter);
        Log.d("sonu" , "Successfully shown All User List");


    }
}