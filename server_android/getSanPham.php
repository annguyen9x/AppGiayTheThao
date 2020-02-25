<?php

include "connect.php";

$page = $_GET['page'];//Phân biệt GET, POST
$idLoaiSP = $_POST['idLoaiSP'];

$soSP = 5;//Lấy về 4 sản phẩm 1 lần
$limit = ($page-1)*$soSP;//Lấy ra số sp cần lấy mỗi lần (1-6),(7-12)
class SanPham{
	function SanPham($id, $tenSP, $gia, $sl, $hinhAnh, $moTa, $gioiTinh, $mauSac, $size, $idLoaiSP){
		$this->id = $id;//$this->id là thuộc tính của file JSON trả về
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

$arraySanPham = array();
$sql = "SELECT* FROM sanpham WHERE IDLoaiSP = $idLoaiSP GROUP BY TenSP ORDER BY ID DESC LIMIT $limit, $soSP";
$data = mysqli_query($conn, $sql);
while ($row = mysqli_fetch_assoc($data)){
	array_push($arraySanPham, new SanPham($row['ID'], $row['TenSP'], $row['Gia'], $row['SoLuong'], 
		$row['HinhAnh'], $row['MoTa'], $row['GioiTinh'], $row['MauSac'], $row['Size'], $row['IDLoaiSP']));
}

echo json_encode($arraySanPham);

?>