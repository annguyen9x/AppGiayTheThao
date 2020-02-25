<?php
include "connect.php";

$tongTien = $_POST['tongTien'];
$tenKH = $_POST['tenKH'];
$dienThoai = $_POST['dienThoai'];
$email = $_POST['email'];
$diaChi = $_POST['diaChi'];
$gioiTinh = $_POST['gioiTinh'];

if( $tongTien > 0 && strlen($tenKH) > 0 && strlen($dienThoai) > 0 && strlen($email) > 0 && strlen($diaChi) > 0 && strlen($gioiTinh) > 0){
	$sql = "INSERT INTO donhang(MaDH, TongTien, TenKH, DienThoai, Email, DiaChi, GioiTinh) VALUES(null, $tongTien, '$tenKH', '$dienThoai', '$email', '$diaChi', '$gioiTinh')";
	if(mysqli_query($conn, $sql)){
		$MaDH = $conn->insert_id;
		echo $MaDH;
	}else{
		echo "Thêm đơn hàng thất bại";
	}
}else{
	echo "Dữ liệu không được rỗng";
}

?>