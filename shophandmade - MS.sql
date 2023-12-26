CREATE DATABASE shophandmade;
USE shophandmade;
GO

-- Create Vaitro table
CREATE TABLE Vaitro (
    Ma INT PRIMARY KEY,
    Ten VARCHAR(20) NOT NULL
);


-- Create Nguoidung table
CREATE TABLE Nguoidung (
    Ma INT PRIMARY KEY IDENTITY(1,1),
    Matkhau VARCHAR(40) NOT NULL DEFAULT '',
    Hoten VARCHAR(40) DEFAULT '',
    Ngaysinh DATE,
    Ma_Vaitro INT,
    SDT VARCHAR(10) NOT NULL,
    Diachi VARCHAR(50) DEFAULT '',
    Ngaytao DATETIME,
    Ngaycapnhat DATETIME,
    Trangthai TINYINT DEFAULT 1,
    FOREIGN KEY (Ma_Vaitro) REFERENCES Vaitro (Ma)
);





-- Create Danhmucsanpham table
CREATE TABLE Danhmucsanpham (
    Ma INT PRIMARY KEY IDENTITY(1,1),
    Ten VARCHAR(30) NOT NULL DEFAULT ''
);


-- Create Sanpham table
CREATE TABLE Sanpham (
    Ma INT PRIMARY KEY IDENTITY(1,1),
    Ten VARCHAR(30),
    Gia FLOAT NOT NULL CHECK (Gia >= 0),
    Hinhthunho VARCHAR(50) DEFAULT '',
    Mota TEXT DEFAULT '',
    Ngaytao DATETIME,
    Ngaycapnhat DATETIME,
    Ma_Danhmucsanpham INT,
    FOREIGN KEY (Ma_Danhmucsanpham) REFERENCES Danhmucsanpham (Ma)
);


-- Create Sanpham_Hinh table
CREATE TABLE Sanpham_Hinh (
    Ma INT PRIMARY KEY IDENTITY(1,1),
    Ma_Sanpham INT,
    Diachihinh VARCHAR(300),
    FOREIGN KEY (Ma_Sanpham) REFERENCES Sanpham (Ma) ON DELETE CASCADE
);


-- Create Danhgia table
CREATE TABLE Danhgia (
    Ma INT PRIMARY KEY IDENTITY(1,1),
    Ngaytao DATETIME,
    Sodiem TINYINT,
    Noidung TEXT DEFAULT '',
    Ma_Sanpham INT,
    Ma_Nguoidung INT,
    FOREIGN KEY (Ma_Sanpham) REFERENCES Sanpham (Ma),
    FOREIGN KEY (Ma_Nguoidung) REFERENCES Nguoidung (Ma)
);


-- Create Donhang table
CREATE TABLE Donhang (
    Ma INT PRIMARY KEY IDENTITY(1,1),
    Ma_Nguoidung INT,
    Hoten_Nguoinhan VARCHAR(30) DEFAULT '',
    SDT_Nguoinhan VARCHAR(10) NOT NULL,
    Diachi_Nguoinhan VARCHAR(50) DEFAULT '',
    Ghichu VARCHAR(300) DEFAULT '',
    Ngaydat DATETIME DEFAULT GETDATE(),
    TrangthaiDH VARCHAR(20),
    Tongtien FLOAT CHECK(Tongtien >= 0), 
    Phuongthucvanchuyen VARCHAR(30),  
    Diachigiaohang VARCHAR(30),  
    Phuongthucthanhtoan VARCHAR(30),   
    Trangthai TINYINT DEFAULT 1,
    FOREIGN KEY (Ma_Nguoidung) REFERENCES Nguoidung (Ma)
);


-- Create Chitietdonhang table
CREATE TABLE Chitietdonhang (
   
    Gia FLOAT CHECK(Gia >= 0),
    Soluong INT CHECK(Soluong > 0),
    Thanhtien FLOAT CHECK(Thanhtien >= 0),
    Ma_Donhang INT,
    Ma_Sanpham INT,
    FOREIGN KEY (Ma_Donhang) REFERENCES Donhang (Ma),
    FOREIGN KEY (Ma_Sanpham) REFERENCES Sanpham (Ma)
);