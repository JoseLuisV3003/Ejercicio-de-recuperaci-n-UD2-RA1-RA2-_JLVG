-- CREACIÓN DE LA BASE DATOS, CREACIÓN DE TABLAS E INSERCIÓN DE DATOS EXPORTADA DE HeidiSQL
-- Por si las moscas :D

-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Versión del servidor:         12.1.2-MariaDB - MariaDB Server
-- SO del servidor:              Win64
-- HeidiSQL Versión:             12.11.0.7065
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Volcando estructura de base de datos para cine
CREATE DATABASE IF NOT EXISTS `cine` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_uca1400_ai_ci */;
USE `cine`;

-- Volcando estructura para tabla cine.peliculas
CREATE TABLE IF NOT EXISTS `peliculas` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `director` varchar(128) DEFAULT NULL,
  `titulo` varchar(128) DEFAULT NULL,
  `fecha` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;

-- Volcando datos para la tabla cine.peliculas: ~5 rows (aproximadamente)
INSERT INTO `peliculas` (`id`, `director`, `titulo`, `fecha`) VALUES
	(1, 'Lana Wachowski', 'The Matrix', '1999-06-23 09:32:03'),
	(2, 'Jim Henson', 'El Laberinto', '2014-09-19 00:00:00'),
	(3, 'Terry Gilliam', '12 Monos', '1998-01-05 00:00:00'),
	(4, 'Kevin', 'Scream 7', '2026-03-24 10:21:00'),
	(5, 'Jose', 'Dead By Daylight:es personal', '2003-03-30 00:00:00');

-- Volcando estructura para tabla cine.usuarios
CREATE TABLE IF NOT EXISTS `usuarios` (
  `password` varchar(20) NOT NULL,
  `usuario` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;

-- Volcando datos para la tabla cine.usuarios: ~2 rows (aproximadamente)
INSERT INTO `usuarios` (`password`, `usuario`) VALUES
	('contraseña', 'yo'),
	('aaaaaa', 'jvalgal'),
	('999999', 'eddie');

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
