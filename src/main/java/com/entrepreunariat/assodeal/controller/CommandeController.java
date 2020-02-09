package com.entrepreunariat.assodeal.controller;

import com.entrepreunariat.assodeal.model.Commande;
import com.entrepreunariat.assodeal.model.dto.CommandeDTO;
import com.entrepreunariat.assodeal.service.CommandeService;
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
@RequestMapping("/commande")
public class CommandeController {

    @Autowired
    CommandeService commandeService;

    @Autowired
    ModelMapper modelMapper;

    private static final Logger LOGGER = LoggerFactory.getLogger(CommandeController.class);

    @ResponseBody
    @GetMapping("/all")
    List<Commande> findAll() {
        return commandeService.findAllCommande();
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    ResponseEntity<Commande> addCommande(@RequestBody CommandeDTO commandeDTO) {
        ResponseEntity<Commande> response = new ResponseEntity<>(HttpStatus.CREATED);
        try {
            commandeService.saveCommande(commandeDTO);
        } catch (Exception exception) {
            LOGGER.error("Erreur ajout commande", exception);
            response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }

    @PutMapping("/{id}")
    ResponseEntity<Commande> updateCommande(@RequestBody CommandeDTO commandeDTO, @PathVariable("id") long idCommande) {
        ResponseEntity<Commande> response = new ResponseEntity<>(HttpStatus.OK);
        Optional<Commande> commande = commandeService.retrieveCommande(idCommande);
        if (!commande.isPresent()) {
            response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
            LOGGER.error("Mise à jour impossible, cette commande n'existe pas");
        } else {
            commande.get().setStatusCommande(commandeDTO.getStatusCommande());
            commande.get().setQuantiteCommande(commandeDTO.getQuantiteCommande());
            commande.get().setProduit(commandeDTO.getProduit());
            commande.get().setDateCommande(commandeDTO.getDateCommande());
            try {
                commandeService.saveCommande(convertToDTO(commande.get()));
            } catch (ParseException e) {
                LOGGER.error("erreur parse update");
                response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
            return response;
    }

    @GetMapping("/{id}")
    Optional<Commande> findCommande(@PathVariable("id") long idCommande) {
        return commandeService.retrieveCommande(idCommande);
    }

    @DeleteMapping("/delete/{id}")
    ResponseEntity<Commande> deleteCommande(@PathVariable("id") long idCommande) {
        ResponseEntity<Commande> response = new ResponseEntity<>(HttpStatus.OK);
        Optional<Commande> commande = commandeService.retrieveCommande(idCommande);
        if (!commande.isPresent()) {
            response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
            LOGGER.error("Suppression impossible, cette commande n'existe pas");
        } else {
            commandeService.deleteCommande(idCommande);
        }
        return response;
    }

    private CommandeDTO convertToDTO(Commande commande) throws ParseException {
        return modelMapper.map(commande, CommandeDTO.class);
    }


}
