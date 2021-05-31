package com.sandsteam.dencalc2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Array;
import java.util.ArrayList;

import AddOn.Barang;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.BarangViewHold> {

    private ArrayList<Barang> liistBarang;

    public RVAdapter(ArrayList<Barang> liistBarang) {
        this.liistBarang = liistBarang;
    }
    @NonNull
    @Override
    public BarangViewHold onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view =layoutInflater.inflate(R.layout.barang_cardview, parent, false);
        return new BarangViewHold(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RVAdapter.BarangViewHold holder, int position) {
        holder.cv_infoJumlah.setText(String.valueOf(liistBarang.get(position).getJumlah()));
        holder.cv_infoPemakaian.setText(String.valueOf(liistBarang.get(position).getTotal_pemakaian()));
        holder.cv_infoWatt.setText(String.valueOf(liistBarang.get(position).getWatt_barang()));
        holder.cv_kategoriBarang.setText(String.valueOf(liistBarang.get(position).getTipe_barang()));
    }

    @Override
    public int getItemCount() {
        return liistBarang.size();
    }

    public class BarangViewHold extends RecyclerView.ViewHolder {
        private TextView cv_infoWatt, cv_infoJumlah, cv_infoPemakaian, cv_kategoriBarang;
        private ImageView cv_editButton, cv_deleteButton;
        public BarangViewHold(View itemView) {
            super(itemView);
            cv_infoWatt = itemView.findViewById(R.id.cv_infoWatt);
            cv_infoJumlah = itemView.findViewById(R.id.cv_infoJumlah);
            cv_infoPemakaian = itemView.findViewById(R.id.cv_infoPemakaian);
            cv_kategoriBarang = itemView.findViewById(R.id.cv_kategoriBarang);
            cv_editButton = itemView.findViewById(R.id.cv_editButton);
            cv_deleteButton = itemView.findViewById(R.id.cv_deleteButton);
        }
    }
}
