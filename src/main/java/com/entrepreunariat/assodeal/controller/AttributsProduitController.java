package com.entrepreunariat.assodeal.controller;

import com.entrepreunariat.assodeal.model.AttributsProduit;
import com.entrepreunariat.assodeal.model.User;
import com.entrepreunariat.assodeal.model.dto.AttributsProduitDTO;
import com.entrepreunariat.assodeal.service.AttributsProduitService;
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
@RequestMapping("/attributs-produit")
@Api(value="Attributs produit", description = "Opérations sur les attributs de produit", authorizations = @Authorization(value = "Bearer"))
public class AttributsProduitController {

    @Autowired
    AttributsProduitService attributsProduitService;

    @Autowired
    ModelMapper modelMapper;

    private static final Logger LOGGER = LoggerFactory.getLogger(AttributsProduitController.class);

    @ResponseBody
    @GetMapping("/all")
    @ApiOperation(value = "Accéder à tous les attributs de produit", authorizations = @Authorization(value = "Bearer"))
    List<AttributsProduit> findAll() {
        return attributsProduitService.findAllAttributsProduit();
    }

    @ResponseBody
    @GetMapping("/all/search")
    @ApiIgnore
    List<AttributsProduit> findAllSearch(@RequestParam(value = "search", required = false) String search)
    {
        return attributsProduitService.findAllSearch(search);
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Ajouter un attribut de produit", authorizations = @Authorization(value = "Bearer"))
    ResponseEntity<AttributsProduit> addAttributProduit(@RequestBody AttributsProduitDTO attributsProduitDTO) {
        ResponseEntity<AttributsProduit> response = new ResponseEntity<>(HttpStatus.CREATED);
        try {
            attributsProduitService.saveAttributProduit(attributsProduitDTO);
        } catch (Exception exception) {
            LOGGER.error("Erreur ajout attributs produit", exception);
            response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Modifier un attribut de produit", authorizations = @Authorization(value = "Bearer"))
    ResponseEntity<AttributsProduit> updateAttributsProduit(@RequestBody AttributsProduitDTO attributsProduitDTO,
                                                            @PathVariable("id") long idAttributsProduit) {
        Optional<AttributsProduit> attributsProduit = attributsProduitService.retrieveAttributProduit(idAttributsProduit);
        ResponseEntity<AttributsProduit> response = new ResponseEntity<>(HttpStatus.OK);
        if (!attributsProduit.isPresent()) {
            LOGGER.error("Mise à jour impossible, cet attribut de produit n'existe pas");
            response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            try {
                attributsProduit.get().setPoidsProduit(attributsProduitDTO.getPoidsProduit());
                attributsProduit.get().setCouleurProduit(attributsProduitDTO.getCouleurProduit());
                attributsProduit.get().setAbreviationProduit(attributsProduitDTO.getAbreviationProduit());

                attributsProduitService.saveAttributProduit(
                        attributsProduitService.convertAttributsProduitToDTO(attributsProduit.get()));
            }
            catch (ParseException e){
                LOGGER.error("erreur parse update");
                response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        return response;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Récupérer un attribut de produit par l'id", authorizations = @Authorization(value = "Bearer"))
    ResponseEntity<AttributsProduit> findAttributsProduit(@PathVariable("id") long idAttributsProduit) {
        ResponseEntity<AttributsProduit> response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        Optional<AttributsProduit> attribut = attributsProduitService.retrieveAttributProduit(idAttributsProduit);
        if(attribut.isPresent()){
            response = ResponseEntity.ok(attribut.get());
        }
        return response;
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Supprimer un attribut de produit", authorizations = @Authorization(value = "Bearer"))
    ResponseEntity<AttributsProduit> deleteAttributsProduit(@PathVariable("id") long idAttributsProduit) {
        ResponseEntity<AttributsProduit> response = new ResponseEntity<>(HttpStatus.OK);
        Optional<AttributsProduit> attributsProduit = attributsProduitService.retrieveAttributProduit(idAttributsProduit);
        if (!attributsProduit.isPresent()) {
            LOGGER.error("Suppression impossible, cet attribut de produit n'existe pas");
            response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            attributsProduitService.deleteAttributProduit(idAttributsProduit);
        }
        return response;
    }

}
