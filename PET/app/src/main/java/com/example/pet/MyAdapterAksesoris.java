package com.example.pet;
import android.content.Context;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;

public class MyAdapterAksesoris extends RecyclerView.Adapter<MyAdapterAksesoris.MyViewHolder> {
    private Context context;
    private ArrayList nama_id, harga_id;

    public MyAdapterAksesoris(Context context, ArrayList nama_id, ArrayList harga_id) {
        this.context = context;
        this.nama_id = nama_id;
        this.harga_id = harga_id;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.entryaksesoris, parent, false);
        return new MyViewHolder(v);
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.nama_id.setText(String.valueOf(nama_id.get(position)));
        holder.harga_id.setText(String.valueOf(harga_id.get(position)));
    }

    @Override
    public int getItemCount() {
        return nama_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {
        MaterialTextView nama_id, harga_id;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            nama_id = itemView.findViewById(R.id.textNamaAksesoris);
            harga_id = itemView.findViewById(R.id.textHargaAksesoris);

            itemView.setOnCreateContextMenuListener(this);
        }

        @Override
        public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {

        }
    }
}