CREATE DATABASE  IF NOT EXISTS `pizza_order` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `pizza_order`;
-- MySQL dump 10.13  Distrib 8.0.30, for Linux (x86_64)
--
-- Host: 127.0.0.1    Database: pizza_order
-- ------------------------------------------------------
-- Server version	8.0.30-0ubuntu0.20.04.2

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
-- Table structure for table `city`
--

DROP TABLE IF EXISTS `city`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `city` (
  `city_id` int NOT NULL AUTO_INCREMENT,
  `city_name` varchar(30) NOT NULL,
  `state_id` int NOT NULL,
  PRIMARY KEY (`city_id`),
  KEY `state_idFK` (`state_id`),
  CONSTRAINT `state_idFK` FOREIGN KEY (`state_id`) REFERENCES `state` (`state_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `city`
--

LOCK TABLES `city` WRITE;
/*!40000 ALTER TABLE `city` DISABLE KEYS */;
INSERT INTO `city` VALUES (1,'North and Middle Andaman',1),(2,'South Andaman',1),(3,'Nicobar',1),(4,'Tirupati',2),(5,'Guntur',2),(6,'Chittoor',2),(7,'Mapusa',11),(8,'Arambol',11),(9,'Candolim',11),(10,'Mumbai',21),(11,'Nagpur',21),(12,'Kolhapur',21);
/*!40000 ALTER TABLE `city` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `country`
--

DROP TABLE IF EXISTS `country`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `country` (
  `country_id` int NOT NULL AUTO_INCREMENT,
  `country_name` varchar(25) NOT NULL,
  PRIMARY KEY (`country_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `country`
--

LOCK TABLES `country` WRITE;
/*!40000 ALTER TABLE `country` DISABLE KEYS */;
INSERT INTO `country` VALUES (1,'India'),(2,'USA'),(3,'Sri Lanka'),(4,'Australia'),(5,'England');
/*!40000 ALTER TABLE `country` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `coupons`
--

DROP TABLE IF EXISTS `coupons`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `coupons` (
  `coupon_id` int NOT NULL AUTO_INCREMENT,
  `coupon_code` varchar(8) NOT NULL,
  `discount` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`coupon_id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `coupons`
--

LOCK TABLES `coupons` WRITE;
/*!40000 ALTER TABLE `coupons` DISABLE KEYS */;
INSERT INTO `coupons` VALUES (1,'ABC180',180),(2,'JIO250',250),(3,'XYZ125',125);
/*!40000 ALTER TABLE `coupons` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer` (
  `customer_id` int NOT NULL AUTO_INCREMENT,
  `customer_name` varchar(40) NOT NULL,
  `address_line1` varchar(50) NOT NULL,
  `address_line2` varchar(50) NOT NULL,
  `city_id` int NOT NULL,
  `state_id` int NOT NULL,
  `gender` enum('M','F') DEFAULT NULL,
  `email` varchar(25) DEFAULT NULL,
  `phone_number` varchar(15) NOT NULL,
  `country_id` int NOT NULL,
  PRIMARY KEY (`customer_id`),
  KEY `city_idFK` (`city_id`),
  KEY `state_idFK1` (`state_id`),
  KEY `country_idFK1` (`country_id`),
  CONSTRAINT `city_idFK` FOREIGN KEY (`city_id`) REFERENCES `city` (`city_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `country_idFK1` FOREIGN KEY (`country_id`) REFERENCES `country` (`country_id`),
  CONSTRAINT `state_idFK1` FOREIGN KEY (`state_id`) REFERENCES `state` (`state_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES (1,'Ram','Room no 1, abc bldg','sector 8',10,21,'M','ram@gmail.com','8794561203',1),(2,'Raj','Room no 7, xyz bldg','sector 24',9,11,'M','raj@gmail.','7854129634',1),(3,'Radha','Row House 2, plot no 502','near ghi band',4,2,'F','radha@gmail.com','8741524697',1),(7,'Ashish','Thane','Nupada, Thane',10,21,'M','ashish@gmail.com','8572006479',1),(10,'Tushar','Lane 1','Sector 9',11,21,'M','tushar@gmail.com','9876543210',1),(11,'Sahil','Abc building','Thane',10,21,'M','sahil@gmail.com','9876543210',1);
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee` (
  `employee_id` int NOT NULL AUTO_INCREMENT,
  `employee_name` varchar(50) NOT NULL,
  `employee_email` varchar(50) NOT NULL,
  `gender` enum('M','F') DEFAULT NULL,
  `address_line1` varchar(30) NOT NULL,
  `address_line2` varchar(30) NOT NULL,
  `city_id` int NOT NULL,
  `state_id` int NOT NULL,
  `country_id` int NOT NULL,
  `phone_number` varchar(20) NOT NULL,
  PRIMARY KEY (`employee_id`),
  KEY `city_idFK3` (`city_id`),
  KEY `state_idFK3` (`state_id`),
  KEY `country_idFK3` (`country_id`),
  CONSTRAINT `city_idFK3` FOREIGN KEY (`city_id`) REFERENCES `city` (`city_id`),
  CONSTRAINT `country_idFK3` FOREIGN KEY (`country_id`) REFERENCES `country` (`country_id`),
  CONSTRAINT `state_idFK3` FOREIGN KEY (`state_id`) REFERENCES `state` (`state_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` VALUES (1,'Ankit','ankit@gmail.com','M','House No 123','Ghansoli',10,21,1,'8759924013'),(2,'Sachin','sachin@gmail.com','M','Room no 1','Dombivali',10,21,1,'7954822480'),(3,'Pooja','pooja@gmail.com','F','Room no 101','abc',8,11,1,'9978245102'),(4,'Aditya','aditya@gmail.com','M','Plot no 505','near jkl bank',2,1,1,'8872014632');
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `feedback`
--

DROP TABLE IF EXISTS `feedback`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `feedback` (
  `feedback_id` int NOT NULL AUTO_INCREMENT,
  `customer_id` int NOT NULL,
  `feedback_status_id` int NOT NULL,
  `comments` varchar(200) NOT NULL,
  PRIMARY KEY (`feedback_id`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `feedback`
--

LOCK TABLES `feedback` WRITE;
/*!40000 ALTER TABLE `feedback` DISABLE KEYS */;
INSERT INTO `feedback` VALUES (1,1,1,'Good Pizza'),(2,3,2,'Preparation could be more faster'),(5,2,1,'Perfect Pizza Destination'),(29,2,2,'Less toppings'),(35,2,1,'Perfect Pizza');
/*!40000 ALTER TABLE `feedback` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `feedback_status`
--

DROP TABLE IF EXISTS `feedback_status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `feedback_status` (
  `feedback_status_id` int NOT NULL AUTO_INCREMENT,
  `feedback_status_type` varchar(20) NOT NULL,
  PRIMARY KEY (`feedback_status_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `feedback_status`
--

LOCK TABLES `feedback_status` WRITE;
/*!40000 ALTER TABLE `feedback_status` DISABLE KEYS */;
INSERT INTO `feedback_status` VALUES (1,'appreciation'),(2,'complaint'),(3,'suggestion');
/*!40000 ALTER TABLE `feedback_status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `login`
--

DROP TABLE IF EXISTS `login`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `login` (
  `login_id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(15) NOT NULL,
  `password` varchar(12) NOT NULL,
  `employee_id` int DEFAULT NULL,
  `customer_id` int DEFAULT NULL,
  PRIMARY KEY (`login_id`),
  UNIQUE KEY `username` (`username`),
  KEY `employee_idFK1` (`employee_id`),
  KEY `coustomer_idFK` (`customer_id`),
  CONSTRAINT `coustomer_idFK` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`customer_id`),
  CONSTRAINT `employee_idFK1` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`employee_id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `login`
--

LOCK TABLES `login` WRITE;
/*!40000 ALTER TABLE `login` DISABLE KEYS */;
INSERT INTO `login` VALUES (1,'ankit','ank123',1,NULL),(2,'pooja','poo123',3,NULL),(3,'aditya','adi123',4,NULL),(4,'sachin','sac123',2,NULL),(5,'ram','ram123',NULL,1),(6,'raj','raj123',NULL,2),(7,'radha','rad123',NULL,3),(11,'ashish','ash123',NULL,7),(12,'zack','zac123',NULL,8),(13,'riddhesh','rid123',NULL,9),(14,'tushar','tus123',NULL,10),(15,'sahil','sah123',NULL,11);
/*!40000 ALTER TABLE `login` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_items`
--

DROP TABLE IF EXISTS `order_items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_items` (
  `order_id` int NOT NULL,
  `pizza_id` int NOT NULL,
  `quantity` int NOT NULL,
  `amount` int NOT NULL,
  KEY `pizza_id_FK3_idx` (`pizza_id`),
  KEY `order_id_FK3_idx` (`order_id`),
  CONSTRAINT `order_id_FK3` FOREIGN KEY (`order_id`) REFERENCES `orders` (`order_id`) ON UPDATE CASCADE,
  CONSTRAINT `pizza_id_FK3` FOREIGN KEY (`pizza_id`) REFERENCES `pizza_menu` (`pizza_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_items`
--

LOCK TABLES `order_items` WRITE;
/*!40000 ALTER TABLE `order_items` DISABLE KEYS */;
INSERT INTO `order_items` VALUES (1,3,2,380),(1,1,3,303),(2,2,3,1050),(3,1,2,202),(3,3,4,760),(4,3,3,570),(4,1,1,101),(5,2,2,700),(5,1,1,101),(6,1,2,202),(6,3,5,950),(7,3,1,190),(7,1,3,303),(7,1,2,202),(7,1,2,202),(7,1,2,202),(7,2,2,700),(8,3,1,190),(8,2,1,350),(9,3,1,190),(9,1,3,303),(9,3,1,190),(10,2,4,1400),(11,2,2,700),(11,3,2,380);
/*!40000 ALTER TABLE `order_items` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_status`
--

DROP TABLE IF EXISTS `order_status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_status` (
  `status_id` int NOT NULL AUTO_INCREMENT,
  `status_type` varchar(45) NOT NULL,
  PRIMARY KEY (`status_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_status`
--

LOCK TABLES `order_status` WRITE;
/*!40000 ALTER TABLE `order_status` DISABLE KEYS */;
INSERT INTO `order_status` VALUES (1,'Completed'),(2,'Pending'),(3,'In Progress');
/*!40000 ALTER TABLE `order_status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `order_id` int NOT NULL AUTO_INCREMENT,
  `customer_id` int NOT NULL,
  `date_of_order` date DEFAULT NULL,
  `status_id` int NOT NULL DEFAULT '2',
  PRIMARY KEY (`order_id`),
  KEY `customer_dFK` (`customer_id`),
  CONSTRAINT `customer_dFK` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`customer_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (1,11,'2022-08-25',2),(2,7,'2022-08-25',2),(3,2,'2022-08-25',2),(4,7,'2022-08-25',2),(5,7,'2022-08-25',2),(6,7,'2022-08-25',2),(7,7,'2022-08-25',2),(8,7,'2022-08-25',2),(9,2,'2022-08-25',2),(10,2,'2022-08-25',2),(11,10,'2022-08-25',2);
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payment`
--

DROP TABLE IF EXISTS `payment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `payment` (
  `payment_id` int NOT NULL AUTO_INCREMENT,
  `mode_id` int NOT NULL,
  `order_id` int NOT NULL,
  `customer_id` int NOT NULL,
  `amount` int NOT NULL,
  `coupon_id` int DEFAULT '0',
  PRIMARY KEY (`payment_id`),
  KEY `mode_idFK` (`mode_id`),
  KEY `order_idFK3` (`order_id`),
  KEY `customer_idFK3` (`customer_id`),
  KEY `coupon_idFK_idx` (`coupon_id`),
  CONSTRAINT `customer_idFK3` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`customer_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `mode_idFK` FOREIGN KEY (`mode_id`) REFERENCES `payment_modes` (`mode_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `order_idFK3` FOREIGN KEY (`order_id`) REFERENCES `orders` (`order_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payment`
--

LOCK TABLES `payment` WRITE;
/*!40000 ALTER TABLE `payment` DISABLE KEYS */;
INSERT INTO `payment` VALUES (1,2,1,11,683,NULL),(2,1,2,7,870,1),(3,4,3,2,962,NULL),(4,2,4,7,546,3),(5,3,5,7,621,1),(6,4,6,7,1027,3),(7,1,7,7,1549,2),(8,2,8,7,540,NULL),(9,1,9,2,503,1),(10,3,10,2,1150,2),(11,2,11,10,900,1);
/*!40000 ALTER TABLE `payment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payment_modes`
--

DROP TABLE IF EXISTS `payment_modes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `payment_modes` (
  `mode_id` int NOT NULL AUTO_INCREMENT,
  `mode` varchar(15) NOT NULL,
  PRIMARY KEY (`mode_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payment_modes`
--

LOCK TABLES `payment_modes` WRITE;
/*!40000 ALTER TABLE `payment_modes` DISABLE KEYS */;
INSERT INTO `payment_modes` VALUES (1,'Cash'),(2,'Debit Card'),(3,'Credit Card'),(4,'UPI');
/*!40000 ALTER TABLE `payment_modes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pizza_menu`
--

DROP TABLE IF EXISTS `pizza_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pizza_menu` (
  `pizza_id` int NOT NULL AUTO_INCREMENT,
  `pizza_name` varchar(20) NOT NULL,
  `price` int NOT NULL,
  PRIMARY KEY (`pizza_id`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pizza_menu`
--

LOCK TABLES `pizza_menu` WRITE;
/*!40000 ALTER TABLE `pizza_menu` DISABLE KEYS */;
INSERT INTO `pizza_menu` VALUES (1,'Cheese',101),(2,'Chicken chunks',350),(3,'Capsicum',190);
/*!40000 ALTER TABLE `pizza_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `state`
--

DROP TABLE IF EXISTS `state`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `state` (
  `state_id` int NOT NULL AUTO_INCREMENT,
  `state_name` varchar(30) NOT NULL,
  `country_id` int NOT NULL,
  PRIMARY KEY (`state_id`),
  KEY `country_idFK` (`country_id`),
  CONSTRAINT `country_idFK` FOREIGN KEY (`country_id`) REFERENCES `country` (`country_id`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `state`
--

LOCK TABLES `state` WRITE;
/*!40000 ALTER TABLE `state` DISABLE KEYS */;
INSERT INTO `state` VALUES (1,'ANDAMAN AND NICOBAR ISLANDS',1),(2,'ANDHRA PRADESH',1),(3,'ARUNACHAL PRADESH',1),(4,'ASSAM',1),(5,'BIHAR',1),(6,'CHATTISGARH',1),(7,'CHANDIGARH',1),(8,'DAMAN AND DIU',1),(9,'DELHI',1),(10,'DADRA AND NAGAR HAVELI',1),(11,'GOA',1),(12,'GUJARAT',1),(13,'HIMACHAL PRADESH',1),(14,'HARYANA',1),(15,'JAMMU AND KASHMIR',1),(16,'JHARKHAND',1),(17,'KERALA',1),(18,'KARNATAKA',1),(19,'LAKSHADWEEP',1),(20,'MEGHALAYA',1),(21,'MAHARASHTRA',1),(22,'MANIPUR',1),(23,'MADHYA PRADESH',1),(24,'MIZORAM',1),(25,'NAGALAND',1),(26,'ORISSA',1),(27,'PUNJAB',1),(28,'PONDICHERRY',1),(29,'RAJASTHAN',1),(30,'SIKKIM',1),(31,'TAMIL NADU',1),(32,'TRIPURA',1),(33,'UTTARAKHAND',1),(34,'UTTAR PRADESH',1),(35,'WEST BENGAL',1),(36,'TELANGANA',1);
/*!40000 ALTER TABLE `state` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'pizza_order'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-08-26 10:04:54
