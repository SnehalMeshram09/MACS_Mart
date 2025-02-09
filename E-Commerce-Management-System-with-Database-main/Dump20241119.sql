-- MySQL dump 10.13  Distrib 8.0.40, for Win64 (x86_64)
--
-- Host: localhost    Database: macs_mart
-- ------------------------------------------------------
-- Server version	8.0.40

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
-- Table structure for table `cart`
--

DROP TABLE IF EXISTS `cart`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cart` (
  `cart_id` int NOT NULL AUTO_INCREMENT,
  `customer_id` int NOT NULL,
  `total_items` int DEFAULT '0',
  `total_amount` decimal(10,2) DEFAULT '0.00',
  `created_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`cart_id`),
  KEY `fk_customer_cart` (`customer_id`),
  CONSTRAINT `fk_customer_cart` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`customer_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cart`
--

LOCK TABLES `cart` WRITE;
/*!40000 ALTER TABLE `cart` DISABLE KEYS */;
INSERT INTO `cart` VALUES (1,1,0,0.00,'2024-11-15 02:23:11','2024-11-15 02:25:50'),(2,2,0,0.00,'2024-11-15 02:34:14','2024-11-15 02:36:10'),(3,5,2,70075.00,'2024-11-15 04:24:32','2024-11-15 04:30:44'),(4,8,2,7120.00,'2024-11-15 05:50:38','2024-11-15 06:25:50');
/*!40000 ALTER TABLE `cart` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cart_item`
--

DROP TABLE IF EXISTS `cart_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cart_item` (
  `cart_item_id` int NOT NULL AUTO_INCREMENT,
  `cart_id` int NOT NULL,
  `product_id` int NOT NULL,
  `quantity` int NOT NULL,
  PRIMARY KEY (`cart_item_id`),
  KEY `product_id` (`product_id`),
  KEY `fk_customer_cart_item` (`cart_id`),
  CONSTRAINT `cart_item_ibfk_2` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`),
  CONSTRAINT `fk_customer_cart_item` FOREIGN KEY (`cart_id`) REFERENCES `cart` (`cart_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cart_item`
--

LOCK TABLES `cart_item` WRITE;
/*!40000 ALTER TABLE `cart_item` DISABLE KEYS */;
INSERT INTO `cart_item` VALUES (18,3,12,5),(19,3,43,1),(25,4,1,10),(26,4,34,1);
/*!40000 ALTER TABLE `cart_item` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = cp850 */ ;
/*!50003 SET character_set_results = cp850 */ ;
/*!50003 SET collation_connection  = cp850_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `update_cart_totals_after_insert` AFTER INSERT ON `cart_item` FOR EACH ROW BEGIN
    DECLARE item_count INT;
    DECLARE amount DECIMAL(10, 2);

    
    SELECT COUNT(*) INTO item_count
    FROM cart_item
    WHERE cart_id = NEW.cart_id;

    
    SELECT SUM(p.price * ci.quantity) INTO amount
    FROM cart_item ci
    JOIN product p ON ci.product_id = p.product_id
    WHERE ci.cart_id = NEW.cart_id;

    
    UPDATE cart
    SET total_items = item_count,
        total_amount = IFNULL(amount, 0)  
    WHERE cart_id = NEW.cart_id;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = cp850 */ ;
