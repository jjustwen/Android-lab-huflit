package com.example.tuan01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class TinhToanActivity extends AppCompatActivity {
    EditText edtA, edtB;
    Button btnCong, btnTru, btnNhan, btnChia;
    TextView txtKetQua;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tinh_toan);

        anhXa();

        btnCong.setOnClickListener(v -> TinhToan("cong"));
        btnTru.setOnClickListener(v -> TinhToan("tru"));
        btnNhan.setOnClickListener(v -> TinhToan("nhan"));
        btnChia.setOnClickListener(v -> TinhToan("chia"));
    }

    public void anhXa() {
        edtA = findViewById(R.id.edtA);
        edtB = findViewById(R.id.edtB);

        btnCong = findViewById(R.id.btnCong);
        btnTru = findViewById(R.id.btnTru);
        btnNhan = findViewById(R.id.btnNhan);
        btnChia = findViewById(R.id.btnChia);

        txtKetQua = findViewById(R.id.txtKetQua);

    }

    void ShowData(int ketQua) {
        txtKetQua.setText(String.valueOf(ketQua));
    }

    void TinhToan(String phepTinh) {
        String soA = edtA.getText().toString();
        String soB = edtB.getText().toString();
        if (soA.isEmpty() || soB.isEmpty()) {
            txtKetQua.setText("Vui lòng nhập số A và B !");
            return;
        }
        int a = Integer.valueOf(soA);
        int b = Integer.valueOf(soB);
        switch (phepTinh) {
            case "cong":
                ShowData(a + b);
                break;
            case "tru":
                ShowData(a - b);
                break;
            case "nhan":
                ShowData(a * b);
                break;
            case "chia":
                if (b == 0) txtKetQua.setText("Mau so khong the la 0 !");
                else {
                    double rs = (double) a / b;
                    txtKetQua.setText(String.valueOf(rs));
                }
                break;

        }
    }

}