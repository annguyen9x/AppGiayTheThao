<?php
	include 'connect.php';
	
	class LoaiSP
	{
		function LoaiSP($id, $tenLoaiSP, $hinhAnhLoaiSP)
		{
			$this->id = $id;//$this->id thuộc tính, là key JSON trả về
			$this->tenLoaiSP = $tenLoaiSP;
			$this->hinhAnhLoaiSP = $hinhAnhLoaiSP;
		}
	}
	
	$sql = "Select* from loaisanpham";
	$data = mysqli_query($conn,$sql);
	$arrLoaiSP = array();
	
	//Đưa dữ liệu trả về vào mảng
	while( $row = mysqli_fetch_assoc($data)){
		array_push($arrLoaiSP, new LoaiSP($row['IDLoai'],$row['TenLoai'],$row['HinhAnh']));
	}
	//Trả về dữ liệu dạng: mảng->JSON
	echo json_encode($arrLoaiSP);
	
?>