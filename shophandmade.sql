create database shophandmade;
use shophandmade;
CREATE TABLE Nguoidung(
    Ma INT PRIMARY KEY AUTO_INCREMENT,
    Matkhau VARCHAR(40) NOT NULL DEFAULT '',
    Hoten VARCHAR(40) DEFAULT '',
    Ngaysinh DATE,
    Ma_Vaitro INT,
    SDT VARCHAR(10) NOT NULL,
    Diachi VARCHAR(50) DEFAULT '',
    Ngaytao DATETIME,
    Ngaycapnhat DATETIME,
    Trangthai TINYINT(1) DEFAULT 1,
    FOREIGN KEY (Ma_Vaitro) REFERENCES Vaitro (Ma)
);

CREATE TABLE Vaitro(
    Ma INT PRIMARY KEY,
    Ten VARCHAR(20) NOT NULL 
);


CREATE TABLE tokens(
    id int PRIMARY KEY AUTO_INCREMENT,
    token varchar(100) UNIQUE NOT NULL,
    token_type varchar(50) NOT NULL,
    expiration_date DATETIME,
    revoked tinyint(1) NOT NULL,
    expired tinyint(1) NOT NULL,
    user_id int,
    FOREIGN KEY (user_id) REFERENCES Nguoidung(ma)
);



CREATE TABLE Danhmucsanpham(
    Ma INT PRIMARY KEY AUTO_INCREMENT,
    Ten varchar(30) NOT NULL DEFAULT '' 
);

CREATE TABLE Sanpham (
    Ma INT PRIMARY KEY AUTO_INCREMENT,
    Ten VARCHAR(30),
    Gia FLOAT NOT NULL CHECK (Gia >= 0),
    Hinhthunho VARCHAR(100) DEFAULT '',
    Mota LONGTEXT DEFAULT '',
    Ngaytao DATETIME,
    Ngaycapnhat DATETIME,
    Ma_Danhmucsanpham INT,
    FOREIGN KEY (Ma_Danhmucsanpham) REFERENCES Danhmucsanpham (Ma)
);
CREATE TABLE Sanpham_Hinh (
    Ma INT PRIMARY KEY AUTO_INCREMENT,
    Ma_Sanpham INT,
    Diachihinh VARCHAR(100),
    FOREIGN KEY (Ma_Sanpham) REFERENCES Sanpham (Ma),
    CONSTRAINT fk_sanpham_hinh_sanpham
    FOREIGN KEY (Ma_Sanpham) REFERENCES Sanpham (Ma) ON DELETE CASCADE
);

CREATE TABLE Danhgia (
    Ma INT PRIMARY KEY AUTO_INCREMENT,
    Ngaytao DATETIME,
    Sodiem TINYINT(5) ,
    Noidung LONGTEXT DEFAULT '',
    Ma_Sanpham INT,
    Ma_Nguoidung INT,
    FOREIGN KEY (Ma_Sanpham) REFERENCES Sanpham (Ma),
    FOREIGN KEY (Ma_Nguoidung) REFERENCES Nguoidung (Ma)
);
CREATE TABLE Donhang(
    Ma INT PRIMARY KEY AUTO_INCREMENT,
    Ma_Nguoidung INT,
    Hoten_Nguoinhan VARCHAR(30) DEFAULT '',
    SDT_Nguoinhan VARCHAR(10) NOT NULL,
    Diachi_Nguoinhan VARCHAR(50) DEFAULT '',
    Ghichu VARCHAR(300) DEFAULT '',
    Ngaydat DATETIME DEFAULT CURRENT_TIMESTAMP,
    TrangthaiDH VARCHAR(20),
    Tongtien FLOAT CHECK(Tongtien >= 0), 
    Phuongthucvanchuyen VARCHAR(30),  
    Diachigiaohang VARCHAR(30),  
    Phuongthucthanhtoan VARCHAR(30),   
    Trangthai TINYINT(1) DEFAULT 1,
    FOREIGN KEY (Ma_Nguoidung) REFERENCES Nguoidung (Ma)
);


CREATE TABLE Chitietdonhang(
    Ma INT PRIMARY KEY AUTO_INCREMENT,
    Gia FLOAT CHECK(Gia >= 0),
    Soluong INT CHECK(Soluong > 0),
    Thanhtien FLOAT CHECK(Thanhtien >= 0),
    Ma_Donhang INT,
    Ma_Sanpham INT,
    FOREIGN KEY (Ma_Donhang) REFERENCES Donhang (Ma),
    FOREIGN KEY (Ma_Sanpham) REFERENCES Sanpham (Ma)
   
);
