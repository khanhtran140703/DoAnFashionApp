package com.example.doanfashionapp.DAO;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.doanfashionapp.DTO.HangSanPham;
import com.example.doanfashionapp.DTO.LoaiSanPham;
import com.example.doanfashionapp.DTO.SanPham;
import com.example.doanfashionapp.Database.DBHelper;

import java.nio.file.LinkOption;
import java.util.ArrayList;

public class DAO_LoaiSanPham {
    private Context context;
    private DBHelper dbHelper;
    private SQLiteDatabase db;
    public static String path = "/data/data/com.example.doanfashionapp/db/FashionAppDB.db";

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public DBHelper getDbHelper() {
        return dbHelper;
    }

    public void setDbHelper(DBHelper dbHelper) {
        this.dbHelper = dbHelper;
    }

    public SQLiteDatabase getDb() {
        return db;
    }

    public void setDb(SQLiteDatabase db) {
        this.db = db;
    }

    public DAO_LoaiSanPham(Context context) {
        this.context = context;
        this.dbHelper = new DBHelper(context);
        this.db = dbHelper.getReadableDatabase();
    }
    public ArrayList<LoaiSanPham> loadLoaiSP(String brand){
        ArrayList<LoaiSanPham> arrayList=new ArrayList<>();
        db=SQLiteDatabase.openOrCreateDatabase(path,null);
        String sql="Select * from CATEGORIES";
        Cursor c =db.rawQuery(sql,null);
        c.moveToFirst();
        while (!c.isAfterLast()){
            LoaiSanPham temp = new LoaiSanPham();
            temp.setIdLoaiSP(c.getString(0));
            temp.setTenLoaiSP(c.getString(1));
            temp.setDsSP(new ArrayList<>());
            String dieuKien=c.getString(0);
            String sql2="Select * from PRODUCTS where CATEGORY_ID = '"+dieuKien+"' and BRAND_ID in (Select BRAND_ID from BRANDS where BRAND_NAME = '"+brand+"')";
            Cursor c2=db.rawQuery(sql2,null);
            c2.moveToFirst();
            while (!c2.isAfterLast()){

                String maSP=c2.getString(0);
                String tenSP=c2.getString(1);
                String loaiSP=c2.getString(2);
                int giaSP=c2.getInt(3);
                String bienTam=c2.getString(4);
                String parts[]=bienTam.split(".jpg");
                String anhDaiDien=parts[0];
                int idAnh=context.getResources().getIdentifier(anhDaiDien,"drawable",context.getPackageName());
                String moTa=c2.getString(5);
                String maHang=c2.getString(6);
                SanPham temp2= new SanPham(maSP,idAnh,tenSP,giaSP,moTa,maHang,loaiSP);
                temp.getDsSP().add(temp2);
                c2.moveToNext();
            }
            if (!temp.getDsSP().isEmpty()){
                arrayList.add(temp);
            }
            c.moveToNext();
            c2.close();
        }
        c.close();
        db.close();
        return arrayList;
    }
}
