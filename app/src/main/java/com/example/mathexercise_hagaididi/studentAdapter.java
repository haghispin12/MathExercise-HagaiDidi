package com.example.mathexercise_hagaididi;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class studentAdapter extends RecyclerView.Adapter<studentAdapter.MyViewHolder> {
    public interface OnItemClickListener{
        void onitemClick(student item);
    }
    private ArrayList<student> students;
    private studentAdapter.OnItemClickListener listener;
    public studentAdapter(ArrayList<student> students, studentAdapter.OnItemClickListener listener){
        this.students =students;
        this.listener=listener;
    }
    @NonNull
    @Override
    public studentAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_student,parent,false);
        return new studentAdapter.MyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull studentAdapter.MyViewHolder holder, int position) {
        holder.bind(students.get(position),listener);
    }

    @Override
    public int getItemCount() {
        return students.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView textName;
        private TextView yourName;
        private TextView textStatus;
        private TextView yourStatus;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textName =itemView.findViewById(R.id.textNameS);
            yourName = itemView.findViewById(R.id.yourNameS);
            textStatus = itemView.findViewById(R.id.textStatus);
            yourStatus = itemView.findViewById(R.id.yourStatus);
        }
        public void bind(final student item, final studentAdapter.OnItemClickListener
                listener) {
            yourName.setText(item.getEmail());
            yourStatus.setText(item.getStatus());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    listener.onitemClick(item);
                }
            });
        }
    }//end inner class
}

