package com.entrepreunariat.assodeal.dao;

import com.entrepreunariat.assodeal.model.Produit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProduitRepository extends JpaRepository<Produit, Long> {
    List<Produit> findAll();
    Optional<Produit> findById(Long id);
    Produit save(Produit produit);
    void deleteByIdProduit(Long idProduit);
}
