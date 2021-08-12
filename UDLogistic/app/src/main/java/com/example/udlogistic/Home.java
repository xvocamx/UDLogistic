package com.example.udlogistic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;


public class Home extends AppCompatActivity {
    CardView cvQuanLyKhachHang, cvDoiXeVanTai, cvQuanLyNhanVien;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setControl();
        setEvent();
    }

    private void setEvent() {
        cvDoiXeVanTai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), QuanLyDoiXeVanTai.class);
                startActivity(intent);
            }
        });
        cvQuanLyKhachHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), QuanLyKhachHang.class);
                startActivity(intent);
            }
        });
        cvQuanLyNhanVien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), QuanLyNhanVien.class);
                startActivity(intent);
            }
        });
    }

    private void setControl() {
        cvDoiXeVanTai = findViewById(R.id.cvDoiXeVanTai);
        cvQuanLyKhachHang = findViewById(R.id.cvQuanLyKhachHang);
        cvQuanLyNhanVien = findViewById(R.id.cvQuanLyNhanVien);
    }
}