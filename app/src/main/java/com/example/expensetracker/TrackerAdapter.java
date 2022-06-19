package com.example.expensetracker;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.expensetracker.room.Tracker;

import java.util.List;

public class TrackerAdapter extends RecyclerView.Adapter<TrackerAdapter.MyViewholder> {
    List<Tracker> list;


    public TrackerAdapter(List<Tracker> list) {
        this.list = list;
    }

    public void setList(List<Tracker> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.row_item, parent, false);
        MyViewholder viewHolder = new MyViewholder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewholder holder, int position) {
        Tracker tracker = list.get(position);
        holder.textView_des.setText(tracker.getDescription());
        holder.textView_date.setText(tracker.getDate());
        holder.textView_amount.setText("RS:" + tracker.getAmount());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewholder extends RecyclerView.ViewHolder {
        TextView textView_des;
        TextView textView_date;
        TextView textView_amount;

        public MyViewholder(@NonNull View itemView) {
            super(itemView);
            textView_des = itemView.findViewById(R.id.row_des);
            textView_date = itemView.findViewById(R.id.row_date);
            textView_amount = itemView.findViewById(R.id.row_amount);
        }


    }
}
