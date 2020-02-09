package com.entrepreunariat.assodeal.dao;

import com.entrepreunariat.assodeal.model.AttributsProduit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AttributsProduitRepository extends JpaRepository<AttributsProduit, Long> {
    List<AttributsProduit> findAll();
    Optional<AttributsProduit> findById(Long id);
    AttributsProduit save(AttributsProduit attributsProduit);
    void deleteByIdAttributProduit(Long idAttributProduit);
}
