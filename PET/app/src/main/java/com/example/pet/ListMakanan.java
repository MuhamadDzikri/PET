package com.example.pet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ListMakanan extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<String> namaProduk, beratProduk, hargaProduk;
    DBHelper db;
    MyAdapter adapter;
    FloatingActionButton add_button;
    Button delete_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_makanan);
        db = new DBHelper(this);
        namaProduk = new ArrayList<>();
        beratProduk = new ArrayList<>();
        hargaProduk = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerview);
        adapter = new MyAdapter(this, namaProduk, beratProduk, hargaProduk);
        recyclerView.setAdapter(adapter);
        recyclerView.setSelected(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        add_button = (FloatingActionButton) findViewById(R.id.add_makanan);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ListMakanan.this,EntryMakanan.class);
                startActivity(i);
                finish();
            }
        });
        /*delete_button = findViewById(R.id.buttonHapus);
        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });*/
        displayMakanan();
    }
    private void displayMakanan() {
        Cursor cursor = db.getData();
        if (cursor.getCount()==0){
            Toast.makeText(ListMakanan.this, "Data Kosong", Toast.LENGTH_LONG).show();
            return;
        }else {
            while (cursor.moveToNext()){
                namaProduk.add(cursor.getString(0));
                beratProduk.add(cursor.getString(1));
                hargaProduk.add(cursor.getString(2));
            }
        }
    }

}