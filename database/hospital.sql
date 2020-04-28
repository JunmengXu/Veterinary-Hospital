CREATE DATABASE  IF NOT EXISTS `hospital` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `hospital`;
-- MySQL dump 10.13  Distrib 8.0.17, for Win64 (x86_64)
--
-- Host: localhost    Database: hospital
-- ------------------------------------------------------
-- Server version	8.0.17

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
-- Table structure for table `booking`
--

DROP TABLE IF EXISTS `booking`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `booking` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `urgency` int(11) NOT NULL,
  `hospital` int(11) NOT NULL,
  `time` datetime NOT NULL,
  `symptom` varchar(100) NOT NULL,
  `petId` int(11) DEFAULT NULL,
  `needtime` date NOT NULL,
  `distribution` int(11) NOT NULL,
  `releasetime` date DEFAULT NULL,
  `ratedis` int(11) NOT NULL,
  `ratevalue` int(11) NOT NULL,
  `ratecontent` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `petId_idx` (`petId`) /*!80000 INVISIBLE */,
  CONSTRAINT `petId` FOREIGN KEY (`petId`) REFERENCES `pet` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `booking`
--

LOCK TABLES `booking` WRITE;
/*!40000 ALTER TABLE `booking` DISABLE KEYS */;
INSERT INTO `booking` VALUES (1,0,0,'2020-04-06 10:20:08','have a fever',1,'2020-04-17',1,NULL,0,0,NULL),(2,1,1,'2020-04-06 11:50:08','red eyes',2,'2020-04-10',1,NULL,0,0,NULL),(3,0,2,'2020-04-07 15:24:08','Lack of vitality',3,'2020-05-01',1,'2020-05-05',0,0,NULL),(4,1,1,'2020-04-07 18:14:20','loss of appetite',2,'2020-05-10',1,NULL,0,0,NULL),(5,0,2,'2020-04-08 19:08:20','constipation',1,'2020-04-21',0,NULL,0,0,NULL),(6,0,1,'2020-04-09 20:45:20','drowsiness',1,'2020-05-15',1,'2020-05-18',1,4,'vdfv fdsvf fv '),(7,0,0,'2020-04-10 21:34:20','caugh',3,'2020-04-26',0,NULL,0,0,NULL),(9,0,2,'2020-04-12 09:04:48','emesis',6,'2020-05-10',1,NULL,0,0,NULL),(10,0,1,'2020-04-14 09:40:01','loose bowels',2,'2020-05-12',0,NULL,0,0,NULL),(11,1,0,'2020-04-15 09:40:01','psoriasis',4,'2020-04-28',0,NULL,0,0,NULL),(12,0,0,'2020-04-16 12:40:01','always nervous',5,'2020-06-07',0,NULL,0,0,NULL),(13,0,2,'2020-04-15 19:04:48','psoriasis psoriasis',5,'2020-05-10',1,NULL,0,0,NULL),(22,0,1,'2020-04-28 20:29:34','没啥病',20,'2020-05-09',1,'2020-05-15',1,5,'理解');
/*!40000 ALTER TABLE `booking` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer` (
  `id` int(11) NOT NULL,
  `name` varchar(45) NOT NULL,
  `pet` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES (1,'john','dog'),(2,'Alan','cat'),(3,'mary','cat');
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `message`
--

DROP TABLE IF EXISTS `message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `message` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` varchar(300) NOT NULL,
  `type` int(11) NOT NULL,
  `time` datetime NOT NULL,
  `custid` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `custid_idx` (`custid`),
  CONSTRAINT `custid` FOREIGN KEY (`custid`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `message`
--

LOCK TABLES `message` WRITE;
/*!40000 ALTER TABLE `message` DISABLE KEYS */;
INSERT INTO `message` VALUES (1,'hello,this is me',0,'2020-04-06 10:20:08',1),(2,'how could I help you',1,'2020-04-06 10:30:58',1),(3,'nothing',0,'2020-04-06 10:37:58',1),(4,'nothing',0,'2020-04-06 10:37:58',1),(5,'nothing',0,'2020-04-06 10:37:58',1),(6,'nothing',0,'2020-04-06 10:37:58',1),(7,'aCXSA',0,'2020-04-17 23:39:51',1),(8,'vfvbd',0,'2020-04-17 23:40:09',1),(9,'你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好',0,'2020-04-17 23:42:47',1),(10,'吃的是草',0,'2020-04-17 23:43:05',1),(11,'吃饭',0,'2020-04-17 23:43:24',1),(12,'下次',0,'2020-04-17 23:47:12',1),(13,'',0,'2020-04-17 23:47:26',1),(14,'口是心非',0,'2020-04-18 19:32:19',1),(15,'你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好',0,'2020-04-18 19:51:32',1),(16,'你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好',0,'2020-04-18 19:51:47',1),(17,'ceshi',0,'2020-04-27 11:34:56',1),(18,'do you know me',0,'2020-04-27 11:46:00',2),(19,'怎么啦',1,'2020-04-27 11:47:22',1),(20,'hcuds',0,'2020-04-27 14:21:26',4),(21,'你的宠物好了',1,'2020-04-28 20:36:14',1),(22,'收到啦',0,'2020-04-28 20:40:36',1);
/*!40000 ALTER TABLE `message` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pet`
--

DROP TABLE IF EXISTS `pet`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pet` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `gender` int(11) NOT NULL,
  `name` varchar(45) NOT NULL,
  `type` varchar(45) NOT NULL,
  `ownerId` int(11) DEFAULT NULL,
  `status` int(11) NOT NULL,
  `cover` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `ownerId_idx` (`ownerId`),
  CONSTRAINT `ownerId` FOREIGN KEY (`ownerId`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pet`
--

LOCK TABLES `pet` WRITE;
/*!40000 ALTER TABLE `pet` DISABLE KEYS */;
INSERT INTO `pet` VALUES (1,0,'Maru','cat',1,1,''),(2,1,'Abigail','cat',1,2,''),(3,1,'Leah','dog',2,3,''),(4,1,'Haley','cat',2,1,''),(5,0,'Pierre','dog',2,0,''),(6,0,'Vincent','cat',2,0,''),(10,0,'noodle','cat',1,0,''),(20,1,'流程测试','cat',1,0,'http://localhost:8181/api/file/2020-04-28-20-28-57d1y515.JPG');
/*!40000 ALTER TABLE `pet` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `account` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `phoneNumber` varchar(45) NOT NULL,
  `type` int(11) NOT NULL,
  `userimage` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_UNIQUE` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'mycust','12345','你猜','testha@tese.com','537-231456',0,'http://localhost:8181/api/file/2020-04-27-11-50-37iik304.JPG'),(2,'test','54321','lalala','test@vds.com','1564321',0,''),(3,'group5','12345','lalala','vvfdbjin','53456123',1,''),(4,'champion','12345','champion','vjd@vfd.com','561231',0,'');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-04-28 23:07:31
