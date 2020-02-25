<?php
header("Content-type: text/html; charset=utf-8");
// $host = "localhost"; //in hosting
$host = "localhost";
// $username="id11165954_nguyenvanan";
$username="root";//#root
// $password="NguyenVanAn";
$password="";
// $database = "id11165954_giaythethao";
$database = "giaythethao";
$conn = mysqli_connect($host,$username,$password,$database);
// mysqli_query($conn,'SET NAMES "UTF8"');
mysqli_set_charset($conn, 'UTF8');
	if($conn == null ){
		echo "Kết nối Thất bại !!!";
	}
?>