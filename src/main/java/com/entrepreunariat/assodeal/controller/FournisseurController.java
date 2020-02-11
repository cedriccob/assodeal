package com.entrepreunariat.assodeal.controller;

import com.entrepreunariat.assodeal.model.Fournisseur;
import com.entrepreunariat.assodeal.model.dto.FournisseurDTO;
import com.entrepreunariat.assodeal.service.FournisseurService;
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
@RequestMapping("/fournisseur")
@Api(value="Fournisseur", description = "Opérations sur les fournisseurs",
        authorizations = @Authorization(value = "Bearer"))
public class FournisseurController {

    @Autowired
    FournisseurService fournisseurService;

    @Autowired
    ModelMapper modelMapper;

    private static final Logger LOGGER = LoggerFactory.getLogger(FournisseurController.class);

    @ResponseBody
    @GetMapping("/all")
    @ApiOperation(value = "Récupérer tous les fournisseurs", authorizations = @Authorization(value = "Bearer"))
    List<Fournisseur> findAll() {
        return fournisseurService.findAllFournisseur();
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Ajouter un fournisseur", authorizations = @Authorization(value = "Bearer"))
    ResponseEntity<Fournisseur> addFournisseur(@RequestBody FournisseurDTO fournisseurDTO) {
        ResponseEntity<Fournisseur> response = new ResponseEntity<>(HttpStatus.CREATED);
        try {
            if(fournisseurDTO.getPasswordFournisseur().equals(fournisseurDTO.getConfirmPasswordFournisseur())) {
                fournisseurService.saveFournisseur(fournisseurDTO);
            }
            else{
                LOGGER.error("Les mots de passe sont différents");
                response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception exception) {
            LOGGER.error("Erreur ajout fournisseur", exception);
            response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Modifier un fournisseur", authorizations = @Authorization(value = "Bearer"))
    ResponseEntity<Fournisseur> updateFournisseur(@RequestBody FournisseurDTO fournisseurDTO, @PathVariable("id") long idFournisseur) {
        ResponseEntity<Fournisseur> response = new ResponseEntity<>(HttpStatus.OK);
        Optional<Fournisseur> fournisseur = fournisseurService.retrieveFournisseur(idFournisseur);
        if (!fournisseur.isPresent()) {
            LOGGER.error("Mise à jour impossible, ce fournisseur n'existe pas");
            response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            fournisseur.get().setUserFournisseur(fournisseurDTO.getUserFournisseur());
            fournisseur.get().setStatutFournisseur(fournisseurDTO.getStatutFournisseur());
            fournisseur.get().setNumeroSiretFournisseur(fournisseurDTO.getNumeroSiretFournisseur());
            fournisseur.get().setNomFournisseur(fournisseurDTO.getNomFournisseur());
            fournisseur.get().setPasswordFournisseur(fournisseurDTO.getPasswordFournisseur());
            try {
                if(convertToDTO(fournisseur.get()).getPasswordFournisseur().equals(convertToDTO(fournisseur.get()).getConfirmPasswordFournisseur())) {
                    fournisseurService.saveFournisseur(convertToDTO(fournisseur.get()));
                }
                else{
                    LOGGER.error("Les mots de passe sont différents");
                    response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
                }
            } catch (ParseException e) {
                LOGGER.error("erreur parse update");
                response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
        return response;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Retrouver un fournisseur", authorizations = @Authorization(value = "Bearer"))
    Optional<Fournisseur> findFournisseur(@PathVariable("id") long idFournisseur) {
        return fournisseurService.retrieveFournisseur(idFournisseur);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Supprimer un fournisseur", authorizations = @Authorization(value = "Bearer"))
    ResponseEntity<Fournisseur> deleteFournisseur(@PathVariable("id") long idFournisseur){
        ResponseEntity<Fournisseur> response = new ResponseEntity<>(HttpStatus.OK);
        Optional<Fournisseur> fournisseur= fournisseurService.retrieveFournisseur(idFournisseur);
        if (!fournisseur.isPresent()){
            LOGGER.error("Suppression impossible, ce fournisseur n'existe pas");
            response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else {
            fournisseurService.deleteFournisseur(idFournisseur);
        }
        return response;
    }

    private FournisseurDTO convertToDTO(Fournisseur fournisseur) throws ParseException {
        return modelMapper.map(fournisseur, FournisseurDTO.class);
    }

}
