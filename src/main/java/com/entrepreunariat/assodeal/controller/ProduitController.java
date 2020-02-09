package com.entrepreunariat.assodeal.controller;


import com.entrepreunariat.assodeal.model.Produit;
import com.entrepreunariat.assodeal.model.dto.ProduitDTO;
import com.entrepreunariat.assodeal.service.ProduitService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/produit")
public class ProduitController {

    @Autowired
    ProduitService produitService;

    @Autowired
    ModelMapper modelMapper;

    private static final Logger LOGGER = LoggerFactory.getLogger(ProduitController.class);

    @ResponseBody
    @GetMapping("/all")
    List<Produit> findAll() {
        return produitService.findAllProduit();
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    ResponseEntity<Produit> addProduit(@RequestBody ProduitDTO produit) {
        ResponseEntity<Produit> response = new ResponseEntity<>(HttpStatus.CREATED);
        try {
            produitService.saveProduit(produit);
        } catch (Exception exception) {
            LOGGER.error("Erreur ajout user", exception);
            response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }

    @PutMapping("/{id}")
    ResponseEntity<Produit> updateProduit(@RequestBody ProduitDTO newProduit, @PathVariable("id") long idProduit) {
        ResponseEntity<Produit> response = new ResponseEntity<>(HttpStatus.OK);
        Optional<Produit> produit = produitService.findProduit(idProduit);
        if (!produit.isPresent()) {
            LOGGER.error("Mise à jour impossible, ce produit n'existe pas");
            response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            produit.get().setAttributsProduit(newProduit.getAttributsProduit());
            produit.get().setCategorieProduit(newProduit.getCategorieProduit());
            produit.get().setDetailProduit(newProduit.getDetailProduit());
            produit.get().setLibelleProduit(newProduit.getLibelleProduit());
            produit.get().setPrixReelProduit(newProduit.getPrixReelProduit());
            produit.get().setPrixVenteProduit(newProduit.getPrixVenteProduit());
            produit.get().setQtStockProduit(newProduit.getQtStockProduit());
            try {
                produitService.saveProduit(convertToDTO(produit.get()));
            } catch (ParseException e) {
                LOGGER.error("erreur parse update");
                response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
        return response;

    }

    @GetMapping("/{id}")
    Optional<Produit> findProduit(@PathVariable("id") long idProduit) {
        return produitService.findProduit(idProduit);
    }

    @DeleteMapping("/delete/{id}")
    ResponseEntity<Produit> deleteProduit(@PathVariable("id") long idProduit) {
        ResponseEntity<Produit> response = new ResponseEntity<>(HttpStatus.OK);
        Optional<Produit> produit = produitService.findProduit(idProduit);
        if (!produit.isPresent()) {
            response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            produitService.deleteProduit(idProduit);
        }
        return response;
    }

    private ProduitDTO convertToDTO(Produit produit) throws ParseException {
        return modelMapper.map(produit, ProduitDTO.class);
    }

}
