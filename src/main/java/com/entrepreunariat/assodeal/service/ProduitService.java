package com.entrepreunariat.assodeal.service;

import com.entrepreunariat.assodeal.model.Produit;
import com.entrepreunariat.assodeal.model.dto.ProduitDTO;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

public interface ProduitService {
    List<Produit> findAllProduit();
    Optional<Produit> findProduit(Long id);
    Produit saveProduit(ProduitDTO produitDTO);
    void deleteProduit(Long idProduit);
    ProduitDTO convertProduitToDTO(Produit produit) throws ParseException;
    Produit convertProduitToEntity(ProduitDTO produitDto) throws ParseException;
}
