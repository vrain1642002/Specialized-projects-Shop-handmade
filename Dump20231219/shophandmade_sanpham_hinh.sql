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
-- Table structure for table `sanpham_hinh`
--

DROP TABLE IF EXISTS `sanpham_hinh`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sanpham_hinh` (
  `Ma` int NOT NULL AUTO_INCREMENT,
  `Ma_Sanpham` int DEFAULT NULL,
  `Diachihinh` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  PRIMARY KEY (`Ma`),
  KEY `fk_sanpham_hinh_sanpham` (`Ma_Sanpham`)
) ENGINE=MyISAM AUTO_INCREMENT=45 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sanpham_hinh`
--

LOCK TABLES `sanpham_hinh` WRITE;
/*!40000 ALTER TABLE `sanpham_hinh` DISABLE KEYS */;
INSERT INTO `sanpham_hinh` VALUES (42,149,'761c7d8c-a897-42b9-8657-9657c7c76400_123.jpg'),(40,149,'cbd5df96-a431-483d-aa48-be834254320c_149.jpg'),(39,149,'0cab6c1c-419b-48e1-b2b6-c7e833b08303_1491.jpg'),(36,11,'04b2e1b5-df69-49bd-b463-34ab6c68ee22_11.jpg'),(38,10,'2727c528-4419-4f7e-963b-15782d4f3ad0_custom-giay-dep.jpg'),(41,149,'c5a8ed0f-0d9c-4e67-aa3d-47413e799fb0_1234.jpg'),(33,11,'ffdade77-e93b-4aa6-91b5-109df374b6e3_11.jpg'),(32,11,'936cb7bf-623c-44b3-9da5-d386c215d3d3_11 (2).jpg'),(30,9,'001e87d9-f2c7-4d98-807b-3d70474994b9_9.jpg'),(29,8,'85ed7a2a-391e-436b-a23b-a6ce2eed46be_8.jpg'),(28,7,'74b3c67b-fc6e-461a-a49d-7de2771a604d_7 (4) .jpg'),(27,7,'006dd82f-a4f9-4949-a77f-72f79016bcd6_7.jpg'),(26,7,'db0411f0-5e6e-4190-a2e9-973b8da6e0df_7 (3).jpg'),(25,5,'8522f49c-123d-47e2-b5ce-9e1ee8356606_download.jpg'),(24,6,'3ecbaeff-e5bc-40b5-ba6a-9f091cb905c6_bo-hoa-hong-bang-len-1691923386.jpg'),(23,4,'efeba2df-8486-41bb-aefa-f282fea1c0e9_2.jpg'),(22,2,'82391a9c-557f-4886-96f6-00268f2b3907_1.jpg'),(43,11,'986a7841-8cc9-4fb0-a8da-bdd951c390fb_anh.jpg'),(44,9,'0b574c34-7384-4163-ad34-532f977f9884_test.jpg');
/*!40000 ALTER TABLE `sanpham_hinh` ENABLE KEYS */;
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

-- Dump completed on 2023-12-19  0:21:25
