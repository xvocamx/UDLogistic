package com.example.udlogistic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.udlogistic.model.NhanVien;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;

public class ThemNhanVien extends AppCompatActivity {
    EditText edtTenNhanVien, edtDiaChi, edtSoDienThoai, edtBoPhan, edtChucVu;
    ImageButton ibtnBack;
    Button btnThemNhanVien;
    DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_nhanvien);
        mDatabase = FirebaseDatabase.getInstance("https://quanlyvanchuyen-8879e-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference();
        setControl();
        setEvent();
    }

    private void setEvent() {
        ibtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), QuanLyNhanVien.class));
                overridePendingTransition(0, 0);
                finish();
            }
        });
        btnThemNhanVien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(KiemTra()){
                    String tenNhanVien = edtTenNhanVien.getText().toString();
                    String diaChi = edtDiaChi.getText().toString();
                    String soDienThoai = edtSoDienThoai.getText().toString();
                    String boPhan = edtBoPhan.getText().toString();
                    String chucVu = edtChucVu.getText().toString();
                    String maNV = UUID.randomUUID().toString();
                    NhanVien nhanVien = new NhanVien(maNV,tenNhanVien,diaChi,soDienThoai,boPhan,chucVu);
                    mDatabase.child("NhanVien").child(maNV).setValue(nhanVien).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText(ThemNhanVien.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                        }
                    });
                    startActivity(new Intent(getApplicationContext(), QuanLyNhanVien.class));
                    overridePendingTransition(0, 0);
                    finish();
                }
            }
        });
    }

    private boolean KiemTra() {
        boolean res = true;
        if (edtTenNhanVien.getText().toString().equals("")) {
            edtTenNhanVien.setError("Vui lòng nhập tên nhân viên");
            res = false;
        }
        if (edtDiaChi.getText().toString().equals("")) {
            edtDiaChi.setError("Vui lòng nhập địa chỉ");
            res = false;
        }
        if (edtSoDienThoai.getText().toString().equals("")) {
            edtSoDienThoai.setError("Vui lòng nhập số điện thoại");
            res = false;
        }
        if (edtBoPhan.getText().toString().equals("")) {
            edtBoPhan.setError("Vui lòng nhập bộ phận");
            res = false;
        }
        if (edtChucVu.getText().toString().equals("")) {
            edtChucVu.setError("Vui lòng nhập chức vự");
            res = false;
        }

        return res;
    }

    private void setControl() {
        edtTenNhanVien = findViewById(R.id.edtTenNhanVien);
        edtDiaChi = findViewById(R.id.edtDiaChi);
        edtSoDienThoai = findViewById(R.id.edtSoDienThoai);
        edtBoPhan = findViewById(R.id.edtBoPhan);
        edtChucVu = findViewById(R.id.edtChucVu);
        ibtnBack = findViewById(R.id.ibtnBack);
        btnThemNhanVien = findViewById(R.id.btnThemNhanVien);
    }
}