package com.annguyen.giaythethao.model;

public class LoaiSP {
    private int IDLoai;
    private String TenLoai;
    private String HinhAnh;

    public LoaiSP(int IDLoai, String tenLoai, String hinhAnh) {
        this.IDLoai = IDLoai;
        TenLoai = tenLoai;
        HinhAnh = hinhAnh;
    }

    public int getIDLoai() {
        return IDLoai;
    }

    public void setIDLoai(int IDLoai) {
        this.IDLoai = IDLoai;
    }

    public String getTenLoai() {
        return TenLoai;
    }

    public void setTenLoai(String tenLoai) {
        TenLoai = tenLoai;
    }

    public String getHinhAnh() {
        return HinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        HinhAnh = hinhAnh;
    }
}
