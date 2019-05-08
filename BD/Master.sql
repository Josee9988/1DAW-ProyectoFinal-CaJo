-- ************************************************************************
-- * CREACION DE TABLAS PROYECTO 1ºDAW JOSE GRACIA Y CARLOS ROBLES 2018-19*
-- ************************************************************************
 
 
 /**                                                                            
  * Descripción: SQL que crea todas las tablas necesarias para el proyecto.
  * Información detallada: Se crean todas las tablas necesarias para el funcionamiento del proyecto de 1ºDAW 2018-2019. (https://github.com/Josee9988/1DAW-ProyectoFinal-CaJo)
  * @author  Jose Gracia
  * @version 1.0 Jose Gracia - Carlos Robles 08/05/2019
  * @since   0.5
  * @return          No tiene parámetro de salida
  */


CREATE DATABASE IF NOT EXISTS proyectodaw;
USE proyectodaw;


DROP TABLE IF EXISTS `incidencias`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `incidencias` (
  `id_incidencia` int(11) NOT NULL AUTO_INCREMENT,
  `usuario` varchar(45) COLLATE utf8mb4_general_ci NOT NULL,
  `descripcion` varchar(85) COLLATE utf8mb4_general_ci NOT NULL,
  `elemento` varchar(45) COLLATE utf8mb4_general_ci NOT NULL,
  `ubicacion` varchar(45) COLLATE utf8mb4_general_ci NOT NULL,
  `fecha` date NOT NULL,
  `urgencia` varchar(30) COLLATE utf8mb4_general_ci NOT NULL,
  `categoria` varchar(45) COLLATE utf8mb4_general_ci NOT NULL,
  `materiales` varchar(45) COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`id_incidencia`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;


DROP TABLE IF EXISTS `mensajes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mensajes` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `asunto` varchar(45) COLLATE utf8mb4_general_ci NOT NULL,
  `cuerpo` varchar(45) COLLATE utf8mb4_general_ci NOT NULL,
  `fecha` date NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


DROP TABLE IF EXISTS `proveedores`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `proveedores` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) COLLATE utf8mb4_general_ci NOT NULL,
  `contacto` varchar(45) COLLATE utf8mb4_general_ci NOT NULL,
  `direccion` varchar(45) COLLATE utf8mb4_general_ci NOT NULL,
  `valoracion` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;



DROP TABLE IF EXISTS `ubicaciones`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ubicaciones` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) COLLATE utf8mb4_general_ci NOT NULL,
  `descripcion` varchar(45) COLLATE utf8mb4_general_ci NOT NULL,
  `edificio` varchar(45) COLLATE utf8mb4_general_ci NOT NULL,
  `planta` varchar(45) COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;



DROP TABLE IF EXISTS `usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuarios` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user` char(9) COLLATE utf8mb4_general_ci NOT NULL,
  `password` char(9) COLLATE utf8mb4_general_ci NOT NULL,
  `rol` int(11) NOT NULL,
  `nombre` varchar(45) COLLATE utf8mb4_general_ci NOT NULL,
  `apellidos` varchar(45) COLLATE utf8mb4_general_ci NOT NULL,
  `telefono` varchar(45) COLLATE utf8mb4_general_ci NOT NULL,
  `direccion` varchar(45) COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios`
--

LOCK TABLES `usuarios` WRITE;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` VALUES (1,'roblito','123456789',1,'Carlos','Robles','537458857','Av Ibi Juan'),(3,'Jefe','1234',4,'Borja','Perez','2464756','Av 18554'),(5,'pepe','1234',2,'Jose','LL','687676','avg'),(6,'luis','1234',3,'Jose','LL','687676','avg'),(8,'carlos','1234',4,'Jose','LL','687676','avg'),(9,'juan','1234',2,'Jose','LL','687676','avg'),(10,'jose','1234',3,'Jose','LL','687676','avg'),(11,'toni','1234',4,'Jose','LL','687676','avg'),(18,'edu','1234',3,'Eduardo','Paya','3475879346','Av Juan Carlos');
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;