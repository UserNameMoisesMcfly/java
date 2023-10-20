-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 21-10-2023 a las 00:50:04
-- Versión del servidor: 10.4.28-MariaDB
-- Versión de PHP: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `db_inventariofree`
--

DELIMITER $$
--
-- Procedimientos
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `NUEVO_PRODUCTO` (`CODIGO` VARCHAR(150))   INSERT INTO inventario (inv_pro_codigo) VALUES (CODIGO)$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `artículos`
--

CREATE TABLE `artículos` (
  `pro_codigo` varchar(50) NOT NULL,
  `pro_descripcion` varchar(150) NOT NULL,
  `nomproveedor` varchar(100) DEFAULT NULL,
  `categoria` int(50) DEFAULT NULL,
  `ubicacion` varchar(150) DEFAULT NULL,
  `estado` varchar(150) DEFAULT NULL,
  `cuerpo` int(11) NOT NULL,
  `reja` int(11) NOT NULL,
  `tapa` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `artículos`
--

INSERT INTO `artículos` (`pro_codigo`, `pro_descripcion`, `nomproveedor`, `categoria`, `ubicacion`, `estado`, `cuerpo`, `reja`, `tapa`) VALUES
('1000', 'testTapa', 'XX Lager', 40, 'mojado', 'nuevo', 500, 500, 500),
('12345678', 'Cerveza', 'Sol', 20, 'mojado', 'Nuevo', 4000, 4000, 0),
('432', 'test', 'Sol', 20, 'nuevo', 'golpeado', 30000, 30000, 0),
('654', 'Cerveza', 'Sol', 40, 'golpeado', 'nuevo', 8000, 8000, 0),
('666', 'cerveza', 'XX Lager', 40, 'intacto', 'regreso', 5000, 5000, 0),
('777', 'cerveza', 'XX Lager', 40, 'mojado', 'Nuevo', 4000, 4000, 0),
('88888', 'test', 'XX Lager', 30, 'golpeado', 'nuevo', 20000, 20000, 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `categorias`
--

CREATE TABLE `categorias` (
  `id` int(50) NOT NULL,
  `valor` int(50) DEFAULT NULL,
  `unidad` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `categorias`
--

INSERT INTO `categorias` (`id`, `valor`, `unidad`) VALUES
(12, 40, 'onzas'),
(16, 20, 'cuartos');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `entrada`
--

CREATE TABLE `entrada` (
  `ent_id` int(50) NOT NULL,
  `ent_categoria` varchar(100) DEFAULT NULL,
  `ent_pro_codigo` varchar(100) DEFAULT NULL,
  `ent_fecha` date NOT NULL,
  `ent_cantidad` int(11) NOT NULL,
  `cuerpo_merma` int(11) NOT NULL,
  `reja_merma` int(11) NOT NULL,
  `tapa_merma` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `entrada`
--

INSERT INTO `entrada` (`ent_id`, `ent_categoria`, `ent_pro_codigo`, `ent_fecha`, `ent_cantidad`, `cuerpo_merma`, `reja_merma`, `tapa_merma`) VALUES
(63, '', '12345678', '2023-10-16', 2, 1, 1, 0),
(64, '', '654', '2023-10-16', 1, 200, 300, 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `inventario`
--

CREATE TABLE `inventario` (
  `inv_pro_codigo` varchar(50) NOT NULL,
  `inv_entradas` int(30) DEFAULT 0,
  `inv_salidas` int(30) DEFAULT 0,
  `inv_stock` int(30) DEFAULT 0,
  `inv_reja` int(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `inventario`
--

INSERT INTO `inventario` (`inv_pro_codigo`, `inv_entradas`, `inv_salidas`, `inv_stock`, `inv_reja`) VALUES
('1000', 0, 0, 0, 0),
('12345678', 2, 0, 2, 0),
('432', 0, 0, 0, 0),
('654', 1, 0, 1, 0),
('666', 0, 0, 0, 0),
('777', 0, 0, 0, 0),
('88888', 0, 0, 0, 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `proveedor`
--

CREATE TABLE `proveedor` (
  `id` int(30) NOT NULL,
  `fecha_altaa` timestamp NULL DEFAULT current_timestamp(),
  `nombre_pro` varchar(100) DEFAULT NULL,
  `correo` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `proveedor`
--

INSERT INTO `proveedor` (`id`, `fecha_altaa`, `nombre_pro`, `correo`) VALUES
(13, '2023-09-29 06:00:00', 'XX Lager', 'xxl@example.com'),
(15, '2023-09-29 06:00:00', 'Sol', 'sol@example.com');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `salida`
--

CREATE TABLE `salida` (
  `sal_id` int(20) NOT NULL,
  `sal_factura` varchar(100) DEFAULT NULL,
  `sal_pro_codigo` varchar(100) DEFAULT NULL,
  `sal_fecha` date NOT NULL,
  `sal_cantidad` int(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tareas`
--

CREATE TABLE `tareas` (
  `idTarea` int(30) NOT NULL,
  `numeroSerie` varchar(50) DEFAULT NULL,
  `nombre` varchar(100) DEFAULT NULL,
  `herramienta` varchar(100) DEFAULT NULL,
  `estadoTarea` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

CREATE TABLE `usuarios` (
  `id` int(30) NOT NULL,
  `fecha_alta` timestamp NULL DEFAULT current_timestamp(),
  `usuario` varchar(100) DEFAULT NULL,
  `clave` varchar(64) DEFAULT NULL,
  `permiso` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`id`, `fecha_alta`, `usuario`, `clave`, `permiso`) VALUES
(13, '2023-10-16 23:40:37', 'root', 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3', 'Administrador');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `artículos`
--
ALTER TABLE `artículos`
  ADD PRIMARY KEY (`pro_codigo`);

--
-- Indices de la tabla `categorias`
--
ALTER TABLE `categorias`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `entrada`
--
ALTER TABLE `entrada`
  ADD PRIMARY KEY (`ent_id`),
  ADD KEY `ent_pro_codigo` (`ent_pro_codigo`);

--
-- Indices de la tabla `inventario`
--
ALTER TABLE `inventario`
  ADD PRIMARY KEY (`inv_pro_codigo`);

--
-- Indices de la tabla `proveedor`
--
ALTER TABLE `proveedor`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `salida`
--
ALTER TABLE `salida`
  ADD PRIMARY KEY (`sal_id`),
  ADD KEY `sal_pro_codigo` (`sal_pro_codigo`);

--
-- Indices de la tabla `tareas`
--
ALTER TABLE `tareas`
  ADD PRIMARY KEY (`idTarea`);

--
-- Indices de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `categorias`
--
ALTER TABLE `categorias`
  MODIFY `id` int(50) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT de la tabla `entrada`
--
ALTER TABLE `entrada`
  MODIFY `ent_id` int(50) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=65;

--
-- AUTO_INCREMENT de la tabla `proveedor`
--
ALTER TABLE `proveedor`
  MODIFY `id` int(30) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT de la tabla `salida`
--
ALTER TABLE `salida`
  MODIFY `sal_id` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=37;

--
-- AUTO_INCREMENT de la tabla `tareas`
--
ALTER TABLE `tareas`
  MODIFY `idTarea` int(30) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  MODIFY `id` int(30) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=37;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `entrada`
--
ALTER TABLE `entrada`
  ADD CONSTRAINT `entrada_ibfk_1` FOREIGN KEY (`ent_pro_codigo`) REFERENCES `artículos` (`pro_codigo`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `inventario`
--
ALTER TABLE `inventario`
  ADD CONSTRAINT `inventario_ibfk_1` FOREIGN KEY (`inv_pro_codigo`) REFERENCES `artículos` (`pro_codigo`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `salida`
--
ALTER TABLE `salida`
  ADD CONSTRAINT `salida_ibfk_1` FOREIGN KEY (`sal_pro_codigo`) REFERENCES `artículos` (`pro_codigo`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
