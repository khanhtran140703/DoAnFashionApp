package com.example.doanfashionapp.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.doanfashionapp.DTO.HangSanPham;
import com.example.doanfashionapp.DTO.SanPham;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper{
    public static final String DB_NAME ="FashionAppDB.db";
    private static final int DB_VERSION = 1;

    public static String path = "/data/data/com.example.doanfashionapp/db/FashionAppDB.db";

    private Context context;

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public DBHelper(@Nullable Context context1) {
        super(context1, DB_NAME, null, DB_VERSION);
        this.context = context1;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
