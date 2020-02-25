package com.annguyen.giaythethao.model;

import java.io.Serializable;

public class SanPham implements Serializable {
    private int id;
    private String tenSP;
    private Double gia;
    private int soLuong;
    private String hinhAnh;
    private String moTa;
    private String gioiTinh;
    private String mauSac;
    private int size;
    private int idLoaiSP;

    public SanPham(int id, String tenSP, Double gia, int soLuong, String hinhAnh, String moTa, String gioiTinh, String mauSac, int size, int idLoaiSP) {
        this.id = id;
        this.tenSP = tenSP;
        this.gia = gia;
        this.soLuong = soLuong;
        this.hinhAnh = hinhAnh;
        this.moTa = moTa;
        this.gioiTinh = gioiTinh;
        this.mauSac = mauSac;
        this.size = size;
        this.idLoaiSP = idLoaiSP;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public Double getGia() {
        return gia;
    }

    public void setGia(Double gia) {
        this.gia = gia;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getMauSac() {
        return mauSac;
    }

    public void setMauSac(String mauSac) {
        this.mauSac = mauSac;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getIdLoaiSP() {
        return idLoaiSP;
    }

    public void setIdLoaiSP(int idLoaiSP) {
        this.idLoaiSP = idLoaiSP;
    }

}
