package com.basicbankingmanagement.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.basicbankingmanagement.DBHelper;
import com.basicbankingmanagement.Model.UserModel;
import com.basicbankingmanagement.R;

public class SingleUserDataActivity extends AppCompatActivity {
    TextView name , account , balance;
    String name1 , account1 , balance1;
    Button sendbutton;
    DBHelper dbhelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_user_data);
        name = findViewById(R.id.singlename);
        account = findViewById(R.id.singleaccountnumber);
        balance = findViewById(R.id.singlemoney);
        sendbutton = findViewById(R.id.send_button);

        dbhelper = new DBHelper(this);
        Intent intent = getIntent();
//        name.setText(intent.getStringExtra("username"));
//        account.setText(intent.getStringExtra("usernumber"));
//        balance.setText(intent.getStringExtra("useramount"));
//

        Cursor cursor = new DBHelper(this).readOneData(intent.getStringExtra("usernumber"));
        while (cursor.moveToNext()){
            account1=cursor.getString(0);
            name1=cursor.getString(1);
            balance1=cursor.getString(3);

        }
        name.setText(name1);
        account.setText(account1);
        balance.setText(balance1);
        sendbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enterAmount();

            }
        });
    }

    private void enterAmount() {
        final AlertDialog.Builder mBuilder = new AlertDialog.Builder(SingleUserDataActivity.this);
        View mView = getLayoutInflater().inflate(R.layout.custom_dialog, null);
        mBuilder.setTitle("Enter amount").setView(mView).setCancelable(false);
        final EditText mAmount = mView.findViewById(R.id.enteredamount);
        mBuilder.setPositiveButton("SEND", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        }).setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog dialog = mBuilder.create();

            dialog.show();
            dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mAmount.getText().toString().isEmpty()) {
                        mAmount.setError("Amount can't be empty");
                    } else if (Double.parseDouble(mAmount.getText().toString()) > Integer.parseInt(balance.getText().toString())) {
                        mAmount.setError("Your account don't have enough balance");
                    } else if (Double.parseDouble(mAmount.getText().toString()) == 0) {
                        mAmount.setError("Your  can't send zero amount");
                    } else {
                        Log.d("sonu", "Activity Single user Dialog Button ON click");

                        Intent intent1 = new Intent(SingleUserDataActivity.this, SelectUserList.class);
                        intent1.putExtra("accountnumber", account.getText().toString());
                        Log.d("sonu", "Activity SIngle user complete Intent and gonna start");
                        intent1.putExtra("amount", mAmount.getText().toString());
                        intent1.putExtra("usernamee", name.getText().toString());
                        startActivity(intent1);
                        finish();

                    }
                }
            });

    }
}