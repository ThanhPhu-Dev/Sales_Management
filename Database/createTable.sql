-- phpMyAdmin SQL Dump
-- version 4.9.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3308
-- Generation Time: Jun 15, 2021 at 05:16 AM
-- Server version: 5.7.28
-- PHP Version: 7.3.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `salesmanagement`
--

-- --------------------------------------------------------

--
-- Table structure for table `bills`
--

DROP TABLE IF EXISTS `bills`;
CREATE TABLE IF NOT EXISTS `bills` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Total` int(11) NOT NULL,
  `CustomerId` int(11) NOT NULL,
  `Discount` float NOT NULL DEFAULT '0',
  `PromotionCustomerId` int(11) DEFAULT NULL,
  `DateCreate` date NOT NULL,
  PRIMARY KEY (`Id`),
  KEY `fk_bills_customer` (`CustomerId`),
  KEY `fk_bills_promotionsCustomer` (`PromotionCustomerId`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `bills`
--

INSERT INTO `bills` (`Id`, `Total`, `CustomerId`, `Discount`, `PromotionCustomerId`, `DateCreate`) VALUES
(1, 1123200, 1, 10, 1, '2021-06-13'),
(2, 2964000, 2, 0, NULL, '2021-06-13'),
(3, 1123200, 3, 0, 1, '2021-06-09'),
(4, 643500, 4, 5, 2, '2021-06-22'),
(5, 1014000, 5, 0, NULL, '2021-06-20'),
(6, 1123200, 6, 0, 2, '2021-06-18');

-- --------------------------------------------------------

--
-- Table structure for table `customers`
--

DROP TABLE IF EXISTS `customers`;
CREATE TABLE IF NOT EXISTS `customers` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `NumberCard` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL,
  `AccountBalance` int(11) NOT NULL,
  `PromotionsId` int(11) DEFAULT NULL,
  `DebtMax` int(11) NOT NULL DEFAULT '2000000',
  PRIMARY KEY (`Id`),
  KEY `fk_customer_promotionsCustommer` (`PromotionsId`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `customers`
--

INSERT INTO `customers` (`Id`, `Name`, `NumberCard`, `AccountBalance`, `PromotionsId`, `DebtMax`) VALUES
(1, 'Nghĩa DX', '02315482', 500000, 1, 2000000),
(2, 'Dương Trọng Phúc', '51324102', 700000, NULL, 2000000),
(3, 'Nguyễn Đức', '5213571', 550000, 1, 2000000),
(4, 'Minh Huy', '50216818', 800000, 2, 2000000),
(5, 'Thành Phú', '50023482', 800000, NULL, 2000000),
(6, 'Hùng Phan', '50516871', 1000000, 2, 2000000);

-- --------------------------------------------------------

--
-- Table structure for table `detail_bills`
--

DROP TABLE IF EXISTS `detail_bills`;
CREATE TABLE IF NOT EXISTS `detail_bills` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `BillId` int(11) NOT NULL,
  `Quantity` int(11) NOT NULL,
  `ProductId` int(11) NOT NULL,
  `LastPrice` int(11) NOT NULL,
  `PromotionProductId` int(11) NOT NULL,
  PRIMARY KEY (`Id`),
  KEY `fk_detailbill_bill` (`BillId`),
  KEY `fk_detailbill_product` (`ProductId`),
  KEY `fk_detailbill_promotionsProduct` (`PromotionProductId`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `detail_bills`
--

INSERT INTO `detail_bills` (`Id`, `BillId`, `Quantity`, `ProductId`, `LastPrice`, `PromotionProductId`) VALUES
(1, 1, 3, 1, 468000, 1),
(2, 1, 4, 2, 936000, 2),
(3, 3, 2, 2, 468000, 2),
(4, 3, 5, 1, 1872000, 1),
(5, 4, 3, 2, 702000, 2),
(6, 4, 1, 1, 156000, 1),
(7, 2, 7, 1, 1092000, 1),
(8, 2, 8, 2, 1872000, 2),
(9, 5, 1, 2, 234000, 2),
(10, 5, 5, 1, 780000, 1),
(11, 6, 4, 2, 936000, 2),
(12, 6, 3, 1, 468000, 1);

-- --------------------------------------------------------

--
-- Table structure for table `products`
--

DROP TABLE IF EXISTS `products`;
CREATE TABLE IF NOT EXISTS `products` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `Specification` int(10) UNSIGNED NOT NULL,
  `HistoricalCost` int(10) UNSIGNED NOT NULL,
  `TradeDiscount` float NOT NULL,
  `PromotionId` int(11) NOT NULL,
  PRIMARY KEY (`Id`),
  KEY `fk_product_promotionsProduct` (`PromotionId`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `products`
--

INSERT INTO `products` (`Id`, `Name`, `Specification`, `HistoricalCost`, `TradeDiscount`, `PromotionId`) VALUES
(1, 'Sản Phẩm Cao Cấp NGC', 100, 200000, 10, 1),
(2, 'Sản Phẩm 102', 120, 300000, 12, 2);

-- --------------------------------------------------------

--
-- Table structure for table `promotions_customers`
--

DROP TABLE IF EXISTS `promotions_customers`;
CREATE TABLE IF NOT EXISTS `promotions_customers` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `StartDate` date NOT NULL,
  `EndDate` date NOT NULL,
  `PercentDiscount` float NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `promotions_customers`
--

INSERT INTO `promotions_customers` (`Id`, `Name`, `StartDate`, `EndDate`, `PercentDiscount`) VALUES
(1, 'Khách hàng mới', '2021-06-01', '2021-06-22', 10),
(2, 'Khách Hàng Tri Ân', '2021-06-02', '2021-06-23', 20);

-- --------------------------------------------------------

--
-- Table structure for table `promotions_products`
--

DROP TABLE IF EXISTS `promotions_products`;
CREATE TABLE IF NOT EXISTS `promotions_products` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `StartDate` date NOT NULL,
  `EndDate` date NOT NULL,
  `PercentDiscount` float NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `promotions_products`
--

INSERT INTO `promotions_products` (`Id`, `Name`, `StartDate`, `EndDate`, `PercentDiscount`) VALUES
(1, 'Sản Phẩm Sắp Hết Hạn', '2021-06-08', '2021-06-22', 12),
(2, 'Sản phẩm mới', '2021-06-13', '2021-06-30', 10);

-- --------------------------------------------------------

--
-- Table structure for table `receipts`
--

DROP TABLE IF EXISTS `receipts`;
CREATE TABLE IF NOT EXISTS `receipts` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `CustomerId` int(11) NOT NULL,
  `Price` int(11) NOT NULL,
  `DateCreate` date NOT NULL,
  PRIMARY KEY (`Id`),
  KEY `fk_receipt_customer` (`CustomerId`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `receipts`
--

INSERT INTO `receipts` (`Id`, `CustomerId`, `Price`, `DateCreate`) VALUES
(1, 2, 200000, '2021-06-15'),
(2, 1, 100000, '2021-06-14');

--
-- Constraints for dumped tables
--

--
-- Constraints for table `bills`
--
ALTER TABLE `bills`
  ADD CONSTRAINT `fk_bills_customer` FOREIGN KEY (`CustomerId`) REFERENCES `customers` (`Id`),
  ADD CONSTRAINT `fk_bills_promotionsCustomer` FOREIGN KEY (`PromotionCustomerId`) REFERENCES `promotions_customers` (`Id`);

--
-- Constraints for table `customers`
--
ALTER TABLE `customers`
  ADD CONSTRAINT `fk_customer_promotionsCustommer` FOREIGN KEY (`PromotionsId`) REFERENCES `promotions_customers` (`Id`);

--
-- Constraints for table `detail_bills`
--
ALTER TABLE `detail_bills`
  ADD CONSTRAINT `fk_detailbill_bill` FOREIGN KEY (`BillId`) REFERENCES `bills` (`Id`),
  ADD CONSTRAINT `fk_detailbill_product` FOREIGN KEY (`ProductId`) REFERENCES `products` (`Id`),
  ADD CONSTRAINT `fk_detailbill_promotionsProduct` FOREIGN KEY (`PromotionProductId`) REFERENCES `promotions_products` (`Id`);

--
-- Constraints for table `products`
--
ALTER TABLE `products`
  ADD CONSTRAINT `fk_product_promotionsProduct` FOREIGN KEY (`PromotionId`) REFERENCES `promotions_products` (`Id`);

--
-- Constraints for table `receipts`
--
ALTER TABLE `receipts`
  ADD CONSTRAINT `fk_receipt_customer` FOREIGN KEY (`CustomerId`) REFERENCES `customers` (`Id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
