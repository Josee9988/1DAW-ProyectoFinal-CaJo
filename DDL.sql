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
CREATE TABLE `incidencias` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `usuario` varchar(45) COLLATE utf8mb4_general_ci NOT NULL,
  `descripcion` varchar(85) COLLATE utf8mb4_general_ci NOT NULL,
  `elemento` varchar(45) COLLATE utf8mb4_general_ci NOT NULL,
  `ubicacion` int(11) COLLATE utf8mb4_general_ci NOT NULL,
  `fecha` date NOT NULL,
  `urgencia` varchar(30) COLLATE utf8mb4_general_ci NOT NULL,
  `categoria` varchar(45) COLLATE utf8mb4_general_ci NOT NULL,
  `materiales` varchar(45) COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;



DROP TABLE IF EXISTS `mensajes`;
CREATE TABLE `mensajes` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `asunto` varchar(45) COLLATE utf8mb4_general_ci NOT NULL,
  `cuerpo` varchar(45) COLLATE utf8mb4_general_ci NOT NULL,
  `incidencia` int(11) NOT NULL,
  `fecha` date NOT NULL,
  `id_emisor` int(11) NOT NULL,
  `id_receptor` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


DROP TABLE IF EXISTS `proveedores`;
CREATE TABLE `proveedores` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) COLLATE utf8mb4_general_ci NOT NULL,
  `contacto` varchar(45) COLLATE utf8mb4_general_ci NOT NULL,
  `direccion` varchar(45) COLLATE utf8mb4_general_ci NOT NULL,
  `valoracion` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;



DROP TABLE IF EXISTS `ubicaciones`;
CREATE TABLE `ubicaciones` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) COLLATE utf8mb4_general_ci NOT NULL,
  `descripcion` varchar(45) COLLATE utf8mb4_general_ci NOT NULL,
  `edificio` varchar(45) COLLATE utf8mb4_general_ci NOT NULL,
  `planta` varchar(45) COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;



DROP TABLE IF EXISTS `usuarios`;
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



/*	INSERTAMOS AL MENOS UN USUARIO ROOT CON PASSWORD 123456 */
INSERT INTO `usuarios` (user,password,rol,nombre,apellidos,telefono,direccion)
VALUES ("root", "a40d8205ed80c785d262e31d1b74f7c6", 4, "Administrador", "Admin", 966521942, "CIPFBatoi, Alcoy, Alicante");