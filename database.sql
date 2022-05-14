/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

CREATE TABLE IF NOT EXISTS `adherents` (
  `num` int(11) NOT NULL AUTO_INCREMENT,
  `cin` int(11) NOT NULL,
  `nom` varchar(255) NOT NULL,
  `prenom` varchar(255) NOT NULL,
  `sexe` varchar(15) NOT NULL,
  `profession` varchar(255) NOT NULL,
  `tel` int(255) NOT NULL,
  `date_naissance` date NOT NULL,
  `date_inscription` date NOT NULL,
  `email` varchar(255) NOT NULL,
  `domain_interet` varchar(255) NOT NULL,
  `motpass` varchar(255) NOT NULL,
  `role` varchar(255) NOT NULL,
  PRIMARY KEY (`num`)
) ENGINE=MyISAM AUTO_INCREMENT=30 DEFAULT CHARSET=latin1;

/*!40000 ALTER TABLE `adherents` DISABLE KEYS */;
INSERT INTO `adherents` (`num`, `cin`, `nom`, `prenom`, `sexe`, `profession`, `tel`, `date_naissance`, `date_inscription`, `email`, `domain_interet`, `motpass`, `role`) VALUES
	(17, 123456789, 'Sana', 'Ben Salem', 'Female', 'Profession', 22222222, '2022-05-01', '2022-05-04', 'u5@gmail.com', 'Admin', '0000', 'Collecteur des dons'),
	(18, 123456789, 'Nizar', 'Mansouri', 'Male', 'Profession', 22222222, '2022-05-01', '2022-05-04', 'u6@gmail.com', 'Admin', '0000', 'Collecteur des dons'),
	(19, 123456789, 'Mariem', 'Hamdi', 'Female', 'Profession', 22222222, '2022-05-01', '2022-05-04', 'u1@gmail.com', 'Admin', '0000', 'Administrateur'),
	(20, 123456789, 'Laila', 'Nasr', 'Female', 'Profession', 22222222, '2022-05-01', '2022-05-04', 'u9@gmail.com', 'Admin', '0000', 'Secrétaire'),
	(22, 123456789, 'Cyrine', 'Ayadi', 'Female', 'Profession', 22222222, '2022-05-01', '2022-05-04', 'u2@gmail.com', 'Admin', '0000', 'Administrateur'),
	(23, 123456789, 'Amna', 'Kouki', 'Female', 'Profession', 22222222, '2022-05-01', '2022-05-04', 'admin@gmail.com', 'Admin', '0000', 'Administrateur'),
	(25, 123456789, 'Moez', 'Salem', 'Male', 'Profession', 22222222, '2022-05-01', '2022-05-04', 'u3@gmail.com', 'Admin', '0000', 'Adhérent'),
	(26, 123456789, 'Ines', 'Bejaoui', 'Female', 'Profession', 22222222, '2022-05-01', '2022-05-04', 'u7@gmail.com', 'Admin', '0000', 'Adhérent'),
	(28, 123456789, 'Hamza', 'Aloui', 'Male', 'Profession', 22222222, '2022-05-01', '2022-05-04', 'u4@gmail.com', 'Admin', '0000', 'Administrateur'),
	(29, 123456789, 'Kamal', 'Sellami', 'Male', 'Profession', 22222222, '2022-05-01', '2022-05-04', 'u8@gmail.com', 'Admin', '0000', 'Collecteur des dons');
/*!40000 ALTER TABLE `adherents` ENABLE KEYS */;

