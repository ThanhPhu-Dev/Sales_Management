-- phpMyAdmin SQL Dump
-- version 4.9.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3308
-- Generation Time: Jun 13, 2021 at 02:52 AM
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
  `Discount` float NOT NULL,
  `PromotionCustomer` int(11) NOT NULL,
  `DateCreate` date NOT NULL,
  PRIMARY KEY (`Id`),
  KEY `fk_bills_customer` (`CustomerId`),
  KEY `fk_bills_promotionsCustomer` (`PromotionCustomer`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

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
  `PromotionsId` int(11) NOT NULL,
  `DebtMax` int(11) NOT NULL DEFAULT '2000000',
  PRIMARY KEY (`Id`),
  KEY `fk_customer_promotionsCustommer` (`PromotionsId`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

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
  `Last Price` int(11) NOT NULL,
  `PromotionProduct` int(11) NOT NULL,
  PRIMARY KEY (`Id`),
  KEY `fk_detailbill_bill` (`BillId`),
  KEY `fk_detailbill_product` (`ProductId`),
  KEY `fk_detailbill_promotionsProduct` (`PromotionProduct`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `bills`
--
ALTER TABLE `bills`
  ADD CONSTRAINT `fk_bills_customer` FOREIGN KEY (`CustomerId`) REFERENCES `customers` (`Id`),
  ADD CONSTRAINT `fk_bills_promotionsCustomer` FOREIGN KEY (`PromotionCustomer`) REFERENCES `promotions_customers` (`Id`);

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
  ADD CONSTRAINT `fk_detailbill_promotionsProduct` FOREIGN KEY (`PromotionProduct`) REFERENCES `promotions_products` (`Id`);

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
