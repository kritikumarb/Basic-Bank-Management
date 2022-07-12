package com.basicbankingmanagement.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.basicbankingmanagement.Model.HistoryModel;
import com.basicbankingmanagement.R;

import java.util.ArrayList;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder>{
    Context context;
    ArrayList<HistoryModel> sendList;

    public HistoryAdapter(Context context, ArrayList<HistoryModel> sendList) {
        this.context = context;
        this.sendList = sendList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.history_card,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //time amount touser fromuser
        holder.time.setText(sendList.get(position).getTime());
        holder.fromuser.setText(sendList.get(position).getUser1());
        holder.touser.setText(sendList.get(position).getUser2());
        holder.amount.setText(sendList.get(position).getAmount());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return sendList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView time,amount, touser,fromuser;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            time = itemView.findViewById(R.id.historytime);
            amount = itemView.findViewById(R.id.historyamount);
            touser = itemView.findViewById(R.id.touser);
            fromuser = itemView.findViewById(R.id.fromuser);
        }
    }
}
