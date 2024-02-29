package com.example.tuan01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText edtTen;
    Button btnTen;
    TextView txtShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtTen = findViewById(R.id.edtTen);
        btnTen = findViewById(R.id.btnTen);
        txtShow = findViewById(R.id.txtShow);
        btnTen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edtTen.getText().toString();
                if (name.isEmpty()) {
                    txtShow.setText("Mời bạn nhập tên");
                } else {
                    txtShow.setText("Xin chào " + name + " !");
                }
            }
        });
    }
}