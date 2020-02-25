<?php
header("Content-type: text/html; charset=utf-8");
include "connect.php";
//Gửi JSON từ App lên server tất cả sản phẩm trong giỏ hàng 1 lần, để tránh mất mạng khi gửi từng sản phẩm sẽ lỗi !!!

$json =$_POST['MyJSON'];
//Lấy dữ liệu từ ArrayJSON 
$data = json_decode($json, true);
foreach ($data as $value) {
	$MaDH = $value['MaDH'];
	$MaSP = $value['MaSP'];
	$SoLuong = $value['SoLuong'];
	$DonGia = $value['DonGia'];

	$sqlI = "INSERT INTO chitietdonhang VALUES($MaDH, $MaSP, $SoLuong, $DonGia)";
	$sqlU = "UPDATE sanpham SET SoLuong = SoLuong - $SoLuong WHERE ID = $MaSP";

	$insert = mysqli_query($conn, $sqlI);
	$update = mysqli_query($conn, $sqlU);
}

if($insert && $update){
	echo "successInsertCTDH";
}else{
	echo "errorInsertCTDH";
}
?>