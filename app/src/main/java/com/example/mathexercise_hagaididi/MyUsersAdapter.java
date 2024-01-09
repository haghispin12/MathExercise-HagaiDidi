package com.example.mathexercise_hagaididi;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyUsersAdapter extends RecyclerView.Adapter<MyUsersAdapter.MyViewHolder> {
    public interface OnItemClickListener{
        void onitemClick(User item);
    }
    private ArrayList<User> Users;
    private OnItemClickListener listener;
    public MyUsersAdapter(ArrayList<User> Users, OnItemClickListener listener){
        this.Users=Users;
        this.listener=listener;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.bind(Users.get(position),listener);
    }

    @Override
    public int getItemCount() {
        return Users.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tvUserName ;
        ImageView ivUserImg;
        TextView tvRate;
        TextView tvScore;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvUserName = itemView.findViewById(R.id. tvUserName);
            ivUserImg = itemView.findViewById(R.id. ivUserImg);
            tvRate = itemView.findViewById(R.id.tvRate);
            tvScore = itemView.findViewById(R.id.tvScore);
        }
        public void bind(final User item, final OnItemClickListener
                listener) {
            tvRate.setText(item.getRate()+"");
            tvScore.setText(item.getScore()+"");
            tvUserName.setText(item.getUserName()+"");
            ivUserImg.setImageBitmap(item.getBitmap());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                listener.onitemClick(item);
                }
            });
        }
    }//end inner class
}
