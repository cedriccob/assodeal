package com.entrepreunariat.assodeal.service.impl;

import com.entrepreunariat.assodeal.dao.CategorieProduitRepository;
import com.entrepreunariat.assodeal.model.CategorieProduit;
import com.entrepreunariat.assodeal.model.dto.CategorieProduitDTO;
import com.entrepreunariat.assodeal.service.CategorieProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CategorieProduitServiceImpl implements CategorieProduitService {

    @Autowired
    CategorieProduitRepository categorieProduitRepository;

    @Override
    public List<CategorieProduit> findAllCategoriesProduit() {
        return categorieProduitRepository.findAll();
    }

    @Override
    public Optional<CategorieProduit> retrieveCategorieProduit(Long id) {
        return categorieProduitRepository.findById(id);
    }

    @Override
    public CategorieProduit saveCategorieProduit(CategorieProduitDTO categorieProduitDTO) {
        CategorieProduit categorieProduit = new CategorieProduit();
        categorieProduit.setIdCategorieProduit(categorieProduitDTO.getIdCategorieProduit());
        categorieProduit.setAbreviationProduit(categorieProduitDTO.getAbreviationProduit());
        categorieProduit.setLibelleProduit(categorieProduitDTO.getLibelleProduit());
        return categorieProduitRepository.save(categorieProduit);
    }

    @Override
    public void deleteCategorieProduit(Long idCategorieProduit) {
        categorieProduitRepository.deleteByIdCategorieProduit(idCategorieProduit);
    }
}
