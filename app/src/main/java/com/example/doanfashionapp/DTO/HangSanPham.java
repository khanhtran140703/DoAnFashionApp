package com.example.doanfashionapp.DTO;

import java.util.ArrayList;

public class HangSanPham {
    private String tenHang;
    private String maHang;

    private ArrayList<SanPham> listSP;

    public ArrayList<SanPham> getListSP() {
        return listSP;
    }

    public void setListSP(ArrayList<SanPham> listSP) {
        this.listSP = listSP;
    }

    public String getTenHang() {
        return tenHang;
    }

    public void setTenHang(String tenHang) {
        this.tenHang = tenHang;
    }

    public String getMaHang() {
        return maHang;
    }

    public void setMaHang(String maHang) {
        this.maHang = maHang;
    }

    public HangSanPham() {

    }

    public HangSanPham(String tenHang, String maHang,ArrayList<SanPham> listSP) {
        this.listSP=listSP;
        this.tenHang = tenHang;
        this.maHang = maHang;
    }
}
