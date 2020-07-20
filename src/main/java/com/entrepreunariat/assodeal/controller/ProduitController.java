package com.entrepreunariat.assodeal.controller;


import com.entrepreunariat.assodeal.model.CategorieProduit;
import com.entrepreunariat.assodeal.model.Produit;
import com.entrepreunariat.assodeal.model.dto.ProduitDTO;
import com.entrepreunariat.assodeal.service.AttributsProduitService;
import com.entrepreunariat.assodeal.service.CategorieProduitService;
import com.entrepreunariat.assodeal.service.ProduitService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/produit")
@Api(value="Produit", description = "Opération sur les produits",
        authorizations = @Authorization(value = "Bearer"))
public class ProduitController {

    @Autowired
    ProduitService produitService;

    @Autowired
    CategorieProduitService categorieProduitService;

    @Autowired
    ModelMapper modelMapper;

    private static final Logger LOGGER = LoggerFactory.getLogger(ProduitController.class);

    @ResponseBody
    @GetMapping("/all")
    @ApiOperation(value = "Récupérer tous les produits", authorizations = @Authorization(value = "Bearer"))
    List<Produit> findAll() {
        return produitService.findAllProduit();
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Ajouter un produit", authorizations = @Authorization(value = "Bearer"))
    ResponseEntity<Produit> addProduit(@RequestPart(value = "imageProduit", required = false) MultipartFile imageProduit,
                                       @RequestPart(value = "libelleProduit", required = false) String libelleProduit,
                                       @RequestPart("prixReelProduit") String prixReelProduit,
                                       @RequestPart ("prixVenteProduit")String prixVenteProduit,
                                       @RequestPart ("qtStockProduit")String qtStockProduit,
                                       @RequestPart ("idCategorieProduit")String idCategorieProduit) {
        ResponseEntity<Produit> response = new ResponseEntity<>(HttpStatus.CREATED);
        ProduitDTO produitDTO = new ProduitDTO();
        try {
            if (imageProduit != null) {
                produitDTO.setImageProduit(imageProduit.getBytes());
            }
            if(idCategorieProduit!=null) {
                Optional<CategorieProduit> categorieProduit = categorieProduitService.retrieveCategorieProduit(Long.parseLong(idCategorieProduit));
                produitDTO.setLibelleProduit(libelleProduit);
                produitDTO.setPrixReelProduit(new BigDecimal(prixReelProduit));
                produitDTO.setPrixReelProduit(new BigDecimal(prixVenteProduit));
                produitDTO.setQtStockProduit(Integer.parseInt(qtStockProduit));
                if (categorieProduit.isPresent()) {
                    produitDTO.setCategorieProduit(categorieProduitService.convertCategorieProduitToDTO(categorieProduit.get()));
                    produitService.saveProduit(produitDTO);
                } else {
                    LOGGER.error("Erreur ajout produit");
                }
            }
            else{
                LOGGER.error("Erreur de récupération de la categorie produit");
            }
        } catch (Exception exception) {
            LOGGER.error("Erreur ajout produit", exception);
            response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Modifier un produit", authorizations = @Authorization(value = "Bearer"))
    ResponseEntity<Produit> updateProduit(@RequestBody ProduitDTO newProduit, @PathVariable("id") long idProduit) {
        ResponseEntity<Produit> response = new ResponseEntity<>(HttpStatus.OK);
        Optional<Produit> produit = produitService.findProduit(idProduit);
        if (!produit.isPresent()) {
            LOGGER.error("Mise à jour impossible, ce produit n'existe pas");
            response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            try {
            produit.get().setAbreviationProduit(newProduit.getAbreviationProduit());
            produit.get().setCouleurProduit(newProduit.getCouleurProduit());
            produit.get().setPoidsProduit(newProduit.getPoidsProduit());
            produit.get().setCategorieProduit(
                    categorieProduitService.convertCategorieProduitToEntity(newProduit.getCategorieProduit()));
            produit.get().setDetailProduit(newProduit.getDetailProduit());
            produit.get().setLibelleProduit(newProduit.getLibelleProduit());
            produit.get().setPrixReelProduit(newProduit.getPrixReelProduit());
            produit.get().setPrixVenteProduit(newProduit.getPrixVenteProduit());
            produit.get().setQtStockProduit(newProduit.getQtStockProduit());
                produitService.saveProduit(convertToDTO(produit.get()));
            } catch (ParseException e) {
                LOGGER.error("erreur parse update");
                response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        return response;

    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Retrouver un produit", authorizations = @Authorization(value = "Bearer"))
    Optional<Produit> findProduit(@PathVariable("id") long idProduit) {
        return produitService.findProduit(idProduit);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Supprimer un produit", authorizations = @Authorization(value = "Bearer"))
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
