package com.example.mathexercise_hagaididi;

import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mathexercise_hagaididi.R;

import java.util.ArrayList;

public class missionAdapter extends RecyclerView.Adapter<missionAdapter.MyViewHolder> {
    public interface OnItemClickListener{
        void onitemClick(mission item);
    }
    private ArrayList<mission> Missions;
    private OnItemClickListener listener;
    public missionAdapter(ArrayList<mission> missions, OnItemClickListener listener){
        this.Missions=missions;
        this.listener=listener;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.bind(Missions.get(position),listener);
    }

    @Override
    public int getItemCount() {
        return Missions.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView name;
        private TextView percentage;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.missionName);
            percentage = itemView.findViewById(R.id.percentage);
        }
        public void bind(final mission item, final OnItemClickListener
                listener) {
            name.setText(item.getName()+"");
            percentage.setText(item.getPercentage()+"");
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    listener.onitemClick(item);
                }
            });
        }
    }//end inner class
}

