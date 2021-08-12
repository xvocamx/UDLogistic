package com.example.udlogistic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;


import com.example.udlogistic.model.KhachHang;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SuaKhachKhang extends AppCompatActivity {
    EditText edtTenKhachHang, edtDiaChi, edtSDT;
    Button btnEdit;
    ImageButton ibtnBack;
    DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sua_khachhang);
        mDatabase = FirebaseDatabase.getInstance("https://quanlyvanchuyen-8879e-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference();
        setControl();
        setEvent();
    }

    private void setEvent() {
        Bundle bundle = getIntent().getExtras();
        if (bundle == null) {
            return;
        }
        KhachHang khachHang = (KhachHang) bundle.get("data_khachhang");
        LoadKhachHang(khachHang);
        ibtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), XemChiTiet_KhachHang.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("data_khachhang", khachHang);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (KiemTra()) {
                    String maKH = khachHang.getMaKH().toString();
                    String tenKhachHang = edtTenKhachHang.getText().toString();
                    String diaChi = edtDiaChi.getText().toString();
                    String soDienThoai = edtSDT.getText().toString();
                    KhachHang khachHang1 = new KhachHang(maKH, tenKhachHang, soDienThoai, diaChi);
                    mDatabase.child("KhachHang").child(maKH).setValue(khachHang1);
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

    private void LoadKhachHang(KhachHang khachHang) {
        edtTenKhachHang.setText(khachHang.getTenKhachHang());
        edtDiaChi.setText(khachHang.getDiaChi());
        edtSDT.setText(khachHang.getSDT());
    }

    private void setControl() {
        edtTenKhachHang = findViewById(R.id.edtTenKhachHang);
        edtDiaChi = findViewById(R.id.edtDiaChi);
        edtSDT = findViewById(R.id.edtSoDienThoai);
        ibtnBack = findViewById(R.id.ibtnBack);
        btnEdit = findViewById(R.id.btnEditKhachHang);
    }
}