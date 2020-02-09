package com.entrepreunariat.assodeal.dao;

import com.entrepreunariat.assodeal.model.Facture;
import com.entrepreunariat.assodeal.model.Fournisseur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FournisseurRepository extends JpaRepository<Fournisseur, Long> {
    List<Fournisseur> findAll();
    Optional<Fournisseur> findById(Long id);
    Fournisseur save(Fournisseur fournisseur);
    void deleteByIdFournisseur(Long idFournisseur);
}
