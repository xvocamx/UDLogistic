package com.example.udlogistic;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.udlogistic.model.DoiXeVanTai;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.UUID;

public class ThemDoiXeVanTai extends AppCompatActivity {
    EditText edtSoXe, edtSoContainer, edtNgayDiGiao, edtNoiLayCong, edtNoiDongHang, edtDonGia, edtKhachHang;
    ImageButton ibtnBack;
    Button btnSave;
    DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_doixe_vantai);
        mDatabase = FirebaseDatabase.getInstance("https://quanlyvanchuyen-8879e-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference();
        setControl();
        setEvent();
    }

    private void setEvent() {
        edtNgayDiGiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChonNgay();
            }
        });
        ibtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), QuanLyDoiXeVanTai.class));
                overridePendingTransition(0, 0);
                finish();
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (KiemTra()) {
                    String soXe = edtSoXe.getText().toString();
                    String khachHang = edtKhachHang.getText().toString();
                    String soContainer = edtSoContainer.getText().toString();
                    String ngayDiGiao = edtNgayDiGiao.getText().toString();
                    String noiLayCong = edtNoiLayCong.getText().toString();
                    String noiDongHang = edtNoiDongHang.getText().toString();
                    String donGia = edtDonGia.getText().toString();
                    String ma = UUID.randomUUID().toString();
                    String maUI = ma;
                    DoiXeVanTai doiXeVanTai = new DoiXeVanTai(maUI, soXe, soContainer, donGia, khachHang, ngayDiGiao, noiLayCong, noiDongHang);
                    mDatabase.child("DoiXeVanTai").child(ma.toString()).setValue(doiXeVanTai);
                    startActivity(new Intent(getApplicationContext(), QuanLyDoiXeVanTai.class));
                    overridePendingTransition(0, 0);
                    finish();
                }

            }
        });

    }

    private void ChonNgay() {
        //Lay ngay hien tai
        final Calendar calendar = Calendar.getInstance();
        int ngay = calendar.get(Calendar.DATE);
        int thang = calendar.get(Calendar.MONTH);
        int nam = calendar.get(Calendar.YEAR);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(year, month, dayOfMonth);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                edtNgayDiGiao.setText(simpleDateFormat.format(calendar.getTime()));
            }
        }, nam, thang, ngay);
        datePickerDialog.show();
    }

    private boolean KiemTra() {
        boolean res = true;
        if (edtSoXe.getText().toString().equals("")) {
            edtSoXe.setError("Vui lòng nhập số xe");
            res = false;
        }
        if (edtKhachHang.getText().toString().equals("")) {
            edtKhachHang.setError("Vui lòng nhập tên khách hàng");
            res = false;
        }
        if (edtNgayDiGiao.getText().toString().equals("")) {
            edtNgayDiGiao.setError("Vui lòng nhập ngày đi giao");
            res = false;
        }
        if (edtNoiLayCong.getText().toString().equals("")) {
            edtNoiLayCong.setError("Vui lòng nhập nơi lấy công");
            res = false;
        }
        if (edtNoiDongHang.getText().toString().equals("")) {
            edtNoiDongHang.setError("Vui lòng nhập nơi đóng hàng");
            res = false;
        }
        if (edtDonGia.getText().toString().equals("")) {
            edtDonGia.setError("Vui lòng nhập đơn giá");
            res = false;
        }
        return res;
    }

    private void setControl() {
        edtKhachHang = findViewById(R.id.edtKhachHang);
        edtNgayDiGiao = findViewById(R.id.edtNgayDiGiao);
        edtSoContainer = findViewById(R.id.edtSoContainer);
        edtNoiLayCong = findViewById(R.id.edtNoiLayCong);
        edtNoiDongHang = findViewById(R.id.edtNoiDongHang);
        edtDonGia = findViewById(R.id.edtDonGia);
        edtSoXe = findViewById(R.id.edtSoXe);
        ibtnBack = findViewById(R.id.ibtnBack);
        btnSave = findViewById(R.id.btnSave);
    }
}