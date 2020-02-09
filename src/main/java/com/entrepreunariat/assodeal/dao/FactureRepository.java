package com.entrepreunariat.assodeal.dao;

import com.entrepreunariat.assodeal.model.Commande;
import com.entrepreunariat.assodeal.model.Facture;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FactureRepository extends JpaRepository<Facture, Long> {

    List<Facture> findAll();
    Optional<Facture> findById(Long id);
    Facture save(Facture facture);
    void deleteByIdFacture(Long id);
}
