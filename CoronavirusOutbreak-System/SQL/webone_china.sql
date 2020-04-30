-- MySQL dump 10.13  Distrib 8.0.19, for Win64 (x86_64)
--
-- Host: localhost    Database: webone
-- ------------------------------------------------------
-- Server version	8.0.19

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

--
-- Table structure for table `china`
--

DROP TABLE IF EXISTS `china`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `china` (
  `provincename` varchar(45) NOT NULL,
  `currentc` int DEFAULT NULL,
  `confirmedc` int DEFAULT NULL,
  `suspectedc` int DEFAULT NULL,
  `curedc` int DEFAULT NULL,
  `deadc` int DEFAULT NULL,
  PRIMARY KEY (`provincename`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `china`
--

LOCK TABLES `china` WRITE;
/*!40000 ALTER TABLE `china` DISABLE KEYS */;
INSERT INTO `china` VALUES ('\"上海市\"',41,647,300,599,7),('\"云南省\"',2,185,0,181,2),('\"内蒙古自治区\"',49,200,34,150,1),('\"北京市\"',42,593,164,542,9),('\"台湾\"',112,429,343,311,6),('\"吉林省\"',11,111,3,99,1),('\"四川省\"',0,561,13,558,3),('\"天津市\"',4,190,48,183,3),('\"宁夏回族自治区\"',0,75,0,75,0),('\"安徽省\"',0,991,0,985,6),('\"山东省\"',8,787,14,772,7),('\"山西省\"',33,197,18,164,0),('\"广东省\"',23,1588,11,1557,8),('\"广西壮族自治区\"',0,254,0,252,2),('\"新疆维吾尔自治区\"',0,76,0,73,3),('\"江苏省\"',5,653,3,648,0),('\"江西省\"',0,937,0,936,1),('\"河北省\"',4,328,0,318,6),('\"河南省\"',0,1276,0,1254,22),('\"浙江省\"',4,1268,3,1263,1),('\"海南省\"',0,168,0,162,6),('\"湖北省\"',0,68128,0,63616,4512),('\"湖南省\"',0,1019,0,1015,4),('\"澳门\"',11,45,9,34,0),('\"甘肃省\"',0,139,0,137,2),('\"福建省\"',1,355,3,353,1),('\"西藏自治区\"',0,1,0,1,0),('\"贵州省\"',0,147,0,145,2),('\"辽宁省\"',1,146,0,143,2),('\"重庆市\"',0,579,1,573,6),('\"陕西省\"',50,306,1,253,3),('\"青海省\"',0,18,0,18,0),('\"香港\"',203,1037,47,830,4),('\"黑龙江省\"',339,939,384,587,13);
/*!40000 ALTER TABLE `china` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-04-30 20:33:34
