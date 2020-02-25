package com.annguyen.giaythethao.model;

public class KhachHangModel {
    private int MaKH;
    private String TenKH;
    private String MatKhau;
    private String DienThoai;
    private String Email;
    private String DiaChi;
    private String GioTinh;

    public KhachHangModel(int maKH, String tenKH, String matKhau, String dienThoai, String email, String diaChi, String gioTinh) {
        MaKH = maKH;
        TenKH = tenKH;
        MatKhau = matKhau;
        DienThoai = dienThoai;
        Email = email;
        DiaChi = diaChi;
        GioTinh = gioTinh;
    }

    public int getMaKH() {
        return MaKH;
    }

    public void setMaKH(int maKH) {
        MaKH = maKH;
    }

    public String getTenKH() {
        return TenKH;
    }

    public void setTenKH(String tenKH) {
        TenKH = tenKH;
    }

    public String getMatKhau() {
        return MatKhau;
    }

    public void setMatKhau(String matKhau) {
        MatKhau = matKhau;
    }

    public String getDienThoai() {
        return DienThoai;
    }

    public void setDienThoai(String dienThoai) {
        DienThoai = dienThoai;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String diaChi) {
        DiaChi = diaChi;
    }

    public String getGioTinh() {
        return GioTinh;
    }

    public void setGioTinh(String gioTinh) {
        GioTinh = gioTinh;
    }
}
