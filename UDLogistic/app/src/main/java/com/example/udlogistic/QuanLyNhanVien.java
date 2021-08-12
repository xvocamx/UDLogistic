package com.example.udlogistic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;


import com.example.udlogistic.adapter.NhanVienAdapter;
import com.example.udlogistic.model.NhanVien;
import com.example.udlogistic.my_interface.IClickItemNhanVienListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class QuanLyNhanVien extends AppCompatActivity {
    RecyclerView rcvDanhSachNhanVien;
    NhanVienAdapter nhanVienAdapter;
    DatabaseReference mDatabase;
    ImageButton ibtnBack;
    Button btnThemNhanVien;
    ArrayList<NhanVien> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nhanvien);
        mDatabase = FirebaseDatabase.getInstance("https://quanlyvanchuyen-8879e-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference();
        setControl();
        LoadData();
        setEvent();
    }

    private void setEvent() {
        btnThemNhanVien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ThemNhanVien.class));
                overridePendingTransition(0, 0);
                finish();
            }
        });
        ibtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Home.class));
                overridePendingTransition(0, 0);
                finish();
            }
        });
    }

    private void LoadData() {
        mDatabase.child("NhanVien").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull  DataSnapshot snapshot) {
                list.clear();
                for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                    NhanVien nhanVien = dataSnapshot.getValue(NhanVien.class);
                    list.add(nhanVien);
                }
                nhanVienAdapter = new NhanVienAdapter(list, new IClickItemNhanVienListener() {
                    @Override
                    public void onClickItemNhanVien(NhanVien nhanVien) {
                        onClickChiTiet(nhanVien);
                    }
                });
                rcvDanhSachNhanVien.setAdapter(nhanVienAdapter);
                nhanVienAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull  DatabaseError error) {

            }
        });
        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        rcvDanhSachNhanVien.setHasFixedSize(true);
                        rcvDanhSachNhanVien.setLayoutManager(new LinearLayoutManager(QuanLyNhanVien.this));
                        rcvDanhSachNhanVien.addItemDecoration(new DividerItemDecoration(QuanLyNhanVien.this, DividerItemDecoration.VERTICAL));
                        nhanVienAdapter = new NhanVienAdapter(list, new IClickItemNhanVienListener() {
                            @Override
                            public void onClickItemNhanVien(NhanVien nhanVien) {
                                onClickChiTiet(nhanVien);
                            }
                        });
                        rcvDanhSachNhanVien.setAdapter(nhanVienAdapter);
                        nhanVienAdapter.notifyDataSetChanged();

                    }
                },
                100);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void onClickChiTiet(NhanVien nhanVien) {
        Intent intent = new Intent(this, XemChiTiet_NhanVien.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("data_nhanvien", nhanVien);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    private void setControl() {
        rcvDanhSachNhanVien = findViewById(R.id.rcvDanhSachNhanVien);
        ibtnBack = findViewById(R.id.ibtnBack);
        btnThemNhanVien = findViewById(R.id.btnThemNhanVien);
    }
}