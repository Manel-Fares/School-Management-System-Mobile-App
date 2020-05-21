-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le :  jeu. 21 mai 2020 à 00:05
-- Version du serveur :  5.7.19
-- Version de PHP :  5.6.31

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `pd2`
--

-- --------------------------------------------------------

--
-- Structure de la table `absence`
--

DROP TABLE IF EXISTS `absence`;
CREATE TABLE IF NOT EXISTS `absence` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_matiere` int(11) DEFAULT NULL,
  `id_user` int(11) DEFAULT NULL,
  `Date` date NOT NULL,
  `TimeDeb` time NOT NULL,
  `TimeFin` time NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_matier` (`id_matiere`),
  KEY `id_user` (`id_user`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `absence`
--

INSERT INTO `absence` (`id`, `id_matiere`, `id_user`, `Date`, `TimeDeb`, `TimeFin`) VALUES
(11, 7, 1, '2020-05-19', '06:00:00', '07:00:00'),
(12, 7, 1, '2020-05-18', '00:00:00', '00:00:00'),
(13, 7, 1, '2020-05-18', '04:35:00', '00:00:00'),
(18, 2, 1, '2020-05-19', '02:20:00', '04:00:00'),
(19, 6, 1, '2020-05-19', '08:00:00', '09:00:00');

-- --------------------------------------------------------

--
-- Structure de la table `books`
--

DROP TABLE IF EXISTS `books`;
CREATE TABLE IF NOT EXISTS `books` (
  `idBook` int(11) NOT NULL AUTO_INCREMENT,
  `titreBook` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `nbrLike` int(11) DEFAULT NULL,
  `descriptionBook` varchar(250) COLLATE utf8_unicode_ci DEFAULT NULL,
  `etatBook` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `picBook` varchar(250) COLLATE utf8_unicode_ci DEFAULT NULL,
  `categorieBook` varchar(250) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`idBook`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `books`
--

INSERT INTO `books` (`idBook`, `titreBook`, `nbrLike`, `descriptionBook`, `etatBook`, `picBook`, `categorieBook`) VALUES
(1, 'book1', 3, 'c++ programming language', '1', 'c++.jpg', 'info'),
(13, 'C', 3, 'c programming language', 'Disponible', 'book1.jpg', 'C++'),
(17, 'HTML', 2, '<p>html</p>', 'Disponible', 'c++.jpg', 'html'),
(18, 'java', 1, 'java', 'Disponible', 'book2.jpg', 'java'),
(19, 'book', 0, 'c++ intermediate level', '1', 'c++.jpg', 'book');

-- --------------------------------------------------------

--
-- Structure de la table `calendarannuel`
--

DROP TABLE IF EXISTS `calendarannuel`;
CREATE TABLE IF NOT EXISTS `calendarannuel` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `subject` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `term` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `DateC` date NOT NULL,
  `startdate` date NOT NULL,
  `enddate` date NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table `classe`
--

DROP TABLE IF EXISTS `classe`;
CREATE TABLE IF NOT EXISTS `classe` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `Niveau` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `Spec` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `Nbr_Etudiant` int(11) NOT NULL,
  `Description` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`Id`),
  UNIQUE KEY `Name` (`Name`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `classe`
--

INSERT INTO `classe` (`Id`, `Name`, `Niveau`, `Spec`, `Nbr_Etudiant`, `Description`) VALUES
(1, '3A1', '3', 'A', 27, '3eme info'),
(2, '3A2', '3', 'info', 27, '3eme info');

-- --------------------------------------------------------

--
-- Structure de la table `classeenseignantmatiere`
--

DROP TABLE IF EXISTS `classeenseignantmatiere`;
CREATE TABLE IF NOT EXISTS `classeenseignantmatiere` (
  `id_user` int(11) DEFAULT NULL,
  `id_matiere` int(11) DEFAULT NULL,
  `id_class` int(11) DEFAULT NULL,
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`Id`),
  KEY `FK_classqqs` (`id_class`),
  KEY `FK_USER` (`id_user`),
  KEY `FK_Matiere` (`id_matiere`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `classeenseignantmatiere`
--

INSERT INTO `classeenseignantmatiere` (`id_user`, `id_matiere`, `id_class`, `Id`) VALUES
(2, 2, 2, 1),
(2, 3, 1, 2);

-- --------------------------------------------------------

--
-- Structure de la table `club`
--

DROP TABLE IF EXISTS `club`;
CREATE TABLE IF NOT EXISTS `club` (
  `idClub` int(11) NOT NULL AUTO_INCREMENT,
  `nomClub` varchar(250) COLLATE utf8_unicode_ci NOT NULL,
  `domaine` varchar(250) COLLATE utf8_unicode_ci NOT NULL,
  `image` text COLLATE utf8_unicode_ci NOT NULL,
  `idResponsable` int(11) DEFAULT NULL,
  PRIMARY KEY (`idClub`),
  KEY `qsdqsd` (`idResponsable`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `club`
--

INSERT INTO `club` (`idClub`, `nomClub`, `domaine`, `image`, `idResponsable`) VALUES
(1, 'Enactus', 'Entrepreneuriat', 'logo.jpg', 5),
(3, 'IEEE', 'computer and electronics industry\r\n', 'ieee.jpg', 5);

-- --------------------------------------------------------

--
-- Structure de la table `comment`
--

DROP TABLE IF EXISTS `comment`;
CREATE TABLE IF NOT EXISTS `comment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `thread_id` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `author` int(11) DEFAULT NULL,
  `body` longtext COLLATE utf8_unicode_ci NOT NULL,
  `ancestors` varchar(1024) COLLATE utf8_unicode_ci NOT NULL,
  `depth` int(11) NOT NULL,
  `created_at` datetime NOT NULL,
  `state` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `IDX_9474526CE2904019` (`thread_id`),
  KEY `IDX_9474526CBDAFD8C8` (`author`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `comment`
--

INSERT INTO `comment` (`id`, `thread_id`, `author`, `body`, `ancestors`, `depth`, `created_at`, `state`) VALUES
(1, '1', 1, 'is this book available ?', '', 0, '2020-04-23 19:41:50', 0),
(2, '19', 1, 'd', '', 0, '2020-05-18 02:36:39', 0),
(3, '19', 1, 'dsdfsd', '', 0, '2020-05-18 02:36:42', 0),
(5, '17', 1, 'comment 1', '', 0, '2020-05-19 00:53:25', 0),
(6, '1', 1, 'comment 123', '', 0, '2020-05-19 01:08:06', 0),
(7, '1', 5, 'livre dispo', '', 0, '2020-05-19 01:22:18', 0),
(8, '1', 5, 'comentaire !!!!', '', 0, '2020-05-19 01:41:55', 0),
(9, '1', 5, 'livre interessant !', '', 0, '2020-05-19 02:04:01', 0);

-- --------------------------------------------------------

--
-- Structure de la table `demandeevenement`
--

DROP TABLE IF EXISTS `demandeevenement`;
CREATE TABLE IF NOT EXISTS `demandeevenement` (
  `idDemandeEvenement` int(11) NOT NULL AUTO_INCREMENT,
  `Description` text COLLATE utf8_unicode_ci NOT NULL,
  `DateDebut` date NOT NULL,
  `DateFin` date NOT NULL,
  `Etat` varchar(250) COLLATE utf8_unicode_ci NOT NULL,
  `Budget` double NOT NULL,
  `image` varchar(250) COLLATE utf8_unicode_ci NOT NULL,
  `idClub` int(11) DEFAULT NULL,
  PRIMARY KEY (`idDemandeEvenement`),
  KEY `qsdqsdqd` (`idClub`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table `emplois`
--

DROP TABLE IF EXISTS `emplois`;
CREATE TABLE IF NOT EXISTS `emplois` (
  `nameclas` int(11) DEFAULT NULL,
  `IdEmplois` int(11) NOT NULL AUTO_INCREMENT,
  `Date` date NOT NULL,
  `Heure` time NOT NULL,
  `Source` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`IdEmplois`),
  KEY `IDX_461274B9B58EDEC1` (`nameclas`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `emplois`
--

INSERT INTO `emplois` (`nameclas`, `IdEmplois`, `Date`, `Heure`, `Source`) VALUES
(1, 1, '2020-05-20', '06:00:00', 'emplois.pdf');

-- --------------------------------------------------------

--
-- Structure de la table `enseigner`
--

DROP TABLE IF EXISTS `enseigner`;
CREATE TABLE IF NOT EXISTS `enseigner` (
  `idMatiere` int(11) NOT NULL,
  `idEnseignant` int(11) NOT NULL,
  PRIMARY KEY (`idMatiere`,`idEnseignant`),
  KEY `FK_enseig` (`idEnseignant`),
  KEY `FK_Mat` (`idMatiere`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `enseigner`
--

INSERT INTO `enseigner` (`idMatiere`, `idEnseignant`) VALUES
(3, 2),
(4, 2),
(7, 2);

-- --------------------------------------------------------

--
-- Structure de la table `evenement`
--

DROP TABLE IF EXISTS `evenement`;
CREATE TABLE IF NOT EXISTS `evenement` (
  `idEvenement` int(11) NOT NULL AUTO_INCREMENT,
  `dateDebut` date NOT NULL,
  `dateFin` date NOT NULL,
  `image` text COLLATE utf8_unicode_ci NOT NULL,
  `idClub` int(11) DEFAULT NULL,
  PRIMARY KEY (`idEvenement`),
  KEY `qsdqsdqsdqsd` (`idClub`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `evenement`
--

INSERT INTO `evenement` (`idEvenement`, `dateDebut`, `dateFin`, `image`, `idClub`) VALUES
(1, '2020-05-01', '2020-05-28', 'event.jpg', 1),
(2, '2020-05-20', '2020-06-23', 'project2.jpeg', 3);

-- --------------------------------------------------------

--
-- Structure de la table `likes`
--

DROP TABLE IF EXISTS `likes`;
CREATE TABLE IF NOT EXISTS `likes` (
  `idbook` int(11) DEFAULT NULL,
  `idetd` int(11) DEFAULT NULL,
  `idLike` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`idLike`),
  KEY `FK_etudiant_like` (`idetd`),
  KEY `FK_Book_like` (`idbook`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `likes`
--

INSERT INTO `likes` (`idbook`, `idetd`, `idLike`) VALUES
(1, 1, 3),
(13, 1, 5),
(1, 5, 7),
(13, 5, 8);

-- --------------------------------------------------------

--
-- Structure de la table `matier`
--

DROP TABLE IF EXISTS `matier`;
CREATE TABLE IF NOT EXISTS `matier` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `coef` double NOT NULL,
  `responsable` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `responsable` (`responsable`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `matier`
--

INSERT INTO `matier` (`id`, `nom`, `coef`, `responsable`) VALUES
(1, 'English', 3, 2),
(2, 'Unix', 1.5, 2),
(3, 'Algebra', 2, 2),
(4, 'prog c++', 2, 2),
(6, 'Management', 1.5, 2),
(7, 'Oracle DB', 2, 2);

-- --------------------------------------------------------

--
-- Structure de la table `note`
--

DROP TABLE IF EXISTS `note`;
CREATE TABLE IF NOT EXISTS `note` (
  `dateNote` date NOT NULL,
  `noteCC` double DEFAULT NULL,
  `noteDS` double DEFAULT NULL,
  `noteExam` double DEFAULT NULL,
  `Moyenne` double DEFAULT NULL,
  `idEtudiant` int(11) NOT NULL,
  `idMatiere` int(11) NOT NULL,
  `idEnseignant` int(11) DEFAULT NULL,
  PRIMARY KEY (`idEtudiant`,`idMatiere`),
  KEY `FK_ens` (`idEnseignant`),
  KEY `FK_USER` (`idEtudiant`),
  KEY `FK_Matiere` (`idMatiere`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `note`
--

INSERT INTO `note` (`dateNote`, `noteCC`, `noteDS`, `noteExam`, `Moyenne`, `idEtudiant`, `idMatiere`, `idEnseignant`) VALUES
('2020-05-19', 14, 12, 13, 13, 1, 3, 2),
('2020-05-17', 18, 12.75, 12.25, 13.55, 5, 2, 2),
('2020-05-17', 12, 2, 5.25, 5.625, 5, 7, 2),
('2020-05-19', 12, 13, 14, 13.3, 8, 2, 2);

-- --------------------------------------------------------

--
-- Structure de la table `notification`
--

DROP TABLE IF EXISTS `notification`;
CREATE TABLE IF NOT EXISTS `notification` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `description` longtext COLLATE utf8_unicode_ci NOT NULL,
  `icon` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `route` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `route_parameters` longtext COLLATE utf8_unicode_ci COMMENT '(DC2Type:array)',
  `notification_date` datetime NOT NULL,
  `seen` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `notification`
--

INSERT INTO `notification` (`id`, `title`, `description`, `icon`, `route`, `route_parameters`, `notification_date`, `seen`) VALUES
(1, 'New Demande Evenement', 'info', NULL, 'demandeevenement_show', 'a:1:{s:18:\"iddemandeevenement\";i:1;}', '2020-05-18 00:45:32', 0),
(2, 'New Demande Evenement', 'info', NULL, 'demandeevenement_show', 'a:1:{s:18:\"iddemandeevenement\";i:1;}', '2020-05-18 00:45:32', 0),
(3, 'New Demande Evenement', 'kj', NULL, 'demandeevenement_show', 'a:1:{s:18:\"iddemandeevenement\";i:2;}', '2020-05-18 00:47:23', 0),
(4, 'New Demande Evenement', 'kj', NULL, 'demandeevenement_show', 'a:1:{s:18:\"iddemandeevenement\";i:2;}', '2020-05-18 00:47:23', 0),
(5, 'New Demande Evenement', 'project for enactus', NULL, 'demandeevenement_show', 'a:1:{s:18:\"iddemandeevenement\";i:3;}', '2020-05-18 11:05:38', 0),
(6, 'New Demande Evenement', 'project for enactus', NULL, 'demandeevenement_show', 'a:1:{s:18:\"iddemandeevenement\";i:3;}', '2020-05-18 11:05:38', 0),
(7, 'New Demande Evenement', 'project', NULL, 'demandeevenement_show', 'a:1:{s:18:\"iddemandeevenement\";i:4;}', '2020-05-18 11:11:58', 0),
(8, 'New Demande Evenement', 'project', NULL, 'demandeevenement_show', 'a:1:{s:18:\"iddemandeevenement\";i:4;}', '2020-05-18 11:11:58', 0);

-- --------------------------------------------------------

--
-- Structure de la table `participation`
--

DROP TABLE IF EXISTS `participation`;
CREATE TABLE IF NOT EXISTS `participation` (
  `idparticipation` int(11) NOT NULL AUTO_INCREMENT,
  `idevenement` int(11) DEFAULT NULL,
  `iduser` int(11) DEFAULT NULL,
  PRIMARY KEY (`idparticipation`),
  KEY `aszdzd` (`iduser`),
  KEY `ploikfdxzs` (`idevenement`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `participation`
--

INSERT INTO `participation` (`idparticipation`, `idevenement`, `iduser`) VALUES
(13, 2, 1);

-- --------------------------------------------------------

--
-- Structure de la table `question`
--

DROP TABLE IF EXISTS `question`;
CREATE TABLE IF NOT EXISTS `question` (
  `id_question` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `tag_id` int(11) DEFAULT NULL,
  `body` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `title` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `open` tinyint(1) NOT NULL,
  `created_at` datetime NOT NULL,
  PRIMARY KEY (`id_question`),
  KEY `user` (`user_id`),
  KEY `tag` (`tag_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table `rate`
--

DROP TABLE IF EXISTS `rate`;
CREATE TABLE IF NOT EXISTS `rate` (
  `idclub` int(11) DEFAULT NULL,
  `iduser` int(11) DEFAULT NULL,
  `idRating` int(11) NOT NULL AUTO_INCREMENT,
  `rating` int(11) NOT NULL,
  PRIMARY KEY (`idRating`),
  KEY `resdcfs` (`iduser`),
  KEY `ploiktgvrfcedxzs` (`idclub`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `rate`
--

INSERT INTO `rate` (`idclub`, `iduser`, `idRating`, `rating`) VALUES
(1, 5, 1, 4),
(1, 1, 2, 4),
(3, 5, 3, 0);

-- --------------------------------------------------------

--
-- Structure de la table `rating`
--

DROP TABLE IF EXISTS `rating`;
CREATE TABLE IF NOT EXISTS `rating` (
  `idrating` int(11) NOT NULL AUTO_INCREMENT,
  `iduser` int(11) NOT NULL,
  `idClub` int(11) NOT NULL,
  `rating` double NOT NULL,
  PRIMARY KEY (`idrating`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table `reclamation`
--

DROP TABLE IF EXISTS `reclamation`;
CREATE TABLE IF NOT EXISTS `reclamation` (
  `idReclamation` int(11) NOT NULL AUTO_INCREMENT,
  `sujetReclamation` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `descriptionReclamation` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `statutReclamation` varchar(11) COLLATE utf8_unicode_ci DEFAULT NULL,
  `dateCreation` date DEFAULT NULL,
  `IdEtd` int(11) DEFAULT NULL,
  PRIMARY KEY (`idReclamation`),
  KEY `FK_etudiant` (`IdEtd`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `reclamation`
--

INSERT INTO `reclamation` (`idReclamation`, `sujetReclamation`, `descriptionReclamation`, `statutReclamation`, `dateCreation`, `IdEtd`) VALUES
(1, 'Enseignant', 'Conflict', 'Treated', '2020-05-18', 1),
(2, 'Note', 'Wrong Grade', 'Pending', '2020-05-18', 1),
(3, 'Note', 'probleme note', 'Pending', '2020-05-19', 1),
(4, 'Note', 'note algebre', 'Pending', '2020-05-19', 1),
(5, 'Note', 'moyenne', 'Pending', '2020-05-19', 5),
(6, 'Note', 'wrong note in algebra', 'Pending', '2020-05-19', 5),
(7, 'Enseignant', 'absence seance 3', 'Pending', '2020-05-19', 1);

-- --------------------------------------------------------

--
-- Structure de la table `reponse`
--

DROP TABLE IF EXISTS `reponse`;
CREATE TABLE IF NOT EXISTS `reponse` (
  `id_reponse` int(11) NOT NULL AUTO_INCREMENT,
  `id_question` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `body` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `vote_reponse` int(11) DEFAULT NULL,
  `valid` tinyint(1) NOT NULL DEFAULT '0',
  `created_at` datetime NOT NULL,
  PRIMARY KEY (`id_reponse`),
  KEY `IDX_5FB6DEC7A76ED395` (`user_id`),
  KEY `id_question` (`id_question`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table `reservationbook`
--

DROP TABLE IF EXISTS `reservationbook`;
CREATE TABLE IF NOT EXISTS `reservationbook` (
  `idReservation` int(11) NOT NULL AUTO_INCREMENT,
  `dateD` date DEFAULT NULL,
  `dateF` date DEFAULT NULL,
  `idEtd` int(11) DEFAULT NULL,
  `idBook` int(11) DEFAULT NULL,
  PRIMARY KEY (`idReservation`),
  KEY `Fk_etudiant_Reservation` (`idEtd`),
  KEY `FK_Book` (`idBook`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `reservationbook`
--

INSERT INTO `reservationbook` (`idReservation`, `dateD`, `dateF`, `idEtd`, `idBook`) VALUES
(1, '2020-05-19', '2020-05-19', 1, 1),
(2, '2020-05-19', '2020-05-19', 1, 13),
(3, '2020-05-19', '2020-05-19', 1, 1),
(4, '2020-05-19', '2020-05-19', 5, 18),
(5, '2020-05-19', '2020-05-19', 5, 1),
(6, '2020-05-19', '2020-05-19', 5, 13),
(7, '2020-05-19', '2020-05-19', 5, 13);

-- --------------------------------------------------------

--
-- Structure de la table `resultat`
--

DROP TABLE IF EXISTS `resultat`;
CREATE TABLE IF NOT EXISTS `resultat` (
  `idEtudiant` int(11) NOT NULL,
  `dateResultat` date NOT NULL,
  `resultat` double DEFAULT NULL,
  PRIMARY KEY (`idEtudiant`),
  KEY `FK_USER` (`idEtudiant`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `resultat`
--

INSERT INTO `resultat` (`idEtudiant`, `dateResultat`, `resultat`) VALUES
(1, '2020-05-19', 13),
(5, '2020-05-19', 9.0214285714286),
(8, '2020-05-19', 0);

-- --------------------------------------------------------

--
-- Structure de la table `tag`
--

DROP TABLE IF EXISTS `tag`;
CREATE TABLE IF NOT EXISTS `tag` (
  `id_tag` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `description` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id_tag`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table `thread`
--

DROP TABLE IF EXISTS `thread`;
CREATE TABLE IF NOT EXISTS `thread` (
  `id` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `permalink` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `is_commentable` tinyint(1) NOT NULL,
  `num_comments` int(11) NOT NULL,
  `last_comment_at` datetime DEFAULT NULL,
  `idBook` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `IDX_31204C83B818FDAF` (`idBook`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `thread`
--

INSERT INTO `thread` (`id`, `permalink`, `is_commentable`, `num_comments`, `last_comment_at`, `idBook`) VALUES
('1', 'http://localhost/projet/schoolMgt/web/app_dev.php/books/1/show', 1, 1, '2020-04-23 19:41:50', NULL),
('17', 'http://localhost/projet/schoolMgt/web/app_dev.php/books/AllCommentJson/17', 1, 0, NULL, NULL),
('19', 'http://localhost/projet/schoolMgt/web/app_dev.php/books/AllCommentJson/19', 1, 0, NULL, NULL);

-- --------------------------------------------------------

--
-- Structure de la table `users`
--

DROP TABLE IF EXISTS `users`;
CREATE TABLE IF NOT EXISTS `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(180) COLLATE utf8_unicode_ci NOT NULL,
  `username_canonical` varchar(180) COLLATE utf8_unicode_ci NOT NULL,
  `email` varchar(180) COLLATE utf8_unicode_ci NOT NULL,
  `email_canonical` varchar(180) COLLATE utf8_unicode_ci NOT NULL,
  `enabled` tinyint(1) NOT NULL,
  `salt` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `password` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `last_login` datetime DEFAULT NULL,
  `confirmation_token` varchar(180) COLLATE utf8_unicode_ci DEFAULT NULL,
  `password_requested_at` datetime DEFAULT NULL,
  `roles` longtext COLLATE utf8_unicode_ci NOT NULL COMMENT '(DC2Type:array)',
  `cinUser` int(11) DEFAULT NULL,
  `nomUser` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `prenomUser` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `DateNaissanceUser` date DEFAULT NULL,
  `sexeUser` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL,
  `emailUser` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `adresseUser` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `numTelUser` int(11) DEFAULT NULL,
  `roleUser` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `dateEmbaucheUser` date DEFAULT NULL,
  `motDePasseUser` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `inscriptionEtd` date DEFAULT NULL,
  `nomResponsableEtd` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `specialiteEtd` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `statutUser` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `salaireUser` double DEFAULT NULL,
  `domaineUser` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `idParent` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL,
  `picUser` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `classeEtd` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UNIQ_1483A5E992FC23A8` (`username_canonical`),
  UNIQUE KEY `UNIQ_1483A5E9A0D96FBF` (`email_canonical`),
  UNIQUE KEY `UNIQ_1483A5E9C05FB297` (`confirmation_token`),
  KEY `IDX_1483A5E96183EFB3` (`classeEtd`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `users`
--

INSERT INTO `users` (`id`, `username`, `username_canonical`, `email`, `email_canonical`, `enabled`, `salt`, `password`, `last_login`, `confirmation_token`, `password_requested_at`, `roles`, `cinUser`, `nomUser`, `prenomUser`, `DateNaissanceUser`, `sexeUser`, `emailUser`, `adresseUser`, `numTelUser`, `roleUser`, `dateEmbaucheUser`, `motDePasseUser`, `inscriptionEtd`, `nomResponsableEtd`, `specialiteEtd`, `statutUser`, `salaireUser`, `domaineUser`, `idParent`, `picUser`, `classeEtd`) VALUES
(1, 'manel', 'manel', 'manelfares5@gmail.com', 'manelfares5@gmail.com', 1, NULL, 'manel', '2020-05-16 05:53:37', NULL, NULL, 'a:1:{i:0;s:13:\"ROLE_ETUDIANT\";}', 11395784, 'Fares', 'Manel', '1997-08-14', 'femme', NULL, NULL, 52478690, 'Etudiant', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1b327e8ec92f326a602d39e951cc0e3f.jpeg', 1),
(2, 'lotfi', 'lotfi', 'lotfi@gmail.com', 'lotfi@gmail.com', 1, NULL, 'manel', '2020-05-11 00:46:09', NULL, NULL, 'a:1:{i:0;s:15:\"ROLE_ENSEIGNANT\";}', 11395711, 'Hachanni', 'lotfi', '1989-11-08', 'homme', NULL, NULL, 21985485, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'd8b2ca425020caabf657aa47fbca493a.jpeg', NULL),
(3, 'yassine', 'yassine', 'yassine@gmail.com', 'yassine@gmail.com', 1, NULL, 'manel', '2020-05-17 22:26:55', NULL, NULL, 'a:1:{i:0;s:14:\"ROLE_PERSONNEL\";}', 14720305, 'Rahmounni', 'yassine', '1987-07-16', 'homme', NULL, NULL, 59213406, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'f12cbdae31a6daea49cc2d9a72ce2609.jpeg', 1),
(4, 'admin', 'admin', 'admin@gmail.com', 'admin@gmail.com', 1, NULL, 'manel', '2020-05-18 05:42:42', NULL, NULL, 'a:1:{i:0;s:19:\"ROLE_ADMINISTRATEUR\";}', 14797317, 'ADMIN', 'admin', '1985-05-16', 'homme', NULL, NULL, 99213407, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'f9e83383e31d8d92f622adb29f07925a.png', NULL),
(5, 'omar', 'omar', 'omar@gmail.com', 'omar@gmail.com', 1, NULL, 'omar', '2020-05-18 03:14:06', NULL, NULL, 'a:1:{i:0;s:13:\"ROLE_ETUDIANT\";}', 14725014, 'Jemai', 'Omar', '1997-10-16', 'homme', NULL, NULL, 21458796, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'b4221601f390229cab4ad1ebd674adaa.png', 2),
(8, 'melek', 'melek', 'melek@gmail.com', 'melek@gmail.com', 1, NULL, 'malek', '2020-04-27 23:51:35', NULL, NULL, 'a:1:{i:0;s:13:\"ROLE_ETUDIANT\";}', 14523612, 'Mezni', 'melek', '1996-08-14', 'Homme', NULL, NULL, 54782130, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 2),
(11, 'souha', 'souha', 'souha@gmail.com', 'souha@gmail.com', 1, 'LhNx7z9eGfDYIhJY8595JIbjBgbzFngvTQSKEzKBeCo', 'souha{LhNx7z9eGfDYIhJY8595JIbjBgbzFngvTQSKEzKBeCo}', NULL, NULL, NULL, 'a:1:{i:0;s:14:\"ROLE_PERSONNEL\";}', 11254789, 'souha', 'gharssalah', NULL, '  Femme', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Structure de la table `wishliste`
--

DROP TABLE IF EXISTS `wishliste`;
CREATE TABLE IF NOT EXISTS `wishliste` (
  `idList` int(11) NOT NULL AUTO_INCREMENT,
  `idEtd` int(11) DEFAULT NULL,
  `idBook` int(11) DEFAULT NULL,
  PRIMARY KEY (`idList`),
  KEY `FK_etudiant_wishliste` (`idEtd`),
  KEY `FK_book_wishliste` (`idBook`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `absence`
--
ALTER TABLE `absence`
  ADD CONSTRAINT `FK_765AE0C94E89FE3A` FOREIGN KEY (`id_matiere`) REFERENCES `matier` (`id`),
  ADD CONSTRAINT `FK_765AE0C96B3CA4B` FOREIGN KEY (`id_user`) REFERENCES `users` (`id`);

--
-- Contraintes pour la table `classeenseignantmatiere`
--
ALTER TABLE `classeenseignantmatiere`
  ADD CONSTRAINT `FK_395724AE3CE58AF` FOREIGN KEY (`id_class`) REFERENCES `classe` (`Id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_395724AE4E89FE3A` FOREIGN KEY (`id_matiere`) REFERENCES `matier` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_395724AE6B3CA4B` FOREIGN KEY (`id_user`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `club`
--
ALTER TABLE `club`
  ADD CONSTRAINT `FK_B8EE3872120FF27F` FOREIGN KEY (`idResponsable`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `comment`
--
ALTER TABLE `comment`
  ADD CONSTRAINT `FK_9474526CBDAFD8C8` FOREIGN KEY (`author`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_9474526CE2904019` FOREIGN KEY (`thread_id`) REFERENCES `thread` (`id`);

--
-- Contraintes pour la table `demandeevenement`
--
ALTER TABLE `demandeevenement`
  ADD CONSTRAINT `FK_63BF64BCCB1366EC` FOREIGN KEY (`idClub`) REFERENCES `club` (`idClub`);

--
-- Contraintes pour la table `emplois`
--
ALTER TABLE `emplois`
  ADD CONSTRAINT `FK_461274B9B58EDEC1` FOREIGN KEY (`nameclas`) REFERENCES `classe` (`Id`);

--
-- Contraintes pour la table `enseigner`
--
ALTER TABLE `enseigner`
  ADD CONSTRAINT `FK_663E85CD353314B` FOREIGN KEY (`idEnseignant`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_663E85CD80AD3CB8` FOREIGN KEY (`idMatiere`) REFERENCES `matier` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `evenement`
--
ALTER TABLE `evenement`
  ADD CONSTRAINT `FK_B26681ECB1366EC` FOREIGN KEY (`idClub`) REFERENCES `club` (`idClub`);

--
-- Contraintes pour la table `likes`
--
ALTER TABLE `likes`
  ADD CONSTRAINT `FK_49CA4E7D182A5291` FOREIGN KEY (`idbook`) REFERENCES `books` (`idBook`),
  ADD CONSTRAINT `FK_49CA4E7DA9D4920D` FOREIGN KEY (`idetd`) REFERENCES `users` (`id`);

--
-- Contraintes pour la table `note`
--
ALTER TABLE `note`
  ADD CONSTRAINT `FK_CFBDFA1422DD08B8` FOREIGN KEY (`idEtudiant`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_CFBDFA14353314B` FOREIGN KEY (`idEnseignant`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_CFBDFA1480AD3CB8` FOREIGN KEY (`idMatiere`) REFERENCES `matier` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `participation`
--
ALTER TABLE `participation`
  ADD CONSTRAINT `FK_AB55E24F5E5C27E9` FOREIGN KEY (`iduser`) REFERENCES `users` (`id`),
  ADD CONSTRAINT `FK_AB55E24F753DC1EB` FOREIGN KEY (`idevenement`) REFERENCES `evenement` (`idEvenement`);

--
-- Contraintes pour la table `question`
--
ALTER TABLE `question`
  ADD CONSTRAINT `FK_B6F7494EA76ED395` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  ADD CONSTRAINT `FK_B6F7494EBAD26311` FOREIGN KEY (`tag_id`) REFERENCES `tag` (`id_tag`);

--
-- Contraintes pour la table `rate`
--
ALTER TABLE `rate`
  ADD CONSTRAINT `FK_DFEC3F395E5C27E9` FOREIGN KEY (`iduser`) REFERENCES `users` (`id`),
  ADD CONSTRAINT `FK_DFEC3F396B21C9D2` FOREIGN KEY (`idclub`) REFERENCES `club` (`idClub`);

--
-- Contraintes pour la table `reclamation`
--
ALTER TABLE `reclamation`
  ADD CONSTRAINT `FK_CE6064045058FBE9` FOREIGN KEY (`IdEtd`) REFERENCES `users` (`id`);

--
-- Contraintes pour la table `reponse`
--
ALTER TABLE `reponse`
  ADD CONSTRAINT `FK_5FB6DEC7A76ED395` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  ADD CONSTRAINT `FK_5FB6DEC7E62CA5DB` FOREIGN KEY (`id_question`) REFERENCES `question` (`id_question`);

--
-- Contraintes pour la table `reservationbook`
--
ALTER TABLE `reservationbook`
  ADD CONSTRAINT `FK_D38C97149199D4ED` FOREIGN KEY (`idEtd`) REFERENCES `users` (`id`),
  ADD CONSTRAINT `FK_D38C9714B818FDAF` FOREIGN KEY (`idBook`) REFERENCES `books` (`idBook`);

--
-- Contraintes pour la table `resultat`
--
ALTER TABLE `resultat`
  ADD CONSTRAINT `FK_E7DB5DE222DD08B8` FOREIGN KEY (`idEtudiant`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `thread`
--
ALTER TABLE `thread`
  ADD CONSTRAINT `FK_31204C83B818FDAF` FOREIGN KEY (`idBook`) REFERENCES `books` (`idBook`);

--
-- Contraintes pour la table `users`
--
ALTER TABLE `users`
  ADD CONSTRAINT `FK_1483A5E96183EFB3` FOREIGN KEY (`classeEtd`) REFERENCES `classe` (`Id`);

--
-- Contraintes pour la table `wishliste`
--
ALTER TABLE `wishliste`
  ADD CONSTRAINT `FK_BE989B4A9199D4ED` FOREIGN KEY (`idEtd`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_BE989B4AB818FDAF` FOREIGN KEY (`idBook`) REFERENCES `books` (`idBook`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