/*!50003 SET character_set_results = cp850 */ ;
/*!50003 SET collation_connection  = cp850_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `update_cart_date` AFTER INSERT ON `cart_item` FOR EACH ROW UPDATE cart 
SET updated_date = NOW() 
WHERE cart_id = NEW.cart_id */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = cp850 */ ;
/*!50003 SET character_set_results = cp850 */ ;
/*!50003 SET collation_connection  = cp850_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `update_cart_totals_after_delete` AFTER DELETE ON `cart_item` FOR EACH ROW BEGIN
    DECLARE item_count INT;
    DECLARE amount DECIMAL(10, 2);

    
    SELECT COUNT(*) INTO item_count
    FROM cart_item
    WHERE cart_id = OLD.cart_id;

    
    SELECT SUM(p.price * ci.quantity) INTO amount
    FROM cart_item ci
    JOIN product p ON ci.product_id = p.product_id
    WHERE ci.cart_id = OLD.cart_id;

    
    UPDATE cart
    SET total_items = item_count,
        total_amount = IFNULL(amount, 0)  
    WHERE cart_id = OLD.cart_id;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category` (
  `category_id` int NOT NULL AUTO_INCREMENT,
  `category_name` varchar(100) NOT NULL,
  PRIMARY KEY (`category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'Groceries'),(2,'Fashion'),(3,'Electricals'),(4,'Stationary');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer` (
  `customer_id` int NOT NULL AUTO_INCREMENT,
  `first_name` varchar(50) NOT NULL,
  `last_name` varchar(50) NOT NULL,
  `Name` varchar(101) DEFAULT NULL,
  `email` varchar(100) NOT NULL,
  `phone_number` varchar(15) NOT NULL,
  `created_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`customer_id`),
  UNIQUE KEY `email` (`email`),
  KEY `idx_phone_number` (`phone_number`),
  CONSTRAINT `chk_email` CHECK ((`email` like _utf8mb4'%@%')),
  CONSTRAINT `chk_phone_number` CHECK (((length(`phone_number`) = 10) and regexp_like(`phone_number`,_utf8mb4'^[0-9]+$')))
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES (1,'chaitali','kulkarni','chaitali kulkarni','chait@gmail.com','1234567890','2024-11-15 02:23:11'),(2,'anjali','mhetre','anjali mhetre','anj@gmail,com','3216549870','2024-11-15 02:34:14'),(5,'Sneha','Rao','Sneha Rao','s123@gmail.com','9898765456','2024-11-15 04:24:32'),(8,'gargi','pawar','gargi pawar','gar@gmail.com;','1234567890','2024-11-15 05:50:38');
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = cp850 */ ;
/*!50003 SET character_set_results = cp850 */ ;
/*!50003 SET collation_connection  = cp850_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `before_insert_customer` BEFORE INSERT ON `customer` FOR EACH ROW SET NEW.Name = CONCAT(NEW.first_name, ' ', NEW.last_name) */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = cp850 */ ;
/*!50003 SET character_set_results = cp850 */ ;
/*!50003 SET collation_connection  = cp850_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `after_customer_insert` AFTER INSERT ON `customer` FOR EACH ROW BEGIN
    
    INSERT INTO cart (customer_id, total_items, total_amount, created_date, updated_date)
    VALUES (NEW.customer_id, 0, 0.00, NOW(), NOW());

    
    
    
    SET @cart_id = LAST_INSERT_ID();
    
    
    
    
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = cp850 */ ;
/*!50003 SET character_set_results = cp850 */ ;
/*!50003 SET collation_connection  = cp850_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `before_update_customer` BEFORE UPDATE ON `customer` FOR EACH ROW SET NEW.Name = CONCAT(NEW.first_name, ' ', NEW.last_name) */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Temporary view structure for view `customer_cart_view`
--

