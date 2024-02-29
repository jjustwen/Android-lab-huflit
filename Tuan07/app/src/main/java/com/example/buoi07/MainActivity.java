package com.example.buoi07;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements UserAdapter.UserCallback {
    RecyclerView rvListCode;
    ArrayList<User> lstUser;
    UserAdapter userAdapter;
    FloatingActionButton fbAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvListCode = findViewById(R.id.rvList);
        fbAdd = findViewById(R.id.fbAdd);
        fbAdd.setOnClickListener(view -> addUserDialog());

        // Lấy dữ liệu
        lstUser = UserDateQuery.getAll(this);
        userAdapter = new UserAdapter(lstUser);
        userAdapter.setCallback(this);

        // Gán adapter
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rvListCode.setAdapter(userAdapter);
        rvListCode.setLayoutManager(linearLayoutManager);
    }

    void addUserDialog() {
        // Khởi tạo dialog để thêm người dùng.
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
        alertDialog.setTitle("Thêm mới");
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_add_user, null);
        alertDialog.setView(dialogView);

        EditText edName = dialogView.findViewById(R.id.edName);
        EditText edAvatar = dialogView.findViewById(R.id.edAvatar);
        // Load phòng ban
        Spinner snPart = dialogView.findViewById(R.id.snDepart);
        ArrayList<com.example.buoi07.Department> lstDepart = DepartmentDataQuery.getAll(this);
        lstDepart.add(0, new com.example.buoi07.Department(0, "Chọn phòng ban"));
        ArrayAdapter<Department> departmentArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, lstDepart);
        departmentArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        snPart.setAdapter(departmentArrayAdapter);

        alertDialog.setPositiveButton("Đồng ý", (dialog, which) -> {
            String name = edName.getText().toString();
            String avatar = edAvatar.getText().toString();
            Department itemde = (Department) snPart.getSelectedItem();

            if (itemde.id == 0) {
                Toast.makeText(MainActivity.this, "Vui lòng chọn phòng ban", Toast.LENGTH_LONG).show();
            } else if (name.isEmpty()) {
                Toast.makeText(MainActivity.this, "Nhập dữ liệu không hợp lệ", Toast.LENGTH_LONG).show();
            } else {
                User us = new User(0, name, avatar); // Make sure your User class constructor accepts these parameters
                us.departid = itemde.id;

                long id = UserDateQuery.insert(MainActivity.this, us);
                if (id > 0) {
                    Toast.makeText(MainActivity.this, "Thêm người dùng thành công.", Toast.LENGTH_LONG).show();
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
    void updateUserDialog(User us){
        // khởi tạo dialog để cập nhật người dùng
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
        alertDialog.setTitle("Cập nhật");
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_add_user, null);
        alertDialog.setView(dialogView);
        EditText edName = (EditText) dialogView.findViewById(R.id.edName);
        EditText edAvatar = (EditText) dialogView.findViewById(R.id.edAvatar);
        //load phong ban
        Spinner snPart = dialogView.findViewById(R.id.snDepart);
        ArrayList<Department> lstDepart= DepartmentDataQuery.getAll(this);
        lstDepart.add(0, new Department(0, "Chọn phòng ban"));
        ArrayAdapter<Department> departmentArrayAdapter= new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,lstDepart);
        departmentArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        snPart.setAdapter(departmentArrayAdapter);
        // gán dữ liệu
        edName.setText(us.getName());
        edAvatar.setText(us.getAvatar());
        snPart.setSelection(us.departid);
        //
        alertDialog.setPositiveButton("Đồng ý", ((dialog, which) -> {
            us.setName(edName.getText().toString());
            us.setAvatar(edAvatar.getText().toString());
            Department itemde=(Department) snPart.getSelectedItem();
            if (itemde.id==0)
            {
                Toast.makeText(MainActivity.this, "Vui lòng chọn phòng ban", Toast.LENGTH_LONG).show();
            } else
            if (us.name.isEmpty())
            {
                Toast.makeText(MainActivity.this, " Nhập dữ liệu không hợp lệ",Toast.LENGTH_LONG).show();
            }
            else {
                us.departid = itemde.id;
                int id = UserDateQuery.update(MainActivity.this, us);
                if (id>0)
                {
                    Toast.makeText(MainActivity.this, " Cập nhật người dùng thành công",Toast.LENGTH_LONG).show();
                    resetData();
                    dialog.dismiss();
                }
            }
        }));
        alertDialog.setNegativeButton("Hủy", ((dialog, which) -> {
            dialog.dismiss();
        }));
        alertDialog.create().show();
    }


    void resetData() {
        lstUser.clear();
        lstUser.addAll(UserDateQuery.getAll(MainActivity.this));
        userAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItemEditClicked(User user, int position) {
        updateUserDialog(user);
    }

    @Override
    public void onItemDeleteClicked(User user, int position) {
        boolean result = UserDateQuery.delete(MainActivity.this, user.id);

        if (result) {
            Toast.makeText(this, "Xóa thành công", Toast.LENGTH_LONG).show();
            resetData();
        } else {
            Toast.makeText(this, "Xóa thất bại", Toast.LENGTH_LONG).show();
        }
    }
}
