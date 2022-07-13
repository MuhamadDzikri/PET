package com.example.pet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ListAksesoris extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<String> namaAksesoris,hargaAksesoris;
    DBHelperAksesoris DB;
    MyAdapterAksesoris adapter;
    FloatingActionButton add_aksesoris;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_aksesori);
        DB = new DBHelperAksesoris(this);
        namaAksesoris = new ArrayList<>();
        hargaAksesoris = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerview);
        adapter = new MyAdapterAksesoris(this, namaAksesoris,hargaAksesoris);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        add_aksesoris = findViewById(R.id.add_aksesoris);
        add_aksesoris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ListAksesoris.this, EntryAksesoris.class);
                startActivity(i);
                finish();
            }
        });
        displaydata();

    }

    private void displaydata() {
        Cursor cursor = DB.getData();
        if (cursor.getCount()==0){
            Toast.makeText(ListAksesoris.this, "tidak ada data",Toast.LENGTH_SHORT).show();
            return;
        }else{
            while (cursor.moveToNext()){{
                namaAksesoris.add(cursor.getString(0));
                hargaAksesoris.add(cursor.getString(1));
            }}

        }


    }
}