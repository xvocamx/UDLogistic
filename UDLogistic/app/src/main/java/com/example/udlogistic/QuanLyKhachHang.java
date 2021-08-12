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

import com.example.udlogistic.adapter.KhachHangAdapter;
import com.example.udlogistic.model.KhachHang;
import com.example.udlogistic.my_interface.IClickItemKhachHangListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class QuanLyKhachHang extends AppCompatActivity {
    RecyclerView rcvDanhSachKhachHang;
    KhachHangAdapter khachHangAdapter;
    DatabaseReference mDatabase;
    Button btnThem;
    ArrayList<KhachHang> list = new ArrayList<>();
    ImageButton ibtnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_khachhang);
        mDatabase = FirebaseDatabase.getInstance("https://quanlyvanchuyen-8879e-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference();
        setControl();
        LoadData();
        setEvent();
    }

    private void setEvent() {
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ThemKhachHang.class));
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
        mDatabase.child("KhachHang").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    KhachHang khachHang = dataSnapshot.getValue(KhachHang.class);
                    list.add(khachHang);
                }
                khachHangAdapter = new KhachHangAdapter(list, new IClickItemKhachHangListener() {
                    @Override
                    public void onClickItemKhachHang(KhachHang khachHang) {
                        onClickChiTiet(khachHang);
                    }
                });
                rcvDanhSachKhachHang.setAdapter(khachHangAdapter);
                khachHangAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        rcvDanhSachKhachHang.setHasFixedSize(true);
                        rcvDanhSachKhachHang.setLayoutManager(new LinearLayoutManager(QuanLyKhachHang.this));
                        rcvDanhSachKhachHang.addItemDecoration(new DividerItemDecoration(QuanLyKhachHang.this, DividerItemDecoration.VERTICAL));
                        khachHangAdapter = new KhachHangAdapter(list, new IClickItemKhachHangListener() {
                            @Override
                            public void onClickItemKhachHang(KhachHang khachHang) {
                                onClickChiTiet(khachHang);
                            }
                        });
                        rcvDanhSachKhachHang.setAdapter(khachHangAdapter);
                        khachHangAdapter.notifyDataSetChanged();

                    }
                },
                100);


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void onClickChiTiet(KhachHang khachHang) {
        Intent intent = new Intent(this, XemChiTiet_KhachHang.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("data_khachhang", khachHang);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    private void setControl() {
        rcvDanhSachKhachHang = findViewById(R.id.rcvDanhSachKhachHang);
        btnThem = findViewById(R.id.btnThemKhachHang);
        ibtnBack = findViewById(R.id.ibtnBack);
    }
}