DROP TABLE IF EXISTS `customer_cart_view`;
/*!50001 DROP VIEW IF EXISTS `customer_cart_view`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `customer_cart_view` AS SELECT 
 1 AS `customer_id`,
 1 AS `first_name`,
 1 AS `last_name`,
 1 AS `cart_id`,
 1 AS `total_amount`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `dairy_products`
--

DROP TABLE IF EXISTS `dairy_products`;
/*!50001 DROP VIEW IF EXISTS `dairy_products`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `dairy_products` AS SELECT 
 1 AS `product_id`,
 1 AS `name`,
 1 AS `price`,
 1 AS `description`,
 1 AS `stock`,
 1 AS `category_id`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `drawing_items`
--

DROP TABLE IF EXISTS `drawing_items`;
/*!50001 DROP VIEW IF EXISTS `drawing_items`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `drawing_items` AS SELECT 
 1 AS `product_id`,
 1 AS `name`,
 1 AS `price`,
 1 AS `description`,
 1 AS `stock`,
 1 AS `category_id`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `electrical_appliances`
--

DROP TABLE IF EXISTS `electrical_appliances`;
/*!50001 DROP VIEW IF EXISTS `electrical_appliances`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `electrical_appliances` AS SELECT 
 1 AS `product_id`,
 1 AS `name`,
 1 AS `price`,
 1 AS `description`,
 1 AS `stock`,
 1 AS `category_id`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `electronic_devices`
--

DROP TABLE IF EXISTS `electronic_devices`;
/*!50001 DROP VIEW IF EXISTS `electronic_devices`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `electronic_devices` AS SELECT 
 1 AS `product_id`,
 1 AS `name`,
 1 AS `price`,
 1 AS `description`,
 1 AS `stock`,
 1 AS `category_id`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `fruits`
--

DROP TABLE IF EXISTS `fruits`;
/*!50001 DROP VIEW IF EXISTS `fruits`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `fruits` AS SELECT 
 1 AS `product_id`,
 1 AS `name`,
 1 AS `price`,
 1 AS `description`,
 1 AS `stock`,
 1 AS `category_id`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `kids_section`
--

DROP TABLE IF EXISTS `kids_section`;
/*!50001 DROP VIEW IF EXISTS `kids_section`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `kids_section` AS SELECT 
 1 AS `product_id`,
 1 AS `name`,
 1 AS `price`,
 1 AS `description`,
 1 AS `stock`,
 1 AS `category_id`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `mens_wear`
--

DROP TABLE IF EXISTS `mens_wear`;
/*!50001 DROP VIEW IF EXISTS `mens_wear`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `mens_wear` AS SELECT 
 1 AS `product_id`,
 1 AS `name`,
 1 AS `price`,
 1 AS `description`,
 1 AS `stock`,
 1 AS `category_id`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `paper_items`
--

DROP TABLE IF EXISTS `paper_items`;
/*!50001 DROP VIEW IF EXISTS `paper_items`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `paper_items` AS SELECT 
 1 AS `product_id`,
 1 AS `name`,
 1 AS `price`,
 1 AS `description`,
 1 AS `stock`,
 1 AS `category_id`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `payment`
--

DROP TABLE IF EXISTS `payment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `payment` (
  `payment_ID` int NOT NULL AUTO_INCREMENT,
  `payment_method` varchar(50) NOT NULL,
  `payment_status` varchar(20) NOT NULL,
  `payment_date` date NOT NULL,
  `cart_id` int DEFAULT NULL,
  PRIMARY KEY (`payment_ID`),
  KEY `cart_id` (`cart_id`),
  CONSTRAINT `payment_ibfk_1` FOREIGN KEY (`cart_id`) REFERENCES `cart` (`cart_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payment`
--

LOCK TABLES `payment` WRITE;
/*!40000 ALTER TABLE `payment` DISABLE KEYS */;
INSERT INTO `payment` VALUES (1,'Credit Card','Paid','2024-11-15',1),(2,'Credit Card','Paid','2024-11-15',2),(3,'Debit Card','Paid','2024-11-15',3),(4,'Debit Card','Paid','2024-11-15',4);
/*!40000 ALTER TABLE `payment` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = cp850 */ ;
/*!50003 SET character_set_results = cp850 */ ;
/*!50003 SET collation_connection  = cp850_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `after_payment_insert` AFTER INSERT ON `payment` FOR EACH ROW BEGIN
    
    DELETE FROM cart_item
    WHERE cart_id = NEW.cart_id;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
  `product_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `price` decimal(10,2) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `stock` int NOT NULL,
  `category_id` int DEFAULT NULL,
  PRIMARY KEY (`product_id`),
  KEY `category_id` (`category_id`),
  FULLTEXT KEY `idx_description` (`description`),
  CONSTRAINT `product_ibfk_1` FOREIGN KEY (`category_id`) REFERENCES `category` (`category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=69 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (1,'Apple',12.00,'Fresh apple',93,1),(2,'Banana',4.00,'Fresh banana',49,1),(3,'Orange',7.00,'Fresh orange',84,1),(4,'Mango',10.00,'Fresh mango',66,1),(5,'Pineapple',5.00,'Fresh pineapple',91,1),(6,'Spinach',10.00,'Fresh spinach',86,1),(7,'Carrots',15.00,'Fresh carrots',65,1),(8,'Potatoes',5.00,'Fresh potatoes',78,1),(9,'Cauliflower',8.00,'Fresh cauliflower',91,1),(10,'Cabbage',8.00,'Fresh cabbage',82,1),(11,'Milk',12.00,'Dairy milk',94,1),(12,'Cheese',15.00,'Dairy cheese',84,1),(13,'Yoghurt',20.00,'Dairy yoghurt',93,1),(14,'Butter',25.00,'Dairy butter',100,1),(15,'Eggs',10.00,'Fresh eggs',38,1),(16,'Shirts',300.00,'Casual shirts',50,2),(17,'Trousers',400.00,'Formal trousers',50,2),(18,'Blazers',500.00,'Formal blazers',50,2),(19,'Ties',50.00,'Fashion ties',50,2),(20,'Tshirt',200.00,'Casual t-shirts',50,2),(21,'Jeans',500.00,'Denim jeans',50,2),(22,'Kurta',150.00,'Traditional kurta',50,2),(23,'Sherwani',800.00,'Traditional sherwani',50,2),(24,'Pajama and churidars',1000.00,'Traditional pajamas',50,2),(25,'Tops',500.00,'Fashion tops',50,2),(26,'Kurtis',600.00,'Traditional kurtis',50,2),(27,'Saree',400.00,'Indian sarees',50,2),(28,'Dress',1200.00,'Formal dresses',50,2),(29,'Babywear',700.00,'Babywear',50,2),(30,'girlswear',250.00,'Girls wear',50,2),(31,'Boyswear',550.00,'Boys wear',50,2),(32,'Refrigerator',15000.00,'Home refrigerator',20,3),(33,'Washing machine',10000.00,'Home washing machine',19,3),(34,'Oven',7000.00,'Microwave oven',19,3),(35,'Vacuum cleaner',5000.00,'Home vacuum cleaner',20,3),(36,'Toaster',3000.00,'Electric toaster',19,3),(37,'Mixer grinder',2000.00,'Electric mixer grinder',20,3),(38,'Blender',1000.00,'Electric blender',20,3),(39,'TV',20000.00,'LED TV',19,3),(40,'Smartphone',30000.00,'Latest smartphone',20,3),(41,'Earphone',1000.00,'Wireless earphone',20,3),(42,'Headphones',2000.00,'Noise-cancelling headphones',18,3),(43,'iPad',70000.00,'Apple iPad',18,3),(44,'Bluetooth speaker',8000.00,'Portable speaker',20,3),(45,'Laptop',80000.00,'Latest laptop',20,3),(46,'Smart watch',5000.00,'Wearable smart watch',20,3),(47,'Monitor',7000.00,'Computer monitor',20,3),(48,'Chart paper',10.00,'Chart paper for projects',190,4),(49,'Color paper',50.00,'Colored paper set',200,4),(50,'Blank sheet',1.00,'Blank sheets',200,4),(51,'Map',2.00,'Geographic map',200,4),(52,'Graph paper',3.00,'Graph sheets',175,4),(53,'Ball pen',5.00,'Ballpoint pen',200,4),(54,'Gel pen',10.00,'Gel ink pen',200,4),(55,'Pencil',8.00,'HB pencils',193,4),(56,'Eraser',6.00,'Rubber erasers',190,4),(57,'Sharpener',5.00,'Pencil sharpener',200,4),(58,'EG set',200.00,'Engineering set',200,4),(59,'Watercolour',100.00,'Watercolor paint',200,4),(60,'Crayons',300.00,'Crayon set',199,4),(61,'Charcoal pencil',500.00,'Charcoal pencils',200,4),(62,'Oil paints',400.00,'Oil paint set',200,4),(63,'A4-sized ruled notebook',350.00,'A4 ruled notebook',200,4),(64,'A5 sketchbook',80.00,'A5 sketchbook',200,4),(65,'Long ruled book',120.00,'Long ruled notebook',200,4),(66,'File',30.00,'Document file',200,4),(67,'Kids set',450.00,'Stationery set for kids',200,4),(68,'Geometry box',100.00,'Box with geometric tools',200,4);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `vegetables`
--

DROP TABLE IF EXISTS `vegetables`;
/*!50001 DROP VIEW IF EXISTS `vegetables`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `vegetables` AS SELECT 
 1 AS `product_id`,
 1 AS `name`,
 1 AS `price`,
 1 AS `description`,
 1 AS `stock`,
 1 AS `category_id`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `womens_wear`
--

DROP TABLE IF EXISTS `womens_wear`;
/*!50001 DROP VIEW IF EXISTS `womens_wear`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `womens_wear` AS SELECT 
 1 AS `product_id`,
 1 AS `name`,
 1 AS `price`,
 1 AS `description`,
 1 AS `stock`,
 1 AS `category_id`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `writing_items`
--

DROP TABLE IF EXISTS `writing_items`;
/*!50001 DROP VIEW IF EXISTS `writing_items`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `writing_items` AS SELECT 
 1 AS `product_id`,
 1 AS `name`,
 1 AS `price`,
 1 AS `description`,
 1 AS `stock`,
 1 AS `category_id`*/;
