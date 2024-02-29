package com.example.tuan03;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText edUserNameC, edPasswordC;
    Button btLoginC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //ánh xạ
        edUserNameC = findViewById(R.id.edUserName);
        edPasswordC = findViewById(R.id.edPassword);
        btLoginC = findViewById(R.id.btLogin);
        btLoginC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //lấy dữ liệu
                String username = edUserNameC.getText().toString().trim();
                String password = edPasswordC.getText().toString().trim();
                //kiểm tra dữ liệu
                if (TextUtils.isEmpty((username)) || TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_LONG).show();
                    return;
                } else {
                    //tạo đường dẫn tới Activity mới
                    Intent i = new Intent(MainActivity.this, InfoActivity.class);
                    i.putExtra("username", username);
                    i.putExtra("password", password);
                    startActivity(i);
                }
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInf = getMenuInflater();
        menuInf.inflate(R.menu.optionmenu, menu);
        return true;
    }

    //tạo sự kiện click trên item của menu
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.mnHome) {
            showActivity(item.getTitle().toString());
            return true;
        } else if (itemId == R.id.mnAccount) {
            showActivity(item.getTitle().toString());
            return true;
        } else if (itemId == R.id.mnSearch) {
            showActivity(item.getTitle().toString());
            return true;
        } else if (itemId == R.id.mnExit) {
            showActivity(item.getTitle().toString());
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    void showActivity(String nameMn) {
        Intent i = new Intent(MainActivity.this, DetailActivity.class);
        i.putExtra("menu", nameMn);
        startActivity(i);
    }

}