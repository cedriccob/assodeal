package com.entrepreunariat.assodeal.service;

import com.entrepreunariat.assodeal.model.AttributsProduit;
import com.entrepreunariat.assodeal.model.dto.AttributsProduitDTO;

import java.util.List;
import java.util.Optional;

public interface AttributsProduitService {
    List<AttributsProduit> findAllAttributsProduit();
    Optional<AttributsProduit> retrieveAttributProduit(Long id);
    AttributsProduit saveAttributProduit(AttributsProduitDTO attributsProduitDTO);
    void deleteAttributProduit(Long idAttributsProduit);
}
