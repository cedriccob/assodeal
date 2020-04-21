CREATE TABLE `application` (
  `id` bigint(20) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `attributs_produit` (
  `id_attribut_produit` bigint(20) NOT NULL,
  `valeur_couleur_produit` varchar(255) DEFAULT NULL,
  `valeur_poids_produit` double DEFAULT NULL,
  PRIMARY KEY (`id_attribut_produit`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `attributs_produits_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `categorie_produit` (
  `id_categorie_produit` bigint(20) NOT NULL,
  `abreviation_produit` varchar(255) DEFAULT NULL,
  `libelle_produit` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_categorie_produit`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `categorie_produit_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `commande` (
  `id_commande` bigint(20) NOT NULL,
  `date_commande` datetime DEFAULT NULL,
  `quantite_commande` int(11) NOT NULL,
  `status_commande` int(11) NOT NULL,
  `id_produit` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id_commande`),
  KEY `FKsnyiwmlx81guec4rq7bdkea5i` (`id_produit`),
  CONSTRAINT `FKsnyiwmlx81guec4rq7bdkea5i` FOREIGN KEY (`id_produit`) REFERENCES `produit` (`id_produit`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `commande_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `confirmation_token` (
  `token_id` bigint(20) NOT NULL,
  `confirmation_token` varchar(255) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`token_id`),
  KEY `FKhjrtky9wbd6lbk7mu9tuddqgn` (`user_id`),
  CONSTRAINT `FKhjrtky9wbd6lbk7mu9tuddqgn` FOREIGN KEY (`user_id`) REFERENCES `user` (`id_user`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `confirmation_token_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `facture` (
  `id_facture` bigint(20) NOT NULL,
  `date_facture` datetime DEFAULT NULL,
  `montant_facture` decimal(19,2) DEFAULT NULL,
  `numero_facture` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_facture`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `facture_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `fournisseur` (
  `id_fournisseur` bigint(20) NOT NULL,
  `nom_fournisseur` varchar(255) DEFAULT NULL,
  `numero_siret_fournisseur` varchar(255) DEFAULT NULL,
  `password_fournisseur` varchar(255) DEFAULT NULL,
  `statut_fournisseur` varchar(255) DEFAULT NULL,
  `user_fournisseur` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_fournisseur`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `fournisseur_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `moyen_paiement` (
  `id_moyen_paiement` bigint(20) NOT NULL,
  `abreviation_moyen_paiement` varchar(255) DEFAULT NULL,
  `libelle_moyen_paiement` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_moyen_paiement`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `moyen_paiement_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `produit` (
  `id_produit` bigint(20) NOT NULL,
  `detail_produit` varchar(255) DEFAULT NULL,
  `libelle_produit` varchar(255) DEFAULT NULL,
  `prix_reel_produit` decimal(19,2) DEFAULT NULL,
  `prix_vente_produit` decimal(19,2) DEFAULT NULL,
  `qt_stock_produit` int(11) NOT NULL,
  `id_attributs_produit` bigint(20) DEFAULT NULL,
  `id_categorie_produit` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id_produit`),
  KEY `FKdv08h23oe2r44fwjfg31uqvq7` (`id_attributs_produit`),
  KEY `FKfo1ou3noydpx86uwabyvkdgj2` (`id_categorie_produit`),
  CONSTRAINT `FKdv08h23oe2r44fwjfg31uqvq7` FOREIGN KEY (`id_attributs_produit`) REFERENCES `attributs_produit` (`id_attribut_produit`),
  CONSTRAINT `FKfo1ou3noydpx86uwabyvkdgj2` FOREIGN KEY (`id_categorie_produit`) REFERENCES `categorie_produit` (`id_categorie_produit`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `produit_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `user` (
  `id_user` bigint(20) NOT NULL,
  `adresse` varchar(255) DEFAULT NULL,
  `contact` varchar(255) DEFAULT NULL,
  `date_dernier_login` datetime DEFAULT NULL,
  `date_enregistrement` datetime DEFAULT NULL,
  `is_enabled` bit(1) NOT NULL,
  `mail` varchar(255) DEFAULT NULL,
  `nationalite` varchar(255) DEFAULT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `pays_residence` varchar(255) DEFAULT NULL,
  `prenom` varchar(255) DEFAULT NULL,
  `status` int(11) NOT NULL,
  `username` varchar(255) DEFAULT NULL,
  `ville` varchar(255) DEFAULT NULL,
  `id_role` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id_user`),
  KEY `FKewn5ukg44l2tuu1cnyhkb9c97` (`id_role`),
  CONSTRAINT `FKewn5ukg44l2tuu1cnyhkb9c97` FOREIGN KEY (`id_role`) REFERENCES `user_role` (`id_role`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `user_role` (
  `id_role` bigint(20) NOT NULL,
  `code_role` varchar(255) DEFAULT NULL,
  `name_role` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_role`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `user_role_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `user_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
