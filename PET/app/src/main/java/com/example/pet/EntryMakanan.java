package com.example.pet;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.io.IOException;

public class EntryMakanan extends AppCompatActivity {
    TextInputEditText nama, berat, harga;
    AppCompatButton submit;
    private ImageView gambar_produk;
    private static final int PICK_IMAGE=1;
    Uri imageUri;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry_makanan);

        nama = (TextInputEditText) findViewById(R.id.productName);
        berat = (TextInputEditText) findViewById(R.id.productWeight);
        harga = (TextInputEditText) findViewById(R.id.productPrice);
        submit = (AppCompatButton) findViewById(R.id.buttonSubmit);
        DB = new DBHelper(this);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String namaTXT = nama.getText().toString();
                int beratTXT = Integer.parseInt(berat.getText().toString());
                int hargaTXT = Integer.parseInt(harga.getText().toString());

                Boolean cekData = DB.insertDataMakanan(namaTXT, beratTXT, hargaTXT);
                if (cekData==true){
                    Toast.makeText(EntryMakanan.this, "Data berhasil ditambahkan", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(EntryMakanan.this, ListMakanan.class));
                }else{
                    Toast.makeText(EntryMakanan.this, "Data tidak berhasil ditambahkan", Toast.LENGTH_SHORT).show();
                }
            }
        });


        gambar_produk = (ImageView) findViewById(R.id.gambarProduk);
        gambar_produk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gallery = new Intent();
                gallery.setType("image/*");
                gallery.setAction(Intent.ACTION_GET_CONTENT);

                startActivityForResult(Intent.createChooser(gallery, "Pilih gambar"),PICK_IMAGE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK){
            imageUri=data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                gambar_produk.setImageBitmap(bitmap);
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}