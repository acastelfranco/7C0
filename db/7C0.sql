-- MySQL dump 10.13  Distrib 8.0.30, for Win64 (x86_64)
--
-- Host: localhost    Database: FC0
-- ------------------------------------------------------
-- Server version	8.0.30

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
-- Current Database: `FC0`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `FC0` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `FC0`;

--
-- Table structure for table `Board`
--

DROP TABLE IF EXISTS `Board`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Board` (
  `country_id` varchar(45) NOT NULL,
  `country_name` varchar(45) NOT NULL,
  `tanks` int NOT NULL DEFAULT '0',
  `username` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`country_id`),
  KEY `username_idx` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Board`
--

LOCK TABLES `Board` WRITE;
/*!40000 ALTER TABLE `Board` DISABLE KEYS */;
INSERT INTO `Board` VALUES ('afghanistan','Afghanistan',1,'bot1'),('africa_del_nord','Africa del Nord',1,'antonio'),('africa_del_sud','Africa del Sud',1,'antonio'),('africa_orientale','Africa Orientale',1,'bot2'),('alaska','Alaska',1,'antonio'),('alberta','Alberta',1,'antonio'),('america_centrale','America Centrale',1,'bot1'),('argentina','Argentina',1,'bot3'),('australia_occidentale','Australia Occidentale',1,'bot2'),('australia_orientale','Australia Orientale',1,'bot2'),('brasile','Brasile',1,'antonio'),('cina','Cina',1,'bot1'),('cita','Cita',1,'antonio'),('congo','Congo',1,'bot2'),('egitto','Egitto',1,'bot2'),('europa_meridionale','Europa Meridionale',1,'bot1'),('europa_occidentale','Europa Occidentale',1,'bot1'),('europa_settentrionale','Europa Settentrionale',1,'bot2'),('giappone','Giappone',1,'bot3'),('gran_bretagna','Gran Bretagna',1,'bot3'),('groenlandia','Groenlandia',1,'antonio'),('india','India',1,'antonio'),('indonesia','Indonesia',1,'antonio'),('islanda','Islanda',1,'antonio'),('jacuzia','Jacuzia',1,'bot1'),('kamchatka','Kamchatka',1,'bot1'),('madagascar','Madagascar',1,'bot3'),('medio_oriente','Medio Oriente',1,'bot2'),('mongolia','Mongolia',1,'bot2'),('nuova_guinea','Nuova Guinea',1,'bot2'),('ontario','Ontario',1,'bot3'),('peru','Perù',1,'bot2'),('quebec','Quebec',1,'bot3'),('scandinavia','Scandinavia',1,'bot3'),('siam','Siam',1,'bot3'),('siberia','Siberia',1,'bot1'),('stati_uniti_occidentali','Stati Uniti Occidentali',1,'bot3'),('stati_uniti_orientali','Stati Uniti Orientali',1,'bot1'),('territori_del_nord_ovest','Territori del Nord Ovest',1,'bot1'),('ucraina','Ucraina',1,'bot3'),('urali','Urali',1,'bot3'),('venezuela','Venezuela',1,'bot2');
/*!40000 ALTER TABLE `Board` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `User`
--

DROP TABLE IF EXISTS `User`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `User` (
  `username` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `name` varchar(45) NOT NULL,
  `surname` varchar(45) NOT NULL,
  `game` varchar(45) DEFAULT NULL,
  `color` varchar(45) NOT NULL DEFAULT '#000000',
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `User`
--

LOCK TABLES `User` WRITE;
/*!40000 ALTER TABLE `User` DISABLE KEYS */;
INSERT INTO `User` VALUES ('antonio','password','Antonio','Castelfranco','World War III','#36454F'),('bot1','password','Han','Solo','World War III','#008000'),('bot2','password','Luke','Skywalker','World War III','#ff0000'),('bot3','password','Obi Wan','Kenobi','World War III','#ffff00'),('marco','password','Marco','Martino',NULL,'#36454F'),('mario','password','Mario','Castelfranco',NULL,'#36454F'),('vincenzo','password','Vincenzo Antonio','Castelfranco',NULL,'#36454F');
/*!40000 ALTER TABLE `User` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `world_war_iii`
--

DROP TABLE IF EXISTS `world_war_iii`;
/*!50001 DROP VIEW IF EXISTS `world_war_iii`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `world_war_iii` AS SELECT 
 1 AS `username`,
 1 AS `color`*/;
SET character_set_client = @saved_cs_client;

--
-- Current Database: `FC0`
--

USE `FC0`;

--
-- Final view structure for view `world_war_iii`
--

/*!50001 DROP VIEW IF EXISTS `world_war_iii`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`antonino`@`%` SQL SECURITY DEFINER */
/*!50001 VIEW `world_war_iii` AS select `user`.`username` AS `username`,`user`.`color` AS `color` from `user` where (`user`.`game` = 'World War III') */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-09-27 10:58:25
