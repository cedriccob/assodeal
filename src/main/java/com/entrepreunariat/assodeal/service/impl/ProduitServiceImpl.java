package com.entrepreunariat.assodeal.service.impl;

import com.entrepreunariat.assodeal.dao.ProduitCustomRepository;
import com.entrepreunariat.assodeal.dao.ProduitRepository;
import com.entrepreunariat.assodeal.model.Produit;
import com.entrepreunariat.assodeal.model.dto.ProduitDTO;
import com.entrepreunariat.assodeal.service.AttributsProduitService;
import com.entrepreunariat.assodeal.service.CategorieProduitService;
import com.entrepreunariat.assodeal.service.ProduitService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProduitServiceImpl implements ProduitService {

    @Autowired
    ProduitRepository produitRepository;

    @Autowired
    ProduitCustomRepository produitCustomRepository;

    @Autowired
    CategorieProduitService categorieProduitService;

    @Autowired
    AttributsProduitService attributsProduitService;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public List<Produit> findAllProduit() {
        return produitRepository.findAll();
    }

    @Override
    public List<Produit> findAllSearch(String searchValue) {
        return produitCustomRepository.findSearch(searchValue);
    }

    @Override
    public Optional<Produit> findProduit(Long id) {
        return produitRepository.findById(id);
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(ProduitServiceImpl.class);


    @Override
    public Produit saveProduit(ProduitDTO produitDTO) {
        Produit produit = new Produit();
        try{
            produit.setIdProduit(produitDTO.getIdProduit());
            produit.setQtStockProduit(produitDTO.getQtStockProduit());
            produit.setPrixVenteProduit(produitDTO.getPrixVenteProduit());
            produit.setPrixReelProduit(produitDTO.getPrixReelProduit());
            produit.setLibelleProduit(produitDTO.getLibelleProduit());
            produit.setDetailProduit(produitDTO.getDetailProduit());
            produit.setImageProduit(produitDTO.getImageProduit());
            produit.setCategorieProduit(
                    categorieProduitService.convertCategorieProduitToEntity(produitDTO.getCategorieProduit()));
            produit.setAbreviationProduit(produitDTO.getAbreviationProduit());
            produit.setCouleurProduit(produitDTO.getCouleurProduit());
            produit.setPoidsProduit(produitDTO.getPoidsProduit());
        } catch (ParseException exception){
            LOGGER.error("erreur service produit");
        }
        return produitRepository.save(produit);
    }

    @Override
    public void deleteProduit(Long idProduit) {
        produitRepository.deleteByIdProduit(idProduit);
    }

    @Override
    public ProduitDTO convertProduitToDTO(Produit produit) throws ParseException {
        return modelMapper.map(produit, ProduitDTO.class);
    }

    @Override
    public Produit convertProduitToEntity(ProduitDTO produitDto) throws ParseException {
        return modelMapper.map(produitDto, Produit.class);
    }
}
