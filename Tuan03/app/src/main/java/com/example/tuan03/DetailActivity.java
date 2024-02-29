package com.example.tuan03;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class DetailActivity extends AppCompatActivity {
    TextView tvMenu;

    Button btBackC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        tvMenu = findViewById(R.id.tvMenu);
        btBackC = findViewById(R.id.btBack);
        //get dữ liệu
        Bundle goi = getIntent().getExtras();
        String mnName = goi.getString("menu");
        tvMenu.setText(mnName);
        btBackC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}