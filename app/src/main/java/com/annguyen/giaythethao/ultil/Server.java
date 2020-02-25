package com.annguyen.giaythethao.ultil;

public class Server {
//    public static String localhost="shoevietnam.000webhostapp.com"; //in hosting
    public static String IPv4="192.168.43.159";//wifi my phone
//    public static String IPv4= "192.168.1.227";//wifi router
    public static String localhost=IPv4+":8080/server_android"; //localhost = IPv4 + :8080/server_android

    //url LoaiSP -> Lấy ra các LoaiSP cho menu (thay localhost = IPv4)
    public static String urlLoaiSP = "http://"+localhost + "/getLoaiSP.php";

    //url SanPhamMoiNhat
    public static String urlSanPhamMoiNhat = "http://"+localhost +"/getSanPhamMoiNhat.php";

    //url SanPham
    public static String urlSanPham = "http://"+localhost +"/getSanPham.php?page=";

    //url ChiTietSanPhamTheoIDSP
    public static String urlChiTietSanPhamTheoIDSP = "http://"+localhost +"/getChiTietSanPhamTheoIDSP.php";

    //url DonHang
    public static String urlGioHang= "http://"+localhost +"/getDonHang.php";

    //url ChiTietDonHang
    public static String urlChiTietDonHang = "http://"+localhost +"/chiTietDonHang.php";

    //url DangNhap
    public static String urlDangNhap = "http://"+localhost +"/dangNhap.php";

    //url CapNhatTaiKhoan
    public static String urlCapNhatTaiKhoan = "http://"+localhost +"/capNhatTaiKhoan.php";

    //url DangKy
    public static String urlDangKy = "http://"+localhost +"/dangKy.php";

}