SET character_set_client = @saved_cs_client;

--
-- Dumping routines for database 'macs_mart'
--
/*!50003 DROP PROCEDURE IF EXISTS `add_item_to_cart` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = cp850 */ ;
/*!50003 SET character_set_results = cp850 */ ;
/*!50003 SET collation_connection  = cp850_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `add_item_to_cart`(
    IN p_cart_id INT,
    IN p_product_id INT,
    IN p_quantity INT
)
BEGIN
    DECLARE product_stock INT;
    DECLARE product_price DECIMAL(10, 2);
    DECLARE product_name VARCHAR(255);
    
    SELECT stock, price, name INTO product_stock, product_price, product_name
    FROM product
    WHERE product_id = p_product_id;

    IF product_stock >= p_quantity THEN
        INSERT INTO cart_item (cart_id, product_id, quantity)
        VALUES (p_cart_id, p_product_id, p_quantity);
        
        UPDATE cart
        SET total_amount = total_amount + (product_price * p_quantity)
        WHERE cart_id = p_cart_id;
        
        UPDATE product
        SET stock = stock - p_quantity
        WHERE product_id = p_product_id;

        SELECT 'Item added successfully to the cart.' AS message;
    ELSE
        SELECT CONCAT('Not enough stock for ', product_name) AS message;
    END IF;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `GetProductsByCategory` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = cp850 */ ;
