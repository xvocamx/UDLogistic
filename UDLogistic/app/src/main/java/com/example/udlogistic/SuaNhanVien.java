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

public class SuaNhanVien extends AppCompatActivity {
    EditText edtTenNhanVien, edtDiaChi, edtSoDienThoai, edtBoPhan, edtChucVu;
    ImageButton ibtnBack;
    DatabaseReference mDatabase;
    Button btnEdit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sua_nhanvien);
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
                Intent intent = new Intent(getApplicationContext(), XemChiTiet_NhanVien.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("data_nhanvien", nhanVien);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(KiemTra()){
                    String tenNhanVien = edtTenNhanVien.getText().toString();
                    String diaChi = edtDiaChi.getText().toString();
                    String soDienThoai = edtSoDienThoai.getText().toString();
                    String boPhan = edtBoPhan.getText().toString();
                    String chucVu = edtChucVu.getText().toString();
                    String maNV = nhanVien.getMaNV().toString();
                    NhanVien nhanVien1 = new NhanVien(maNV,tenNhanVien,diaChi,soDienThoai,boPhan,chucVu);
                    mDatabase.child("NhanVien").child(maNV).setValue(nhanVien1);
                    startActivity(new Intent(getApplicationContext(), QuanLyNhanVien.class));
                    overridePendingTransition(0, 0);
                    finish();
                }
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
    private boolean KiemTra() {
        boolean res = true;
        if (edtTenNhanVien.getText().toString().equals("")) {
            edtTenNhanVien.setError("Vui l??ng nh???p t??n nh??n vi??n");
            res = false;
        }
        if (edtDiaChi.getText().toString().equals("")) {
            edtDiaChi.setError("Vui l??ng nh???p ?????a ch???");
            res = false;
        }
        if (edtSoDienThoai.getText().toString().equals("")) {
            edtSoDienThoai.setError("Vui l??ng nh???p s??? ??i???n tho???i");
            res = false;
        }
        if (edtBoPhan.getText().toString().equals("")) {
            edtBoPhan.setError("Vui l??ng nh???p b??? ph???n");
            res = false;
        }
        if (edtChucVu.getText().toString().equals("")) {
            edtChucVu.setError("Vui l??ng nh???p ch???c v???");
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
        btnEdit = findViewById(R.id.btnEditNhanVien);
    }
}