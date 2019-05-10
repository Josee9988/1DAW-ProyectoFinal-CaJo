/* *************************************************************************
   * CREACION DE TABLAS PROYECTO 1ºDAW JOSE GRACIA Y CARLOS ROBLES 2018-19 *
   ************************************************************************* */
 
 
 /**                                                                            
  * Descripción: SQL que crea todas las tablas necesarias para el proyecto.
  * Información detallada: Se crean todas las tablas necesarias para el funcionamiento del proyecto de 1ºDAW 2018-2019. (https://github.com/Josee9988/1DAW-ProyectoFinal-CaJo)
  * @author  Jose Gracia
  * @version 1.0 Jose Gracia - Carlos Robles 08/05/2019
  * @since   0.5
  * @return          No tiene parámetro de salida
  */


CREATE DATABASE IF NOT EXISTS m_proyectodaw;

USE m_proyectodaw;


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
  `user` char(15) COLLATE utf8mb4_general_ci NOT NULL,
  `password` char(32) COLLATE utf8mb4_general_ci NOT NULL,
  `rol` int(11) NOT NULL,
  `nombre` varchar(45) COLLATE utf8mb4_general_ci NOT NULL,
  `apellidos` varchar(45) COLLATE utf8mb4_general_ci NOT NULL,
  `telefono` varchar(45) COLLATE utf8mb4_general_ci NOT NULL,
  `direccion` varchar(80) COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;




/*INSERT INTO `usuarios` (user,password,rol,nombre,apellidos,telefono,direccion)
VALUES ("root", "1234", 4, "Administrador", "Admin", 966521942, "CIPFBatoi, Alcoy, Alicante");*/

/*	INSERT INTO FIRST USER ROOT AS AN ADMIN	*/
INSERT INTO `usuarios` (user,password,rol,nombre,apellidos,telefono,direccion)
VALUES ("root", "e01064882b3febf97916c7df6e33b4b2", 4, "Administrador", "Admin", 966521942, "CIPFBatoi, Alcoy, Alicante");
