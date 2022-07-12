package com.basicbankingmanagement.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.basicbankingmanagement.Activity.AllUserList;
import com.basicbankingmanagement.Activity.SelectUserList;
import com.basicbankingmanagement.DBHelper;
import com.basicbankingmanagement.Model.UserModel;
import com.basicbankingmanagement.R;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

public class SelectUserAdapter extends RecyclerView.Adapter<SelectUserAdapter.ViewHolder> {
    Context context;
    ArrayList<UserModel> sendList;
    String account1 , balance , name1;


    public SelectUserAdapter(Context context, ArrayList<UserModel> sendList , String account1 , String balance , String name1) {
        this.context = context;
        this.sendList = sendList;
        this.account1 = account1;
        this.balance = balance;
        this.name1 = name1;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(context).inflate(R.layout.user_list_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d("sonu" , "OnBind View Holder start");
        DBHelper dbhelper = new DBHelper((SelectUserList)context);

        holder.username.setText(sendList.get(position).getName());
        holder.usermoney.setText(sendList.get(position).getBalance());
        holder.accountnumber.setText(sendList.get(position).getAccount());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                Date date = new Date();
                dbhelper.addHistory(formatter.format(date),name1 , holder.username.getText().toString(),balance , "1");

                Log.d("DATABASE","Reciever user A/c No :"+holder.accountnumber.getText().toString()+"NAme :"+holder.username.getText().toString());
                Log.d("DATABASE","Send user A/c No :"+account1);
                DBHelper db = new DBHelper(view.getContext());
                db.dataUpdate(account1 , holder.accountnumber.getText().toString() , balance);
                Toast.makeText(context,"Send to "+ holder.username.getText().toString(), Toast.LENGTH_SHORT).show();
                ((SelectUserList)context).startActivity(new Intent(context,AllUserList.class));
                ((SelectUserList) context).finish();
            }
        });
    }

    @Override
    public int getItemCount() {
        return sendList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView username,usermoney,accountnumber;
        public ViewHolder(@NonNull View item) {
            super(item);
            username=itemView.findViewById(R.id.username);
            usermoney=itemView.findViewById(R.id.usermoney);
            accountnumber=itemView.findViewById(R.id.accountnumber);

        }
    }
}