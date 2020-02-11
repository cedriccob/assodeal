package com.entrepreunariat.assodeal.controller;

import com.entrepreunariat.assodeal.model.Facture;
import com.entrepreunariat.assodeal.model.dto.FactureDTO;
import com.entrepreunariat.assodeal.service.FactureService;
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

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/facture")
@Api(value="facture", description = "Opérations sur les factures de commande",
        authorizations = @Authorization(value = "Bearer"))
public class FactureController  {
    @Autowired
    FactureService factureService;

    @Autowired
    ModelMapper modelMapper;

    private static final Logger LOGGER = LoggerFactory.getLogger(FactureController.class);

    @ResponseBody
    @GetMapping("/all")
    @ApiOperation(value = "Récupérer toutes les factures", authorizations = @Authorization(value = "Bearer"))
    List<Facture> findAll() {
        return factureService.findAllFacture();
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    ResponseEntity<Facture> addFacture(@RequestBody FactureDTO factureDTO) {
        ResponseEntity<Facture> response = new ResponseEntity<>(HttpStatus.CREATED);
        try {
            factureService.saveFacture(factureDTO);
        } catch (Exception exception) {
            LOGGER.error("Erreur ajout commande", exception);
            response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Modifier une facture", authorizations = @Authorization(value = "Bearer"))
    ResponseEntity<Facture> updateFacture(@RequestBody FactureDTO factureDTO, @PathVariable("id") long idFacture) {
        ResponseEntity<Facture> response = new ResponseEntity<>(HttpStatus.OK);
        Optional<Facture> facture = factureService.retrieveFacture(idFacture);
        if(!facture.isPresent()) {
            response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
            LOGGER.error("Mise à jour impossible, cette facture n'existe pas");
        }
        else {
            facture.get().setNumeroFacture(factureDTO.getNumeroFacture());
            facture.get().setMontantFacture(factureDTO.getMontantFacture());
            facture.get().setDateFacture(factureDTO.getDateFacture());
            try {
                factureService.saveFacture(convertToDTO(facture.get()));
            } catch (ParseException e) {
                LOGGER.error("erreur parse update");
                response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
        return response;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Retrouver une facture", authorizations = @Authorization(value = "Bearer"))
    Optional<Facture> findCommande(@PathVariable("id") long idFacture) {
        return factureService.retrieveFacture(idFacture);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Supprimer une facture", authorizations = @Authorization(value = "Bearer"))
    ResponseEntity<Facture> deleteFacture(@PathVariable("id") long idFacture){
        ResponseEntity<Facture> response = new ResponseEntity<>(HttpStatus.OK);
        Optional<Facture> facture= factureService.retrieveFacture(idFacture);
        if (!facture.isPresent()){
            response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else {
            factureService.deleteFacture(idFacture);
        }
        return response;
    }

    private FactureDTO convertToDTO(Facture facture) throws ParseException {
        return modelMapper.map(facture, FactureDTO.class);
    }
}