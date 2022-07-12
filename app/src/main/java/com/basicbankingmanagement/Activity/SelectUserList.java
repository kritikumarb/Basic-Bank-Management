package com.basicbankingmanagement.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;

import com.basicbankingmanagement.Adapter.AllUserDataAdapter;
import com.basicbankingmanagement.Adapter.SelectUserAdapter;
import com.basicbankingmanagement.DBHelper;
import com.basicbankingmanagement.Model.UserModel;
import com.basicbankingmanagement.R;

import java.util.ArrayList;
import java.util.Objects;

public class SelectUserList extends AppCompatActivity {
    ArrayList<UserModel> userData = new ArrayList<>();
    RecyclerView userlistRecycler;
    DBHelper dbhelper;
    SelectUserAdapter selectuseradapter;
    UserModel model;
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("sonu" , "Selected UserList start");
        setContentView(R.layout.activity_select_user_list);
        dbhelper = new DBHelper(this);
        Log.d("sonu" , "Selected UserList dbhelper created");
        userlistRecycler = findViewById(R.id.selectuserlist);
        Log.d("sonu" , "Selected UserList recycler");
        userlistRecycler.setLayoutManager(new LinearLayoutManager(this));
        Log.d("sonu" , "Selected UserList end");
        showData();;
    }

    private void showData() {
        Log.d("sonu" , "Inside Showdata()");
        Intent intent = getIntent();
        String account = intent.getStringExtra("accountnumber");
        String amount = intent.getStringExtra("amount");
        name = intent.getStringExtra("usernamee");
        Cursor cursor = new DBHelper(this).readselectuserdata(account);
        Log.d("sonu" , "Selected UserList show data function");
        while (cursor.moveToNext()) {
            String account1 = cursor.getString(0);
            String name = cursor.getString(1);
            String mobile = cursor.getString(2);
            String balance = cursor.getString(3);
            model = new UserModel(name, account1, balance, mobile);
            userData.add(model);
        }
// constructor add account 1
        Log.d("sonu" , "Select UserList while loop end");
        selectuseradapter = new SelectUserAdapter(this, userData ,account , amount ,name );
        userlistRecycler.setAdapter(selectuseradapter);
        Log.d("sonu" , "data shown sucessfully in select user List");

    }
}