// UserAdapter.java
package com.example.buoi05;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {
    private ArrayList<User> lstUser;
    UserCallback userCallback;
    public UserAdapter(ArrayList<User> lstUser, UserCallback userCallback) {
        this.lstUser = lstUser;
        this.userCallback= userCallback;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View userView = inflater.inflate(R.layout.layoutitem, parent, false);

        return new UserViewHolder(userView);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        User user = lstUser.get(position);

        holder.tvName.setText(user.getName());
        holder.imAvatar.setImageBitmap(Utils.converToBitmapFromAssets(holder.itemView.getContext(), user.getAvatar()));

        //lấy sự kiện
        holder.itemView.setOnClickListener(view -> userCallback.onItemClick(user.getId()));
    }


    @Override
    public int getItemCount() {
        return lstUser.size();
    }

    public void setData(ArrayList<User> newData) {
        lstUser = newData;
    }

    static class UserViewHolder extends RecyclerView.ViewHolder {
        TextView tvName;
        ImageView imAvatar;

        UserViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            imAvatar = itemView.findViewById(R.id.ivAvartar);
        }
    }
    public interface UserCallback {
        void onItemClick(String id);
    }
}
