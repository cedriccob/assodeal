package com.entrepreunariat.assodeal.dao;

import com.entrepreunariat.assodeal.model.CategorieProduit;
import com.entrepreunariat.assodeal.model.Commande;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommandeRepository extends JpaRepository<Commande, Long> {
    List<Commande> findAll();
    Optional<Commande> findById(Long id);
    Commande save(Commande commande);
    void deleteByIdCommande(Long idCommande);
}
