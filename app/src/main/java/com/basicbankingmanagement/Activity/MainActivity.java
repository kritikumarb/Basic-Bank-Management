package com.basicbankingmanagement.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.basicbankingmanagement.DBHelper;
import com.basicbankingmanagement.R;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    Button viewUser;
    DBHelper dbhelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Objects.requireNonNull(getSupportActionBar()).hide();
        viewUser = findViewById(R.id.alluser);
        dbhelper = new DBHelper(this);
        Log.d("MainActivity", "DATABASE ADD DATA STARTED");


        dbhelper.addContacts("Kriti Kumar Behera", "1111101", "10000","9090323291");
        dbhelper.addContacts("Janmejay Swain", "1111102", "10000","65465456");
        dbhelper.addContacts("Mrityunjay Patra", "1111103", "10000","654654654654");
        dbhelper.addContacts("Soumya Ranjan Das", "1111104", "10000","5454456454");
        dbhelper.addContacts("Papoo sahu", "1111105", "10000","54544654654654");
        dbhelper.addContacts("Biswasmurti Sahu", "1111106", "10000","5445454465");
        dbhelper.addContacts("Manas ranjan Mohanta", "1111107", "10000","554545454545");
        dbhelper.addContacts("Sunil Kumar Sahu", "1111108", "10000","45454544545454");
        dbhelper.addContacts("Malaya ranjan Dash", "1111109", "10000","5454545454654");
        dbhelper.addContacts("Sunil Kumar Sahu", "11111010", "10000","46546546546546");
        dbhelper.addContacts("Jashobanta kumar sa", "1111111", "10000","65465465465465");
        dbhelper.addContacts("Suroj Subudhi", "11111012", "10000","46546546546546");
        dbhelper.addContacts("Satya siba Sahu", "1111113", "10000","65465465465465");



        Log.d("MainActivity", "DATABASE END INSERT");
        viewUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("sonu" , "MainActivity On Click Button");
                startActivity(new Intent(MainActivity.this , AllUserList.class));

                (MainActivity.this).finish();
            }
        });

    }
}