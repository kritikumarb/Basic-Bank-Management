package com.basicbankingmanagement.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.basicbankingmanagement.Activity.AllUserList;
import com.basicbankingmanagement.Activity.SingleUserDataActivity;
import com.basicbankingmanagement.Model.UserModel;
import  com.basicbankingmanagement.R;

import java.util.ArrayList;

public class AllUserDataAdapter  extends RecyclerView.Adapter<AllUserDataAdapter.ViewHolder>  {
    Context context;
    ArrayList<UserModel> arr;

    public AllUserDataAdapter(Context c, ArrayList<UserModel> arr) {
        this.context = c;
        this.arr = arr;

    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.user_list_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.name.setText(arr.get(position).getName());
        holder.number.setText(arr.get(position).getAccount());
        holder.amount.setText (arr.get(position).getBalance());

    }

    @Override
    public int getItemCount() {
        return arr.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, number, amount;

        public ViewHolder(View item) {
            super(item);
            name = item.findViewById(R.id.username);
            number = item.findViewById(R.id.accountnumber);
            amount = item.findViewById(R.id.usermoney);
            item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent = new Intent(view.getContext(), SingleUserDataActivity.class);
                    intent.putExtra("username", name.getText());
                    intent.putExtra(  "usernumber" ,  number.getText());
                    intent.putExtra("useramount" , amount.getText());
//                    view.getContext().startActivity(intent);
                    ((AllUserList)context).startActivity(intent);
                    ((AllUserList) context).finish();


                }
            });
        }
    }

}
