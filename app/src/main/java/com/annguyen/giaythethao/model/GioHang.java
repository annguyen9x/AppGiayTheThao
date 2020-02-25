package com.annguyen.giaythethao.model;

public class GioHang {
    private int idsp;
    private String hinhAnh;
    private String tensp;
    private String gioitinh;
    private int size;
    private String mausac;
    private Double giasp;
    private int soluong;
    private Double thanhTien;

    public GioHang(int idsp, String hinhAnh, String tensp, String gioitinh, int size, String mausac, Double giasp, int soluong, Double thanhTien) {
        this.idsp = idsp;
        this.hinhAnh = hinhAnh;
        this.tensp = tensp;
        this.gioitinh = gioitinh;
        this.size = size;
        this.mausac = mausac;
        this.giasp = giasp;
        this.soluong = soluong;
        this.thanhTien = thanhTien;
    }

    public int getIdsp() {
        return idsp;
    }

    public void setIdsp(int idsp) {
        this.idsp = idsp;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public String getTensp() {
        return tensp;
    }

    public void setTensp(String tensp) {
        this.tensp = tensp;
    }

    public String getGioitinh() {
        return gioitinh;
    }

    public void setGioitinh(String gioitinh) {
        this.gioitinh = gioitinh;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getMausac() {
        return mausac;
    }

    public void setMausac(String mausac) {
        this.mausac = mausac;
    }

    public Double getGiasp() {
        return giasp;
    }

    public void setGiasp(Double giasp) {
        this.giasp = giasp;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public Double getThanhTien() {
        return thanhTien;
    }

    public void setThanhTien(Double thanhTien) {
        this.thanhTien = thanhTien;
    }
}