CREATE TABLE IF NOT EXISTS `compte_bancaire` (
  `num` int(11) NOT NULL AUTO_INCREMENT,
  `nom_banque` varchar(20) NOT NULL,
  `code_branche` int(11) NOT NULL,
  `total` float NOT NULL,
  PRIMARY KEY (`num`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

/*!40000 ALTER TABLE `compte_bancaire` DISABLE KEYS */;
/*!40000 ALTER TABLE `compte_bancaire` ENABLE KEYS */;

CREATE TABLE IF NOT EXISTS `depenses` (
  `num` int(11) NOT NULL AUTO_INCREMENT,
  `libelle` varchar(27) NOT NULL,
  `categorie` varchar(255) NOT NULL,
  `type` varchar(255) NOT NULL,
  `montant` double NOT NULL,
  `date_operation` date NOT NULL,
  `mode_paiement` varchar(255) NOT NULL,
  `num_compte` int(11) NOT NULL,
  PRIMARY KEY (`num`),
  KEY `fk_depense` (`num_compte`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

/*!40000 ALTER TABLE `depenses` DISABLE KEYS */;
/*!40000 ALTER TABLE `depenses` ENABLE KEYS */;

CREATE TABLE IF NOT EXISTS `dons` (
  `num` int(11) NOT NULL AUTO_INCREMENT,
  `libelle` varchar(50) DEFAULT NULL,
  `categorie` varchar(50) DEFAULT NULL,
  `type` varchar(50) DEFAULT NULL,
  `date_don` date DEFAULT NULL,
  `cin` int(11) DEFAULT NULL,
  `quantite` int(11) DEFAULT NULL,
  `montant` double DEFAULT NULL,
  `mode_paiement` varchar(50) DEFAULT NULL,
  `num_compte` int(11) DEFAULT NULL,
  `num_cheque` int(11) DEFAULT NULL,
  PRIMARY KEY (`num`)
) ENGINE=MyISAM AUTO_INCREMENT=31 DEFAULT CHARSET=latin1;

/*!40000 ALTER TABLE `dons` DISABLE KEYS */;
INSERT INTO `dons` (`num`, `libelle`, `categorie`, `type`, `date_don`, `cin`, `quantite`, `montant`, `mode_paiement`, `num_compte`, `num_cheque`) VALUES
	(22, 'Test 1', 'Vêtements', 'Financier', '2022-05-11', 10, 10, 10, 'Espece', NULL, NULL),
	(20, 'Test 1', 'Vêtements', 'Financier', '2022-05-11', 10, 10, 10, 'Espece', NULL, NULL),
	(21, 'Test 1', 'Livres', 'Financier', '2022-05-11', 10, 10, 10, 'Espece', NULL, NULL),
	(23, 'Test 1', 'Vêtements', 'Financier', '2022-05-11', 10, 10, 10, 'Espece', NULL, NULL),
	(24, 'Test 1', 'Don Alimentaire', 'Financier', '2022-05-11', 10, 10, 10, 'Espece', NULL, NULL),
	(25, 'Test 1', 'Vêtements', 'Financier', '2022-05-11', 10, 10, 10, 'Espece', NULL, NULL),
	(26, 'Test 1', 'Don Alimentaire', 'Financier', '2022-05-11', 10, 10, 10, 'Espece', NULL, NULL),
	(27, 'Test 1', 'Autres', 'Financier', '2022-05-11', 10, 10, 10, 'Espece', NULL, NULL),
	(28, 'Test 1', 'Livres', 'Financier', '2022-05-11', 10, 10, 10, 'Espece', NULL, NULL),
	(29, 'Test 1', 'Médicaments', 'Financier', '2022-05-11', 10, 10, 10, 'Espece', NULL, NULL),
	(30, 'Test 1', 'Médicaments', 'Financier', '2022-05-11', 10, 10, 10, 'Espece', NULL, NULL);
/*!40000 ALTER TABLE `dons` ENABLE KEYS */;

CREATE TABLE IF NOT EXISTS `events` (
  `num` int(11) NOT NULL,
  `titre` varchar(27) NOT NULL,
  `objectif` varchar(255) NOT NULL,
  `date_debut` date NOT NULL,
  `date_fin` date NOT NULL,
  `couverture` varchar(255) NOT NULL,
  `lieu` varchar(255) NOT NULL,
  `tarif` double NOT NULL,
  `num_depense` int(11) NOT NULL,
  `num_recette` int(11) NOT NULL,
  `adherents` varchar(255) NOT NULL,
  `pauvres` varchar(255) NOT NULL,
  PRIMARY KEY (`num`),
  KEY `FK_g` (`num_depense`),
  KEY `FK_g1` (`num_recette`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

/*!40000 ALTER TABLE `events` DISABLE KEYS */;
/*!40000 ALTER TABLE `events` ENABLE KEYS */;

CREATE TABLE IF NOT EXISTS `familles` (
  `num` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(50) NOT NULL DEFAULT '0',
  PRIMARY KEY (`num`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

/*!40000 ALTER TABLE `familles` DISABLE KEYS */;
/*!40000 ALTER TABLE `familles` ENABLE KEYS */;

CREATE TABLE IF NOT EXISTS `pauvres` (
  `num` int(11) NOT NULL AUTO_INCREMENT,
  `cin` int(11) NOT NULL,
  `nom` varchar(255) NOT NULL,
  `prenom` varchar(255) NOT NULL,
  `sexe` varchar(15) NOT NULL,
  `profession` varchar(255) NOT NULL,
  `tel` int(11) NOT NULL,
  `date_naissance` date NOT NULL,
  `loyer` int(11) NOT NULL,
  `adresse` varchar(255) NOT NULL,
  `enfants` int(11) NOT NULL,
  `etat_sante` varchar(15) NOT NULL,
  `etat_civil` varchar(255) NOT NULL,
  `salaire` float NOT NULL,
  `niveau_education` varchar(255) NOT NULL,
  `num_famille` int(11) NOT NULL,
  `date_inscription` date NOT NULL,
  PRIMARY KEY (`num`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

/*!40000 ALTER TABLE `pauvres` DISABLE KEYS */;
INSERT INTO `pauvres` (`num`, `cin`, `nom`, `prenom`, `sexe`, `profession`, `tel`, `date_naissance`, `loyer`, `adresse`, `enfants`, `etat_sante`, `etat_civil`, `salaire`, `niveau_education`, `num_famille`, `date_inscription`) VALUES
	(1, 15789652, 'heni', 'ourabi', 'm', 'infirmier', 79037552, '1995-03-05', 1, 'nahj il basatin 25415', 0, 'm', 'marié', 450, 'secondaire', 1, '2022-04-07');
/*!40000 ALTER TABLE `pauvres` ENABLE KEYS */;

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