/*!50003 SET character_set_results = cp850 */ ;
/*!50003 SET collation_connection  = cp850_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `GetProductsByCategory`(IN cat_id INT)
BEGIN
    SELECT product_id, name 
    FROM product 
    WHERE category_id = cat_id;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `GetTop10Customers` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = cp850 */ ;
/*!50003 SET character_set_results = cp850 */ ;
/*!50003 SET collation_connection  = cp850_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `GetTop10Customers`()
BEGIN
    SELECT 
        c.customer_id, 
        c.customer_name, 
        SUM(ci.quantity * p.price) AS total_cart_value
    FROM 
        customers c
    JOIN 
        cart_items ci ON c.customer_id = ci.customer_id
    JOIN 
        products p ON ci.product_id = p.product_id
    GROUP BY 
        c.customer_id, c.customer_name
    ORDER BY 
        total_cart_value DESC
    LIMIT 10;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `GetTop10ProductsSold` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = cp850 */ ;
/*!50003 SET character_set_results = cp850 */ ;
/*!50003 SET collation_connection  = cp850_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `GetTop10ProductsSold`()
BEGIN
    SELECT 
        p.product_id, 
        p.product_name, 
        SUM(ci.quantity) AS total_quantity_sold
    FROM 
        product p 
    JOIN 
        cart_items ci ON p.product_id = ci.product_id
    GROUP BY 
        p.product_id, p.product_name
    ORDER BY 
        total_quantity_sold DESC
    LIMIT 10;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `get_cart_details` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = cp850 */ ;
