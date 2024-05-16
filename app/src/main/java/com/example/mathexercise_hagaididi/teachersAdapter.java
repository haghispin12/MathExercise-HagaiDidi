package com.example.mathexercise_hagaididi;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class teachersAdapter extends RecyclerView.Adapter<teachersAdapter.MyViewHolder> {
    public interface OnItemClickListener{
        void onitemClick(teacher item);
    }
    private ArrayList<teacher> teachers;
    private OnItemClickListener listener;
    public teachersAdapter(ArrayList<teacher> teachers, OnItemClickListener listener){
        this.teachers =teachers;
        this.listener=listener;
    }
    @NonNull
    @Override
    public teachersAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view,parent,false);
        return new MyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.bind(teachers.get(position),listener);
    }

    @Override
    public int getItemCount() {
        return teachers.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView textName;
        private TextView yourName;
        private TextView textPhoneNumber;
        private TextView yourPhoneNumber;
        private TextView textPrice;
        private TextView yourPrice;
        private TextView textLocation;
        private TextView yourLocation;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textName =itemView.findViewById(R.id.textName);
            yourName = itemView.findViewById(R.id.yourName);
            textPhoneNumber = itemView.findViewById(R.id.textPhoneNumber);
            yourPhoneNumber = itemView.findViewById(R.id.yourPhoneNumber);
            textPrice = itemView.findViewById(R.id.textPrice);
            yourPrice = itemView.findViewById(R.id.yourPrice);
            textLocation=itemView.findViewById(R.id.textLocation);
            yourLocation = itemView.findViewById(R.id.yourLocation);

        }
        public void bind(final teacher item, final OnItemClickListener
                listener) {
            yourName.setText(item.getName());
            yourLocation.setText(item.getLocation());
            yourPrice.setText(item.getPrice());
            yourPhoneNumber.setText(item.getPhoneNumber());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    listener.onitemClick(item);
                }
            });
        }
    }//end inner class
}
