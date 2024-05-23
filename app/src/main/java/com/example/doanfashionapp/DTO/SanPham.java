package com.example.doanfashionapp.DTO;

public class SanPham {
    private String idSanPham;
    private int idAnhSanPham;
    private String tenSanPham;
    private int giaSanPham;
    private String moTaSP;
    private String idBrand;
    private String idLoaiSP;

    public String getIdSanPham() {
        return idSanPham;
    }

    public void setIdSanPham(String idSanPham) {
        this.idSanPham = idSanPham;
    }

    public int getIdAnhSanPham() {
        return idAnhSanPham;
    }

    public void setIdAnhSanPham(int idAnhSanPham) {
        this.idAnhSanPham = idAnhSanPham;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public int getGiaSanPham() {
        return giaSanPham;
    }

    public void setGiaSanPham(int giaSanPham) {
        this.giaSanPham = giaSanPham;
    }

    public String getMoTaSP() {
        return moTaSP;
    }

    public void setMoTaSP(String moTaSP) {
        this.moTaSP = moTaSP;
    }

    public String getIdBrand() {
        return idBrand;
    }

    public void setIdBrand(String idBrand) {
        this.idBrand = idBrand;
    }

    public String getIdLoaiSP() {
        return idLoaiSP;
    }

    public void setIdLoaiSP(String idLoaiSP) {
        this.idLoaiSP = idLoaiSP;
    }

    public SanPham() {

    }

    public SanPham(String idSanPham, int idAnhSanPham, String tenSanPham, int giaSanPham, String moTaSP, String idBrand, String idLoaiSP) {
        this.idSanPham = idSanPham;
        this.idAnhSanPham = idAnhSanPham;
        this.tenSanPham = tenSanPham;
        this.giaSanPham = giaSanPham;
        this.moTaSP = moTaSP;
        this.idBrand = idBrand;
        this.idLoaiSP = idLoaiSP;
    }
}
