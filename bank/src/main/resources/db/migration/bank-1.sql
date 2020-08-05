-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Aug 05, 2020 at 01:36 PM
-- Server version: 5.5.62
-- PHP Version: 7.4.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `bank`
--

-- --------------------------------------------------------

--
-- Table structure for table `account`
--

CREATE TABLE `account` (
  `ID` bigint(10) NOT NULL,
  `USER_CNP` bigint(13) DEFAULT NULL,
  `IBAN` varchar(24) DEFAULT NULL,
  `BALANCE` decimal(10,2) DEFAULT '0.00',
  `CREATION_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `LAST_UPDATED` datetime DEFAULT '2020-01-01 00:00:00'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `account`
--

INSERT INTO `account` (`ID`, `USER_CNP`, `IBAN`, `BALANCE`, `CREATION_DATE`, `LAST_UPDATED`) VALUES
(1, 1800101021917, 'RO98INGB0000100100100100', '845.77', '2020-07-23 10:52:14', '2020-08-01 07:37:34'),
(20, 1800101021920, 'RO98INGB0000100100100109', '405.20', '2020-07-24 20:30:44', '2020-08-01 07:37:34'),
(22, 1800101021922, 'RO98INGB0000100100100111', '800.00', '2020-07-27 07:28:06', '2020-07-31 21:08:44'),
(27, 1800101021922, 'RO98INGB0000100100100116', '0.00', '2020-07-27 13:51:07', '2020-07-27 16:51:07'),
(28, 1800101021923, 'RO98INGB0000100100100117', '100.50', '2020-07-28 07:07:53', '2020-07-29 11:39:08'),
(30, 1910101020908, 'RO98INGB0000100100100120', '1500.00', '2020-07-29 09:01:41', '2020-07-29 12:01:41'),
(31, 1910101020908, 'RO98INGB0000100100100121', '0.00', '2020-07-29 10:58:45', '2020-07-29 13:58:45'),
(32, 1910101020908, 'RO98INGB0000100100100122', '0.00', '2020-07-31 09:56:45', '2020-07-31 12:56:45'),
(33, 1825678112145, 'RO98INGB0000100100100123', '0.00', '2020-07-31 13:11:15', '2020-07-31 00:00:00'),
(34, 1800101021923, 'RO98INGB0000100100100124', '0.00', '2020-07-31 13:22:54', '2020-07-31 16:22:54'),
(35, 2605678112145, 'RO98INGB0000100100100135', '9914.50', '2020-07-31 19:18:51', '2020-07-31 22:20:22'),
(36, 2605678112145, 'RO98INGB0000100100100137', '8500.00', '2020-07-31 19:24:47', '2020-07-31 22:48:13'),
(37, 1935678112193, 'RO98INGB0000100100100140', '0.00', '2020-08-01 07:27:08', '2020-08-01 10:27:08');

-- --------------------------------------------------------

--
-- Table structure for table `card`
--

CREATE TABLE `card` (
  `ID` bigint(10) NOT NULL,
  `IBAN` varchar(24) DEFAULT NULL,
  `CARD_NUMBER` bigint(16) DEFAULT NULL,
  `pin` varchar(4) DEFAULT NULL,
  `STATUS` enum('ACTIVE','BLOCKED','PENDING') DEFAULT NULL,
  `CREATION_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `LAST_UPDATED` datetime DEFAULT NULL,
  `EXPIRATION_DATE` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `card`
--

INSERT INTO `card` (`ID`, `IBAN`, `CARD_NUMBER`, `pin`, `STATUS`, `CREATION_DATE`, `LAST_UPDATED`, `EXPIRATION_DATE`) VALUES
(1, 'RO98INGB0000100100100111', 1234123412340000, '1235', 'ACTIVE', '2020-07-28 08:04:02', '2020-07-31 19:43:04', '2022-07-30 00:00:00'),
(2, 'RO98INGB0000100100100109', 1234123412340001, '1237', 'ACTIVE', '2020-07-28 12:03:55', '2020-07-31 20:28:26', '2022-07-30 00:00:00'),
(4, 'RO98INGB0000100100100100', 1234123412340003, '1234', 'ACTIVE', '2020-07-29 07:40:19', '2020-07-31 20:51:50', '2020-10-01 00:00:00'),
(5, 'RO98INGB0000100100100116', 1234123412340007, '1234', 'ACTIVE', '2020-07-30 16:22:50', '2020-07-30 19:22:50', '2022-07-30 19:22:50'),
(7, 'RO98INGB0000100100100137', 1234123412340019, '1111', 'ACTIVE', '2020-07-31 19:36:54', '2020-07-31 22:47:44', '2022-07-31 22:36:54'),
(8, 'RO98INGB0000100100100116', 1234123412340020, '1234', 'ACTIVE', '2020-08-02 18:39:00', '2020-08-02 21:39:00', '2022-08-02 21:39:00'),
(9, 'RO98INGB0000100100100121', 1234123412340022, '8699', 'PENDING', '2020-08-05 11:02:20', '2020-08-05 14:02:20', '2022-08-05 14:02:20'),
(10, 'RO98INGB0000100100100121', 1234123412340023, '9031', 'ACTIVE', '2020-08-05 11:04:44', '2020-08-05 14:05:18', '2022-08-05 14:04:44');

-- --------------------------------------------------------

--
-- Table structure for table `card_number`
--

CREATE TABLE `card_number` (
  `id` int(8) NOT NULL,
  `card_number` bigint(16) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `card_number`
--

INSERT INTO `card_number` (`id`, `card_number`) VALUES
(25, 1234123412340024),
(26, 1234123412340025),
(27, 1234123412340026),
(28, 1234123412340027),
(29, 1234123412340028),
(30, 1234123412340029),
(31, 1234123412340030),
(32, 1234123412340031),
(33, 1234123412340032),
(34, 1234123412340033);

-- --------------------------------------------------------

--
-- Table structure for table `deleted_account`
--

CREATE TABLE `deleted_account` (
  `id` int(8) NOT NULL,
  `u_IBAN` varchar(24) NOT NULL,
  `CNP` varchar(13) NOT NULL,
  `creation_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `deleted_account`
--

INSERT INTO `deleted_account` (`id`, `u_IBAN`, `CNP`, `creation_date`) VALUES
(1, 'RO98INGB0000100100100100', '1800320045118', '2020-07-13 17:11:25'),
(2, 'RO98INGB0000100100100113', '1800101021922', '2020-07-27 13:46:08'),
(3, 'RO98INGB0000100100100114', '1800101021922', '2020-07-27 13:49:52'),
(4, 'RO98INGB0000100100100115', '1800101021922', '2020-07-27 13:51:19'),
(5, 'RO98INGB0000100100100110', '1800101021922', '2020-07-27 14:35:32'),
(6, 'RO98INGB0000100100100119', '1910101020908', '2020-07-29 09:04:25'),
(7, 'RO98INGB0000100100100119', '1910101020908', '2020-07-29 09:06:25');

-- --------------------------------------------------------

--
-- Table structure for table `deleted_card`
--

CREATE TABLE `deleted_card` (
  `id` int(8) NOT NULL,
  `u_IBAN` varchar(24) NOT NULL,
  `card_number` bigint(16) DEFAULT NULL,
  `creation_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `deleted_card`
--

INSERT INTO `deleted_card` (`id`, `u_IBAN`, `card_number`, `creation_date`) VALUES
(1, 'RO98INGB0000100100100100', 1234123412340000, '2020-07-13 17:13:51'),
(2, 'RO98INGB0000100100100121', 1234123412340006, '2020-07-29 11:49:01'),
(3, 'RO98INGB0000100100100124', 1234123412340009, '2020-07-31 17:44:18');

-- --------------------------------------------------------

--
-- Table structure for table `iban`
--

CREATE TABLE `iban` (
  `id` int(5) NOT NULL,
  `name_IBAN` varchar(12) NOT NULL DEFAULT 'RO98INGB0000',
  `no_IBAN` bigint(12) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `iban`
--

INSERT INTO `iban` (`id`, `name_IBAN`, `no_IBAN`) VALUES
(42, 'RO98INGB0000', 100100100141),
(43, 'RO98INGB0000', 100100100142),
(44, 'RO98INGB0000', 100100100143),
(45, 'RO98INGB0000', 100100100144);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `ID` bigint(10) NOT NULL,
  `LAST_NAME` varchar(20) DEFAULT NULL,
  `FIRST_NAME` varchar(55) DEFAULT NULL,
  `CNP` bigint(13) DEFAULT NULL,
  `CREATION_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`ID`, `LAST_NAME`, `FIRST_NAME`, `CNP`, `CREATION_DATE`) VALUES
(1, 'ANTONESCU', 'VICTOR', 1800101021917, '2020-07-17 07:35:49'),
(2, 'Odobescu', 'Alexandru Mihai', 2800101021918, '2020-07-22 10:52:50'),
(3, 'ION', 'ION ION', 1790219035017, '2020-07-22 14:51:55'),
(4, 'Lita', 'Cornel Dumitru', 1800101021920, '2020-07-23 05:46:06'),
(5, 'Mihaescu', 'Marius Victor', 1800101021922, '2020-07-24 10:44:11'),
(7, 'Calinescu', 'George', 1800101021923, '2020-07-24 10:49:02'),
(8, 'Marinescu', 'Ionut', 1650101020908, '2020-07-27 14:46:41'),
(9, 'Dutu', 'Alin Nicusor', 1910101020908, '2020-07-29 08:49:35'),
(10, 'Iordache', 'Cosmin Florin', 1825678112145, '2020-07-31 10:59:06'),
(11, 'Nechifor', 'Rodica', 2605678112145, '2020-07-31 19:18:19'),
(12, 'Nechifor', 'Mihaela', 2725678112172, '2020-08-01 05:11:40'),
(13, 'Spalatelu', 'Ionut', 1925678112193, '2020-08-01 07:20:34'),
(14, 'Spalatelu', 'Ionut', 1935678112193, '2020-08-01 07:24:37');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `account`
--
ALTER TABLE `account`
  ADD PRIMARY KEY (`ID`),
  ADD UNIQUE KEY `IBAN` (`IBAN`),
  ADD KEY `USER_CNP` (`USER_CNP`),
  ADD KEY `ID_SOME` (`IBAN`);

--
-- Indexes for table `card`
--
ALTER TABLE `card`
  ADD PRIMARY KEY (`ID`),
  ADD UNIQUE KEY `CARD_NUMBER` (`CARD_NUMBER`),
  ADD KEY `IBAN` (`IBAN`);

--
-- Indexes for table `card_number`
--
ALTER TABLE `card_number`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `deleted_account`
--
ALTER TABLE `deleted_account`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `deleted_card`
--
ALTER TABLE `deleted_card`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `iban`
--
ALTER TABLE `iban`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`ID`),
  ADD UNIQUE KEY `CNP` (`CNP`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `account`
--
ALTER TABLE `account`
  MODIFY `ID` bigint(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=38;

--
-- AUTO_INCREMENT for table `card`
--
ALTER TABLE `card`
  MODIFY `ID` bigint(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `card_number`
--
ALTER TABLE `card_number`
  MODIFY `id` int(8) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=35;

--
-- AUTO_INCREMENT for table `deleted_account`
--
ALTER TABLE `deleted_account`
  MODIFY `id` int(8) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `deleted_card`
--
ALTER TABLE `deleted_card`
  MODIFY `id` int(8) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `iban`
--
ALTER TABLE `iban`
  MODIFY `id` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=46;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `ID` bigint(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `account`
--
ALTER TABLE `account`
  ADD CONSTRAINT `account_ibfk_1` FOREIGN KEY (`USER_CNP`) REFERENCES `user` (`CNP`);

--
-- Constraints for table `card`
--
ALTER TABLE `card`
  ADD CONSTRAINT `card_ibfk_1` FOREIGN KEY (`IBAN`) REFERENCES `account` (`IBAN`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
