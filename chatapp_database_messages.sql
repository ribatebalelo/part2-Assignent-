-- MySQL dump 10.13  Distrib 8.0.41, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: chatapp_database
-- ------------------------------------------------------
-- Server version	8.0.41

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
-- Table structure for table `messages`
--

DROP TABLE IF EXISTS `messages`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `messages` (
  `id` int NOT NULL AUTO_INCREMENT,
  `sender` varchar(50) NOT NULL,
  `receiver` varchar(50) NOT NULL,
  `content` text NOT NULL,
  `timestamp` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `delivered` tinyint(1) DEFAULT '0',
  `read_status` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `messages`
--

LOCK TABLES `messages` WRITE;
/*!40000 ALTER TABLE `messages` DISABLE KEYS */;
INSERT INTO `messages` VALUES (1,'Riba_','Teba_','{\"from\":\"Riba_\",\"to\":\"Teba_\",\"message\":\"Hi\"}','2025-05-23 11:29:45',1,1),(2,'Teba_','Riba_','{\"from\":\"Teba_\",\"to\":\"Riba_\",\"message\":\"Hi\"}','2025-05-23 11:46:06',1,1),(3,'Riba_','Teba_','{\"from\":\"Riba_\",\"to\":\"Teba_\",\"message\":\"Are you good?\"}','2025-05-23 11:52:11',1,1),(4,'Riba_','Teba_','{\"from\":\"Riba_\",\"to\":\"Teba_\",\"message\":\"Are you good\"}','2025-05-23 11:52:40',1,1),(5,'Teba_','Riba_','{\"from\":\"Teba_\",\"to\":\"Riba_\",\"message\":\"How are you?\"}','2025-05-23 11:54:55',1,1),(6,'Riba_','Teba_','{\"from\":\"Riba_\",\"to\":\"Teba_\",\"message\":\"HI\"}','2025-05-23 12:06:36',1,1),(7,'Teba_','Riba_','{\"from\":\"Teba_\",\"to\":\"Riba_\",\"message\":\"Hi\"}','2025-05-23 12:13:39',1,1),(8,'Teba_','Riba_','{\"from\":\"Teba_\",\"to\":\"Riba_\",\"message\":\"Hi\"}','2025-05-23 17:17:14',1,1),(9,'Teba_','Riba_','{\"from\":\"Teba_\",\"to\":\"Riba_\",\"message\":\"Hello\"}','2025-05-24 12:19:54',1,1),(10,'Teba_','Riba_','{\"message\":\"Hi\"}','2025-05-24 12:41:10',1,1),(11,'Lelo_','Riba_','{\"from\":\"Lelo_\",\"to\":\"Riba_\",\"message\":\"Hi\"}','2025-05-24 13:09:39',1,1),(12,'Teba_','Riba_','{\"from\":\"Teba_\",\"to\":\"Riba_\",\"message\":\"hi\"}','2025-05-24 13:29:50',1,1),(13,'Teba_','Riba_','{\"from\":\"Teba_\",\"to\":\"Riba_\",\"message\":\"Hi\"}','2025-05-24 13:37:41',1,1),(14,'Teba_','Lelo_','{\"from\":\"Teba_\",\"to\":\"Lelo_\",\"message\":\"Hi\"}','2025-05-24 13:38:07',1,0),(15,'Teba_','Lelo_','{\"from\":\"Teba_\",\"to\":\"Lelo_\",\"message\":\"How are you?\"}','2025-05-24 13:59:14',1,0),(16,'Teba_','John_D','{\"from\":\"Teba_\",\"to\":\"John_D\",\"message\":\"Hi\"}','2025-05-24 14:09:53',1,0),(17,'Teba_','Teba_','{\"from\":\"Teba_\",\"to\":\"Teba_\",\"message\":\"Hi\"}','2025-05-24 14:14:34',1,1),(18,'Teba_','Tebz_','{\"from\":\"Teba_\",\"to\":\"Tebz_\",\"message\":\"Hi\"}','2025-05-24 14:17:35',1,0),(19,'Teba_','Riba_','{\"from\":\"Teba_\",\"to\":\"Riba_\",\"message\":\"I miss you\"}','2025-05-24 14:18:45',1,1),(20,'test_','receiver_','{\"from\":\"test_\",\"to\":\"receiver_\",\"message\":\"Hello from test\"}','2025-05-24 15:29:32',1,0),(21,'test_','Bruc_','{\"from\":\"test_\",\"to\":\"Bruc_\",\"message\":\"Hello from test\"}','2025-05-24 15:35:23',1,0);
/*!40000 ALTER TABLE `messages` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-05-26  6:56:38
