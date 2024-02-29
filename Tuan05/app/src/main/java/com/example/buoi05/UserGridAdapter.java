package com.example.buoi05;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;



public class UserGridAdapter extends RecyclerView.Adapter<UserGridAdapter.UserGridViewHolder> {
    ArrayList<User> lstUser;
    Context context;
    UserGridCallBack userGridCallBack;

    public UserGridAdapter(ArrayList<User> lstUser,UserGridCallBack userGridCallBack) {
        this.lstUser = lstUser;
        this.userGridCallBack = userGridCallBack;

    }

    @NonNull
    @Override
    public UserGridViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Nạp layout cho View biểu diễn phần tử User
        View userView = inflater.inflate(R.layout.layoutitemgrid, parent, false);

        // Tạo và trả về UserGridViewHolder
        UserGridViewHolder viewHolder = new UserGridViewHolder(userView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull UserGridViewHolder holder, int position) {
        User item = lstUser.get(position);
        // Gán vào item của View
        holder.imAvarta.setImageBitmap(Utils.converToBitmapFromAssets(context, item.getAvatar()));
    //set sự kiện
        holder.itemView.setOnClickListener(view -> userGridCallBack.onItemClick(item.getId(), position));

    }

    @Override
    public int getItemCount() {
        return lstUser.size();
    }

    class UserGridViewHolder extends RecyclerView.ViewHolder {
        ImageView imAvarta;

        public UserGridViewHolder(@NonNull View itemView) {
            super(itemView);
            imAvarta = itemView.findViewById(R.id.ivAvartar);
        }

    }
    public interface UserGridCallBack {
        void onItemClick(String userId, int position);
    }
}
