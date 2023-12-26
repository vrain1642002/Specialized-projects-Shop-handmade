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
-- Table structure for table `nguoidung`
--

DROP TABLE IF EXISTS `nguoidung`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `nguoidung` (
  `Ma` int NOT NULL AUTO_INCREMENT,
  `Matkhau` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '',
  `Hoten` varchar(40) DEFAULT '',
  `Ngaysinh` date DEFAULT NULL,
  `Ma_Vaitro` int DEFAULT NULL,
  `SDT` varchar(10) NOT NULL,
  `Diachi` varchar(50) DEFAULT '',
  `Ngaytao` datetime DEFAULT NULL,
  `Ngaycapnhat` datetime DEFAULT NULL,
  `Trangthai` tinyint(1) DEFAULT '1',
  PRIMARY KEY (`Ma`),
  KEY `Ma_Vaitro` (`Ma_Vaitro`)
) ENGINE=MyISAM AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nguoidung`
--

LOCK TABLES `nguoidung` WRITE;
/*!40000 ALTER TABLE `nguoidung` DISABLE KEYS */;
INSERT INTO `nguoidung` VALUES (13,'$2a$10$fj7R3aBvfT9nZiVCBr5HnOp4Y2ZenXxJsTm6qzvkgN0xOV0hVlJDO','Nguyễn Huỳnh Quốc Việt','2002-05-27',1,'0866715211','Phu yen','2023-12-10 10:23:20','2023-12-16 11:33:45',1),(3,'$2a$10$3vENkbIxgBRckskXLk8bwuSMSheI93aOkqY8gh2MVnUTHbr9DB4ZW','Châu Thanh Quỳnh Kim','2005-05-27',2,'0866715212','Đồng Nai','2023-12-07 16:43:11','2023-12-07 16:43:11',1),(4,'$2a$10$tfkxIdh5XwvBpyZC.4SrOONbgRSASP7IEixf3CGkjp9LzAtgqI00q','test','2023-11-30',2,'0866715214','Ho chi minh','2023-12-08 14:17:47','2023-12-08 14:17:47',1),(11,'$2a$10$trHa737wYXUgOw02gOe5FuA/ZiPJ6NpKXElmrdDgcVhMEKVS2yC1y','Châu Thanh Quỳnh Kim','2005-05-27',2,'0866715217','Đồng Nai','2023-12-08 22:15:44','2023-12-08 22:15:44',1),(6,'$2a$10$1j7ZN7qfH6YKa/wgiiNoYeiVOt0Joq2ZeWdV.R4NFISd5R9V9unnG','test','2023-12-08',2,'0866715213','','2023-12-08 14:20:31','2023-12-08 14:20:31',1),(7,'$2a$10$aoUa8kO0fdiArUvXH.KhOOC/sJ2kzXhZBPbtit1mKRRVTwekzLIEy','test','2023-11-28',2,'0866715215','Ho chi minh','2023-12-08 14:28:57','2023-12-08 14:28:57',1),(8,'$2a$10$aM8LgiLc1lTpEr.yZ0ayIu.g2j2dkrHIMc.FKfqZaAYCvTNLUmMTS','test','2023-11-28',2,'0866715218','Ho chi minh','2023-12-08 14:35:27','2023-12-08 14:35:27',1),(12,'$2a$10$0cJPLreaWRVpmfeqJ7eVoOosOPwhqmvO/lDK3pb8qQd4Jl.xHyk0O','Châu Thanh Quỳnh Kim','2005-05-27',2,'0866715219','Đồng Nai','2023-12-08 22:17:19','2023-12-08 22:17:19',1),(25,'$2a$10$WVJ6aYUHHtgrbgegSNQk/.uSX/rIMe1i5FP6M.V4SoxnyWwXa5fE2','abc','2023-12-02',2,'0123453779','phumy','2023-12-15 21:15:07','2023-12-15 21:15:07',1),(24,'$2a$10$BxMGBNUYEtxdKI0WHKk4T.lTeO5uZ0II791dnqYMkiVs5iPfUgdsi','abc','2023-12-02',2,'0123454779','phumy','2023-12-15 21:14:54','2023-12-15 21:14:54',1),(23,'$2a$10$zToSmALUdUDsGagviJltueYZ/0DNcHzqOT3Xy6itpQEZnFyzADZxa','abc','2023-12-02',2,'0123456799','phu quoc','2023-12-15 21:06:29','2023-12-15 21:06:29',1),(22,'123456789','Quoc nam','2023-11-01',2,'0123456789','Ho chi minh','2023-12-15 21:06:02','2023-12-16 15:30:18',1),(21,'$2a$10$OicYwfoz7lWfaHNIJ1B3GO/kv.h/HK.Q0IMkurh2w3g3GgBh42Esy','Quoc Thong','2023-12-01',2,'0123456779','Đồng Nai','2023-12-15 20:47:46','2023-12-18 15:16:51',1);
/*!40000 ALTER TABLE `nguoidung` ENABLE KEYS */;
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

-- Dump completed on 2023-12-19  0:21:19
