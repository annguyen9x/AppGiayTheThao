<?php
include "connect.php";

$tenKH = $_POST['tenKH'];
$matKhau = $_POST['matKhau'];
$dienThoai = $_POST['dienThoai'];
$email = $_POST['email'];
$diaChi = $_POST['diaChi'];
$gioiTinh = $_POST['gioiTinh'];

$sql = "INSERT INTO khachhang(TenKH, MatKhau, DienThoai, Email, DiaChi, GioiTinh) 
			Values('$tenKH', '$matKhau', '$dienThoai', '$email', '$diaChi', '$gioiTinh')";
$insert = mysqli_query($conn, $sql);
if($insert){
	echo "success";
}
else{
	echo "error";
}

?>