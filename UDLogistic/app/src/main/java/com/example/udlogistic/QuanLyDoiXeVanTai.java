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

import com.example.udlogistic.adapter.DoiXeVanTaiAdapter;
import com.example.udlogistic.model.DoiXeVanTai;
import com.example.udlogistic.my_interface.IClickIitemDoiXeVanTaiListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class QuanLyDoiXeVanTai extends AppCompatActivity {
    RecyclerView rcvDanhSachDoiXeVanTai;
    DoiXeVanTaiAdapter doiXeVanTaiAdapter;
    DatabaseReference mDatabase;
    DoiXeVanTai doiXeVanTai;
    Button btnThem;
    ArrayList<DoiXeVanTai> list = new ArrayList<>();
    ImageButton ibtnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doivantaixe);
        mDatabase = FirebaseDatabase.getInstance("https://quanlyvanchuyen-8879e-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference();
        setControl();
        LoadData();
        setEvent();
    }

    private void setEvent() {

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ThemDoiXeVanTai.class));
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
        mDatabase.child("DoiXeVanTai").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    DoiXeVanTai doiXeVanTai = dataSnapshot.getValue(DoiXeVanTai.class);
                    list.add(doiXeVanTai);
                }
                doiXeVanTaiAdapter = new DoiXeVanTaiAdapter(QuanLyDoiXeVanTai.this, list, new IClickIitemDoiXeVanTaiListener() {
                    @Override
                    public void onClickItemDoiXeVanTai(DoiXeVanTai doiXeVanTai) {
                        onClickChiTiet(doiXeVanTai);
                    }
                });
                rcvDanhSachDoiXeVanTai.setAdapter(doiXeVanTaiAdapter);
                doiXeVanTaiAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        rcvDanhSachDoiXeVanTai.setHasFixedSize(true);
                        rcvDanhSachDoiXeVanTai.setLayoutManager(new LinearLayoutManager(QuanLyDoiXeVanTai.this));
                        rcvDanhSachDoiXeVanTai.addItemDecoration(new DividerItemDecoration(QuanLyDoiXeVanTai.this, DividerItemDecoration.VERTICAL));
                        doiXeVanTaiAdapter = new DoiXeVanTaiAdapter(QuanLyDoiXeVanTai.this, list, new IClickIitemDoiXeVanTaiListener() {
                            @Override
                            public void onClickItemDoiXeVanTai(DoiXeVanTai doiXeVanTai) {
                                onClickChiTiet(doiXeVanTai);
                            }
                        });
                        rcvDanhSachDoiXeVanTai.setAdapter(doiXeVanTaiAdapter);
                        doiXeVanTaiAdapter.notifyDataSetChanged();

                    }
                },
                100);


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void onClickChiTiet(DoiXeVanTai doiXeVanTai) {
        Intent intent = new Intent(this, XemChiTiet_DoiXeVanTai.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("data_doixevantai", doiXeVanTai);
        intent.putExtras(bundle);
        startActivity(intent);
    }


    private void setControl() {
        rcvDanhSachDoiXeVanTai = findViewById(R.id.rcvDanhSachDoiVanXeTai);
        btnThem = findViewById(R.id.btnThem);
        ibtnBack = findViewById(R.id.ibtnBack);
    }
}