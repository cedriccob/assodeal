package com.entrepreunariat.assodeal.service;

import com.entrepreunariat.assodeal.model.CategorieProduit;
import com.entrepreunariat.assodeal.model.dto.CategorieProduitDTO;

import java.util.List;
import java.util.Optional;


public interface CategorieProduitService {
    List<CategorieProduit> findAllCategoriesProduit();
    Optional<CategorieProduit> retrieveCategorieProduit(Long id);
    CategorieProduit saveCategorieProduit(CategorieProduitDTO categorieProduitDTO);
    void deleteCategorieProduit(Long idCategorieProduit);

}
