package com.example.buoi05;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        TextView tvDetail = findViewById(R.id.tvDetail);

        // Get user ID from the intent
        Intent intent = getIntent();
        if (intent != null) {
            String userId = intent.getStringExtra("userId");

            // Update the TextView with the received user ID
            if (userId != null) {
                String detailText = "User ID: " + userId;
                tvDetail.setText(detailText);
            }
        }
    }
}
