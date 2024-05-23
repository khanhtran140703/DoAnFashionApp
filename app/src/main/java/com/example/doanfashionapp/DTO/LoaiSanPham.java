package com.example.doanfashionapp.DTO;

import java.util.ArrayList;

public class LoaiSanPham {
    private String idLoaiSP;
    private String tenLoaiSP;
    private ArrayList<SanPham> dsSP;

    public String getIdLoaiSP() {
        return idLoaiSP;
    }

    public void setIdLoaiSP(String idLoaiSP) {
        this.idLoaiSP = idLoaiSP;
    }

    public String getTenLoaiSP() {
        return tenLoaiSP;
    }

    public void setTenLoaiSP(String tenLoaiSP) {
        this.tenLoaiSP = tenLoaiSP;
    }

    public ArrayList<SanPham> getDsSP() {
        return dsSP;
    }

    public void setDsSP(ArrayList<SanPham> dsSP) {
        this.dsSP = dsSP;
    }

    public LoaiSanPham() {
    }

    public LoaiSanPham(String idLoaiSP, String tenLoaiSP, ArrayList<SanPham> dsSP) {
        this.idLoaiSP = idLoaiSP;
        this.tenLoaiSP = tenLoaiSP;
        this.dsSP = dsSP;
    }
}
