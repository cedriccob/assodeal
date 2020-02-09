package com.entrepreunariat.assodeal.controller;

import com.entrepreunariat.assodeal.model.AttributsProduit;
import com.entrepreunariat.assodeal.model.dto.AttributsProduitDTO;
import com.entrepreunariat.assodeal.service.AttributsProduitService;
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
@RequestMapping("/attributs-produit")
public class AttributsProduitController {

    @Autowired
    AttributsProduitService attributsProduitService;

    @Autowired
    ModelMapper modelMapper;

    private static final Logger LOGGER = LoggerFactory.getLogger(AttributsProduitController.class);

    @ResponseBody
    @GetMapping("/all")
    List<AttributsProduit> findAll() {
        return attributsProduitService.findAllAttributsProduit();
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    ResponseEntity<AttributsProduit> addCategorieProduit(@RequestBody AttributsProduitDTO attributsProduitDTO) {
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
    ResponseEntity<AttributsProduit> updateAttributsProduit(@RequestBody AttributsProduitDTO attributsProduitDTO,
                                                            @PathVariable("id") long idAttributsProduit) {
        Optional<AttributsProduit> attributsProduit = attributsProduitService.retrieveAttributProduit(idAttributsProduit);
        ResponseEntity<AttributsProduit> response = new ResponseEntity<>(HttpStatus.OK);
        if (!attributsProduit.isPresent()) {
            LOGGER.error("Mise à jour impossible, cet attribut de produit n'existe pas");
            response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            attributsProduit.get().setPoidsProduit(attributsProduitDTO.getPoidsProduit());
            attributsProduit.get().setCouleurProduit(attributsProduitDTO.getCouleurProduit());
            try {
                attributsProduitService.saveAttributProduit(convertToDTO(attributsProduit.get()));
            } catch (ParseException e) {
                LOGGER.error("erreur parse update");
                response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
        return response;
    }

    @GetMapping("/{id}")
    Optional<AttributsProduit> findAttributsProduit(@PathVariable("id") long idAttributsProduit) {
        return attributsProduitService.retrieveAttributProduit(idAttributsProduit);
    }

    @DeleteMapping("/delete/{id}")
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

    private AttributsProduitDTO convertToDTO(AttributsProduit attributsProduit) throws ParseException {
        return modelMapper.map(attributsProduit, AttributsProduitDTO.class);
    }


}
