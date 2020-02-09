package com.entrepreunariat.assodeal.dao;

import com.entrepreunariat.assodeal.model.AttributsProduit;
import com.entrepreunariat.assodeal.model.CategorieProduit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategorieProduitRepository extends JpaRepository<CategorieProduit, Long> {

    List<CategorieProduit> findAll();
    Optional<CategorieProduit> findById(Long id);
    CategorieProduit save(CategorieProduit categorieProduit);
    void deleteByIdCategorieProduit(Long idCategorieProduit);
}
