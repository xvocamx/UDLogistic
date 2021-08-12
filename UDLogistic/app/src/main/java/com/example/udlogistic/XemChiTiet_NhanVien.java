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

import com.example.udlogistic.model.NhanVien;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class XemChiTiet_NhanVien extends AppCompatActivity {
    Button btnEdit,btnDelete;
    EditText edtTenNhanVien, edtDiaChi, edtSoDienThoai, edtBoPhan, edtChucVu;
    DatabaseReference mDatabase;
    ImageButton ibtnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xemchitiet_nhanvien);
        mDatabase = FirebaseDatabase.getInstance("https://quanlyvanchuyen-8879e-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference();
        setControl();
        setEvent();
    }

    private void setEvent() {
        Bundle bundle = getIntent().getExtras();
        if (bundle == null){
            return;
        }
        NhanVien nhanVien = (NhanVien) bundle.get("data_nhanvien");
        LoadNhanVien(nhanVien);
        ibtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), QuanLyNhanVien.class));
                overridePendingTransition(0, 0);
                finish();
            }
        });
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SuaNhanVien.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("data_nhanvien", nhanVien);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDatabase.child("NhanVien").child(nhanVien.getMaNV().toString()).removeValue(new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(@Nullable DatabaseError error, DatabaseReference ref) {
                        Toast.makeText(XemChiTiet_NhanVien.this, "Xóa thành công", Toast.LENGTH_SHORT).show();
                    }
                });
                finish();
            }
        });

    }

    private void LoadNhanVien(NhanVien nhanVien) {
        edtTenNhanVien.setText(nhanVien.getTenNV());
        edtDiaChi.setText(nhanVien.getDiaChi());
        edtSoDienThoai.setText(nhanVien.getSoDT());
        edtBoPhan.setText(nhanVien.getBoPhan());
        edtChucVu.setText(nhanVien.getChucVu());
    }

    private void setControl() {
        edtTenNhanVien = findViewById(R.id.edtTenNhanVien);
        edtDiaChi = findViewById(R.id.edtDiaChi);
        edtSoDienThoai = findViewById(R.id.edtSoDienThoai);
        edtBoPhan = findViewById(R.id.edtBoPhan);
        edtChucVu = findViewById(R.id.edtChucVu);
        ibtnBack = findViewById(R.id.ibtnBack);
        btnDelete = findViewById(R.id.btnDelete);
        btnEdit = findViewById(R.id.btnEdit);
    }
}