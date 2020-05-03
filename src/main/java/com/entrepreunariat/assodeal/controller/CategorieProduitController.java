package com.entrepreunariat.assodeal.controller;

import com.entrepreunariat.assodeal.model.CategorieProduit;
import com.entrepreunariat.assodeal.model.Commande;
import com.entrepreunariat.assodeal.model.dto.CategorieProduitDTO;
import com.entrepreunariat.assodeal.service.CategorieProduitService;
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
import springfox.documentation.annotations.ApiIgnore;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/categorie-produit")
@Api(value="authentication", description = "Opérations sur les catégories de produit",
        authorizations = @Authorization(value = "Bearer"))
public class CategorieProduitController {

    @Autowired
    CategorieProduitService categorieProduitService;

    @Autowired
    ModelMapper modelMapper;

    private static final Logger LOGGER = LoggerFactory.getLogger(CategorieProduitController.class);

    @ResponseBody
    @GetMapping("/all")
    @ApiOperation(value = "Récupérer toutes les catégories de produit")
    List<CategorieProduit> findAll() {
        return categorieProduitService.findAllCategoriesProduit();
    }

    @ResponseBody
    @GetMapping("/all/search")
    @ApiIgnore
    List<CategorieProduit> findAllSearch(@RequestParam(value = "search") String search) {
        return categorieProduitService.findAllSearch(search);
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Ajouter une catégorie de produit", authorizations = @Authorization(value = "Bearer"))
    ResponseEntity<Commande> addCategorieProduit(@RequestBody CategorieProduitDTO categorieProduitDTO) {
        ResponseEntity<Commande> response = new ResponseEntity<>(HttpStatus.CREATED);
        try {
            categorieProduitService.saveCategorieProduit(categorieProduitDTO);
        } catch (Exception exception) {
            LOGGER.error("Erreur ajout categorie produit", exception);
            response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Modifier une catégorie de produit", authorizations = @Authorization(value = "Bearer"))
    ResponseEntity<CategorieProduit> updateCategorieProduit(@RequestBody CategorieProduitDTO categorieProduitDTO,
                                                            @PathVariable("id") long idCategorieProduit) {
        ResponseEntity<CategorieProduit> response = new ResponseEntity<>(HttpStatus.OK);
        Optional<CategorieProduit> categorieProduit = categorieProduitService.retrieveCategorieProduit(idCategorieProduit);
        if (!categorieProduit.isPresent()) {
            response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
            LOGGER.error("Mise à jour impossible, cette catégorie de produit n'existe pas");
        } else {
            try {
                categorieProduit.get().setLibelleCategorieProduit(categorieProduitDTO.getLibelleCategorieProduit());
                categorieProduitService.saveCategorieProduit(
                        categorieProduitService.convertCategorieProduitToDTO(categorieProduit.get()));
            } catch (ParseException e) {
                LOGGER.error("erreur parse update");
                response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        return response;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Retrouver une catégorie de produit", authorizations = @Authorization(value = "Bearer"))
    Optional<CategorieProduit> findCategorieProduit(@PathVariable("id") long idCategorieProduit) {
        return categorieProduitService.retrieveCategorieProduit(idCategorieProduit);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Supprimer une catégorie de produit", authorizations = @Authorization(value = "Bearer"))
    ResponseEntity<CategorieProduit> deleteCategorieProduit(@PathVariable("id") long idCategorieProduit) {
        ResponseEntity<CategorieProduit> response = new ResponseEntity<>(HttpStatus.OK);
        Optional<CategorieProduit> categorieProduit = categorieProduitService.retrieveCategorieProduit(idCategorieProduit);
        if (!categorieProduit.isPresent()) {
            response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
            LOGGER.error("Suppression impossible, cette catégorie de produit n'existe pas");

        } else {
            categorieProduitService.deleteCategorieProduit(idCategorieProduit);
        }
        return response;
    }


}
