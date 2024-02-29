package com.example.buoi07;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DepartmentAdapter extends RecyclerView.Adapter<DepartmentAdapter.DepartmentViewHolder> {
    ArrayList<Department> lstDepart;
    Context context;
    DepartmentCallback userCallback;

    public DepartmentAdapter(ArrayList<Department> lstDepart) {
        this.lstDepart = lstDepart;
    }

    public void setCallback(DepartmentCallback callback) {
        this.userCallback = callback;
    }

    @NonNull
    @Override
    public DepartmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View userView = inflater.inflate(R.layout.layoutitemtext, parent, false); // Corrected layout resource name
        DepartmentViewHolder viewHolder = new DepartmentViewHolder(userView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DepartmentViewHolder holder, int position) {
        Department department = lstDepart.get(position);

        // Bind data to the ViewHolder
        holder.tvName.setText(department.getName());

        // Set up click listeners for edit and delete buttons
        holder.ivEdit.setOnClickListener(view -> {
            if (userCallback != null) {
                userCallback.onItemEditClicked(department, position);
            }
        });

        holder.ivDelete.setOnClickListener(view -> {
            if (userCallback != null) {
                userCallback.onItemDeleteClicked(department, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return lstDepart.size();
    }

    class DepartmentViewHolder extends RecyclerView.ViewHolder {
        TextView tvName;
        ImageView ivEdit;
        ImageView ivDelete;

        public DepartmentViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            ivEdit = itemView.findViewById(R.id.ivEdit);
            ivDelete = itemView.findViewById(R.id.ivDelete);
        }
    }

    public interface DepartmentCallback {
        void onItemDeleteClicked(Department department, int position);

        void onItemEditClicked(Department department, int position);
    }
}
