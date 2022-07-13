package com.example.pet;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;


public class DBHelperAksesoris extends SQLiteOpenHelper {
    public DBHelperAksesoris(Context context) {
        super(context, "Aksesoris.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table DataAksesoris(namaAksesoris TEXT primary key, hargaAksesoris INT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int ii) {
        DB.execSQL("drop Table if exists DataAksesoris");
    }

    public Boolean insertDataAksesoris(String namaAksesoris, int hargaAksesoris){
        SQLiteDatabase DB  = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("namaAksesoris", namaAksesoris);
        contentValues.put("hargaAksesoris", hargaAksesoris);
        long result = DB.insert("DataAksesoris", null, contentValues);
        if (result==-1){
            return false;
        }else {
            return true;
        }
    }
    public Cursor getData(){
        SQLiteDatabase DB= this.getReadableDatabase();
        Cursor cursor = DB.rawQuery("Select * from DataAksesoris", null);

        return cursor;
    }
}
