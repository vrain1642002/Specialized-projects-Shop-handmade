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
-- Table structure for table `sanpham`
--

DROP TABLE IF EXISTS `sanpham`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sanpham` (
  `Ma` int NOT NULL AUTO_INCREMENT,
  `Ten` varchar(30) DEFAULT NULL,
  `Gia` float NOT NULL,
  `Hinhthunho` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '',
  `Mota` longtext,
  `Ngaytao` datetime DEFAULT NULL,
  `Ngaycapnhat` datetime DEFAULT NULL,
  `Ma_Danhmucsanpham` int DEFAULT NULL,
  PRIMARY KEY (`Ma`),
  KEY `Ma_Danhmucsanpham` (`Ma_Danhmucsanpham`),
  CONSTRAINT `sanpham_chk_1` CHECK ((`Gia` >= 0))
) ENGINE=MyISAM AUTO_INCREMENT=152 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sanpham`
--

LOCK TABLES `sanpham` WRITE;
/*!40000 ALTER TABLE `sanpham` DISABLE KEYS */;
INSERT INTO `sanpham` VALUES (2,'Bông hoa đan hình con heo',100000,'82391a9c-557f-4886-96f6-00268f2b3907_1.jpg','bông hoa len dễ thương','2023-12-04 10:26:10','2023-12-09 10:54:49',4),(4,'Bông hoa hình hướng dương',70000,'efeba2df-8486-41bb-aefa-f282fea1c0e9_2.jpg','hoa','2023-12-04 23:56:42','2023-12-09 10:55:08',4),(5,'Bông hoa tulip',80000,'8522f49c-123d-47e2-b5ce-9e1ee8356606_download.jpg','Bông hoa tulip','2023-12-04 23:56:42','2023-12-09 10:59:55',4),(7,'Hoa kẽm nhung',60000,'74b3c67b-fc6e-461a-a49d-7de2771a604d_7 (4) .jpg','Hoa kẽm nhung','2023-12-04 23:57:12','2023-12-09 11:01:25',5),(8,'Hộp kit tự làm',200000,'85ed7a2a-391e-436b-a23b-a6ce2eed46be_8.jpg','hộp quà tặng','2023-12-04 23:58:21','2023-12-09 11:01:49',3),(9,'Bộ cột tóc handmade',150000,'0b574c34-7384-4163-ad34-532f977f9884_test.jpg','Bộ nguyên liệu cột tóc','2023-12-04 23:58:21','2023-12-16 16:25:23',3),(10,'Giày custom',700000,'2727c528-4419-4f7e-963b-15782d4f3ad0_custom-giay-dep.jpg','Giày custom\r\n','2023-12-04 23:58:21','2023-12-09 16:49:56',12),(11,'Áo custom',500000,'986a7841-8cc9-4fb0-a8da-bdd951c390fb_anh.jpg','Áo vẽ tay','2023-12-04 23:58:21','2023-12-11 00:38:59',12),(149,'Hoa Len siêu đẹp',700000,'761c7d8c-a897-42b9-8657-9657c7c76400_123.jpg','Hoa đan thủ công ','2023-12-10 10:28:38','2023-12-11 00:35:07',4);
/*!40000 ALTER TABLE `sanpham` ENABLE KEYS */;
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

-- Dump completed on 2023-12-19  0:20:57
