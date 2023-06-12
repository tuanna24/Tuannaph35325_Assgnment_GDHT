package com.example.tuannaph35325_assgnment_gdht;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class SelectActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);
        LinearLayout exit = findViewById(R.id.exit);
        LinearLayout phongBan = findViewById(R.id.phongban);
        LinearLayout nhanVien = findViewById(R.id.nhanvien);
        ImageView imageView = findViewById(R.id.house);

        phongBan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ActivityPhongBan.class);
                startActivity(intent);
            }
        });
        nhanVien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ActivityNhanVien.class);
                startActivity(intent);
            }
        });

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}