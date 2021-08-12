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

import com.example.udlogistic.model.DoiXeVanTai;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class XemChiTiet_DoiXeVanTai extends AppCompatActivity {
    EditText edtSoXe, edtSoContainer, edtNgayDiGiao, edtNoiLayCong, edtNoiDongHang, edtDonGia, edtKhachHang;
    ImageButton ibtnBack;
    Button btnDelete, btnEdit;
    DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xemthongtinchitiet_doixevantai);
        mDatabase = FirebaseDatabase.getInstance("https://quanlyvanchuyen-8879e-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference();
        setControl();
        setEvent();
    }

    private void setEvent() {
        Bundle bundle = getIntent().getExtras();
        if (bundle == null) {
            return;
        }
        DoiXeVanTai doiXeVanTai = (DoiXeVanTai) bundle.get("data_doixevantai");
        LoadDoiXeVanTai(doiXeVanTai);
        ibtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), QuanLyDoiXeVanTai.class));
                overridePendingTransition(0, 0);
                finish();
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDatabase.child("DoiXeVanTai").child(doiXeVanTai.getMaUI().toString()).removeValue(new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(@Nullable DatabaseError error, DatabaseReference ref) {
                        Toast.makeText(XemChiTiet_DoiXeVanTai.this, "Xóa thành công", Toast.LENGTH_SHORT).show();
                    }
                });
                finish();
            }
        });
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SuaDoiXeVanTai.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("data_doixevantai", doiXeVanTai);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

    }

    private void LoadDoiXeVanTai(DoiXeVanTai doiXeVanTai) {
        edtSoXe.setText(doiXeVanTai.getSoXe());
        edtKhachHang.setText(doiXeVanTai.getKhachHang());
        edtSoContainer.setText(doiXeVanTai.getSoContainer());
        edtNgayDiGiao.setText(doiXeVanTai.getNgayDiGiao());
        edtNoiLayCong.setText(doiXeVanTai.getNoiLayCong());
        edtNoiDongHang.setText(doiXeVanTai.getNoiDongHang());
        edtDonGia.setText(doiXeVanTai.getDonGia());
    }

    private void setControl() {
        edtSoXe = findViewById(R.id.edtSoXe);
        edtSoContainer = findViewById(R.id.edtSoContainer);
        edtNgayDiGiao = findViewById(R.id.edtNgayDiGiao);
        edtNoiLayCong = findViewById(R.id.edtNoiLayCong);
        edtNoiDongHang = findViewById(R.id.edtNoiDongHang);
        edtDonGia = findViewById(R.id.edtDonGia);
        edtKhachHang = findViewById(R.id.edtKhachHang);
        ibtnBack = findViewById(R.id.ibtnBack);
        btnDelete = findViewById(R.id.btnDelete);
        btnEdit = findViewById(R.id.btnEdit);
    }
}