-- phpMyAdmin SQL Dump
-- version 4.2.7.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Dec 06, 2019 at 04:45 AM
-- Server version: 5.6.20
-- PHP Version: 5.5.15

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `giaythethao`
--

-- --------------------------------------------------------

--
-- Table structure for table `chitietdonhang`
--

CREATE TABLE IF NOT EXISTS `chitietdonhang` (
  `MaDH` int(11) NOT NULL,
  `MaSP` int(11) NOT NULL,
  `SoLuong` int(11) NOT NULL,
  `DonGia` decimal(10,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `chitietdonhang`
--

INSERT INTO `chitietdonhang` (`MaDH`, `MaSP`, `SoLuong`, `DonGia`) VALUES
(11, 6, 1, '2090000.00'),
(11, 7, 5, '1690000.00'),
(11, 8, 10, '2232000.00'),
(12, 1, 5, '2264000.00'),
(12, 9, 5, '1800000.00'),
(12, 24, 1, '2350000.00');

-- --------------------------------------------------------

--
-- Table structure for table `donhang`
--

CREATE TABLE IF NOT EXISTS `donhang` (
`MaDH` int(11) NOT NULL,
  `TongTien` decimal(10,2) NOT NULL,
  `TenKH` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `DienThoai` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `Email` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `DiaChi` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `GioiTinh` varchar(100) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=17 ;

--
-- Dumping data for table `donhang`
--

INSERT INTO `donhang` (`MaDH`, `TongTien`, `TenKH`, `DienThoai`, `Email`, `DiaChi`, `GioiTinh`) VALUES
(11, '32860000.00', 'Nguyen Van An', '0987654321', 'an@gmail.com', 'Hair Chau, Day Nang', 'Nam'),
(12, '22670000.00', 'Nguyen Van An', '0347244142', 'an@gmail.com', 'P. Thuan Phuoc, Q. Hai Chau, TP. Da Nang', 'Nam');

-- --------------------------------------------------------

--
-- Table structure for table `khachhang`
--

CREATE TABLE IF NOT EXISTS `khachhang` (
`MaKH` int(11) NOT NULL,
  `TenKH` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `MatKhau` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `DienThoai` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `Email` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `DiaChi` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `GioiTinh` varchar(100) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=4 ;

--
-- Dumping data for table `khachhang`
--

INSERT INTO `khachhang` (`MaKH`, `TenKH`, `MatKhau`, `DienThoai`, `Email`, `DiaChi`, `GioiTinh`) VALUES
(1, 'Nguyen Van An', '123456', '0987654321', 'nguyenvanan@gmail.com', 'Thuan Phuoc, Hai Chau, Da Nang', 'Nam'),
(2, 'Cap Kim Tram', '123456', '0987678901', 'capkimtram@gmail.com', 'Hoa Khanh, Lien Chieu, Da Nang', 'Nữ'),
(3, 'Le Van Dung', '123456', '0976545678', 'levandung@gmail.com', 'Mỹ An, Ngũ Hàng Sơn, Da Nang', 'Nữ');

-- --------------------------------------------------------

--
-- Table structure for table `loaisanpham`
--

CREATE TABLE IF NOT EXISTS `loaisanpham` (
`IDLoai` int(11) NOT NULL,
  `TenLoai` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `HinhAnh` varchar(200) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=4 ;

--
-- Dumping data for table `loaisanpham`
--

INSERT INTO `loaisanpham` (`IDLoai`, `TenLoai`, `HinhAnh`) VALUES
(1, 'Giày Nike', 'http://pngimg.com/uploads/nike/nike_PNG12.png'),
(2, 'Giày Vans', 'https://seeklogo.com/images/V/vans-logo-037AC1382F-seeklogo.com.png'),
(3, 'Giày Adidas', 'R.drawable.adidas');

-- --------------------------------------------------------

--
-- Table structure for table `sanpham`
--

CREATE TABLE IF NOT EXISTS `sanpham` (
`ID` int(11) NOT NULL,
  `TenSP` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `Gia` decimal(10,0) NOT NULL,
  `SoLuong` int(11) NOT NULL DEFAULT '10',
  `HinhAnh` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `MoTa` text COLLATE utf8_unicode_ci NOT NULL,
  `GioiTinh` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `MauSac` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  `Size` int(11) DEFAULT NULL,
  `IDLoaiSP` int(11) NOT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=25 ;

--
-- Dumping data for table `sanpham`
--

INSERT INTO `sanpham` (`ID`, `TenSP`, `Gia`, `SoLuong`, `HinhAnh`, `MoTa`, `GioiTinh`, `MauSac`, `Size`, `IDLoaiSP`) VALUES
(1, 'Giày nike air zoom', '2264000', 10, 'https://www.kingsport.vn/vnt_upload/product/giay_nike_nam/thumbs/300_crop_nikecourt_air_zoom_ultra_nike_845007-116_men_s_tennis_shoe_6245.jpg', 'Chất liệu cao cấp, đặc biệt và độc quyền: phổ biến là EVA (ethylene vinyl acetate), PU (polyurethane), Lunarlon, Cushlon, Phylon,….\r\nThiết kế nén khí làm chân tiếp đất “êm” hơn, giúp giảm bớt đi lực tác động giúp bạn được bảo vệ tránh dẫn đến các chấn thương từ cường độ hoạt động cao. \r\nPhần trên của giày luôn ôm sát vào chân bạn , giữ chân không bị xộc xệch, chống sốc và cân bằng, cải thiện khả năng chống sốc và bảo vệ cho giày.', 'Nam', 'Trắng', 39, 1),
(2, 'Giày nike metcon repper', '2264000', 10, 'https://www.kingsport.vn/vnt_upload/product/giay_nike_nam/thumbs/300_crop_metcon-repper-dsx-training-shoe_2.jpg', 'Đôi giày sử dụng đế giữa mang công nghệ Dynamic Support với mật độ kép lõi Phylon và Cushlon, tăng cường đệm dưới chân đồng thời làm giảm đáng kể khả năng bàn chân bị lật má trong.Upper lưới được chế tác linh hoạt và thích ứng tốt với bàn chân, cùng công nghệ Flywire giữ bàn chân cực kì cố định. Ngoài ra, giày Nike Air Zoom Structure 21 có đế ngoài vân waffle cho độ bền và độ bám cao trên đường chạy.\r\nTúi “toàn” Air giúp lưu thông khí, tương tác với bàn chân diễn ra nhanh hơn, tạo cảm giác tốt hơn khi chạy bộ\r\n\r\nUpper được làm hoàn toàn bằng Flyknit cho chuyển động linh hoạt, thay đổi phù hợp với kích cỡ của mọi bàn chân.', 'Nam', 'Xám', 40, 1),
(3, 'Giày nike zoom train complete', '2264000', 10, 'https://www.kingsport.vn/vnt_upload/product/giay_nike_nam/thumbs/300_crop_f02dc8c14d93d2000950a81c5ddfedeb.jpg', 'Chất liệu cao cấp, đặc biệt và độc quyền: phổ biến là EVA (ethylene vinyl acetate), PU (polyurethane), Lunarlon, Cushlon, Phylon,….\r\nThiết kế nén khí làm chân tiếp đất “êm” hơn, giúp giảm bớt đi lực tác động giúp bạn được bảo vệ tránh dẫn đến các chấn thương từ cường độ hoạt động cao. \r\nPhần trên của giày luôn ôm sát vào chân bạn , giữ chân không bị xộc xệch, chống sốc và cân bằng, cải thiện khả năng chống sốc và bảo vệ cho giày.', 'Nam', 'Đen', 41, 1),
(4, 'Giày nike air zoom structure', '2752000', 10, 'https://www.kingsport.vn/vnt_upload/product/giay_nike_nam/thumbs/300_crop_nike_air_zoom_structure_21_nike_904695-401_men_s_running_shoe_8651.jpg', 'Đôi giày sử dụng đế giữa mang công nghệ Dynamic Support với mật độ kép lõi Phylon và Cushlon, tăng cường đệm dưới chân đồng thời làm giảm đáng kể khả năng bàn chân bị lật má trong.Upper lưới được chế tác linh hoạt và thích ứng tốt với bàn chân, cùng công nghệ Flywire giữ bàn chân cực kì cố định. Ngoài ra, giày Nike Air Zoom Structure 21 có đế ngoài vân waffle cho độ bền và độ bám cao trên đường chạy.\r\nNike Zoom Fly là dòng giày chạy bộ với thiết kế đơn giản, ít tính năng cao cấp, phù hợp cho tập luyện lẫn thi đấu.', 'Nam', 'Xanh dương', 42, 1),
(5, 'Giày nike run swift-908989', '1790000', 10, 'https://www.kingsport.vn/vnt_upload/product/giay_nike_nam/thumbs/300_crop_Untitled-1-Recovered-Recovered_2.png', 'Chất liệu cao cấp, đặc biệt và độc quyền: phổ biến là EVA (ethylene vinyl acetate), PU (polyurethane), Lunarlon, Cushlon, Phylon,….\r\n\r\nThiết kế nén khí làm chân tiếp đất “êm” hơn, giúp giảm bớt đi lực tác động giúp bạn được bảo vệ tránh dẫn đến các chấn thương từ cường độ hoạt động cao. Phần trên của giày luôn ôm sát vào chân bạn , giữ chân không bị xộc xệch, chống sốc và cân bằng, cải thiện khả năng chống sốc và bảo vệ cho giày.', 'Nam', 'Xanh dương', 43, 1),
(6, 'Giày nike air max advantage', '2090000', 10, 'https://www.kingsport.vn/vnt_upload/product/giay_nike_nam/thumbs/300_crop_Untitled-1-Recovered-Recovered_1.png', 'Chất liệu cao cấp, đặc biệt và độc quyền: phổ biến là EVA (ethylene vinyl acetate), PU (polyurethane), Lunarlon, Cushlon, Phylon,….\r\nThiết kế nén khí làm chân tiếp đất “êm” hơn, giúp giảm bớt đi lực tác động giúp bạn được bảo vệ tránh dẫn đến các chấn thương từ cường độ hoạt động cao. \r\nPhần trên của giày luôn ôm sát vào chân bạn , giữ chân không bị xộc xệch, chống sốc và cân bằng, cải thiện khả năng chống sốc và bảo vệ cho giày.', 'Nam', 'Đen', 44, 1),
(7, 'Giày nike city trainer', '1690000', 10, 'https://www.kingsport.vn/vnt_upload/product/giay_nike_nu/thumbs/300_crop_avatar.jpg', 'Giày Nike City Trainer là loại giày chạy bộ dành cho nữ. Sản phẩm với gam màu nhẹ nhàng cùng với chất liệu thoáng mát sẽ đem lại sự thoải mái và tiện dụng tối ưu cho những cô nàng cá tính yêu thích thể thao. Giày với đế cao su có bề mặt có độ ma sát tối ưu, hạn chế các tình huống không hay xảy ra nếu gặp thời tiết xấu hay đường trơn trượt, cho bạn những bước đi vững chãi và yên tâm hơn.', 'Nữ', 'Hồng', 36, 1),
(8, 'Giày nike lunar skyelux', '2232000', 10, 'https://www.kingsport.vn/vnt_upload/product/01_2018/thumbs/300_crop_p_18483351_93744831_2661011.jpg', 'Đôi giày sử dụng đế giữa mang công nghệ Dynamic Support với mật độ kép lõi Phylon và Cushlon, tăng cường đệm dưới chân đồng thời làm giảm đáng kể khả năng bàn chân bị lật má trong.Upper lưới được chế tác linh hoạt và thích ứng tốt với bàn chân, cùng công nghệ Flywire giữ bàn chân cực kì cố định. Ngoài ra, giày có đế ngoài vân waffle cho độ bền và độ bám cao trên đường chạy.', 'Nữ', 'Đỏ', 38, 1),
(9, 'Vans x peanuts old skool', '1800000', 10, 'https://bizweb.dktcdn.net/thumb/medium/100/140/774/products/vans-x-peanuts-old-skool-charlie-brown-vn0a38g1ohj-1.png?v=1523331253710', 'Phiên bản Old Skool Charlie Brown lấy cảm hứng từ chiếc áo màu vàng của cậu bé nhân vật chính Charlie Brown - một cậu bé hiền lành nhưng tràn đầy nhiệt huyết, là bạn thân của chú chó Snoopy, mỗi tập phim của Peanuts luôn có cảnh cậu bé Charlie Brown phân vân lựa áo trong khi tất cả chiếc áo của cậu đều giống nhau, và phiên bản Old Skool này đặc biệt mang lại những cảm xúc tuổi thơ khó quên cho những ai từng ngồi trước tivi coi phim hoạt hình Peanuts mỗi ngày.', 'Nam', 'Vàng', 40, 2),
(10, 'Vans checkerboard authentic black/white', '1350000', 10, 'https://bizweb.dktcdn.net/thumb/medium/100/140/774/products/vans-checkerboard-authentic-black-true-white-vn0a2z5ihrk-1.png?v=1523331038873', 'Vans checkerboard authentic black/white. Với những ai yêu thích những môn thể thao mạo hiểm như trượt ván, xe đạp BMX hoặc mô tô đua thì VANS là sự lựa chọn hoàn hảo, với thiết kế đế cao su độ bám tốt và tối ưu cho những môn thể thao đó.', 'Nam', 'Trắng/đen', 41, 2),
(11, 'Vans primary checkeboard old skool', '1650000', 10, 'https://bizweb.dktcdn.net/thumb/medium/100/140/774/products/vans-primary-checkerboard-old-skool-black-white-vn0a38g1p0s-1.png?v=1514944321010', 'Vans Old Skool ra đời với phong cách trẻ trung, năng động, nhưng không kém phần tinh tế, thời trang và nay điều ấy thật sự được khẳng định thêm lần nữa khi VANS PRIMARY CHECKERBOARD OLD SKOOL BLACK/WHITE ra đời, với sọc caro đậm chất VANS, đôi giày này một lần nữa khẳng định vị trí đặc biệt của mình đối với các tín đồ yêu cái đẹp cùng sự mạnh mẽ vốn có của VANS...', 'Nam', 'Trắng/ đen', 42, 2),
(12, 'Vans classic old skool navy/white', '1485000', 10, 'https://bizweb.dktcdn.net/thumb/medium/100/140/774/products/vans-classic-old-skool-navy-white-vn000d3hnvy1-1.png?v=1526461229000', 'VANS OLD SKOOL là phiên bản được fan hâm mộ của VANS ưa thích nhất bởi kiểu dáng đơn giản nhưng vẫn hợp thời trang. Với đế cao su độ bám tốt cùng thiết kế tối ưu cho môn trượt ván, BMX và các môn thể thao mạo hiểm khác, những lĩnh vực VANS nhắm tới hoàn toàn là thể thao mạo hiểm với những chương trình và cuộc thi hấp dẫn tại US.\r\n\r\nVANS Classic Old Skool Navy/White:\r\nDù không nằm trong mục Best Seller của VANS nhưng vẫn là một trong những phiên bản lâu đời nhất của dòng Old Skool và vẫn có một lượng fan hâm mộ của VANS từ khi ra đời năm 1977', 'Nam', 'Xanh dương', 42, 2),
(13, 'Vans dallas clayton authentic rainbow', '1800000', 10, 'https://bizweb.dktcdn.net/thumb/medium/100/140/774/products/vans-dallas-clayton-authentic-rainbow-true-white-vn0a38emmou-1.jpg?v=1523252455043', 'DALLAS CLAYTON là một tác giả và họa sĩ minh hoạ sách cho thiếu nhi, với những suy nghĩ hài hước, phù hợp với những suy nghĩ của đại đa số sự dễ thương của thiếu nhi, bất ngờ DALLAS có một cuộc bắt tay với hãng giày thể thao VANS-OFF THE WALL để mua bộ sưu tập giày có tác phẩm nghệ thuật rực rỡ từ những cuốn sách dành cho thiếu nhi nổi tiếng của ông. Cũng gióng như DALLAS, tất cả những gì VANS DALLAS CLAYTON AUTHENTIC RAINBOW TRUE WHITE thể hiện chính là sự trong sáng và ngây thơ cho một đôi giày cá tính, lẫn trong đó chính là sự nhiệt huyết cùng những chất lượng không thể bãi bỏ đến từ nhà sản xuất VANS.', 'Nữ', 'Rainbow', 37, 2),
(14, 'Vans authentic leather tapioca', '2000000', 10, 'https://bizweb.dktcdn.net/thumb/medium/100/140/774/products/vans-authentic-leather-tapioca-true-white-vn0a38emq8w-1.jpg?v=1525076931000', 'VANS vốn nổi tiếng nhờ biết bao mẫu giày canvas tuy giản dị nhưng lại rất chất lượng, hợp với mọi phong cách và tất cả mọi người.VANS không chỉ đẹp, dễ nhìn mà quan trọng là chúng còn phù hợp với tất cả các loại trang phục. Vì thế, dù không có quá nhiều cải tiến về phom dáng mà chỉ đẩy mạnh vào việc thay đổi màu sắc, họa tiết trong suốt nhiều năm qua nhưng giày của VANS vẫn được mến mộ đặc biệt. VANS AUTHENTIC LEATHER TAPIOCA TRUE WHITE với sự cải tiến về thiết kế đế giày, không còn là sọc đen như các đôi CLASSIC trước đây, nhưng trắng hoàn toàn, nhằm tạo nên một cái nhìn mới hoàn toàn cho sản phẩm.', 'Nữ', 'Hồng', 38, 2),
(15, 'Vans old skool zip black', '2300000', 10, 'https://bizweb.dktcdn.net/thumb/medium/100/140/774/products/vans-old-skool-zip-leather-black-vn0a3493l3a-1.jpg?v=1523253467547', 'VANS OLD SKOOL là một kiểu dáng thuộc nhà sản xuất VANS, kiểu dáng từ trước đến nay đã làm nên thương hiệu của VANS và giúp VANS vững mạnh trên thị trường những đôi giày sneaker đang ngày càng phát triển. Dù kiểu dáng OLD SKOOL là một kiểu dáng cũ, nhưng với tất cả mọi người, OLD SKOOL tuy cũ nhưng vẫn mới, VANS luôn biết cách xây dựng kiểu dáng OLD SKOOL dựa trên nhiều kiểu cách, họa tiết màu sắc khác nhau để phù hợp với tất cả phong cách hay các mùa trong năm.', 'Nữ', 'Xám', 36, 2),
(16, 'Vans vault og old skool lx', '2000000', 10, 'https://bizweb.dktcdn.net/thumb/medium/100/140/774/products/vans-og-old-skool-lx-suede-canvas-checkerboard-majolica-vn0a36c8u9w-1.jpg?v=1538659215000', 'VANS Vault là phân nhánh cao cấp của VANS được ra đời vào năm 2003 với mục đích hồi sinh những dòng giày xưa cũ của thương hiệu này, nhưng với số lượng hạn chế đi cùng những tiêu chuẩn gắt gao hơn về chất lượng, nhằm đem lại những sản phẩm hết sức độc đáo nhưng vẫn luôn mang những giá trị nguyên bản (Original ) mà VANS luôn hướng tới.', 'Nữ', 'Xanh xám', 37, 2),
(17, 'Adidas busenitz pureboost primeknit', '1750000', 10, 'https://giayadidas.com.vn/wp-content/uploads/2018/11/CQ1160_01_standard.jpg', 'Pro skater Dennis Busenitz biết tất cả các quy tắc, nhưng không giống như mọi người, anh không phải lúc nào cũng nghe theo. Những đôi giày này đặc biệt thích nghi và thích ứng nhất trong dòng sản phẩm của anh, với sự kết hợp giữa adidas Primeknit và đế Boost. Chúng có một đế ngoài cao su mỏng cho một cảm giác nhẹ nhàng hơn, linh hoạt hơn.\r\nCam kết hàng chính hãng 100% bao check bao test.', 'Nam', 'Xám', 42, 3),
(18, 'Adidas Ultraboost got “house targaryen”', '2650000', 10, 'https://giayadidas.com.vn/wp-content/uploads/2019/09/zz-EE3711-01-600x600.jpg', 'Lấy cảm hứng từ bộ phim truyền hình Game of Thrones, đôi giày chạy này có màu sắc và chi tiết mang sắc thái của gia đình quý tộc, các phe phái chiến tranh trong Bảy vương quốc, bên ngoài Bức tường. Đôi giày này có phần đế đan nhẹ, cho phép chân bạn tự nhiên di chuyển nhẹ và nhịp nhàng. Đế giữa đáp ứng và đế ngoài linh hoạt phối hợp với nhau để mang đến một chuyến đi suôn sẻ và tràn đầy năng lượng.', 'Nam', 'Trắng', 41, 3),
(19, 'Adidas AlphaBounce beyond “linen white”', '1350000', 10, 'https://giayadidas.com.vn/wp-content/uploads/2019/07/zz-CG4763-01-600x600.jpg', 'Thiết kế dành cho những vận động viên thể thao giỏi nhất, Đôi giày này hỗ trợ các chuyển động đa chiều với đệm linh hoạt và nền tảng rộng, ổn định ở ngón chân và gót chân. Thân trên thiết kế liền mạch dạng lưới co giãn và phù hợp với dáng chân. Đế giữa Bounce mang đến sự thoải mái và linh hoạt. Đế ngoài cao su continental™ đặc biệt chống chịu điều kiện ẩm ướt và khô.\r\nCam kết hàng chính hãng 100% bao check bao test.', 'Nam', 'Linen/Chalk White', 39, 3),
(20, 'Adidas UltraBoost laceless “triple blacr”', '2350000', 10, 'https://giayadidas.com.vn/wp-content/uploads/2019/08/zz-B37685-01-1-600x600.jpg', 'Đôi giày chạy bộ này có thiết kế đơn giản để mang lại cho bạn cảm giác tự do và không bị hạn chế. Thân giày được đan bằng vải primeknit hỗ trợ và ôm sát chân. Đế giữa linh hoạt và đế ngoài hỗ trợ mang đến một chuyến đi suôn sẻ và thoải mái.\r\nCam kết hàng chính hãng 100% bao check bao test.', 'Nam', 'Đen', 40, 3),
(21, 'Adidas Alphabounce beyond “aero blue”', '1150000', 10, 'https://giayadidas.com.vn/wp-content/uploads/2019/09/DB1410-01-600x600.jpg', 'Thiết kế dành cho những vận động viên thể thao giỏi nhất, Đôi giày này hỗ trợ các chuyển động đa chiều với đệm linh hoạt và nền tảng rộng, ổn định ở ngón chân và gót chân. Thân trên thiết kế liền mạch dạng lưới co giãn và phù hợp với dáng chân. Đế giữa Bounce mang đến sự thoải mái và linh hoạt. Đế ngoài cao su continental™ đặc biệt chống chịu điều kiện ẩm ướt và khô.\r\nCam kết hàng chính hãng 100% bao check bao test.', 'Nữ', 'Aero Blue', 39, 3),
(22, 'Adidas Pureboost go “carbon maroon”', '1550000', 10, 'https://giayadidas.com.vn/wp-content/uploads/2019/09/zz-B75667-01-600x600.jpg', 'Đôi giày thiết kế để dành cho việc chạy bộ trong các thành phố hiện đại. Đế giữa không quá sâu và một nền tảng ngón chân cái rộng hơn cho bạn một cảm giác chân tiếp đất nhẹ hơn trên các con đường thành thị. Thân giày đan da lộn lót cho sự thoải mái mềm mại.\r\nCam kết hàng chính hãng 100% bao check bao test.', 'Nữ', 'Carbon Maroon', 40, 3),
(23, 'Adidas NMD r1 “black pink”', '1650000', 10, 'https://giayadidas.com.vn/wp-content/uploads/2019/09/zz-B37649-01-600x600.jpg', 'Ảnh hưởng từ các thiết kế mang phong cách Nhật, những đôi giày nam này được đan theo phong cách Sashiko trên thân giày adidas Primeknit. Thiết kế đầy cảm hứng này được hỗ trợ bởi công nghệ boost™, tạo nguồn năng lượng và sự thoải mái trong mỗi bước chân.\r\nCam kết hàng chính hãng 100% bao check bao test.', 'Nữ', 'Black Pink', 39, 3),
(24, 'Adidas UltraBoost laceless “pink”', '2350000', 10, 'https://giayadidas.com.vn/wp-content/uploads/2019/09/zz-B75856-01-600x600.jpg', 'Đôi giày chạy bộ này có thiết kế đơn giản để mang lại cho bạn cảm giác tự do và không bị hạn chế. Thân giày được đan bằng vải primeknit hỗ trợ và ôm sát chân. Đế giữa linh hoạt và đế ngoài hỗ trợ mang đến một chuyến đi suôn sẻ và thoải mái.\r\nCam kết hàng chính hãng 100% bao check bao test.', 'Nữ', 'Pink', 38, 3);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `chitietdonhang`
--
ALTER TABLE `chitietdonhang`
 ADD PRIMARY KEY (`MaDH`,`MaSP`), ADD KEY `FR_SanPham_CTDH` (`MaSP`);

--
-- Indexes for table `donhang`
--
ALTER TABLE `donhang`
 ADD PRIMARY KEY (`MaDH`);

--
-- Indexes for table `khachhang`
--
ALTER TABLE `khachhang`
 ADD PRIMARY KEY (`MaKH`);

--
-- Indexes for table `loaisanpham`
--
ALTER TABLE `loaisanpham`
 ADD PRIMARY KEY (`IDLoai`);

--
-- Indexes for table `sanpham`
--
ALTER TABLE `sanpham`
 ADD PRIMARY KEY (`ID`), ADD KEY `IDLoaiSP` (`IDLoaiSP`), ADD KEY `IDSize` (`Size`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `donhang`
--
ALTER TABLE `donhang`
MODIFY `MaDH` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=17;
--
-- AUTO_INCREMENT for table `khachhang`
--
ALTER TABLE `khachhang`
MODIFY `MaKH` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `loaisanpham`
--
ALTER TABLE `loaisanpham`
MODIFY `IDLoai` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `sanpham`
--
ALTER TABLE `sanpham`
MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=25;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `chitietdonhang`
--
ALTER TABLE `chitietdonhang`
ADD CONSTRAINT `FK_DonHang_CTDH` FOREIGN KEY (`MaDH`) REFERENCES `donhang` (`MaDH`) ON DELETE NO ACTION ON UPDATE CASCADE,
ADD CONSTRAINT `FR_SanPham_CTDH` FOREIGN KEY (`MaSP`) REFERENCES `sanpham` (`ID`) ON DELETE NO ACTION ON UPDATE CASCADE;

--
-- Constraints for table `sanpham`
--
ALTER TABLE `sanpham`
ADD CONSTRAINT `FR_sp_loaiSP` FOREIGN KEY (`IDLoaiSP`) REFERENCES `loaisanpham` (`IDLoai`) ON DELETE NO ACTION ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
