-- MySQL dump 10.13  Distrib 8.0.42, for Linux (x86_64)
--
-- Host: localhost    Database: acte_deces
-- ------------------------------------------------------
-- Server version	8.0.42

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `Acte_deces`
--

DROP TABLE IF EXISTS `Acte_deces`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Acte_deces` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nom_defunt` varchar(100) NOT NULL,
  `sexe_defunt` varchar(20) NOT NULL,
  `date_naissance_defunt` date DEFAULT NULL,
  `lieu_naissance_defunt` varchar(100) DEFAULT NULL,
  `ville_residence_defunt` varchar(100) DEFAULT NULL,
  `fonction_defunt` varchar(100) DEFAULT NULL,
  `nom_pere_defunt` varchar(100) DEFAULT NULL,
  `pere_defunt_vivant` tinyint(1) DEFAULT NULL,
  `nom_mere_defunt` varchar(100) DEFAULT NULL,
  `mere_defunt_vivante` tinyint(1) DEFAULT NULL,
  `nom_declarant` varchar(100) NOT NULL,
  `fonction_declarant` varchar(100) DEFAULT NULL,
  `ville_residence_declarant` varchar(100) DEFAULT NULL,
  `date_deces` date DEFAULT NULL,
  `heure_deces` time DEFAULT NULL,
  `ville_deces` varchar(100) DEFAULT NULL,
  `lieu_deces` varchar(100) DEFAULT NULL,
  `region_declaration` varchar(100) NOT NULL,
  `commune_declaration` varchar(100) NOT NULL,
  `officier_etat_civil` varchar(100) NOT NULL,
  `date_declaration` date NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Acte_deces`
--

LOCK TABLES `Acte_deces` WRITE;
/*!40000 ALTER TABLE `Acte_deces` DISABLE KEYS */;
INSERT INTO `Acte_deces` VALUES (1,'RAKOTO Rabe','Masculin',NULL,'Toamasina','','','',0,'',0,'KOTO Bota','','','2013-03-10','00:14:00','Antseranampasika','','Atsinanana','Toamasina','RATREMA','2013-03-11'),(2,'RASALAMA Tsara','Féminin','1999-02-11','Antanimenabaka','Andilamena','Mpampianatra','RASALAMA',0,'TSARA',1,'RADOKO Be','dokotera','Andilamena','2024-12-25','12:00:00','Andilamena','hopitaly','Alaotra-Mangoro','Andilamena','RASEFO Tany','2024-12-25'),(3,'Maina Lena','Masculin',NULL,'','','','',0,'',0,'Basivava','','','2014-05-03','14:18:00','Ankodahoda','','Alaotra-Mangoro','Ankodahoda','Mivolana','2018-05-15'),(4,'ZAZA Mena','Masculin','2024-07-13','Ambatolamby','Ambatolampy','','PAPA zazamena',1,'ZAZAMENA Reniny',1,'MAHERY Jhonson','mpitsabo','Ambatolampy','2024-07-29','13:23:00','Ambatolampy','hopitaly','Alaotra-Mangoro','Ambatolampy','NGIAH Be','2025-07-29'),(5,'JEAN Batiste','Masculin','2018-11-01','','','','',0,'',0,'KOTOZAFY Alexis','','','2025-08-01','07:25:00','Ankarofa','','Alaotra-Mangoro','Ankarofa','RANDRINA Tsara','2025-08-01'),(6,'ROSE SOA','Féminin','2010-03-05','','','','',0,'',0,'KOTOZAFY Alexis','','','2025-08-01','19:39:00','Ambodiakondro','','Atsimo-Andrefana','Ambodiakondro','RAJAOSOA','2025-08-01'),(7,'CLOSE Medi','Masculin','2010-02-28','','','','',0,'',0,'JEAN Batiste','','','2025-08-01','19:43:00','Brickaville','','Atsinanana','Brickaville','RAJAOSOA','2025-08-01'),(8,'SABINE Tazy','Féminin','2014-08-21','','','','',0,'',0,'KOTOZAFY Alexis','','','2025-08-01','19:45:00','Ampintandrano','','Anosy','Ampitandrano','RAJAONASY','2025-08-01'),(9,'NIRINA Ave','Féminin','1900-03-05','','','','',0,'',0,'KOTOZAFY Alexis','','','2025-08-01','19:46:00','Teotsita','','Sofia','Teotsita','RAJAONASY','2025-08-01'),(10,'RAZANAKA Paquerette','Masculin','1950-07-13','','','','',0,'',0,'KOTOZAFY Alexis','','','2025-08-01','19:49:00','Patsaroa','','Sava','Patsaroa','RAJAONASY','2025-08-01'),(11,'BOZINETY Tsarasoa Paiso','Masculin','1962-05-30','','','','',0,'',0,'KOTOZAFY Alexis','','','2025-08-01','19:53:00','Morapaoma','','Alaotra-Mangoro','Morapaoma','RAJAONASY','2025-08-01'),(12,'BERAZANA Andriabevava','Masculin','1937-01-12','','','','',0,'',0,'KOTOZAFY Alexis','','','2025-08-01','20:08:00','Soavahiny','','Fitovinany','Soavahiny','RAJAONASY','2025-08-01'),(13,'Herimanambintana Qwerty','Masculin','2000-04-12','','','','',0,'',0,'KOTOZAFY Alexis','','','2025-08-01','20:11:00','Azerty','','Alaotra-Mangoro','Azerty','RAJAONASY','2025-08-01'),(14,'Mister Bean Rakotomalala','Masculin','1999-06-13','','','','',0,'',0,'KOTOZAFY Alexis','','','2025-08-01','20:15:00','Amerigasy','','Alaotra-Mangoro','Amerigasy','RAJAONASY','2025-08-01'),(15,'RATSARATAREHY Baghia','Féminin','1989-12-24','','','','',0,'',0,'KOTOZAFY Alexis','','','2025-08-01','20:17:00','Toamasina','','Atsinanana','Toamasina','RAJAONASY','2025-08-01'),(16,'ZANAKATSIMO Soameva Tania','Masculin','2003-02-13','','','','',0,'',0,'KOTOZAFY Alexis','','','2025-08-01','20:19:00','Ankorozy','','Diana','Ankorozy','RAJAONASY','2025-08-01');
/*!40000 ALTER TABLE `Acte_deces` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-08-01 20:46:37
