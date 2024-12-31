-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 31-12-2024 a las 07:18:53
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `matriculadb`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `aula`
--


CREATE DATABASE matriculadb;
USE matriculadb;

CREATE TABLE `aula` (
  `ID_AULA` int(11) NOT NULL,
  `TIPO_AULA` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `aula`
--

INSERT INTO `aula` (`ID_AULA`, `TIPO_AULA`) VALUES
(1, 'SALÓN'),
(2, 'LABORATORIO');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `curso`
--

CREATE TABLE `curso` (
  `ID_CURSO` int(11) NOT NULL,
  `NOMBRE` varchar(100) DEFAULT NULL,
  `CREDITOS` int(11) DEFAULT NULL,
  `TOTAL_HORAS` int(11) DEFAULT NULL,
  `ID_AULA` int(11) DEFAULT NULL,
  `CICLO_DISPONIBLE` int(11) DEFAULT NULL,
  `anio` int(11) NOT NULL CHECK (`anio` between 1 and 5),
  `ID_ESCUELA` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `curso`
--

INSERT INTO `curso` (`ID_CURSO`, `NOMBRE`, `CREDITOS`, `TOTAL_HORAS`, `ID_AULA`, `CICLO_DISPONIBLE`, `anio`, `ID_ESCUELA`) VALUES
(1, 'INGLES I', 1, 1, 1, 1, 1, 1),
(2, 'LENGUAJE Y COMUNICACIÓN', 3, 1, 1, 1, 1, 1),
(3, 'FILOSOFÍA Y ÉTICA', 3, 1, 1, 1, 1, 1),
(4, 'FUNDAMENTOS DE CÁLCULO', 3, 1, 1, 1, 1, 1),
(5, 'METODOLOGÍA DEL TRABAJO UNIVERSITARIO', 2, 1, 1, 1, 1, 1),
(6, 'ACTIVIDADES CULTURALES Y DEPORTIVAS', 1, 1, 1, 1, 1, 1),
(7, 'MATEMATICA', 5, 1, 1, 1, 1, 1),
(8, 'INTRODUCCIÓN A LA INGENIERIA DE SISTEMAS', 4, 1, 1, 1, 1, 1),
(9, 'INGLES II', 1, 1, 1, 2, 1, 1),
(10, 'LIDERAZGO Y DESARROLLO PERSONAL', 3, 1, 1, 2, 1, 1),
(11, 'MEDIO AMBIENTE Y DESARROLLO SOSTENIBLE', 3, 1, 1, 2, 1, 1),
(12, 'TECNOLOGIAS DE LA INFORMACION Y COMUNICACIÓN', 2, 1, 1, 2, 1, 1),
(13, 'SOCIOLOGIA', 2, 1, 1, 2, 1, 1),
(14, 'TEORIA DE SISTEMAS', 3, 1, 1, 2, 1, 1),
(15, 'CALCULO DIFERENCIAL E INTEGRAL', 3, 1, 1, 2, 1, 1),
(16, 'FUNDAMENTOS DE PROGRAMACION I', 3, 1, 2, 2, 1, 1),
(17, 'INGLÉS III', 1, 1, 1, 3, 2, 1),
(18, 'PSICOLOGIA ORGANIZACIONAL', 2, 1, 1, 3, 2, 1),
(19, 'ESTADISTICA', 3, 1, 1, 3, 2, 1),
(20, 'GEOPOLITICA Y REALIDAD NACIONAL', 3, 1, 1, 3, 2, 1),
(21, 'METODOLOGIA DE LA INVESTIGACION CIENTIFICA', 3, 1, 1, 3, 2, 1),
(22, 'FISICA', 3, 1, 2, 3, 2, 2),
(23, 'ECUACIONES DIFERENCIALES', 4, 1, 1, 3, 2, 1),
(24, 'FUNDAMENTOS DE PROGRAMACION II', 3, 1, 2, 3, 2, 1),
(25, 'PROGRAMACION APLICADA I', 4, 1, 2, 4, 2, 1),
(26, 'GESTION CONTABLE', 3, 1, 1, 4, 2, 1),
(27, 'ESTADISTICA II', 3, 1, 1, 4, 2, 1),
(28, 'INVESTIGACION OPERATIVA', 4, 1, 1, 4, 2, 1),
(29, 'ELECTROMAGNETISMO Y ELECTRONICA BASICA', 4, 1, 1, 4, 2, 1),
(30, 'MATEMATICAS DISCRETAS', 4, 1, 1, 4, 2, 1),
(31, 'PROGRAMACION APLICADA II', 3, 1, 2, 5, 3, 1),
(32, 'INGENIERIA DE COSTOS Y PRESUPUESTOS', 3, 1, 1, 5, 4, 1),
(33, 'FUNDAMENTO DE BASE DE DATOS', 3, 1, 2, 5, 3, 1),
(34, 'INGENIERIA DE PROCESOS DE NEGOCIOS', 3, 1, 2, 5, 3, 1),
(35, 'SISTEMAS DIGITALES Y ARQUITECTURA DE COMPUTADORAS', 3, 1, 2, 5, 3, 1),
(36, 'SISTEMAS OPERATIVOS', 3, 1, 1, 5, 3, 1),
(37, 'DINAMICA DE SISTEMAS', 2, 1, 2, 5, 3, 1),
(38, 'INGLES I', 1, 1, 1, 1, 1, 2),
(39, 'LENGUAJE Y COMUNICACION', 3, 1, 1, 1, 1, 2),
(40, 'FILOSOFIA Y ETICA', 3, 1, 1, 1, 1, 2),
(41, 'FUNDAMENTOS DE CALCULO', 3, 1, 1, 1, 1, 2),
(42, 'METODOLOGIA DEL TRABAJO UNIVERSITARIO', 2, 1, 1, 1, 1, 2),
(43, 'ACTIVIDADES CULTURALES Y DEPORTIVAS', 1, 1, 1, 1, 1, 2),
(44, 'CONTABILIDAD EMPRESARIAL', 3, 1, 1, 1, 1, 2),
(45, 'QUIMICA GENERAL', 2, 1, 2, 1, 1, 2),
(46, 'DISEÑO TECNICO', 2, 1, 1, 1, 1, 2),
(47, 'INGLES II', 1, 1, 1, 2, 1, 2),
(48, 'LIDERAZGO Y DESARROLLO PERSONAL', 3, 1, 1, 2, 1, 2),
(49, 'MEDIO AMBIENTE Y DESARROLLO SOSTENIBLE', 3, 1, 1, 2, 1, 2),
(50, 'TECNOLOGIAS DE INFORMACION', 2, 1, 1, 2, 1, 2),
(51, 'SOCIOLOGIA', 2, 1, 1, 2, 1, 2),
(52, 'MATEMATICA BASICA', 3, 1, 1, 2, 1, 2),
(53, 'QUIMICA INDUSTRIAL', 3, 1, 2, 2, 1, 2),
(54, 'GEOMETRIA DESCRIPTIVA', 3, 1, 1, 2, 1, 2),
(55, 'INGLES III', 1, 1, 1, 3, 2, 2),
(56, 'PSICOLOGIA ORGANIZACIONAL', 2, 1, 1, 3, 2, 2),
(57, 'ESTADISTICA', 3, 1, 1, 3, 2, 2),
(58, 'GEOPOLITICA Y REALIDAD NACIONAL', 3, 1, 1, 3, 2, 2),
(59, 'METODOLOGIA DE LA INVESTIGACIÓN CIENTIFICA', 3, 1, 1, 3, 2, 2),
(60, 'FISICA GENERAL', 2, 1, 2, 3, 2, 2),
(61, 'CALCULO I', 3, 1, 1, 3, 2, 2),
(62, 'LECTURA DE PLANOS', 3, 1, 1, 3, 2, 2),
(63, 'INGENIERIA DE COSTOS I', 3, 1, 1, 4, 2, 2),
(64, 'ADMINISTRACION DE EMPRESAS', 3, 1, 1, 4, 2, 2),
(65, 'ESTADISTICA INDUSTRIAL', 3, 1, 1, 4, 2, 2),
(66, 'CALCULO II', 3, 1, 1, 4, 2, 2),
(67, 'ECONOMIA EMPRESARIAL I', 3, 1, 1, 4, 2, 2),
(68, 'INVESTIGACION DE OPERACIONES I', 3, 1, 1, 4, 2, 2),
(69, 'ELECTROTECNIA', 2, 1, 2, 4, 2, 2),
(70, 'INGENIERÍA DE COSTOS II', 3, 1, 1, 5, 3, 2),
(71, 'INGENIERÍA DE METODOS', 3, 1, 1, 5, 3, 2),
(72, 'ECONOMÍA EMPRESARIAL II', 2, 1, 1, 5, 3, 2),
(73, 'INVESTIGACIÓN DE OPERACIONES II', 3, 1, 1, 5, 3, 2),
(74, 'CONTROL DE CALIDAD', 3, 1, 1, 5, 3, 2),
(75, 'OPERACIONES DE PROCESOS UNITARIOS', 2, 1, 1, 5, 3, 2),
(76, 'LENGUAJE DE PROGRAMACIÓN', 2, 1, 2, 5, 3, 2),
(77, 'INGLES I', 1, 1, 1, 1, 1, 3),
(78, 'LENGUAJE Y COMUNICACIÓN', 3, 1, 1, 1, 1, 3),
(79, 'FILOSOFIA Y ETICA', 3, 1, 1, 1, 1, 3),
(80, 'FUNDAMENTO DE CALCULO', 3, 1, 1, 1, 1, 3),
(81, 'METODOLOGIA DEL TRABAJO UNIVERSITARIO', 2, 1, 1, 1, 1, 3),
(82, 'ACTIVIDADES CULTURALES Y DEPORTIVAS', 1, 1, 1, 1, 1, 3),
(83, 'INTRODUCCION A LA INGENIERIA DE TRASNPORTES', 5, 1, 1, 1, 1, 3),
(84, 'QUIMICA', 4, 1, 1, 1, 1, 3),
(85, 'INGLES II', 1, 1, 1, 2, 1, 3),
(86, 'LIDERAZGO Y DESARROLLO PERSONAL', 3, 1, 1, 2, 1, 3),
(87, 'MEDIO AMBIENTE Y DESARROLLO SOSTENIBLE', 3, 1, 1, 2, 1, 3),
(88, 'TECNOLOGIAS DE LA INFORMACION.Y COMUNICACIÓN', 2, 1, 1, 2, 1, 3),
(89, 'SOCIOLOGIA', 2, 1, 1, 2, 1, 3),
(90, 'ALGEBRA LINEAL', 3, 1, 1, 2, 1, 3),
(91, 'FISICA APLICADA I', 4, 1, 1, 2, 1, 3),
(92, 'DIBUJO DE INGENIERIA I', 3, 1, 1, 2, 1, 3),
(93, 'INGLES III', 1, 1, 1, 3, 2, 3),
(94, 'PSICOLOGIA ORGANIZACIONAL', 2, 1, 1, 3, 2, 3),
(95, 'ESTADISTICA', 3, 1, 1, 3, 2, 3),
(96, 'GEOPOLÍTICA Y REALIDAD NACIONAL', 3, 1, 1, 3, 2, 3),
(97, 'METODOLOGÍA DE LA INVESTIGACIÓN CIENTÍFICA', 3, 1, 1, 3, 2, 3),
(98, 'ANÁLISIS MATEMÁTICO I', 4, 1, 1, 3, 2, 3),
(99, 'FÍSICA APLICADA II', 3, 1, 1, 3, 2, 3),
(100, 'DIBUJO DE INGENIERÍA II', 3, 1, 1, 3, 2, 3),
(101, 'ECONOMÍA I', 2, 1, 1, 4, 2, 3),
(102, 'CONTABILIDAD', 2, 1, 1, 4, 2, 3),
(103, 'ESTADÍSTICA INFERENCIAL', 3, 1, 1, 4, 2, 3),
(104, 'INGENIERÍA DE MÉTODOS', 3, 1, 1, 4, 2, 3),
(105, 'ELECTROTECNIA', 3, 1, 1, 4, 2, 3),
(106, 'ANÁLISIS MATEMÁTICO II', 3, 1, 1, 4, 2, 3),
(107, 'MECÁNICA Y RESISTENCIA DE MATERIALES', 3, 1, 1, 4, 2, 3),
(108, 'TOPOGRAFÍA Y GEODESIA', 3, 1, 1, 4, 2, 3),
(109, 'ECONOMÍA II', 3, 1, 1, 5, 3, 3),
(110, 'INGENIERÍA DE COSTOS Y PRESUPUESTO', 3, 1, 1, 5, 3, 3),
(111, 'TERMODINÁMICA APLICADA', 3, 1, 1, 5, 3, 3),
(112, 'INGENIERÍA DE MANTENIMIENTO', 3, 1, 1, 5, 3, 3),
(113, 'INVESTIGACIÓN OPERATIVA I', 3, 1, 1, 5, 3, 3),
(114, 'ALGORITMO Y LENGUAJE DE PROGRAMACIÓN', 5, 1, 1, 5, 3, 3),
(115, 'FUNDAMENTOS DE CÁLCULO', 3, 1, 1, 1, 1, 4),
(116, 'FUNDAMENTOS DE INGENIERIA AGROINDUSTRIAL', 3, 1, 1, 1, 1, 4),
(117, 'FILOSOFÍA Y ÉTICA', 3, 1, 1, 1, 1, 4),
(118, 'METODOLOGÍA DEL TRABAJO UNIVERSITARIO', 2, 1, 1, 1, 1, 4),
(119, 'ACTIVIDADES CULTURALES Y DEPORTIVAS', 1, 1, 1, 1, 1, 4),
(120, 'INGLES I', 1, 1, 1, 1, 1, 4),
(121, 'LENGUAJE Y COMUNICACIÓN', 3, 1, 1, 1, 1, 4),
(122, 'BIOLOGIA', 3, 1, 2, 1, 1, 4),
(123, 'INGLÉS II', 1, 1, 1, 2, 1, 4),
(124, 'LIDERAZGO Y DESARROLLO PERSONAL', 3, 1, 1, 2, 1, 4),
(125, 'MEDIO AMBIENTE Y DESARROLLO SOSTENIBLE', 3, 1, 1, 2, 1, 4),
(126, 'CALCULO I', 3, 1, 1, 2, 1, 4),
(127, 'TECNOLOGÍAS DE LA INFORMACIÓN Y COMUNICACIÓN', 2, 1, 1, 2, 1, 4),
(128, 'SOCIOLOGÍA', 2, 1, 1, 2, 1, 4),
(130, 'QUIMICA GENERAL', 3, 1, 2, 2, 1, 4),
(131, 'GENÉTICA', 2, 1, 1, 2, 1, 4),
(132, 'INGLÉS III', 1, 1, 1, 3, 2, 4),
(133, 'PSICOLOGÍA ORGANIZACIONAL', 2, 1, 1, 3, 2, 4),
(134, 'ESTADÍSTICA', 3, 1, 1, 3, 2, 4),
(135, 'CÁLCULO II', 3, 1, 1, 3, 2, 4),
(136, 'METODOLOGÍA DE LA INVESTIGACIÓN', 3, 1, 1, 3, 2, 4),
(137, 'GEOPOLÍTICA Y REALIDAD NACIONAL', 3, 1, 1, 3, 2, 4),
(138, 'FÍSICA I', 3, 1, 1, 3, 2, 4),
(139, 'QUÍMICA ORGÁNICA', 3, 1, 2, 3, 2, 4),
(140, 'ADMINISTRACIÓN GENERAL', 2, 1, 1, 4, 2, 4),
(141, 'ESTADÍSTICA INFERENCIAL', 3, 1, 1, 4, 2, 4),
(142, 'FÍSICA II', 3, 1, 1, 4, 2, 4),
(143, 'ECONOMÍA', 2, 1, 1, 4, 2, 4),
(144, 'FÍSICO QUÍMICA', 3, 1, 1, 4, 2, 4),
(145, 'BIOQUÍMICA I', 4, 1, 2, 4, 2, 4),
(146, 'MICROBIOLOGÍA GENERAL', 4, 1, 2, 4, 2, 4),
(147, 'INVESTIGACIÓN OPERATIVA', 3, 1, 1, 5, 3, 4),
(148, 'MICROBIOLOGÍA APLICADA', 4, 1, 2, 5, 3, 4),
(149, 'CONTABILIDAD PARA INGENIEROS', 2, 1, 1, 5, 3, 4),
(150, 'TERMODINÁMICA', 3, 1, 1, 5, 3, 4),
(151, 'BALANCE DE MATERIA Y ENERGÍA', 3, 1, 1, 5, 3, 4),
(152, 'BIOQUÍMICA II', 4, 1, 2, 5, 3, 4),
(100550, 'tu mama', 6, 9, 2, 2, 4, 4),
(100551, 'gsg', 2, 3, 1, 3, 3, 3);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `dia`
--

CREATE TABLE `dia` (
  `ID_DIA` int(11) NOT NULL,
  `DIA` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `dia`
--

INSERT INTO `dia` (`ID_DIA`, `DIA`) VALUES
(0, 'NULL'),
(1, 'LUNES'),
(2, 'MARTES'),
(3, 'MIÉRCOLES'),
(4, 'JUEVES'),
(5, 'VIERNES'),
(6, 'SÁBADO');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `escuela`
--

CREATE TABLE `escuela` (
  `ID_ESCUELA` int(11) NOT NULL,
  `NOMBRE` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `escuela`
--

INSERT INTO `escuela` (`ID_ESCUELA`, `NOMBRE`) VALUES
(1, 'Ingeniería de Sistemas'),
(2, 'Ingeniería de Industrial'),
(3, 'Ingeniería de Transportes'),
(4, 'Ingeniería Agroindustrial');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `estado_matricula`
--

CREATE TABLE `estado_matricula` (
  `ID_STUDENT` int(11) NOT NULL,
  `ANIO` int(11) NOT NULL,
  `CICLO` int(11) NOT NULL,
  `ESTADO` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `estado_matricula`
--

INSERT INTO `estado_matricula` (`ID_STUDENT`, `ANIO`, `CICLO`, `ESTADO`) VALUES
(2023023531, 1, 1, 'MATRICULADO'),
(2023023531, 1, 2, 'MATRICULADO'),
(2023023531, 2, 3, 'MATRICULADO'),
(2023023543, 1, 2, 'MATRICULADO');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `estudiante`
--

CREATE TABLE `estudiante` (
  `ID_STUDENT` int(11) NOT NULL,
  `NAME` varchar(30) DEFAULT NULL,
  `SURNAME` varchar(30) DEFAULT NULL,
  `MAIL` varchar(100) DEFAULT NULL,
  `GENDER` varchar(10) DEFAULT NULL,
  `PASSWORD` varchar(20) DEFAULT NULL,
  `ID_CICLO` int(11) DEFAULT NULL,
  `ID_ESCUELA` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `estudiante`
--

INSERT INTO `estudiante` (`ID_STUDENT`, `NAME`, `SURNAME`, `MAIL`, `GENDER`, `PASSWORD`, `ID_CICLO`, `ID_ESCUELA`) VALUES
(2023023531, 'PATRICK ALEXANDER', 'ALCANTARA SEDANO', '2023023531@unfv.edu.pe', 'MASCULINO', 'comeresbueno', 2, 1),
(2023023532, 'MARIA', 'RODRIGUEZ', '2023023532@unfv.edu.pe', 'FEMENINO', 'password123', 2, 2),
(2023023533, 'CARLOS', 'GOMEZ', '2023023533@unfv.edu.pe', 'MASCULINO', 'password456', 3, 3),
(2023023534, 'ANA', 'MARTINEZ', '2023023534@unfv.edu.pe', 'FEMENINO', 'password789', 4, 1),
(2023023543, 'JUAN JESUS', 'ESQUIVEZ ZAPATA', '2023023543@unfv.edu.pe', 'MASCULINO', 'MINAR', 2, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `hora`
--

CREATE TABLE `hora` (
  `ID_HORA` int(11) NOT NULL,
  `HORA` varchar(5) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `hora`
--

INSERT INTO `hora` (`ID_HORA`, `HORA`) VALUES
(0, 'NULL'),
(1, '08:00'),
(2, '08:50'),
(3, '09:40'),
(4, '10:30'),
(5, '11:20'),
(6, '12:10'),
(7, '13:00'),
(8, '13:50'),
(9, '14:40'),
(10, '15:30'),
(11, '16:20'),
(12, '17:10'),
(13, '18:00'),
(14, '18:50'),
(15, '19:40');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `horario`
--

CREATE TABLE `horario` (
  `ID_HORARIO` int(11) NOT NULL,
  `ID_CURSO` int(11) DEFAULT NULL,
  `ID_SECCION` int(11) DEFAULT NULL,
  `ID_DIA_N1` int(11) DEFAULT NULL,
  `ID_HORA_INICIO_N1` int(11) DEFAULT NULL,
  `ID_HORA_FIN_N1` int(11) DEFAULT NULL,
  `ID_DIA_N2` int(11) DEFAULT NULL,
  `ID_HORA_INICIO_N2` int(11) DEFAULT NULL,
  `ID_HORA_FIN_N2` int(11) DEFAULT NULL,
  `ID_PROFESOR` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `horario`
--

INSERT INTO `horario` (`ID_HORARIO`, `ID_CURSO`, `ID_SECCION`, `ID_DIA_N1`, `ID_HORA_INICIO_N1`, `ID_HORA_FIN_N1`, `ID_DIA_N2`, `ID_HORA_INICIO_N2`, `ID_HORA_FIN_N2`, `ID_PROFESOR`) VALUES
(1, 1, 1, 1, 1, 3, 0, 0, 0, 1),
(2, 1, 2, 1, 3, 5, 0, 0, 0, 1),
(3, 1, 3, 1, 5, 7, 0, 0, 0, 1),
(4, 2, 1, 0, 0, 0, 0, 0, 0, 0),
(5, 2, 2, 0, 0, 0, 0, 0, 0, 0),
(6, 2, 3, 0, 0, 0, 0, 0, 0, 0),
(7, 3, 1, 5, 1, 5, 0, 0, 0, 2),
(8, 3, 2, 5, 5, 9, 0, 0, 0, 2),
(9, 3, 3, 4, 5, 9, 0, 0, 0, 2),
(10, 4, 1, 2, 3, 5, 3, 5, 7, 3),
(11, 4, 2, 0, 0, 8, 0, 0, 0, 3),
(12, 4, 3, 0, 0, 0, 0, 0, 0, 3),
(13, 5, 1, 1, 3, 6, 0, 0, 0, 4),
(14, 5, 2, 2, 2, 5, 0, 0, 0, 6),
(15, 5, 3, 2, 5, 8, 0, 0, 0, 6),
(16, 6, 1, 0, 0, 0, 0, 0, 0, 0),
(17, 6, 2, 0, 0, 0, 0, 0, 0, 0),
(18, 6, 3, 0, 0, 0, 0, 0, 0, 0),
(19, 7, 1, 0, 0, 0, 0, 0, 0, 0),
(20, 7, 2, 0, 0, 0, 0, 0, 0, 0),
(21, 7, 3, 0, 0, 0, 0, 0, 0, 0),
(22, 8, 1, 2, 5, 7, 4, 4, 7, 5),
(23, 8, 2, 1, 5, 7, 5, 1, 5, 7),
(24, 8, 3, 1, 7, 9, 3, 2, 5, 8),
(25, 9, 1, 3, 1, 4, 0, 0, 0, 1),
(26, 9, 2, 3, 3, 5, 0, 0, 0, 1),
(27, 9, 3, 3, 5, 7, 0, 0, 0, 1),
(28, 10, 1, 1, 2, 5, 2, 2, 5, 13),
(29, 10, 2, 1, 5, 8, 2, 5, 8, 13),
(30, 10, 3, 2, 2, 4, 4, 2, 4, 17),
(31, 11, 1, 1, 5, 8, 2, 5, 8, 1),
(32, 11, 2, 5, 4, 9, 0, 0, 0, 2),
(33, 11, 3, 2, 7, 10, 3, 1, 4, 10),
(34, 12, 1, 5, 1, 4, 0, 0, 0, 11),
(35, 12, 2, 2, 2, 5, 0, 0, 0, 7),
(36, 12, 3, 1, 4, 7, 0, 0, 0, 12),
(37, 13, 1, 4, 6, 9, 0, 0, 0, 2),
(38, 13, 2, 4, 3, 6, 0, 0, 0, 2),
(39, 13, 3, 5, 1, 4, 0, 0, 0, 2),
(40, 14, 1, 3, 3, 7, 0, 0, 0, 6),
(41, 14, 2, 1, 3, 5, 3, 1, 3, 16),
(42, 14, 3, 2, 4, 7, 4, 4, 7, 18),
(43, 15, 1, 4, 1, 3, 5, 3, 8, 14),
(44, 15, 2, 1, 1, 3, 3, 5, 8, 14),
(45, 15, 3, 0, 0, 0, 0, 0, 0, 14),
(46, 16, 1, 3, 7, 9, 4, 4, 6, 15),
(47, 16, 2, 4, 6, 10, 0, 0, 0, 15),
(48, 16, 3, 1, 1, 4, 5, 4, 7, 15),
(49, 17, 1, 3, 3, 5, 0, 0, 0, 1),
(50, 17, 2, 3, 5, 7, 0, 0, 0, 1),
(51, 17, 3, 3, 7, 9, 0, 0, 0, 1),
(52, 18, 1, 4, 7, 10, 0, 0, 0, 6),
(53, 18, 2, 0, 0, 0, 0, 0, 0, 6),
(54, 18, 3, 0, 0, 0, 0, 0, 0, 6),
(55, 19, 1, 2, 5, 7, 4, 5, 7, 19),
(56, 19, 2, 2, 3, 5, 4, 3, 5, 19),
(57, 19, 3, 2, 1, 3, 4, 1, 3, 16),
(58, 20, 1, 6, 3, 7, 0, 0, 0, 19),
(59, 20, 2, 1, 3, 5, 5, 6, 8, 21),
(60, 20, 3, 1, 5, 7, 5, 3, 5, 21),
(61, 21, 1, 2, 2, 5, 4, 2, 5, 13),
(62, 21, 2, 2, 5, 8, 4, 5, 8, 13),
(63, 21, 3, 2, 4, 7, 4, 4, 7, 18),
(64, 22, 1, 3, 1, 3, 5, 1, 3, 20),
(65, 22, 2, 1, 1, 3, 4, 1, 3, 20),
(66, 22, 3, 3, 3, 5, 5, 3, 5, 20),
(67, 23, 1, 1, 1, 4, 3, 5, 8, 14),
(68, 23, 2, 3, 1, 4, 5, 3, 6, 14),
(69, 23, 3, 0, 0, 0, 0, 0, 0, 14),
(70, 24, 1, 1, 4, 6, 5, 5, 7, 15),
(71, 24, 2, 1, 6, 8, 5, 8, 10, 15),
(72, 24, 3, 1, 1, 5, 0, 0, 0, 9),
(73, 38, 1, 2, 5, 9, 0, 0, 0, 22),
(74, 38, 2, 3, 5, 9, 0, 0, 0, 22),
(75, 38, 3, 3, 11, 13, 0, 0, 0, 0),
(76, 39, 1, 1, 4, 8, 0, 0, 0, 23),
(77, 39, 2, 5, 1, 5, 0, 0, 0, 23),
(78, 39, 3, 1, 11, 13, 0, 0, 0, 0),
(79, 40, 1, 2, 1, 5, 0, 0, 0, 24),
(80, 40, 2, 2, 4, 8, 4, 1, 3, 17),
(81, 40, 3, 2, 11, 13, 3, 7, 11, 17),
(82, 41, 1, 3, 1, 4, 5, 5, 8, 25),
(83, 41, 2, 1, 5, 8, 4, 5, 8, 32),
(84, 41, 3, 4, 5, 7, 5, 6, 8, 33),
(85, 42, 1, 5, 1, 5, 0, 0, 0, 26),
(86, 42, 2, 5, 5, 9, 0, 0, 0, 26),
(87, 42, 3, 2, 7, 11, 0, 0, 0, 26),
(88, 43, 1, 3, 4, 6, 0, 0, 0, 0),
(89, 43, 2, 4, 3, 5, 0, 0, 0, 0),
(90, 43, 3, 4, 11, 13, 0, 0, 0, 0),
(91, 44, 1, 4, 5, 9, 0, 0, 0, 27),
(92, 44, 2, 3, 1, 5, 0, 0, 0, 27),
(93, 44, 3, 1, 7, 11, 0, 0, 0, 27),
(94, 45, 1, 1, 1, 4, 0, 0, 0, 28),
(95, 45, 2, 2, 1, 4, 0, 0, 0, 28),
(96, 45, 3, 5, 8, 11, 0, 0, 0, 28),
(97, 46, 1, 4, 1, 5, 0, 0, 0, 0),
(98, 46, 2, 1, 1, 5, 0, 0, 0, 0),
(99, 46, 3, 4, 7, 11, 0, 0, 0, 0),
(100, 47, 1, 1, 6, 8, 0, 0, 0, 0),
(101, 47, 2, 2, 6, 8, 0, 0, 0, 0),
(102, 47, 3, 1, 12, 14, 0, 0, 0, 22),
(103, 48, 1, 2, 3, 7, 4, 1, 3, 29),
(104, 48, 2, 4, 5, 8, 5, 4, 7, 29),
(105, 48, 3, 3, 10, 14, 5, 10, 14, 34),
(106, 49, 1, 5, 1, 5, 0, 0, 0, 30),
(107, 49, 2, 3, 5, 9, 0, 0, 0, 30),
(108, 49, 3, 2, 10, 14, 0, 0, 0, 30),
(109, 50, 1, 5, 6, 9, 0, 0, 0, 31),
(110, 50, 2, 4, 1, 5, 0, 0, 0, 31),
(111, 50, 3, 4, 6, 10, 0, 0, 0, 31),
(112, 51, 1, 1, 4, 6, 3, 5, 8, 23),
(113, 51, 2, 1, 6, 9, 3, 1, 3, 23),
(114, 51, 3, 2, 7, 10, 0, 0, 0, 23),
(115, 52, 1, 1, 1, 4, 2, 1, 3, 32),
(116, 52, 2, 3, 3, 5, 5, 1, 4, 32),
(117, 52, 3, 4, 10, 13, 5, 7, 10, 32),
(118, 53, 1, 3, 1, 5, 0, 0, 0, 28),
(119, 53, 2, 1, 1, 5, 0, 0, 0, 28),
(120, 53, 3, 3, 6, 10, 0, 0, 0, 28),
(121, 54, 1, 4, 3, 8, 0, 0, 0, 0),
(122, 54, 2, 2, 1, 6, 0, 0, 0, 0),
(123, 54, 3, 1, 7, 12, 0, 0, 0, 0),
(124, 55, 1, 4, 1, 3, 0, 0, 0, 0),
(125, 55, 2, 1, 1, 5, 0, 0, 0, 22),
(126, 55, 3, 0, 0, 0, 0, 0, 0, 0),
(127, 56, 1, 4, 3, 7, 0, 0, 0, 31),
(128, 56, 2, 5, 5, 9, 0, 0, 0, 32),
(129, 56, 3, 4, 7, 11, 0, 0, 0, 32),
(130, 57, 1, 2, 1, 5, 0, 0, 0, 35),
(131, 57, 2, 3, 5, 7, 5, 1, 5, 38),
(132, 57, 3, 3, 7, 9, 5, 5, 9, 38),
(133, 58, 1, 1, 4, 8, 5, 1, 3, 30),
(134, 58, 2, 3, 1, 3, 4, 1, 5, 30),
(135, 58, 3, 2, 4, 8, 5, 3, 5, 39),
(136, 59, 1, 2, 8, 10, 4, 7, 9, 34),
(137, 59, 2, 3, 7, 9, 4, 5, 7, 34),
(138, 59, 3, 2, 11, 13, 3, 5, 7, 34),
(139, 60, 1, 1, 1, 4, 3, 1, 3, 36),
(140, 60, 2, 1, 5, 8, 3, 3, 5, 36),
(141, 60, 3, 1, 10, 12, 3, 9, 12, 36),
(142, 61, 1, 2, 5, 7, 5, 3, 6, 33),
(143, 61, 2, 2, 1, 4, 4, 7, 9, 33),
(144, 61, 3, 2, 8, 11, 4, 11, 14, 32),
(145, 62, 1, 3, 3, 8, 0, 0, 0, 37),
(146, 62, 2, 2, 4, 9, 0, 0, 0, 37),
(147, 62, 3, 1, 5, 10, 0, 0, 0, 37),
(148, 77, 1, 1, 5, 7, 0, 0, 0, 0),
(149, 77, 2, 2, 6, 8, 0, 0, 0, 0),
(150, 78, 1, 1, 3, 5, 3, 3, 5, 0),
(151, 78, 2, 1, 1, 3, 3, 1, 3, 0),
(152, 79, 1, 2, 1, 3, 4, 1, 3, 40),
(153, 79, 2, 4, 6, 8, 5, 1, 3, 44),
(154, 80, 1, 1, 1, 3, 1, 1, 3, 0),
(155, 80, 2, 1, 3, 5, 3, 3, 5, 0),
(156, 81, 1, 1, 7, 9, 3, 5, 8, 41),
(157, 81, 2, 1, 5, 7, 5, 5, 8, 41),
(158, 82, 1, 5, 1, 3, 0, 0, 0, 42),
(159, 82, 2, 5, 3, 5, 0, 0, 0, 42),
(160, 83, 1, 2, 6, 8, 4, 6, 8, 43),
(161, 83, 2, 2, 4, 6, 3, 5, 7, 44),
(162, 84, 1, 2, 4, 6, 4, 3, 6, 0),
(163, 84, 2, 2, 1, 4, 4, 1, 3, 0),
(164, 85, 1, 3, 7, 9, 0, 0, 0, 0),
(165, 85, 2, 1, 1, 3, 0, 0, 0, 0),
(166, 86, 1, 1, 6, 9, 4, 3, 5, 45),
(167, 86, 2, 2, 1, 4, 3, 4, 7, 42),
(168, 87, 1, 1, 3, 6, 5, 6, 9, 37),
(169, 87, 2, 2, 4, 6, 5, 1, 3, 49),
(170, 88, 1, 2, 1, 5, 0, 0, 0, 46),
(171, 88, 2, 5, 3, 7, 0, 0, 0, 46),
(172, 89, 1, 3, 1, 4, 0, 0, 0, 42),
(173, 89, 2, 1, 5, 8, 0, 0, 0, 42),
(174, 90, 1, 1, 1, 3, 3, 4, 7, 0),
(175, 90, 2, 1, 3, 5, 3, 1, 4, 0),
(176, 91, 1, 2, 5, 8, 4, 5, 6, 47),
(177, 91, 2, 2, 6, 8, 4, 6, 9, 47),
(178, 92, 1, 5, 1, 6, 0, 0, 0, 48),
(179, 92, 2, 4, 1, 6, 0, 0, 0, 48),
(180, 93, 1, 1, 5, 7, 0, 0, 0, 0),
(181, 93, 2, 1, 1, 3, 0, 0, 0, 0),
(182, 94, 1, 5, 4, 7, 0, 0, 0, 50),
(183, 94, 2, 5, 1, 4, 0, 0, 0, 50),
(184, 95, 1, 2, 1, 3, 4, 1, 3, 43),
(185, 95, 2, 3, 1, 3, 4, 3, 5, 43),
(186, 96, 1, 1, 1, 3, 3, 5, 7, 42),
(187, 96, 2, 1, 3, 5, 3, 7, 9, 42),
(188, 97, 1, 2, 3, 6, 4, 3, 6, 51),
(189, 97, 2, 2, 4, 7, 4, 5, 8, 52),
(190, 98, 1, 3, 1, 3, 5, 1, 4, 0),
(191, 98, 2, 3, 3, 5, 5, 4, 7, 0),
(192, 99, 1, 1, 3, 5, 3, 3, 5, 47),
(193, 99, 2, 1, 5, 7, 3, 5, 7, 47),
(194, 100, 1, 2, 6, 10, 4, 6, 8, 48),
(195, 100, 2, 2, 1, 4, 4, 1, 3, 48),
(196, 115, 1, 2, 1, 3, 3, 1, 3, 3),
(197, 115, 2, 1, 1, 5, 0, 0, 0, 49),
(198, 116, 1, 4, 1, 7, 0, 0, 0, 53),
(199, 116, 2, 3, 3, 7, 5, 3, 5, 53),
(200, 117, 1, 3, 3, 5, 5, 3, 5, 54),
(201, 117, 2, 2, 3, 5, 4, 3, 5, 54),
(202, 118, 1, 0, 0, 0, 0, 0, 0, 0),
(203, 118, 2, 0, 0, 0, 0, 0, 0, 0),
(204, 119, 1, 2, 3, 5, 0, 0, 0, 55),
(205, 119, 2, 4, 1, 3, 0, 0, 0, 55),
(206, 120, 1, 0, 0, 0, 0, 0, 0, 0),
(207, 120, 2, 0, 0, 0, 0, 0, 0, 0),
(208, 121, 1, 3, 5, 9, 0, 0, 0, 56),
(209, 121, 2, 1, 5, 9, 0, 0, 0, 56),
(210, 122, 1, 1, 1, 5, 0, 0, 0, 57),
(211, 122, 2, 3, 1, 3, 5, 1, 3, 63),
(212, 123, 1, 0, 0, 0, 0, 0, 0, 0),
(213, 123, 2, 0, 0, 0, 0, 0, 0, 0),
(214, 124, 1, 4, 1, 5, 5, 4, 6, 55),
(215, 124, 2, 2, 3, 7, 0, 0, 0, 54),
(216, 125, 1, 3, 3, 9, 0, 0, 0, 54),
(217, 125, 2, 2, 7, 9, 3, 3, 5, 60),
(218, 126, 1, 2, 1, 5, 0, 0, 0, 3),
(219, 126, 2, 4, 3, 7, 0, 0, 0, 14),
(220, 127, 1, 4, 5, 9, 0, 0, 0, 59),
(221, 127, 2, 3, 5, 8, 0, 0, 0, 59),
(222, 128, 1, 1, 5, 8, 0, 0, 0, 57),
(223, 128, 2, 5, 1, 4, 0, 0, 0, 55),
(224, 130, 1, 1, 1, 5, 0, 0, 0, 57),
(225, 130, 2, 1, 5, 9, 0, 0, 0, 57),
(226, 131, 1, 5, 1, 4, 0, 0, 0, 53),
(227, 131, 2, 5, 4, 7, 0, 0, 0, 53),
(228, 132, 1, 0, 0, 0, 0, 0, 0, 0),
(229, 132, 2, 0, 0, 0, 0, 0, 0, 0),
(230, 133, 1, 6, 1, 4, 0, 0, 0, 64),
(231, 133, 2, 2, 3, 7, 5, 3, 5, 64),
(232, 134, 1, 2, 1, 5, 6, 5, 7, 65),
(233, 134, 2, 3, 5, 7, 6, 1, 5, 65),
(234, 135, 1, 1, 1, 5, 0, 0, 0, 3),
(235, 135, 2, 1, 5, 7, 5, 1, 3, 14),
(236, 136, 1, 3, 1, 5, 0, 0, 0, 58),
(237, 136, 2, 4, 1, 5, 0, 0, 0, 58),
(238, 137, 1, 5, 3, 7, 0, 0, 0, 60),
(239, 137, 2, 1, 1, 5, 0, 0, 0, 55),
(240, 138, 1, 4, 1, 3, 0, 0, 0, 67),
(241, 138, 2, 3, 3, 5, 0, 0, 0, 67),
(242, 139, 1, 3, 5, 9, 4, 1, 3, 68),
(243, 139, 2, 3, 1, 3, 4, 5, 9, 68);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `matricula`
--

CREATE TABLE `matricula` (
  `ID_MATRICULA` int(11) NOT NULL,
  `ID_STUDENT` int(11) NOT NULL,
  `ID_CURSO` int(11) NOT NULL,
  `FECHA_MATRICULA` datetime DEFAULT current_timestamp(),
  `CICLO` int(11) NOT NULL DEFAULT 1,
  `ID_SECCION` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `matricula`
--

INSERT INTO `matricula` (`ID_MATRICULA`, `ID_STUDENT`, `ID_CURSO`, `FECHA_MATRICULA`, `CICLO`, `ID_SECCION`) VALUES
(11, 2023023531, 3, '2024-11-24 19:43:44', 2, 3),
(12, 2023023531, 4, '2024-11-24 19:43:44', 2, 2),
(13, 2023023531, 5, '2024-11-24 19:43:44', 2, 1),
(14, 2023023531, 17, '2024-12-13 21:22:26', 3, 2),
(15, 2023023531, 18, '2024-12-13 21:22:26', 3, 2),
(16, 2023023531, 19, '2024-12-13 21:22:26', 3, 2),
(17, 2023023531, 20, '2024-12-13 21:22:26', 3, 2),
(18, 2023023531, 23, '2024-12-13 21:22:26', 3, 3),
(19, 2023023531, 24, '2023-12-13 21:22:26', 3, 1),
(20, 2023023531, 1, '2024-12-15 11:27:54', 1, 2),
(21, 2023023531, 2, '2024-12-15 11:27:54', 1, 1),
(22, 2023023531, 3, '2024-12-15 11:27:54', 1, 2),
(23, 2023023531, 4, '2024-12-15 11:27:54', 1, 2),
(24, 2023023531, 5, '2024-12-15 11:27:54', 1, 2),
(25, 2023023531, 6, '2024-12-15 11:27:54', 1, 1),
(26, 2023023531, 7, '2024-12-15 11:27:54', 1, 2),
(27, 2023023531, 8, '2024-12-15 11:27:54', 1, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `profesor`
--

CREATE TABLE `profesor` (
  `ID_PROFESOR` int(11) NOT NULL,
  `NOMBRE` varchar(100) NOT NULL,
  `APELLIDO` varchar(100) NOT NULL,
  `EMAIL` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `profesor`
--

INSERT INTO `profesor` (`ID_PROFESOR`, `NOMBRE`, `APELLIDO`, `EMAIL`) VALUES
(0, 'NULL', 'NULL', 'NULL'),
(1, 'CARMEN ANGELICA', 'SALAZAR DEZA', 'csalazar@unfv.edu.pe'),
(2, 'CESAR GERARDO', 'LEON VELARDE', 'cleon@unfv.edu.pe'),
(3, 'JAIME DEMETRIO', 'CANO ESPADA', 'jcano@unfv.edu.pe'),
(4, 'JESUS ELIAS', 'CARRILLO BALCEDA', 'jcarrillo@unfv.edu.pe'),
(5, 'LUIS AVELINO', 'MUÑOZ RAMOS', 'lmunozr@unfv.edu.pe'),
(6, 'BERTHA BEATRIZ', 'LOPEZ JUAREZ', 'blopez@unfv.edu.pe'),
(7, 'ROGELIO GONZALO', 'COHELLO AGUIRRE', 'rcohello@unfv.edu.pe'),
(8, 'ORLANDO', 'ALVARADO ALVARADO', 'jalvaradoa@unfv.edu.pe'),
(9, 'IVAN CARLO', 'PETRLIK AZABACHE', 'ipetrlik@unfv.edu.pe'),
(10, 'JORGE ALBERTO', 'VALES CARRILLO', 'jvales@unfv.ed.pe'),
(11, 'JOSE ANTONIO', 'OGOSI AUQUI', 'jogosi@unfv.edu.pe'),
(12, 'MANUEL', 'NARRO ANDRADE', 'mnarro@unfv.edu.pe'),
(13, 'OSCAR HUGO', 'MUJICA RUIZ', 'omujica@unfv.edu.pe'),
(14, 'HENRY', 'ROJAS CARRETERO', 'hrojas@unfv.edu.pe'),
(15, 'ARMANDO RICARDO', 'HUAPAYA SOTERO', 'ahuapaya@unfv.edu.pe'),
(16, 'PABLO ROBERTO', 'APARICIO MONTENEGRO', 'paparicio@unfv.edu.pe'),
(17, 'GESHLER GALINDO', 'MARICIANO ESPINOZA', 'gmariciano@unfv.edu.pe'),
(18, 'JUAN OSWALDO', 'ALFARO BERNEDO', 'jalfaro@unfv.edu.pe'),
(19, 'LUZ NOEMI', 'RAMIREZ SAAVEDRA', 'lramirez@unfv.edu.pe'),
(20, 'HERIBERTO REGINALDO', 'MAGALLANES VILLAVERDE', 'hmagallanes@unfv.edu.pe'),
(21, 'JORGE RAFAEL', 'TELLO VERA', 'jtellov@unfv.edu.pe'),
(22, 'CESAR ALEJANDRO', 'REYNAFARJE PASTOR', 'creynafarje@unfv.edu.pe'),
(23, 'GABRIEL JORGE', 'CARLOS REYES', 'gcarlos@unfv.edu.pe'),
(24, 'JOSÉ', 'RAMIREZ ROSILLO', 'jramirezr@unfv.edu.pe'),
(25, 'JUAN ALBERTO', 'GONZALES CORREA', 'jgonzalesc@unfv.edu.pe'),
(26, 'FELICITA NANCY', 'FERNANDEZ YBARRA', 'ffernandez@unfv.edu.pe'),
(27, 'LUXI ASUNCION', 'BENITES CERNA', 'lbenites@unfv.edu.pe'),
(28, 'MAXIMO', 'ZEVALLOS LEON', 'mzevallos@unfv.edu.pe'),
(29, 'JUAN CLEMENTE', 'PAIRAZAMAN CARBAJAL', 'jpairazaman@unfv.edu.pe'),
(30, 'ENRIQUE', 'PEREYRA ZELADA', 'epereyra@unfv.edu.pe'),
(31, 'FRANCISCA SONIA', 'VERA TITO', 'fvera@unfv.edu.pe'),
(32, 'FERNANDO', 'HERNANDEZ CONDE', 'fhernandez@unfv.edu.pe'),
(33, 'CARLOS GUSTAVO', 'GALLEGOS COCA', 'cgallegos@unfv.edu.pe'),
(34, 'MANUEL ANTONIO', 'ESTRADA LAU', 'mestrada@unfv.edu.pe'),
(35, 'LUIS HUMBERTO', 'MANRIQUE SUAREZ', 'lmanrique@unfv.edu.pe'),
(36, 'ISAIAS SEVERO', 'TAFUR MALLQUI', 'itafur@unfv.edu.pe'),
(37, 'CESAR AUGUSTO', 'RIVADENEYRA RIVAS', 'crivadeneyra@unfv.edu.pe'),
(38, 'NANCY ALEJANDRA', 'OCHOA SOTOMAYOR', 'nochoa@unfv.edu.pe'),
(39, 'ORLANDO ELEODORO', 'MEZA ARMAS', 'omeza@unfv.edu.pe'),
(40, 'PERVIS', 'PAREDES PAREDES', 'pparedes@unfv.edu.pe'),
(41, 'LEONIDAS CAROLINA', 'SALAZAR BRAVO', 'lsalazar@unfv.edu.pe'),
(42, 'BLASDEMIR ISIDRO', 'CALDERON CUENCA', 'bvalderonc@unfv.edu.pe'),
(43, 'JOSSY CARLOT', 'ENCISO LOPEZ', 'jencisolop@unfv.edu.pe'),
(44, 'JHONY', 'PARDAVE LIVIA', 'jpardave@unfv.edu.pe'),
(45, 'MARIA ELENA', 'CAMPOS MIRANDA', 'mcamposm@unfv.edu.pe'),
(46, 'EDUARDO SILVANO', 'VIDAL RETAMOZO', 'evidalr@unfv.edu.pe'),
(47, 'MAGNO', 'ATAUJE PARIONA', 'matauje@unfv.edu.pe'),
(48, 'OSCAR ALFONSO', 'BURGA VASQUEZ', 'oburga@unfv.edu.pe'),
(49, 'ROBERTO AGUSTIN', 'ROJAS TORRES', 'rrojast@unfv.edu.pe'),
(50, 'JESUS ALEJANDRO', 'CHAVEZ DUEÑAS', 'jchavezdu@unfv.edu.pe'),
(51, 'HIGINIO', 'FLORES VIDAL', 'hflores@unfv.edu.pe'),
(52, 'WALTER', 'VILLALOBOS CUEVA', 'wvillalobos@unfv.edu.pe'),
(53, 'MARLENI VILMA', 'BAUTISTA ESPINOZA ', 'mbautista@unfv.edu.pe'),
(54, 'ISAAC', 'SANCHEZ CACERES', 'isanchez@unfv.edu.pe'),
(55, 'EMILIO IGNACIO', 'CARBONEL ALHUAY', 'ecarbonel@unfv.edu.pe'),
(56, 'DIANA', 'INGA LINDO', 'dinga@unfv.edu.pe'),
(57, 'ANGELICA YSABEL', 'MIRANDA JARA', 'amiranda@unfv.edu.pe'),
(58, 'JOSE ALBERTO', 'HUIMAN SANDOVAL', 'jhuiman@unfv.edu.pe'),
(59, 'FRANO', 'CAPETA MONDOÑEDO', 'fcapeta@unfv.edu.pe'),
(60, 'OSCAR', 'BENAVIDES CAVERO', 'obenavides@unfv.edu.pe'),
(61, 'JOSE LUIS', 'BAZAN BRICEÑO', 'jbazan@unfv.edu.pe'),
(62, 'PABLO ERNESTO', 'ESCOBAR RODRIGUEZ', 'pescobar@unfv.edu.pe'),
(63, 'MARIBEL MARGOT', 'HUATUCO LOZANO', 'mhuatuco@unfv.edu.pe'),
(64, 'PATRICIA MILAGROS', 'QUISPE BARRANTES', 'pquispe@unfv.edu.pe'),
(65, 'LUCIO', 'JARA BAUTISTA', 'ljara@unfv.edu.pe'),
(66, 'JULIAN', 'CCASANI ALLENDE', 'jccasani@unfv.edu.pe'),
(67, 'VIOLETA', 'ROMERO CARRION', 'vromero@unfv.edu.pe'),
(68, 'WILBER', 'QUISPE PRADO', 'wquispe@unfv.edu.pe'),
(69, 'WALTER', 'ALFARO BARDALES', 'walfaro@unfv.edu.pe');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `seccion`
--

CREATE TABLE `seccion` (
  `ID_SECCION` int(11) NOT NULL,
  `NOMBRE_SECCION` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `seccion`
--

INSERT INTO `seccion` (`ID_SECCION`, `NOMBRE_SECCION`) VALUES
(1, 'A'),
(2, 'B'),
(3, 'C');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `aula`
--
ALTER TABLE `aula`
  ADD PRIMARY KEY (`ID_AULA`);

--
-- Indices de la tabla `curso`
--
ALTER TABLE `curso`
  ADD PRIMARY KEY (`ID_CURSO`),
  ADD KEY `fk_curso_escuela` (`ID_ESCUELA`),
  ADD KEY `fk_aula` (`ID_AULA`);

--
-- Indices de la tabla `dia`
--
ALTER TABLE `dia`
  ADD PRIMARY KEY (`ID_DIA`);

--
-- Indices de la tabla `escuela`
--
ALTER TABLE `escuela`
  ADD PRIMARY KEY (`ID_ESCUELA`);

--
-- Indices de la tabla `estado_matricula`
--
ALTER TABLE `estado_matricula`
  ADD PRIMARY KEY (`ID_STUDENT`,`ANIO`,`CICLO`);

--
-- Indices de la tabla `estudiante`
--
ALTER TABLE `estudiante`
  ADD PRIMARY KEY (`ID_STUDENT`),
  ADD KEY `fk_escuela` (`ID_ESCUELA`);

--
-- Indices de la tabla `hora`
--
ALTER TABLE `hora`
  ADD PRIMARY KEY (`ID_HORA`);

--
-- Indices de la tabla `horario`
--
ALTER TABLE `horario`
  ADD PRIMARY KEY (`ID_HORARIO`),
  ADD KEY `ID_CURSO` (`ID_CURSO`),
  ADD KEY `ID_HORA_INICIO` (`ID_HORA_INICIO_N1`),
  ADD KEY `ID_HORA_FIN` (`ID_HORA_FIN_N1`),
  ADD KEY `ID_DIA` (`ID_DIA_N1`),
  ADD KEY `ID_SECCION` (`ID_SECCION`),
  ADD KEY `ID_DIA_N2` (`ID_DIA_N2`),
  ADD KEY `ID_HORA_INICIO_N2` (`ID_HORA_INICIO_N2`),
  ADD KEY `ID_HORA_FIN_N2` (`ID_HORA_FIN_N2`),
  ADD KEY `ID_PROFESOR` (`ID_PROFESOR`);

--
-- Indices de la tabla `matricula`
--
ALTER TABLE `matricula`
  ADD PRIMARY KEY (`ID_MATRICULA`),
  ADD KEY `FK_MATRICULA_ESTUDIANTE` (`ID_STUDENT`),
  ADD KEY `FK_MATRICULA_CURSO` (`ID_CURSO`),
  ADD KEY `ID_SECCION` (`ID_SECCION`);

--
-- Indices de la tabla `profesor`
--
ALTER TABLE `profesor`
  ADD PRIMARY KEY (`ID_PROFESOR`),
  ADD UNIQUE KEY `EMAIL` (`EMAIL`);

--
-- Indices de la tabla `seccion`
--
ALTER TABLE `seccion`
  ADD PRIMARY KEY (`ID_SECCION`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `aula`
--
ALTER TABLE `aula`
  MODIFY `ID_AULA` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `curso`
--
ALTER TABLE `curso`
  MODIFY `ID_CURSO` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=100552;

--
-- AUTO_INCREMENT de la tabla `dia`
--
ALTER TABLE `dia`
  MODIFY `ID_DIA` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT de la tabla `escuela`
--
ALTER TABLE `escuela`
  MODIFY `ID_ESCUELA` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de la tabla `hora`
--
ALTER TABLE `hora`
  MODIFY `ID_HORA` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT de la tabla `horario`
--
ALTER TABLE `horario`
  MODIFY `ID_HORARIO` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=244;

--
-- AUTO_INCREMENT de la tabla `matricula`
--
ALTER TABLE `matricula`
  MODIFY `ID_MATRICULA` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=28;

--
-- AUTO_INCREMENT de la tabla `profesor`
--
ALTER TABLE `profesor`
  MODIFY `ID_PROFESOR` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=70;

--
-- AUTO_INCREMENT de la tabla `seccion`
--
ALTER TABLE `seccion`
  MODIFY `ID_SECCION` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `curso`
--
ALTER TABLE `curso`
  ADD CONSTRAINT `curso_ibfk_1` FOREIGN KEY (`ID_AULA`) REFERENCES `aula` (`ID_AULA`),
  ADD CONSTRAINT `curso_ibfk_2` FOREIGN KEY (`ID_ESCUELA`) REFERENCES `escuela` (`ID_ESCUELA`),
  ADD CONSTRAINT `fk_aula` FOREIGN KEY (`ID_AULA`) REFERENCES `aula` (`ID_AULA`),
  ADD CONSTRAINT `fk_curso_escuela` FOREIGN KEY (`ID_ESCUELA`) REFERENCES `escuela` (`ID_ESCUELA`);

--
-- Filtros para la tabla `estado_matricula`
--
ALTER TABLE `estado_matricula`
  ADD CONSTRAINT `estado_matricula_ibfk_1` FOREIGN KEY (`ID_STUDENT`) REFERENCES `estudiante` (`ID_STUDENT`),
  ADD CONSTRAINT `estado_matricula_ibfk_2` FOREIGN KEY (`ID_STUDENT`) REFERENCES `estudiante` (`ID_STUDENT`);

--
-- Filtros para la tabla `estudiante`
--
ALTER TABLE `estudiante`
  ADD CONSTRAINT `estudiante_ibfk_1` FOREIGN KEY (`ID_ESCUELA`) REFERENCES `escuela` (`ID_ESCUELA`);

--
-- Filtros para la tabla `horario`
--
ALTER TABLE `horario`
  ADD CONSTRAINT `horario_ibfk_1` FOREIGN KEY (`ID_CURSO`) REFERENCES `curso` (`ID_CURSO`),
  ADD CONSTRAINT `horario_ibfk_2` FOREIGN KEY (`ID_SECCION`) REFERENCES `seccion` (`ID_SECCION`),
  ADD CONSTRAINT `horario_ibfk_3` FOREIGN KEY (`ID_DIA_N1`) REFERENCES `dia` (`ID_DIA`),
  ADD CONSTRAINT `horario_ibfk_4` FOREIGN KEY (`ID_DIA_N2`) REFERENCES `dia` (`ID_DIA`),
  ADD CONSTRAINT `horario_ibfk_5` FOREIGN KEY (`ID_HORA_INICIO_N1`) REFERENCES `hora` (`ID_HORA`),
  ADD CONSTRAINT `horario_ibfk_6` FOREIGN KEY (`ID_HORA_FIN_N1`) REFERENCES `hora` (`ID_HORA`),
  ADD CONSTRAINT `horario_ibfk_7` FOREIGN KEY (`ID_HORA_INICIO_N2`) REFERENCES `hora` (`ID_HORA`),
  ADD CONSTRAINT `horario_ibfk_8` FOREIGN KEY (`ID_HORA_FIN_N2`) REFERENCES `hora` (`ID_HORA`),
  ADD CONSTRAINT `horario_ibfk_9` FOREIGN KEY (`ID_PROFESOR`) REFERENCES `profesor` (`ID_PROFESOR`);

--
-- Filtros para la tabla `matricula`
--
ALTER TABLE `matricula`
  ADD CONSTRAINT `matricula_ibfk_1` FOREIGN KEY (`ID_STUDENT`) REFERENCES `estudiante` (`ID_STUDENT`),
  ADD CONSTRAINT `matricula_ibfk_2` FOREIGN KEY (`ID_CURSO`) REFERENCES `curso` (`ID_CURSO`),
  ADD CONSTRAINT `matricula_ibfk_3` FOREIGN KEY (`ID_SECCION`) REFERENCES `seccion` (`ID_SECCION`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
