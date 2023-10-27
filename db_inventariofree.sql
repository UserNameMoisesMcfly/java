-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 27-10-2023 a las 02:33:38
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
  `categoria` int(11) DEFAULT NULL,
  `ubicacion` varchar(150) DEFAULT NULL,
  `cuerpo` int(11) NOT NULL,
  `reja` int(11) NOT NULL,
  `tapa` int(11) NOT NULL,
  `tarimas` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `artículos`
--

INSERT INTO `artículos` (`pro_codigo`, `pro_descripcion`, `nomproveedor`, `categoria`, `ubicacion`, `cuerpo`, `reja`, `tapa`, `tarimas`) VALUES
('000', 'test', 'XX Lager', 20, 'test', 621, 621, 621, 0),
('0000', 'test', 'XX Lager', 20, 'test', 621, 621, 621, 0),
('001', 'test', 'XX Lager', 20, 'test', 98, 98, 98, NULL),
('002', 'test', 'XX Lager', 40, 'test', 98, 98, 98, NULL),
('003', 'test', 'XX Lager', 40, 'test', 19, 19, 19, NULL),
('004', 'test', 'XX Lager', 40, 'test', 95, 92, 94, NULL),
('005', 'test', 'XX Lager', 40, 'test', 97, 97, 97, NULL),
('006', 'test', 'XX Lager', 40, 'test', 98, 96, 98, NULL),
('007', 'test', 'XX Lager', 40, 'test', 92, 93, 94, NULL),
('008', 'test', 'XX Lager', 40, 'test', 170, 185, 180, NULL),
('009', 'test', 'XX Lager', 40, 'test', 93, 97, 96, NULL),
('010', 'test', 'XX Lager', 40, 'test', 621, 621, 621, 0),
('011', 'test', 'XX Lager', 20, 'test', 621, 621, 621, 0),
('012', 'test', 'XX Lager', 40, 'test', 621, 621, 621, 0),
('12345', 'hi', 'Sol', 20, 'hi', 1800, 1800, 1800, NULL),
('12345678', 'Cerveza', 'Sol', 20, 'mojado', 4000, 4000, 0, NULL),
('333333', 'test', 'XX Lager', 40, 'test', 19700, 19700, 19700, NULL),
('432', 'test', 'Sol', 20, 'nuevo', 30000, 30000, 0, NULL),
('5162546354', 'hola', 'XX Lager', 40, 'adios', 57650, 57650, 57700, NULL),
('654', 'Cerveza', 'Sol', 40, 'golpeado', 8000, 8000, 0, NULL),
('666', 'cerveza', 'XX Lager', 40, 'intacto', 5000, 5000, 0, NULL),
('777', 'cerveza', 'XX Lager', 40, 'mojado', 4000, 4000, 0, NULL),
('77777777', 'test', 'XX Lager', 40, 'test', 90, 90, -100, NULL),
('999999', 'test', 'XX Lager', 40, 'test', 1990, 1990, 1990, NULL);

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
  `res_cuerpo` int(11) NOT NULL,
  `res_reja` int(11) NOT NULL,
  `res_tapa` int(11) NOT NULL,
  `cuerpo_merma` int(11) DEFAULT NULL,
  `reja_merma` int(11) DEFAULT NULL,
  `tapa_merma` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `entrada`
--

INSERT INTO `entrada` (`ent_id`, `ent_categoria`, `ent_pro_codigo`, `ent_fecha`, `ent_cantidad`, `res_cuerpo`, `res_reja`, `res_tapa`, `cuerpo_merma`, `reja_merma`, `tapa_merma`) VALUES
(63, '', '12345678', '2023-10-16', 2, 0, 0, 0, 1, 1, 0),
(64, '', '654', '2023-10-16', 1, 0, 0, 0, 200, 300, 0),
(65, '', '12345', '2023-10-20', 0, 0, 0, 0, 200, 200, 200),
(66, '', '5162546354', '2023-10-20', 57650, 0, 0, 0, 500, 500, 500),
(67, '02072023102320', '12345', '2023-10-23', 0, 0, 0, 0, 200, 200, 200),
(68, '68902023102340', '5162546354', '2023-10-23', 57650, 0, 0, 0, 2000, 2000, 2000),
(69, '0902023102540', '5162546354', '2023-10-25', 57650, 0, 0, 0, 200, 200, 200),
(70, '0902023102540', '333333', '2023-10-25', 19700, 0, 0, 0, 300, 300, 300),
(71, '0902023102540', '77777777', '2023-10-25', -100, 0, 0, 0, 10, 10, 200),
(72, '0902023102540', '999999', '2023-10-25', 1990, 50, 1000, 10, 10, 10, 10),
(73, '02072023102620', '001', '2023-10-26', 0, 2, 5, 2, 2, 2, 2),
(74, '0902023102640', '002', '2023-10-26', 0, 2, 5, 2, 2, 2, 2),
(75, '0902023102640', '003', '2023-10-26', 0, 5, 3, 1, 1, 1, 1),
(76, '0902023102640', '005', '2023-10-26', 97, 3, 3, 3, 3, 3, 3),
(77, '0902023102640', '004', '2023-10-26', 92, 2, 5, 3, 3, 3, 3),
(78, '0902023102640', '006', '2023-10-26', 0, 1, 3, 1, 1, 1, 1),
(79, '0902023102640', '007', '2023-10-26', 3, 5, 4, 3, 3, 3, 3),
(80, '0902023102640', '008', '2023-10-26', 170, 20, 5, 10, 10, 10, 10),
(81, '1902023102640', '009', '2023-10-26', 93, 5, 1, 2, 2, 2, 2),
(82, '2902023102640', '5162546354', '2023-10-26', 57650, 100, 100, 50, 50, 50, 50);

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
('000', 0, 0, 0, 0),
('0000', 0, 0, 0, 0),
('001', 0, 0, 0, 0),
('002', 0, 0, 0, 0),
('003', 0, 0, 0, 0),
('004', 0, 0, 0, 0),
('005', 0, 0, 0, 0),
('006', 0, 0, 0, 0),
('007', 0, 0, 0, 0),
('008', 0, 0, 0, 0),
('009', 0, 0, 0, 0),
('010', 0, 0, 0, 0),
('011', 0, 0, 0, 0),
('012', 0, 0, 0, 0),
('12345', 0, 0, 0, 0),
('12345678', 2, 0, 2, 0),
('333333', 0, 0, 0, 0),
('432', 0, 0, 0, 0),
('5162546354', 173400, 0, 173400, 0),
('654', 1, 0, 1, 0),
('666', 0, 0, 0, 0),
('777', 0, 0, 0, 0),
('77777777', 0, 0, 0, 0),
('999999', 0, 0, 0, 0);

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
  MODIFY `ent_id` int(50) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=83;

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