/*!50003 SET character_set_results = cp850 */ ;
/*!50003 SET collation_connection  = cp850_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `get_cart_details`()
BEGIN
    
    DECLARE done INT DEFAULT 0;
    DECLARE v_cart_id INT;
    DECLARE v_customer_id INT;
    DECLARE v_total_amount DECIMAL(10,2);
    DECLARE v_first_name VARCHAR(100);
    DECLARE v_last_name VARCHAR(100);
    
    
    DECLARE cart_cursor CURSOR FOR
        SELECT cart_id, customer_id, total_amount
        FROM cart;
    
    
    DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = 1;
    
    
    OPEN cart_cursor;
    
    
    read_loop: LOOP
        FETCH cart_cursor INTO v_cart_id, v_customer_id, v_total_amount;
        
        IF done THEN
            LEAVE read_loop;
        END IF;
        
        
        SELECT first_name, last_name 
        INTO v_first_name, v_last_name
        FROM customer
        WHERE customer_id = v_customer_id;
        
        
        SELECT CONCAT('Cart ID: ', v_cart_id) AS Cart_Info, 
               CONCAT('Customer Name: ', v_first_name, ' ', v_last_name) AS Customer_Info,
               CONCAT('Total Amount: ', v_total_amount) AS Total_Amount;
        
    END LOOP;
    
    
    CLOSE cart_cursor;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `UpdateCustomerStatus` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = cp850 */ ;
