package com.example.mathexercise_hagaididi;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class lessonsAdapter  extends RecyclerView.Adapter<lessonsAdapter.MyViewHolder> {
    public interface OnItemClickListener{
        void onitemClick(lesson item);
    }
    private ArrayList<lesson> lessons;
    private lessonsAdapter.OnItemClickListener listener;
    public lessonsAdapter(ArrayList<lesson> lessons, lessonsAdapter.OnItemClickListener listener){
        this.lessons =lessons;
        this.listener=listener;
    }
    @NonNull
    @Override
    public lessonsAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lessons,parent,false);
        return new lessonsAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull lessonsAdapter.MyViewHolder holder, int position) {
        holder.bind(lessons.get(position),listener);
    }

    @Override
    public int getItemCount() {
        return lessons.size();}
        public static class MyViewHolder extends RecyclerView.ViewHolder {
            private TextView textDate;
            private TextView yourDate;
            private TextView textHour;
            private TextView yourHour;


            public MyViewHolder(@NonNull View itemView) {
                super(itemView);
                textDate = itemView.findViewById(R.id.textDate);
                yourDate = itemView.findViewById(R.id.yourDate);
                textHour = itemView.findViewById(R.id.textHour);
                yourHour = itemView.findViewById(R.id.yourHour);
            }

            public void bind(final lesson item, final lessonsAdapter.OnItemClickListener
                    listener) {
                yourDate.setText(item.getDate());
                yourHour.setText(item.getHour());
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        listener.onitemClick(item);
                    }
                });
            }

        }
    }

