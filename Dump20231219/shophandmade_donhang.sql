-- MySQL dump 10.13  Distrib 8.0.29, for Win64 (x86_64)
--
-- Host: handmadedb.chzjyklcgaiu.ap-southeast-2.rds.amazonaws.com    Database: shophandmade
-- ------------------------------------------------------
-- Server version	8.0.31

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
SET @MYSQLDUMP_TEMP_LOG_BIN = @@SESSION.SQL_LOG_BIN;
SET @@SESSION.SQL_LOG_BIN= 0;

--
-- GTID state at the beginning of the backup 
--

SET @@GLOBAL.GTID_PURGED=/*!80000 '+'*/ '';

--
-- Table structure for table `donhang`
--

DROP TABLE IF EXISTS `donhang`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `donhang` (
  `Ma` int NOT NULL AUTO_INCREMENT,
  `Ma_Nguoidung` int DEFAULT NULL,
  `Hoten_Nguoinhan` varchar(30) DEFAULT '',
  `SDT_Nguoinhan` varchar(10) NOT NULL,
  `Ghichu` varchar(300) DEFAULT '',
  `Ngaydat` datetime DEFAULT CURRENT_TIMESTAMP,
  `TrangthaiDH` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `Tongtien` float DEFAULT NULL,
  `Phuongthucvanchuyen` varchar(30) DEFAULT NULL,
  `Diachigiaohang` varchar(30) DEFAULT NULL,
  `Phuongthucthanhtoan` varchar(30) DEFAULT NULL,
  `Trangthai` tinyint(1) DEFAULT '1',
  PRIMARY KEY (`Ma`),
  KEY `Ma_Nguoidung` (`Ma_Nguoidung`),
  CONSTRAINT `donhang_chk_1` CHECK ((`Tongtien` >= 0))
) ENGINE=MyISAM AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `donhang`
--

LOCK TABLES `donhang` WRITE;
/*!40000 ALTER TABLE `donhang` DISABLE KEYS */;
INSERT INTO `donhang` VALUES (26,13,'Quốc Tiến','0866715216','giao nhanh','2023-12-18 00:00:00','Đang chuẩn bị',350000,'normal','phus yen','other',1),(25,13,'abc','1231231212','','2023-12-16 00:00:00','Đang chuẩn bị',2900000,'normal','phus yen','other',1),(24,13,'Quốc Tiến','1234567890','','2023-12-16 00:00:00','Đang chuẩn bị',3150000,'express','Ho chi minh','cod',1),(23,13,'abc','1234567890','','2023-12-15 00:00:00','Đang chuẩn bị',1250000,'normal','bagafaagag','cod',1),(22,13,'quocviet','0866715211','de vo','2023-12-15 00:00:00','Đang chuẩn bị',4000000,'express','Ho chi Minh','cod',1),(21,13,'quocviet','0866715211','','2023-12-15 00:00:00','Đang chuẩn bị',1800000,'express','Ho chi Minh','cod',1),(20,13,'quocviet','0866715211','','2023-12-15 00:00:00','Đang chuẩn bị',2750000,'express','phuyenn','cod',1),(18,13,'quocviet','0866715217','Hangdevo','2023-12-14 00:00:00','Giao hang thanh cong',500,'giaotietkiem','ha noi','cod',1),(19,13,'quocviet','0866715211','de vo','2023-12-15 00:00:00','Đang chuẩn bị',400000,'express','Ho chi Minh','other',1);
/*!40000 ALTER TABLE `donhang` ENABLE KEYS */;
UNLOCK TABLES;
SET @@SESSION.SQL_LOG_BIN = @MYSQLDUMP_TEMP_LOG_BIN;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-12-19  0:21:33
