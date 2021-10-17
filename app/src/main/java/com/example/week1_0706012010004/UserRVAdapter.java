package com.example.week1_0706012010004;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import model.useruser;

public class UserRVAdapter extends RecyclerView.Adapter<UserRVAdapter.UserViewHolder>{

    private ArrayList<useruser> listUser;
    private OnCardListener cardListener;

    public UserRVAdapter(ArrayList<useruser> listUser, OnCardListener cardListener) {
        this.listUser = listUser;
        this.cardListener = cardListener;
    }

    @Override
    public UserRVAdapter.UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.card_user, parent, false);
        UserViewHolder userViewHolder = new UserViewHolder(view);
        return userViewHolder;
    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, int position) {
        holder.name.setText(listUser.get(position).getName());
        holder.age.setText(String.valueOf(listUser.get(position).getAge()));
        holder.address.setText(listUser.get(position).getAddress());
    }

    @Override
    public int getItemCount() {
        return listUser.size();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {

        private TextView name, age, address;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            age = itemView.findViewById(R.id.age);
            address = itemView.findViewById(R.id.address);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    cardListener.onCardClick(getAdapterPosition());
                }
            });
        }
    }
}
