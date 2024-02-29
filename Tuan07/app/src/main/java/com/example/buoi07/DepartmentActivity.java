package com.example.buoi07;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class DepartmentActivity extends AppCompatActivity implements DepartmentAdapter.DepartmentCallback {
    RecyclerView rvListCode;
    ArrayList<Department> lstDepart;
    DepartmentAdapter departAdapter;
    FloatingActionButton fbAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_department);

        rvListCode = findViewById(R.id.rvListDe);
        fbAdd = findViewById(R.id.fbAdd);

        fbAdd.setOnClickListener(view -> addUserDialog());

        // Lấy dữ liệu
        lstDepart = DepartmentDataQuery.getAll(this);
        departAdapter = new DepartmentAdapter(lstDepart);
        departAdapter.setCallback(this);

        // Gán adapter
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rvListCode.setAdapter(departAdapter);
        rvListCode.setLayoutManager(linearLayoutManager);
    }

    void addUserDialog() {
        // Khởi tạo dialog để thêm người dùng
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(DepartmentActivity.this);
        alertDialog.setTitle("Thêm mới");
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_add_depart, null);
        alertDialog.setView(dialogView);

        EditText edName = dialogView.findViewById(R.id.edNameDe);
        Spinner snType = dialogView.findViewById(R.id.snType);

        // Load data for Spinner
        String[] arrType = new String[]{"Khu 1", "Khu 2", "Khu 3"};
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, arrType);
        snType.setAdapter(arrayAdapter);

        alertDialog.setPositiveButton("Đồng ý", (dialog, which) -> {
            String name = edName.getText().toString();
            if (name.isEmpty()) {
                Toast.makeText(DepartmentActivity.this, "Nhập dữ liệu không hợp lệ", Toast.LENGTH_LONG).show();
            } else {
                Department department = new Department(0, name);
                long id = DepartmentDataQuery.insert(DepartmentActivity.this, department);
                if (id > 0) {
                    Toast.makeText(DepartmentActivity.this, "Thêm phòng ban thành công.", Toast.LENGTH_LONG).show();
                    resetData();
                    dialog.dismiss();
                }
            }
        });

        alertDialog.setNegativeButton("Hủy", (dialog, which) -> {
            dialog.dismiss();
        });

        alertDialog.create().show();
    }

    void updateUserDialog(Department department) {
        // Khởi tạo dialog để cập nhật người dùng
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(DepartmentActivity.this);
        alertDialog.setTitle("Cập nhật");
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_add_depart, null);
        alertDialog.setView(dialogView);

        EditText edName = dialogView.findViewById(R.id.edNameDe);

        // Gán dữ liệu
        edName.setText(department.getName());

        alertDialog.setPositiveButton("Đồng ý", (dialog, which) -> {
            department.setName(edName.getText().toString());
            if (department.getName().isEmpty()) {
                Toast.makeText(DepartmentActivity.this, "Nhập dữ liệu không hợp lệ", Toast.LENGTH_LONG).show();
            } else {
                int id = DepartmentDataQuery.update(DepartmentActivity.this, department);
                if (id > 0) {
                    Toast.makeText(DepartmentActivity.this, "Cập nhật phòng ban thành công", Toast.LENGTH_LONG).show();
                    resetData();
                    dialog.dismiss();
                }
            }
        });

        alertDialog.setNegativeButton("Hủy", (dialog, which) -> {
            dialog.dismiss();
        });

        alertDialog.create().show();
    }

    void resetData() {
        lstDepart.clear();
        lstDepart.addAll(DepartmentDataQuery.getAll(DepartmentActivity.this));
        departAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItemDeleteClicked(Department department, int position) {
        boolean result = DepartmentDataQuery.delete(DepartmentActivity.this, department.getId());

        if (result) {
            Toast.makeText(this, "Xóa thành công", Toast.LENGTH_LONG).show();
            resetData();
        } else {
            Toast.makeText(this, "Xóa thất bại", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onItemEditClicked(Department department, int position) {
        updateUserDialog(department);
    }
}