/*!50003 SET character_set_results = cp850 */ ;
/*!50003 SET collation_connection  = cp850_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `UpdateCustomerStatus`()
BEGIN
    DECLARE done INT DEFAULT 0;
    DECLARE customer_id INT;
    DECLARE customer_status VARCHAR(50);
    DECLARE cur CURSOR FOR 
        SELECT customer_id, status FROM customers WHERE status = 'inactive';
    DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = 1;

    OPEN cur;

    read_loop: LOOP
        FETCH cur INTO customer_id, customer_status;
        IF done THEN
            LEAVE read_loop;
        END IF;

        UPDATE customers 
        SET status = 'active' 
        WHERE customer_id = customer_id;
    END LOOP;

    CLOSE cur;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Final view structure for view `customer_cart_view`
--

/*!50001 DROP VIEW IF EXISTS `customer_cart_view`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = cp850 */;
/*!50001 SET character_set_results     = cp850 */;
/*!50001 SET collation_connection      = cp850_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `customer_cart_view` AS select `c`.`customer_id` AS `customer_id`,`c`.`first_name` AS `first_name`,`c`.`last_name` AS `last_name`,`crt`.`cart_id` AS `cart_id`,`crt`.`total_amount` AS `total_amount` from (`customer` `c` left join `cart` `crt` on((`c`.`customer_id` = `crt`.`customer_id`))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `dairy_products`
--

/*!50001 DROP VIEW IF EXISTS `dairy_products`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = cp850 */;
/*!50001 SET character_set_results     = cp850 */;
/*!50001 SET collation_connection      = cp850_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `dairy_products` AS select `product`.`product_id` AS `product_id`,`product`.`name` AS `name`,`product`.`price` AS `price`,`product`.`description` AS `description`,`product`.`stock` AS `stock`,`product`.`category_id` AS `category_id` from `product` where (`product`.`name` in ('Milk','Cheese','Yoghurt','Butter','Eggs')) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `drawing_items`
--

/*!50001 DROP VIEW IF EXISTS `drawing_items`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = cp850 */;
/*!50001 SET character_set_results     = cp850 */;
/*!50001 SET collation_connection      = cp850_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `drawing_items` AS select `product`.`product_id` AS `product_id`,`product`.`name` AS `name`,`product`.`price` AS `price`,`product`.`description` AS `description`,`product`.`stock` AS `stock`,`product`.`category_id` AS `category_id` from `product` where (`product`.`name` in ('EG set','Watercolour','Crayons','Charcoal pencil','Oil paints')) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `electrical_appliances`
--

/*!50001 DROP VIEW IF EXISTS `electrical_appliances`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = cp850 */;
/*!50001 SET character_set_results     = cp850 */;
/*!50001 SET collation_connection      = cp850_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `electrical_appliances` AS select `product`.`product_id` AS `product_id`,`product`.`name` AS `name`,`product`.`price` AS `price`,`product`.`description` AS `description`,`product`.`stock` AS `stock`,`product`.`category_id` AS `category_id` from `product` where (`product`.`name` in ('Refrigerator','Washing machine','Oven','Vacuum cleaner','Toaster','Mixer grinder','Blender','TV')) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `electronic_devices`
--

/*!50001 DROP VIEW IF EXISTS `electronic_devices`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = cp850 */;
/*!50001 SET character_set_results     = cp850 */;
/*!50001 SET collation_connection      = cp850_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `electronic_devices` AS select `product`.`product_id` AS `product_id`,`product`.`name` AS `name`,`product`.`price` AS `price`,`product`.`description` AS `description`,`product`.`stock` AS `stock`,`product`.`category_id` AS `category_id` from `product` where (`product`.`name` in ('Smartphone','Earphone','Headphones','iPad','Bluetooth speaker','Laptop','Smart watch','Monitor')) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `fruits`
--

/*!50001 DROP VIEW IF EXISTS `fruits`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = cp850 */;
/*!50001 SET character_set_results     = cp850 */;
/*!50001 SET collation_connection      = cp850_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `fruits` AS select `product`.`product_id` AS `product_id`,`product`.`name` AS `name`,`product`.`price` AS `price`,`product`.`description` AS `description`,`product`.`stock` AS `stock`,`product`.`category_id` AS `category_id` from `product` where (`product`.`name` in ('Apple','Banana','Orange','Mango','Pineapple')) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `kids_section`
--

/*!50001 DROP VIEW IF EXISTS `kids_section`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = cp850 */;
/*!50001 SET character_set_results     = cp850 */;
/*!50001 SET collation_connection      = cp850_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `kids_section` AS select `product`.`product_id` AS `product_id`,`product`.`name` AS `name`,`product`.`price` AS `price`,`product`.`description` AS `description`,`product`.`stock` AS `stock`,`product`.`category_id` AS `category_id` from `product` where (`product`.`product_id` in (29,30,31)) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `mens_wear`
--

/*!50001 DROP VIEW IF EXISTS `mens_wear`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = cp850 */;
/*!50001 SET character_set_results     = cp850 */;
/*!50001 SET collation_connection      = cp850_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `mens_wear` AS select `product`.`product_id` AS `product_id`,`product`.`name` AS `name`,`product`.`price` AS `price`,`product`.`description` AS `description`,`product`.`stock` AS `stock`,`product`.`category_id` AS `category_id` from `product` where (`product`.`product_id` in (16,17,18,19,20,21,22,23,24)) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `paper_items`
--

/*!50001 DROP VIEW IF EXISTS `paper_items`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = cp850 */;
/*!50001 SET character_set_results     = cp850 */;
/*!50001 SET collation_connection      = cp850_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `paper_items` AS select `product`.`product_id` AS `product_id`,`product`.`name` AS `name`,`product`.`price` AS `price`,`product`.`description` AS `description`,`product`.`stock` AS `stock`,`product`.`category_id` AS `category_id` from `product` where (`product`.`name` in ('Chart paper','Color paper','Blank sheet','Graph paper','Map')) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `vegetables`
--

/*!50001 DROP VIEW IF EXISTS `vegetables`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = cp850 */;
/*!50001 SET character_set_results     = cp850 */;
/*!50001 SET collation_connection      = cp850_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `vegetables` AS select `product`.`product_id` AS `product_id`,`product`.`name` AS `name`,`product`.`price` AS `price`,`product`.`description` AS `description`,`product`.`stock` AS `stock`,`product`.`category_id` AS `category_id` from `product` where (`product`.`name` in ('Spinach','Carrots','Potatoes','Cauliflower','Cabbage')) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `womens_wear`
--

/*!50001 DROP VIEW IF EXISTS `womens_wear`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = cp850 */;
/*!50001 SET character_set_results     = cp850 */;
/*!50001 SET collation_connection      = cp850_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `womens_wear` AS select `product`.`product_id` AS `product_id`,`product`.`name` AS `name`,`product`.`price` AS `price`,`product`.`description` AS `description`,`product`.`stock` AS `stock`,`product`.`category_id` AS `category_id` from `product` where (`product`.`product_id` in (25,26,27,28)) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `writing_items`
--

/*!50001 DROP VIEW IF EXISTS `writing_items`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = cp850 */;
/*!50001 SET character_set_results     = cp850 */;
/*!50001 SET collation_connection      = cp850_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `writing_items` AS select `product`.`product_id` AS `product_id`,`product`.`name` AS `name`,`product`.`price` AS `price`,`product`.`description` AS `description`,`product`.`stock` AS `stock`,`product`.`category_id` AS `category_id` from `product` where (`product`.`name` in ('Ball pen','Gel pen','Pencil','Eraser','Sharpener')) */;
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

-- Dump completed on 2024-11-19 20:16:01
