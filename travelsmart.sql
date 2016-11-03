-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: May 12, 2015 at 10:05 AM
-- Server version: 5.6.17
-- PHP Version: 5.5.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `travelsmart`
--
CREATE DATABASE IF NOT EXISTS `travelsmart` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `travelsmart`;

-- --------------------------------------------------------

--
-- Table structure for table `admins`
--

CREATE TABLE IF NOT EXISTS `admins` (
  `idUser` int(11) NOT NULL AUTO_INCREMENT,
  `nom` text NOT NULL,
  `prenom` text NOT NULL,
  `email` text NOT NULL,
  `adresse` text NOT NULL,
  `tel` text NOT NULL,
  `type` int(11) NOT NULL,
  `profile_pic` text NOT NULL,
  `BADGES_idBadge` int(11) NOT NULL,
  PRIMARY KEY (`idUser`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=2 ;

--
-- Dumping data for table `admins`
--

INSERT INTO `admins` (`idUser`, `nom`, `prenom`, `email`, `adresse`, `tel`, `type`, `profile_pic`, `BADGES_idBadge`) VALUES
(1, 'admin', 'admin', 'hedi@mejri.com', 'sdgdgdfgdfg', '20257887', 2, '', 1);

-- --------------------------------------------------------

--
-- Table structure for table `aeroport`
--

CREATE TABLE IF NOT EXISTS `aeroport` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nomaeroport` varchar(20) NOT NULL,
  `nbrpiste` varchar(11) NOT NULL,
  `adresse` varchar(50) NOT NULL,
  `description` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Dumping data for table `aeroport`
--

INSERT INTO `aeroport` (`id`, `nomaeroport`, `nbrpiste`, `adresse`, `description`) VALUES
(1, 'dfghj', '2', 'ugtvbihknj', 'ihyvbknj,k;mlm;:'),
(2, 'fdghjk', '1', '684dfghjkl', 'sxdcvghnj,k;lswxdcgvhbkj,l'),
(3, 'aaaa', '', '', '');

-- --------------------------------------------------------

--
-- Table structure for table `comment`
--

CREATE TABLE IF NOT EXISTS `comment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `forum_id` int(11) NOT NULL,
  `user` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `comment` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `created` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `IDX_9474526C29CCBAD0` (`forum_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=2 ;

--
-- Dumping data for table `comment`
--

INSERT INTO `comment` (`id`, `forum_id`, `user`, `comment`, `created`) VALUES
(1, 1, 'a', 'a', '2015-04-08 13:14:19');

-- --------------------------------------------------------

--
-- Table structure for table `commentaire`
--

CREATE TABLE IF NOT EXISTS `commentaire` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `comment` varchar(50) NOT NULL,
  `user` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `user` (`user`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=23 ;

--
-- Dumping data for table `commentaire`
--

INSERT INTO `commentaire` (`id`, `comment`, `user`) VALUES
(22, 'beaux', 2);

-- --------------------------------------------------------

--
-- Table structure for table `compagnies`
--

CREATE TABLE IF NOT EXISTS `compagnies` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nomcompagnie` varchar(50) NOT NULL,
  `type` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Dumping data for table `compagnies`
--

INSERT INTO `compagnies` (`id`, `nomcompagnie`, `type`) VALUES
(1, 'tfghjk', 'aerienne'),
(2, 'jhklm', 'aerienne');

-- --------------------------------------------------------

--
-- Table structure for table `experiences`
--

CREATE TABLE IF NOT EXISTS `experiences` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `titre` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `destination1` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `destination2` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `destination3` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `destination4` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `destination5` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `desc1` longtext COLLATE utf8_unicode_ci NOT NULL,
  `desc2` longtext COLLATE utf8_unicode_ci NOT NULL,
  `desc3` longtext COLLATE utf8_unicode_ci NOT NULL,
  `desc4` longtext COLLATE utf8_unicode_ci NOT NULL,
  `desc5` longtext COLLATE utf8_unicode_ci NOT NULL,
  `dep1` decimal(10,0) NOT NULL,
  `dep2` decimal(10,0) NOT NULL,
  `dep3` decimal(10,0) NOT NULL,
  `dep4` decimal(10,0) NOT NULL,
  `dep5` decimal(10,0) NOT NULL,
  `chemin` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=13 ;

--
-- Dumping data for table `experiences`
--

INSERT INTO `experiences` (`id`, `titre`, `destination1`, `destination2`, `destination3`, `destination4`, `destination5`, `desc1`, `desc2`, `desc3`, `desc4`, `desc5`, `dep1`, `dep2`, `dep3`, `dep4`, `dep5`, `chemin`) VALUES
(12, 'Test', 'paris', '', '', '', '', 'bon', '', '', '', '', '12', '0', '0', '0', '0', '');

-- --------------------------------------------------------

--
-- Table structure for table `forum`
--

CREATE TABLE IF NOT EXISTS `forum` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sujet` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `titre` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `utilisateur` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `forum` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `creation` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=5 ;

--
-- Dumping data for table `forum`
--

INSERT INTO `forum` (`id`, `sujet`, `titre`, `utilisateur`, `forum`, `creation`) VALUES
(1, 'azer', 'asd', 'asd', 'qd', '2015-04-08 11:35:28'),
(2, 'azer', 'tttt', 'hedi', 'asdrf', '2015-04-08 11:48:25'),
(3, 'azer', 'a', 'a', 'a', '2015-04-08 12:29:03'),
(4, 'c', 'c', 'c', 'c', '2015-04-08 13:31:49');

-- --------------------------------------------------------

--
-- Table structure for table `guides`
--

CREATE TABLE IF NOT EXISTS `guides` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `titre` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `destination1` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `destination2` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `destination3` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `destination4` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `destination5` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `desc1` longtext COLLATE utf8_unicode_ci NOT NULL,
  `desc2` longtext COLLATE utf8_unicode_ci,
  `desc3` longtext COLLATE utf8_unicode_ci,
  `desc4` longtext COLLATE utf8_unicode_ci,
  `desc5` longtext COLLATE utf8_unicode_ci,
  `chemin` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `user_id` int(11) NOT NULL DEFAULT '2',
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=50 ;

--
-- Dumping data for table `guides`
--

INSERT INTO `guides` (`id`, `titre`, `destination1`, `destination2`, `destination3`, `destination4`, `destination5`, `desc1`, `desc2`, `desc3`, `desc4`, `desc5`, `chemin`, `user_id`) VALUES
(49, '12Mai', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 2);

-- --------------------------------------------------------

--
-- Table structure for table `hotels`
--

CREATE TABLE IF NOT EXISTS `hotels` (
  `idHotel` int(11) NOT NULL AUTO_INCREMENT,
  `nomHotel` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `description` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `categorie` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  `Latitude` double NOT NULL,
  `Longitude` double NOT NULL,
  `VILLES_idVille` int(11) DEFAULT NULL,
  `chemin` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`idHotel`),
  KEY `IDX_E402F625AB70066B` (`VILLES_idVille`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=3 ;

--
-- Dumping data for table `hotels`
--

INSERT INTO `hotels` (`idHotel`, `nomHotel`, `description`, `categorie`, `Latitude`, `Longitude`, `VILLES_idVille`, `chemin`) VALUES
(2, 'El Mouradi El Menzah Hotel, Yasmine Hammamet, Nabeul, Tunisie', 'fghf', '4 Etoiles', 36.367596, 10.537353, 3, '/hotel2.jpg');

-- --------------------------------------------------------

--
-- Table structure for table `login`
--

CREATE TABLE IF NOT EXISTS `login` (
  `username` varchar(250) NOT NULL,
  `password` text NOT NULL,
  `idUser` int(11) NOT NULL,
  `valide` int(11) NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `login`
--

INSERT INTO `login` (`username`, `password`, `idUser`, `valide`) VALUES
('hedi@mejri.com', '123456', 1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `medias`
--

CREATE TABLE IF NOT EXISTS `medias` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `chemin` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `size` decimal(10,0) NOT NULL,
  `description` longtext COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=11 ;

--
-- Dumping data for table `medias`
--

INSERT INTO `medias` (`id`, `chemin`, `size`, `description`) VALUES
(6, 'f7e84c90b1704048b90519c15e4ac1ede609ccd9.png', '445', ''),
(7, '46d88d4a06f74b70423dfa60349fb92969fb21b0.jpeg', '323079', ''),
(9, '42123d249efe4508c2d235d282bbd10c9bd587b2.jpeg', '323079', ''),
(10, '877c9bef20149bad9c68fd2eb43f93215171a85c.png', '240766', 'This is a description');

-- --------------------------------------------------------

--
-- Table structure for table `pays`
--

CREATE TABLE IF NOT EXISTS `pays` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nomPays` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `capitale` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `monnaie` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `langue` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `chemin` text COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=5 ;

--
-- Dumping data for table `pays`
--

INSERT INTO `pays` (`id`, `nomPays`, `capitale`, `monnaie`, `langue`, `chemin`) VALUES
(1, 'Tunisie', 'Tunis', 'Dinar', 'Arabe', ''),
(2, 'germany', 'berline', 'dinars', 'allemand', ''),
(3, 'France', 'Paris', 'Euro', 'français', '');

-- --------------------------------------------------------

--
-- Table structure for table `rating`
--

CREATE TABLE IF NOT EXISTS `rating` (
  `id` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `num_votes` int(11) NOT NULL,
  `rate` decimal(4,1) NOT NULL,
  `security_role` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `permalink` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `created_at` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `rating`
--

INSERT INTO `rating` (`id`, `num_votes`, `rate`, `security_role`, `permalink`, `created_at`) VALUES
('1', 0, '0.0', NULL, NULL, '2015-04-08 14:13:08'),
('1_HOTEL', 0, '0.0', 'IS_AUTHENTICATED_FULLY', 'http://localhost:8000/app_dev.php/hotels/1/show', '2015-05-12 09:07:57'),
('10', 0, '0.0', NULL, NULL, '2015-05-11 11:31:19'),
('11', 0, '0.0', NULL, NULL, '2015-04-08 14:13:09'),
('12', 0, '0.0', NULL, NULL, '2015-04-08 14:13:10'),
('2', 0, '0.0', NULL, NULL, '2015-04-29 22:41:28'),
('2_HOTEL', 0, '0.0', 'IS_AUTHENTICATED_FULLY', 'http://localhost:8000/app_dev.php/hotels/2/show', '2015-05-12 09:28:26'),
('20', 0, '0.0', NULL, NULL, '2015-05-11 11:31:19'),
('3', 1, '3.0', 'IS_AUTHENTICATED_FULLY', 'http://localhost:8000/app_dev.php/hotels/3/show', '2015-04-08 12:51:19'),
('30', 0, '0.0', NULL, NULL, '2015-05-11 11:31:19'),
('4', 2, '2.5', 'IS_AUTHENTICATED_FULLY', 'http://localhost:8000/app_dev.php/hotels/4/show', '2015-04-08 12:48:43'),
('40', 0, '0.0', NULL, NULL, '2015-05-11 11:31:19'),
('5', 0, '0.0', NULL, NULL, '2015-04-08 14:13:09'),
('6', 0, '0.0', NULL, NULL, '2015-04-08 14:13:09'),
('7', 0, '0.0', NULL, NULL, '2015-04-08 14:13:09');

-- --------------------------------------------------------

--
-- Table structure for table `restaurants`
--

CREATE TABLE IF NOT EXISTS `restaurants` (
  `idRestaurant` int(11) NOT NULL AUTO_INCREMENT,
  `nomRestaurant` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `categorie` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  `description` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `Latitude` double NOT NULL,
  `Longitude` double NOT NULL,
  `chemin` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `VILLES_idVille` int(11) DEFAULT NULL,
  PRIMARY KEY (`idRestaurant`),
  KEY `IDX_AD837724AB70066B` (`VILLES_idVille`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=9 ;

--
-- Dumping data for table `restaurants`
--

INSERT INTO `restaurants` (`idRestaurant`, `nomRestaurant`, `categorie`, `description`, `Latitude`, `Longitude`, `chemin`, `VILLES_idVille`) VALUES
(1, '3F', '2 fourchettes', 'Specialite Fruits de mer', 36.359432, 10.532072, '/3f.jpg', 2),
(2, 'Bistro', '3 fourchettes', 'specialites qui varient de la cuisine internationale a la cuisine\r\nasiatique a travers ses delicieux nouilles sautes et rouleaux de\r\nprintemps', 36.8188232, 10.2012616, '/bistro.jpg', 2),
(3, 'Dar el Marsa', '5 fourchettes', 'Enjoy a satisfying meal at a restaurant serving guests of Dar El Marsa', 36.8842526, 10.3321366, '/darElmarsa.jpg', 2),
(4, 'Dar Zarouk Sidi Bou Said', '4 fourchettes', 'Dar zarrouk restaurant gastronomique et cuisine tunisienne et mediteranieenne ', 36.8705511, 10.3495495, '/darZarouk.jpg', 2),
(5, 'La Falaise', '3 fourchettes', 'je recommande ce restaurant cadre magnifique la nourriture est super', 36.8798245, 10.3421574, '/LaFalaise.jpg', 2),
(7, 'Heaven', '3 fourchettes', 'Un restaurant familial tres accueillant le personnel est tres souriant et chaleureux les plats sont vraiment tres bons et copieux', 36.8535527, 10.1327292, '/Heaven.jpg', 2),
(8, 'Hambara', '4 fourchettes', 'Un monde de perfection ', 36.3974253, 10.6192604, '/hambara.jpg', 2);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE IF NOT EXISTS `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `username_canonical` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `email` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `email_canonical` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `enabled` tinyint(1) NOT NULL,
  `salt` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `last_login` datetime DEFAULT NULL,
  `locked` tinyint(1) NOT NULL,
  `expired` tinyint(1) NOT NULL,
  `expires_at` datetime DEFAULT NULL,
  `confirmation_token` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `password_requested_at` datetime DEFAULT NULL,
  `roles` longtext COLLATE utf8_unicode_ci NOT NULL COMMENT '(DC2Type:array)',
  `credentials_expired` tinyint(1) NOT NULL,
  `credentials_expire_at` datetime DEFAULT NULL,
  `nom` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `prenom` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `pays` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UNIQ_1483A5E992FC23A8` (`username_canonical`),
  UNIQUE KEY `UNIQ_1483A5E9A0D96FBF` (`email_canonical`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=8 ;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `username`, `username_canonical`, `email`, `email_canonical`, `enabled`, `salt`, `password`, `last_login`, `locked`, `expired`, `expires_at`, `confirmation_token`, `password_requested_at`, `roles`, `credentials_expired`, `credentials_expire_at`, `nom`, `prenom`, `pays`) VALUES
(1, 'hedi', 'aaaa', 'aaaa@aa.a', 'aaaa@aa.a', 1, 'tl7sue3glpwcw80ksscgcoo80sc0s48', 'mejri', NULL, 0, 0, NULL, NULL, NULL, 'a:0:{}', 0, NULL, 'aaaa', 'aaaa', 'Allemagne'),
(2, 'alpha', 'alpha', 'aaa@a.aa', 'aaa@a.aa', 1, 'rj6yt8x2mr4ogkcoosw8s804k0wcgw8', 'beta', '2015-04-08 15:13:55', 0, 0, NULL, NULL, NULL, 'a:1:{i:0;s:10:"ROLE_ADMIN";}', 0, NULL, 'aa', 'aaa', 'France'),
(5, 'abir', '', '', '', 0, '', 'bokri', NULL, 0, 0, NULL, NULL, NULL, '', 0, NULL, '', '', ''),
(6, 'admin', 'admin', 'admin@admin.com', 'admin@admin.com', 1, '6p5ogqu2h3c4ko4oockg0oc08osg8go', 'h0D5FcisTRNngDsFdtFdhH97+Pgr06v+npfWnj2Jx9pgCj4kgHvT7uwUW6vIgqw1WsihJuenOkpTp9PD3my2Sg==', '2015-05-12 09:09:35', 0, 0, NULL, NULL, NULL, 'a:1:{i:0;s:10:"ROLE_ADMIN";}', 0, NULL, 'Hedi', 'Mejri', 'USA'),
(7, 'hamza', 'hamza', 'hamza@gmail.com', 'hamza@gmail.com', 1, 'k52p8x000moo4kkc0kw0o8gkswckowk', '4bGrtIWw4pxuRtw+JLE5HcqoebYF/LlGDzXzxq6oXNvEsrE9Mun90hMp/xL/uRW7SQRxWHuacfAp/rMgBdcJCg==', NULL, 0, 0, NULL, NULL, NULL, 'a:0:{}', 0, NULL, 'Hamza', 'Hamza', 'Tunisie');

-- --------------------------------------------------------

--
-- Table structure for table `villes`
--

CREATE TABLE IF NOT EXISTS `villes` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nomVille` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `Latitude` double NOT NULL,
  `Longitude` double NOT NULL,
  `chemin` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `idPays_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `IDX_1E8C9AEEB65D29B6` (`idPays_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=7 ;

--
-- Dumping data for table `villes`
--

INSERT INTO `villes` (`id`, `nomVille`, `Latitude`, `Longitude`, `chemin`, `idPays_id`) VALUES
(1, 'Zurich', 47.3824, 8.54017, '', 2),
(2, 'Marrakech', 31.62692, -7.98474, '', 1),
(3, 'Tunis', 36.81113, 10.18458, '', 1),
(4, 'London', 51.49995, -0.15979, '', 1),
(5, 'Dubai', 25.08007, 55.13612, '', 2),
(6, 'Rome', 41.89683, 12.49548, '', 3);

-- --------------------------------------------------------

--
-- Table structure for table `vote`
--

CREATE TABLE IF NOT EXISTS `vote` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `voter_id` int(11) DEFAULT NULL,
  `value` int(11) NOT NULL,
  `created_at` datetime NOT NULL,
  `rating_id` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `IDX_5A108564EBB4B8AD` (`voter_id`),
  KEY `IDX_5A108564A32EFC6` (`rating_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=14 ;

--
-- Dumping data for table `vote`
--

INSERT INTO `vote` (`id`, `voter_id`, `value`, `created_at`, `rating_id`) VALUES
(11, 1, 4, '2015-04-08 12:48:55', '4'),
(12, 2, 1, '2015-04-08 12:49:59', '4'),
(13, 1, 3, '2015-04-08 13:35:09', '3');

--
-- Constraints for dumped tables
--

--
-- Constraints for table `comment`
--
ALTER TABLE `comment`
  ADD CONSTRAINT `FK_9474526C29CCBAD0` FOREIGN KEY (`forum_id`) REFERENCES `forum` (`id`);

--
-- Constraints for table `commentaire`
--
ALTER TABLE `commentaire`
  ADD CONSTRAINT `commentaire_ibfk_1` FOREIGN KEY (`user`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `guides`
--
ALTER TABLE `guides`
  ADD CONSTRAINT `FK_USERS_GUIDES` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);

--
-- Constraints for table `hotels`
--
ALTER TABLE `hotels`
  ADD CONSTRAINT `FK_E402F625AB70066B` FOREIGN KEY (`VILLES_idVille`) REFERENCES `villes` (`id`);

--
-- Constraints for table `restaurants`
--
ALTER TABLE `restaurants`
  ADD CONSTRAINT `FK_AD837724AB70066B` FOREIGN KEY (`VILLES_idVille`) REFERENCES `villes` (`id`);

--
-- Constraints for table `villes`
--
ALTER TABLE `villes`
  ADD CONSTRAINT `FK_1E8C9AEEB65D29B6` FOREIGN KEY (`idPays_id`) REFERENCES `pays` (`id`);

--
-- Constraints for table `vote`
--
ALTER TABLE `vote`
  ADD CONSTRAINT `FK_5A108564A32EFC6` FOREIGN KEY (`rating_id`) REFERENCES `rating` (`id`),
  ADD CONSTRAINT `FK_5A108564EBB4B8AD` FOREIGN KEY (`voter_id`) REFERENCES `users` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
