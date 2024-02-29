package com.example.buoi05;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity implements UserGridAdapter.UserGridCallBack {
    RecyclerView rvGridC;
    ArrayList<User> lstUser;
    UserGridAdapter userGridAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        rvGridC = findViewById(R.id.rvGrid);
        LoadData();

        if (lstUser != null) {
            userGridAdapter = new UserGridAdapter(lstUser, this);
            GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
            rvGridC.setAdapter(userGridAdapter);
            rvGridC.setLayoutManager(gridLayoutManager);
        } else {
            // Handle the case where lstUser is not initialized
        }
    }

    @Override
    public void onItemClick(String userId, int position) {
        Intent intent = new Intent(MainActivity2.this, DetailActivity.class);
        intent.putExtra("userId", userId);
        intent.putExtra("position", position + 1); // Adding 1 to display position starting from 1
        startActivity(intent);
    }


    void LoadData() {
        lstUser = new ArrayList<>();
        // Add user data
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
    }
}

