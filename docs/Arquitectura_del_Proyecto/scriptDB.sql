-- MariaDB dump 10.17  Distrib 10.5.5-MariaDB, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: ProyectoFinal
-- ------------------------------------------------------
-- Server version	10.5.5-MariaDB-1:10.5.5+maria~focal

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `material`
--

DROP TABLE IF EXISTS `material`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `material` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `cantidad` int(11) NOT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `tipo_material_id` bigint(20) DEFAULT NULL,
  `imagen` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKtreg09jcsnyolu4phkbammear` (`tipo_material_id`),
  CONSTRAINT `FKtreg09jcsnyolu4phkbammear` FOREIGN KEY (`tipo_material_id`) REFERENCES `tipo_material` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `material`
--

LOCK TABLES `material` WRITE;
/*!40000 ALTER TABLE `material` DISABLE KEYS */;
INSERT INTO `material` VALUES (10,3274,'Botella de 70 CL para El Milagrito.','Botella 70cl El Milagrito',24,'Bote Milagrito.jpg'),(14,0,'Tapon para bote de 1 L Rosa','Tapon 1L Verde',17,'Tapon 1L.jpg');
/*!40000 ALTER TABLE `material` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `material_producto`
--

DROP TABLE IF EXISTS `material_producto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `material_producto` (
  `id_material` bigint(20) NOT NULL,
  `id_producto` bigint(20) NOT NULL,
  KEY `FK22ide0oq2udn5todth7d94edr` (`id_producto`),
  KEY `FKbjnfbhvlajspxwaxwlg49sg3f` (`id_material`),
  CONSTRAINT `FK22ide0oq2udn5todth7d94edr` FOREIGN KEY (`id_producto`) REFERENCES `producto` (`id`),
  CONSTRAINT `FKbjnfbhvlajspxwaxwlg49sg3f` FOREIGN KEY (`id_material`) REFERENCES `material` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `material_producto`
--

LOCK TABLES `material_producto` WRITE;
/*!40000 ALTER TABLE `material_producto` DISABLE KEYS */;
INSERT INTO `material_producto` VALUES (10,36),(10,35);
/*!40000 ALTER TABLE `material_producto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `producto`
--

DROP TABLE IF EXISTS `producto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `producto` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `cantidad_botes` int(11) NOT NULL,
  `cantidad_palets` int(11) NOT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `tipo_palet_id` bigint(20) DEFAULT NULL,
  `tipo_producto_id` bigint(20) DEFAULT NULL,
  `usuario_id` bigint(20) DEFAULT NULL,
  `imagen` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKm9v7q9yti0gx0knukos5s5s3g` (`tipo_palet_id`),
  KEY `FK2em9dpm8qsg21wqf2v1bmshdw` (`tipo_producto_id`),
  KEY `FK4f8g2yvj0uj7hqxlauy8p8k39` (`usuario_id`),
  CONSTRAINT `FK2em9dpm8qsg21wqf2v1bmshdw` FOREIGN KEY (`tipo_producto_id`) REFERENCES `tipo_producto` (`id`),
  CONSTRAINT `FK4f8g2yvj0uj7hqxlauy8p8k39` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`),
  CONSTRAINT `FKm9v7q9yti0gx0knukos5s5s3g` FOREIGN KEY (`tipo_palet_id`) REFERENCES `tipo_palet` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `producto`
--

LOCK TABLES `producto` WRITE;
/*!40000 ALTER TABLE `producto` DISABLE KEYS */;
INSERT INTO `producto` VALUES (35,0,0,'El Milagrito 1L',5,11,1,'Bote Milagrito 1L.jpg'),(36,0,0,'El Milagrito Spray',5,11,1,'Bote Milagrito.jpg');
/*!40000 ALTER TABLE `producto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rol`
--

DROP TABLE IF EXISTS `rol`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rol` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rol`
--

LOCK TABLES `rol` WRITE;
/*!40000 ALTER TABLE `rol` DISABLE KEYS */;
INSERT INTO `rol` VALUES (1,'Rol de usuario','USER'),(4,'Usuario administrador','ADMIN');
/*!40000 ALTER TABLE `rol` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipo_material`
--

DROP TABLE IF EXISTS `tipo_material`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tipo_material` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipo_material`
--

LOCK TABLES `tipo_material` WRITE;
/*!40000 ALTER TABLE `tipo_material` DISABLE KEYS */;
INSERT INTO `tipo_material` VALUES (17,'Tapones','Tapon'),(18,'Pistola Spray.','Pistola Spray'),(19,'Botes.','Bote'),(20,'Garrafas.','Garrafa'),(21,'Tapaderas.','Tapadera'),(22,'Etiquetas.','Etiqueta'),(23,'Cajas.','Caja'),(24,'Botellas.','Botella');
/*!40000 ALTER TABLE `tipo_material` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipo_palet`
--

DROP TABLE IF EXISTS `tipo_palet`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tipo_palet` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(255) DEFAULT NULL,
  `medidas` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipo_palet`
--

LOCK TABLES `tipo_palet` WRITE;
/*!40000 ALTER TABLE `tipo_palet` DISABLE KEYS */;
INSERT INTO `tipo_palet` VALUES (5,'Este es el tipo de palet normal, que se usa para la mayoría de clientes.','220x180 cm','Palet Normal'),(6,'Este es el tipo de palet pequeño, que se usa para Aldi.','100x70 cm','Palet pequeño');
/*!40000 ALTER TABLE `tipo_palet` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipo_producto`
--

DROP TABLE IF EXISTS `tipo_producto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tipo_producto` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipo_producto`
--

LOCK TABLES `tipo_producto` WRITE;
/*!40000 ALTER TABLE `tipo_producto` DISABLE KEYS */;
INSERT INTO `tipo_producto` VALUES (11,'Este es el tipo para los productos de quitar grasa.','Desengrasante'),(12,'Este es el tipo para los productos limpia suelos.','Limpia Suelos'),(13,'Este es el tipo para los productos de oxigeno activo para la ropa.','Oxigeno Activo Ropa'),(14,'Este es el tipo para los productos de limpia hornos.','Limpia Hornos'),(15,'Este es el tipo para los productos de limpia cachimbas.','Limpia Cachimbas');
/*!40000 ALTER TABLE `tipo_producto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users_roles`
--

DROP TABLE IF EXISTS `users_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users_roles` (
  `id_usuario` bigint(20) NOT NULL,
  `id_rol` bigint(20) NOT NULL,
  KEY `FK7al60apj7uavohu6gre48l8dg` (`id_rol`),
  KEY `FKpecv1garil02ijo6tcgyuvt4b` (`id_usuario`),
  CONSTRAINT `FK7al60apj7uavohu6gre48l8dg` FOREIGN KEY (`id_rol`) REFERENCES `rol` (`id`),
  CONSTRAINT `FKpecv1garil02ijo6tcgyuvt4b` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users_roles`
--

LOCK TABLES `users_roles` WRITE;
/*!40000 ALTER TABLE `users_roles` DISABLE KEYS */;
INSERT INTO `users_roles` VALUES (1,1),(8,1),(9,4),(15,1);
/*!40000 ALTER TABLE `users_roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuario` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `apellidos` varchar(255) DEFAULT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK863n1y3x0jalatoir4325ehal` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (1,'prueba1','prueba1','prueba1@gmail.com','prueba','$2a$10$kp1w0iw02SbiWnnVgTY9k.oGOpy6dSy5R0w1lthLLCtVwkiR9cqnO','prueba1'),(8,'ooo','samuel','samuel@gmail.com','Samuel','$2a$10$6uRU254UhgsipYj7HkSyduLXXkFJtN7vNPe5pJb2wHbmiBiNqobg2','samuel'),(9,'cordero','jorge','jorge@gmail.com','jorge','$2a$10$SvHkItZyRopn2SsTJ9CiZeE/AOHlouvmX.hqDmy6bR7XgkK3ip8DS','jorge'),(15,'pepe','prueba roles','pepe@gmail.com','Pepe','$2a$10$KHm2GnrfmhJcLA.998l1XuZQQE/SJJ6btkQSNxjuaPDHuT8tEpngq','pepe');
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-06-18 14:36:43
