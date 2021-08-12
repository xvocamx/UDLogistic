package com.example.udlogistic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;


import com.example.udlogistic.model.KhachHang;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;

public class ThemKhachHang extends AppCompatActivity {
    EditText edtTenKhachHang, edtDiaChi, edtSDT;
    Button btnThemKhachHang;
    ImageButton ibtnBack;
    DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_khachhang);
        mDatabase = FirebaseDatabase.getInstance("https://quanlyvanchuyen-8879e-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference();
        setControl();
        setEvent();
    }

    private void setEvent() {
        ibtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), QuanLyKhachHang.class));
                overridePendingTransition(0, 0);
                finish();
            }
        });
        btnThemKhachHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (KiemTra()) {
                    String tenKhachHang = edtTenKhachHang.getText().toString();
                    String diaChi = edtDiaChi.getText().toString();
                    String SDT = edtSDT.getText().toString();
                    String maKH =UUID.randomUUID().toString();
                    KhachHang khachHang = new KhachHang(maKH,tenKhachHang,SDT,diaChi);
                    mDatabase.child("KhachHang").child(maKH).setValue(khachHang).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText(ThemKhachHang.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                        }
                    });
                    startActivity(new Intent(getApplicationContext(), QuanLyKhachHang.class));
                    overridePendingTransition(0, 0);
                    finish();
                }

            }
        });
    }

    private boolean KiemTra() {
        boolean res = true;
        if (edtTenKhachHang.getText().toString().equals("")) {
            edtTenKhachHang.setError("Vui lòng nhập tên khách hàng");
            res = false;
        }
        if (edtDiaChi.getText().toString().equals("")) {
            edtDiaChi.setError("Vui lòng nhập địa chỉ");
            res = false;
        }
        if (edtSDT.getText().toString().equals("")) {
            edtSDT.setError("Vui lòng nhập số điện thoại");
            res = false;
        }
        return res;
    }

    private void setControl() {
        edtTenKhachHang = findViewById(R.id.edtTenKhachHang);
        edtDiaChi = findViewById(R.id.edtDiaChi);
        edtSDT = findViewById(R.id.edtSoDienThoai);
        ibtnBack = findViewById(R.id.ibtnBack);
        btnThemKhachHang = findViewById(R.id.btnThemKhachHang);
    }
}