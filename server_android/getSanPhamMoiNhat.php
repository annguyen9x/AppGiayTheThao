<?php
include "connect.php";

class SanPhamMoiNhat
{
	
	function SanPhamMoiNhat($id, $tenSP, $gia, $sl, $hinhAnh, $moTa, $gioiTinh, $mauSac, $size, $idLoaiSP)
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

$sql = "SELECT* From sanpham Group By TenSP Order By ID DESC Limit 6";
$data = mysqli_query($conn,$sql);
$arrSanPhamMoiNhat = array();
while ($row = mysqli_fetch_assoc($data)) {
	array_push($arrSanPhamMoiNhat,new SanPhamMoiNhat($row['ID'], $row['TenSP'], $row['Gia'], $row['SoLuong'], 
		$row['HinhAnh'], $row['MoTa'], $row['GioiTinh'], $row['MauSac'], $row['Size'], $row['IDLoaiSP'])); 
}

echo json_encode($arrSanPhamMoiNhat);//Trả về URL mảng JSON để Android đọc
?>