package com.example.pet;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;


public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context) {
        super(context, "Makanan.db", null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table DataMakanan(namaMakanan TEXT primary key, beratMakanan INT, hargaMakanan INT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int ii) {
        DB.execSQL("drop Table if exists DataMakanan");
    }

    public Boolean insertDataMakanan(String namaMakanan, int beratMakanan, int hargaMakanan){
        SQLiteDatabase DB  = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("namaMakanan", namaMakanan);
        contentValues.put("beratMakanan", beratMakanan);
        contentValues.put("hargaMakanan", hargaMakanan);
        long result = DB.insert("DataMakanan", null, contentValues);
        if (result==-1){
            return false;
        }else {
            return true;
        }
    }
    public Cursor getData(){
        SQLiteDatabase DB= this.getReadableDatabase();
        Cursor cursor = DB.rawQuery("Select * from DataMakanan", null);

        return cursor;
    }
}
