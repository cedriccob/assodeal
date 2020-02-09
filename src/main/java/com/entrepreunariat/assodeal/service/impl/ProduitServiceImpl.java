package com.entrepreunariat.assodeal.service.impl;

import com.entrepreunariat.assodeal.dao.ProduitRepository;
import com.entrepreunariat.assodeal.model.Produit;
import com.entrepreunariat.assodeal.model.dto.ProduitDTO;
import com.entrepreunariat.assodeal.service.ProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProduitServiceImpl implements ProduitService {

    @Autowired
    ProduitRepository produitRepository;

    @Override
    public List<Produit> findAllProduit() {
        return produitRepository.findAll();
    }

    @Override
    public Optional<Produit> findProduit(Long id) {
        return produitRepository.findById(id);
    }

    @Override
    public Produit saveProduit(ProduitDTO produitDTO) {
        Produit produit = new Produit();
        produit.setIdProduit(produitDTO.getIdProduit());
        produit.setQtStockProduit(produitDTO.getQtStockProduit());
        produit.setPrixVenteProduit(produitDTO.getPrixVenteProduit());
        produit.setPrixReelProduit(produitDTO.getPrixReelProduit());
        produit.setLibelleProduit(produitDTO.getLibelleProduit());
        produit.setDetailProduit(produitDTO.getDetailProduit());
        produit.setCategorieProduit(produitDTO.getCategorieProduit());
        produit.setAttributsProduit(produitDTO.getAttributsProduit());
        return produitRepository.save(produit);
    }

    @Override
    public void deleteProduit(Long idProduit) {
        produitRepository.deleteByIdProduit(idProduit);
    }
}
