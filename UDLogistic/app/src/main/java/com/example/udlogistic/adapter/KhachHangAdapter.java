package com.example.udlogistic.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.udlogistic.my_interface.IClickItemKhachHangListener;
import com.example.udlogistic.model.KhachHang;
import com.example.udlogistic.R;

import java.util.ArrayList;

public class KhachHangAdapter extends RecyclerView.Adapter<KhachHangAdapter.KhachHangViewHolder> {

    ArrayList<KhachHang> mList;
    IClickItemKhachHangListener iClickItemKhachHangListener;
    public KhachHangAdapter(ArrayList<KhachHang> mList, IClickItemKhachHangListener listener) {
        this.mList = mList;
        this.iClickItemKhachHangListener = listener;
    }

    @NonNull
    @Override
    public KhachHangViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_item_khachhang, parent, false);
        return new KhachHangViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull KhachHangAdapter.KhachHangViewHolder holder, int position) {
        KhachHang khachHang = mList.get(position);
        if (khachHang == null){
            return;
        }
        holder.imgAvatar.setImageResource(R.drawable.unnamed);
        holder.tvTenKhachHang.setText(khachHang.getTenKhachHang());
        holder.tvDiaChi.setText(khachHang.getDiaChi());
        holder.tvSDT.setText(khachHang.getSDT());
        holder.lnKhachHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iClickItemKhachHangListener.onClickItemKhachHang(khachHang);
            }
        });
    }


    @Override
    public int getItemCount() {
        if (mList != null) {
            return mList.size();
        }
        return 0;
    }

    public class KhachHangViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgAvatar;
        private TextView tvTenKhachHang, tvDiaChi, tvSDT;
        LinearLayout lnKhachHang;

        public KhachHangViewHolder(@NonNull View itemView) {
            super(itemView);

            imgAvatar = itemView.findViewById(R.id.imgAvatar);
            tvTenKhachHang = itemView.findViewById(R.id.tvTenKhachHang);
            tvDiaChi = itemView.findViewById(R.id.tvDiaChi);
            tvSDT = itemView.findViewById(R.id.tvSDT);
            lnKhachHang =itemView.findViewById(R.id.lnKhachHang);

        }
    }

}
