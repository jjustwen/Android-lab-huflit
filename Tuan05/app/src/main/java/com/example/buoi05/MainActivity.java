package com.example.buoi05;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements UserAdapter.UserCallback {
    RecyclerView rvList;
    ArrayList<User> lstUser;
    UserAdapter userAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvList = findViewById(R.id.rvList);
        LoadData();

        userAdapter = new UserAdapter(lstUser, this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rvList.setLayoutManager(linearLayoutManager);
        rvList.setAdapter(userAdapter);
    }

    @Override
    public void onItemClick(String userId) {

        Intent intent = new Intent(MainActivity.this,DetailActivity.class);
        intent.putExtra("userId", userId);
        startActivity(intent);
    }


    void LoadData() {
        // Khởi tạo hoặc xóa danh sách
        lstUser = new ArrayList<>();

        // Thêm dữ liệu người dùng
        lstUser.add(new User("1", "C#", "anh1.jpg"));
        lstUser.add(new User("2", "C", "anh2.jpg"));
        lstUser.add(new User("3", "C++", "anh3.jpg"));
        lstUser.add(new User("4", "Java", "anh4.jpg"));
        lstUser.add(new User("5", "Lisp", "anh5.jpg"));
        lstUser.add(new User("6", "Python", "anh6.jpg"));
        lstUser.add(new User("7", "Swift", "anh7.jpg"));
        lstUser.add(new User("8", "Objective C", "anh8.jpg"));
        lstUser.add(new User("9", "Ruby", "anh9.jpg"));
        lstUser.add(new User("10", "Golang", "anh10.jpg"));
        // Thêm các user khác...

        // Thông báo cho adapter biết rằng dữ liệu đã thay đổi
        if (userAdapter != null) {
            userAdapter.setData(lstUser);
            userAdapter.notifyDataSetChanged();
        }
    }
}
