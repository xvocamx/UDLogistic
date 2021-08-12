package com.example.udlogistic.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.udlogistic.R;
import com.example.udlogistic.model.NhanVien;
import com.example.udlogistic.my_interface.IClickItemNhanVienListener;

import java.util.ArrayList;

public class NhanVienAdapter extends RecyclerView.Adapter<NhanVienAdapter.NhanVienViewHolder> {

    private ArrayList<NhanVien> mList;
    IClickItemNhanVienListener iClickItemNhanVienListener;

    public NhanVienAdapter(ArrayList<NhanVien> mList, IClickItemNhanVienListener listener) {
        this.mList = mList;
        this.iClickItemNhanVienListener = listener;
    }

    @NonNull
    @Override
    public NhanVienViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_item_nhanvien,parent,false);
        return new NhanVienViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NhanVienAdapter.NhanVienViewHolder holder, int position) {
        NhanVien nhanVien = mList.get(position);
        if(nhanVien == null){
            return;
        }
        holder.imgAvatar.setImageResource(R.drawable.avatar);
        holder.tvTenNhanVien.setText(nhanVien.getTenNV());
        holder.tvDiaChi.setText(nhanVien.getDiaChi());
        holder.tvSDT.setText(nhanVien.getSoDT());
        holder.lnNhanVien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iClickItemNhanVienListener.onClickItemNhanVien(nhanVien);
            }
        });
    }


    @Override
    public int getItemCount() {
        if(mList !=null){
            return mList.size();
        }
        return 0;
    }

    public class NhanVienViewHolder extends RecyclerView.ViewHolder {

        private ImageView imgAvatar;
        private TextView tvTenNhanVien, tvDiaChi, tvSDT;
        private LinearLayout lnNhanVien;

        public NhanVienViewHolder(@NonNull View itemView) {
            super(itemView);

            imgAvatar = itemView.findViewById(R.id.imgAvatar);
            tvTenNhanVien = itemView.findViewById(R.id.tvTenNhanVien);
            tvDiaChi = itemView.findViewById(R.id.tvDiaChi);
            tvSDT = itemView.findViewById(R.id.tvSDT);
            lnNhanVien = itemView.findViewById(R.id.lnNhanVien);
        }
    }
}
