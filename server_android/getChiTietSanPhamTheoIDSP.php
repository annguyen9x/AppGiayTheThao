<?php
include "connect.php";

$idSP = $_POST['IDSP'];

class ChiTietSanPham
{
	
	function ChiTietSanPham($id, $tenSP, $gia, $sl, $hinhAnh, $moTa, $gioiTinh, $mauSac, $size, $idLoaiSP)
	{
		$this->id = $id;
		$this->tenSP = $tenSP;
		$this->gia = $gia;
		$this->sl = $sl;
		$this->hinhAnh = $hinhAnh;
		$this->moTa = $moTa;
		$this->gioiTinh = $gioiTinh;
		$this->mauSac = $mauSac;
		$this->size = $size;
		$this->idLoaiSP = $idLoaiSP;

	}
}

$sql = "SELECT* From sanpham Where ID = $idSP";
$data = mysqli_query($conn,$sql);
$arrChiTietSanPham = array();
while ($row = mysqli_fetch_assoc($data)) {
	array_push($arrChiTietSanPham,new ChiTietSanPham($row['ID'], $row['TenSP'], $row['Gia'], $row['SoLuong'], 
		$row['HinhAnh'], $row['MoTa'], $row['GioiTinh'], $row['MauSac'], $row['Size'], $row['IDLoaiSP'])); 
}

echo json_encode($arrChiTietSanPham);//Trả về URL mảng JSON để Android đọc
?>