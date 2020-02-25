<?php
include "connect.php";

$tenDN = $_POST['TenDN'];
$matKhau = $_POST['MatKhau'];
class DangNhap
{
	
	function DangNhap($makh, $tenkh, $matkhau, $dienthoai, $email, $diachi, $gioitinh)
	{
		$this->makh = $makh;
		$this->tenkh = $tenkh;
		$this->matkhau = $matkhau;
		$this->dienthoai = $dienthoai;
		$this->email = $email;
		$this->diachi = $diachi;
		$this->gioitinh = $gioitinh;
	}
}

$sql = "SELECT* From khachhang Where TenKH = '$tenDN' And MatKhau = $matKhau";
$data = mysqli_query($conn,$sql);
$arrTaiKhoan = array();
while($row = mysqli_fetch_assoc($data)) {
	array_push($arrTaiKhoan,new DangNhap($row['MaKH'], $row['TenKH'], $row['MatKhau'], $row['DienThoai'], $row['Email'], $row['DiaChi'], $row['GioiTinh'])); 
}

echo json_encode($arrTaiKhoan);//Trả về URL mảng JSON để Android đọc
?>