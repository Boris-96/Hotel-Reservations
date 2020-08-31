/*
SQLyog Community v13.1.5  (64 bit)
MySQL - 10.4.11-MariaDB : Database - hotel
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`hotel` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

USE `hotel`;

/*Table structure for table `klijent` */

DROP TABLE IF EXISTS `klijent`;

CREATE TABLE `klijent` (
  `klijentId` bigint(20) NOT NULL AUTO_INCREMENT,
  `imeKlijent` varchar(50) NOT NULL,
  `prezimeKlijent` varchar(50) NOT NULL,
  `emailKlijent` varchar(50) NOT NULL,
  `brojPasosaKlijent` varchar(50) NOT NULL,
  `telefonKlijent` varchar(50) NOT NULL,
  `adresaKlijent` varchar(50) NOT NULL,
  PRIMARY KEY (`klijentId`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

/*Data for the table `klijent` */

insert  into `klijent`(`klijentId`,`imeKlijent`,`prezimeKlijent`,`emailKlijent`,`brojPasosaKlijent`,`telefonKlijent`,`adresaKlijent`) values 
(1,'Jovan','Jovanovic','joca123@gmail.com','1023956','063/733-13-42','Marka Markovica 5');

/*Table structure for table `rezervacija` */

DROP TABLE IF EXISTS `rezervacija`;

CREATE TABLE `rezervacija` (
  `rezervacijaId` bigint(20) NOT NULL AUTO_INCREMENT,
  `datumOd` date NOT NULL,
  `datumDo` date NOT NULL,
  `datumRezervacije` date NOT NULL,
  `ukupnaCena` double NOT NULL,
  `klijentId` bigint(20) DEFAULT NULL,
  `zaposleniId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`rezervacijaId`),
  KEY `FK_REZERVACIJA_KLIJENT` (`klijentId`),
  KEY `FK_REZERVACIJA_ZAPOSLENI` (`zaposleniId`),
  CONSTRAINT `FK_REZERVACIJA_KLIJENT` FOREIGN KEY (`klijentId`) REFERENCES `klijent` (`klijentId`) ON DELETE CASCADE,
  CONSTRAINT `FK_REZERVACIJA_ZAPOSLENI` FOREIGN KEY (`zaposleniId`) REFERENCES `zaposleni` (`zaposleniId`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4;

/*Data for the table `rezervacija` */

/*Table structure for table `rezervacijasobe` */

DROP TABLE IF EXISTS `rezervacijasobe`;

CREATE TABLE `rezervacijasobe` (
  `rezervacijaId` bigint(20) NOT NULL,
  `sobaId` bigint(20) NOT NULL,
  PRIMARY KEY (`rezervacijaId`,`sobaId`),
  KEY `FK_REZERVACIJASOBA_SOBA` (`sobaId`),
  CONSTRAINT `FK_REZERVACIJASOBA_REZERVACIJA` FOREIGN KEY (`rezervacijaId`) REFERENCES `rezervacija` (`rezervacijaId`) ON DELETE CASCADE,
  CONSTRAINT `FK_REZERVACIJASOBA_SOBA` FOREIGN KEY (`sobaId`) REFERENCES `soba` (`sobaId`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `rezervacijasobe` */

/*Table structure for table `rezervacijausluge` */

DROP TABLE IF EXISTS `rezervacijausluge`;

CREATE TABLE `rezervacijausluge` (
  `rezervacijaId` bigint(20) NOT NULL,
  `uslugaId` bigint(20) NOT NULL,
  `brojDanaUsluge` int(11) DEFAULT NULL,
  PRIMARY KEY (`rezervacijaId`,`uslugaId`),
  KEY `FK_REZERVACIJAUSLUGA_USLUGA` (`uslugaId`),
  CONSTRAINT `FK_REZERVACIJAUSLUGA_REZERVACIJA` FOREIGN KEY (`rezervacijaId`) REFERENCES `rezervacija` (`rezervacijaId`) ON DELETE CASCADE,
  CONSTRAINT `FK_REZERVACIJAUSLUGA_USLUGA` FOREIGN KEY (`uslugaId`) REFERENCES `usluga` (`uslugaId`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `rezervacijausluge` */

/*Table structure for table `soba` */

DROP TABLE IF EXISTS `soba`;

CREATE TABLE `soba` (
  `sobaId` bigint(20) NOT NULL AUTO_INCREMENT,
  `sprat` int(11) NOT NULL,
  `brojSobe` int(11) NOT NULL,
  `cenaSobePoDanu` double NOT NULL,
  `brojKreveta` int(11) NOT NULL,
  `tipSobeId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`sobaId`),
  KEY `FK_SOBA_TIPSOBE` (`tipSobeId`),
  CONSTRAINT `FK_SOBA_TIPSOBE` FOREIGN KEY (`tipSobeId`) REFERENCES `tipsobe` (`tipSobeId`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4;

/*Data for the table `soba` */

insert  into `soba`(`sobaId`,`sprat`,`brojSobe`,`cenaSobePoDanu`,`brojKreveta`,`tipSobeId`) values 
(1,1,1,20,2,3),
(2,1,2,20,2,3),
(3,1,3,30,3,3),
(4,1,4,30,3,3),
(5,1,5,30,3,3),
(6,1,6,30,3,3),
(7,1,7,40,4,3),
(8,1,8,40,4,3),
(9,1,9,15,1,3),
(10,1,10,15,1,3),
(11,2,1,15,1,3),
(12,2,2,40,2,1),
(13,2,3,40,2,1),
(14,2,4,40,2,1),
(15,2,5,100,2,2),
(16,2,6,60,3,1),
(17,2,7,60,3,1),
(18,2,8,60,3,1),
(19,2,9,60,3,1),
(20,2,10,80,4,1);

/*Table structure for table `tipsobe` */

DROP TABLE IF EXISTS `tipsobe`;

CREATE TABLE `tipsobe` (
  `tipSobeId` bigint(20) NOT NULL AUTO_INCREMENT,
  `nazivTipaSobe` varchar(50) NOT NULL,
  PRIMARY KEY (`tipSobeId`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;

/*Data for the table `tipsobe` */

insert  into `tipsobe`(`tipSobeId`,`nazivTipaSobe`) values 
(1,'Delux room'),
(2,'President room'),
(3,'Standard room');

/*Table structure for table `usluga` */

DROP TABLE IF EXISTS `usluga`;

CREATE TABLE `usluga` (
  `uslugaId` bigint(20) NOT NULL AUTO_INCREMENT,
  `cenaUslugePoDanu` double NOT NULL,
  `nazivUsluge` varchar(50) NOT NULL,
  PRIMARY KEY (`uslugaId`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;

/*Data for the table `usluga` */

insert  into `usluga`(`uslugaId`,`cenaUslugePoDanu`,`nazivUsluge`) values 
(1,8,'SPA'),
(2,20,'Room service'),
(3,10,'Masaza');

/*Table structure for table `zaposleni` */

DROP TABLE IF EXISTS `zaposleni`;

CREATE TABLE `zaposleni` (
  `zaposleniId` bigint(20) NOT NULL AUTO_INCREMENT,
  `imeZaposleni` varchar(50) NOT NULL,
  `prezimeZaposleni` varchar(50) NOT NULL,
  `emailZaposleni` varchar(50) NOT NULL,
  `telefonZaposleni` varchar(50) NOT NULL,
  `adresaZaposleni` varchar(50) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `radniStatus` tinyint(1) NOT NULL,
  `brojPasosaZaposleni` varchar(50) NOT NULL,
  PRIMARY KEY (`zaposleniId`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

/*Data for the table `zaposleni` */

insert  into `zaposleni`(`zaposleniId`,`imeZaposleni`,`prezimeZaposleni`,`emailZaposleni`,`telefonZaposleni`,`adresaZaposleni`,`username`,`password`,`radniStatus`,`brojPasosaZaposleni`) values 
(1,'Boris','Zivkov','borisZivkov@gmail.com','064565123','Marka Tajcevica 6b','boris','boris',1,'32443324324324');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
