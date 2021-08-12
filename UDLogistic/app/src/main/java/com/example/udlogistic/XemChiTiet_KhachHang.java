package com.example.udlogistic;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;


import com.example.udlogistic.model.KhachHang;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class XemChiTiet_KhachHang extends AppCompatActivity {
    Button btnEdit,btnDelete;
    EditText edtTenKhachHang,edtDiaChi,edtSDT;
    DatabaseReference mDatabase;
    ImageButton ibtnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xemchitiet_khachhang);
        mDatabase = FirebaseDatabase.getInstance("https://quanlyvanchuyen-8879e-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference();
        setControl();
        setEvent();
    }

    private void setEvent() {
        Bundle bundle = getIntent().getExtras();
        if (bundle == null){
            return;
        }
        KhachHang khachHang = (KhachHang) bundle.get("data_khachhang");
        LoadKhachHang(khachHang);
        ibtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), QuanLyKhachHang.class));
                overridePendingTransition(0, 0);
                finish();
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDatabase.child("KhachHang").child(khachHang.getMaKH().toString()).removeValue(new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(@Nullable DatabaseError error, DatabaseReference ref) {
                        Toast.makeText(XemChiTiet_KhachHang.this, "Xóa thành công", Toast.LENGTH_SHORT).show();
                    }
                });
                finish();
            }
        });
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SuaKhachKhang.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("data_khachhang", khachHang);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    private void LoadKhachHang(KhachHang khachHang) {
        edtTenKhachHang.setText(khachHang.getTenKhachHang());
        edtDiaChi.setText(khachHang.getDiaChi());
        edtSDT.setText(khachHang.getSDT());
    }

    private void setControl() {
        btnEdit = findViewById(R.id.btnEdit);
        btnDelete = findViewById(R.id.btnDelete);
        edtTenKhachHang =findViewById(R.id.edtTenKhachHang);
        edtDiaChi = findViewById(R.id.edtDiaChi);
        edtSDT = findViewById(R.id.edtSoDienThoai);
        ibtnBack = findViewById(R.id.ibtnBack);

    }
}