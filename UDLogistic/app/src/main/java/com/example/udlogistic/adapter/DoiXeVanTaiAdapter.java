package com.example.udlogistic.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.udlogistic.model.DoiXeVanTai;
import com.example.udlogistic.my_interface.IClickIitemDoiXeVanTaiListener;
import com.example.udlogistic.R;

import java.util.ArrayList;

public class DoiXeVanTaiAdapter extends RecyclerView.Adapter<DoiXeVanTaiAdapter.DoiXeVanTaiViewHolder> {

    Context context;
    ArrayList<DoiXeVanTai> data;
    IClickIitemDoiXeVanTaiListener iClickIitemDoiXeVanTaiListener;

    public DoiXeVanTaiAdapter(Context context, ArrayList<DoiXeVanTai> data, IClickIitemDoiXeVanTaiListener listener) {
        this.context = context;
        this.data = data;
        this.iClickIitemDoiXeVanTaiListener = listener;
    }

    @NonNull
    @Override
    public DoiXeVanTaiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_item_doixevantai, parent, false);
        return new DoiXeVanTaiViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DoiXeVanTaiAdapter.DoiXeVanTaiViewHolder holder, int position) {
        DoiXeVanTai doiXeVanTai = data.get(position);
        if (doiXeVanTai == null) {
            return;
        }

        holder.tvSoThuTu.setText(position+1 + "");
        holder.tvSoXe.setText(doiXeVanTai.getSoXe());
        holder.tvKhachHang.setText(doiXeVanTai.getKhachHang());
        holder.layout_Item_DoiXeVanTai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iClickIitemDoiXeVanTaiListener.onClickItemDoiXeVanTai(doiXeVanTai);
            }
        });
    }


    @Override
    public int getItemCount() {
        if (data != null) {
            return data.size();
        }
        return 0;
    }

    public class DoiXeVanTaiViewHolder extends RecyclerView.ViewHolder {
        TableLayout layout_Item_DoiXeVanTai;
        TextView tvSoThuTu, tvSoXe, tvKhachHang;

        public DoiXeVanTaiViewHolder(@NonNull View itemView) {
            super(itemView);
            tvSoThuTu = itemView.findViewById(R.id.tvSoThuTu);
            tvSoXe = itemView.findViewById(R.id.tvSoXe);
            tvKhachHang = itemView.findViewById(R.id.tvKhachHang);

            layout_Item_DoiXeVanTai = itemView.findViewById(R.id.layout_item_doixevantai);

        }
    }
}
