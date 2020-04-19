package com.entrepreunariat.assodeal.service.impl;

import com.entrepreunariat.assodeal.controller.AttributsProduitController;
import com.entrepreunariat.assodeal.dao.CommandeRepository;
import com.entrepreunariat.assodeal.model.Commande;
import com.entrepreunariat.assodeal.model.Produit;
import com.entrepreunariat.assodeal.model.dto.CommandeDTO;
import com.entrepreunariat.assodeal.service.CommandeService;
import com.entrepreunariat.assodeal.service.ProduitService;
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
public class CommandeServiceImpl implements CommandeService {

    @Autowired
    CommandeRepository commandeRepository;

    @Autowired
    ProduitService produitService;

    private static final Logger LOGGER = LoggerFactory.getLogger(CommandeServiceImpl.class);


    @Override
    public List<Commande> findAllCommande() {
        return commandeRepository.findAll();
    }

    @Override
    public Optional<Commande> retrieveCommande(Long id) {
        return commandeRepository.findById(id);
    }

    @Override
    public Commande saveCommande(CommandeDTO commandeDTO)
    {   Commande commande = new Commande();
        try {
            commande.setDateCommande(commandeDTO.getDateCommande());
            commande.setProduit(produitService.convertProduitToEntity(commandeDTO.getProduit()));
            commande.setQuantiteCommande(commandeDTO.getQuantiteCommande());
            commande.setStatusCommande(commandeDTO.getStatusCommande());
        }
        catch (ParseException exception){
            LOGGER.error("erreur service commande");
        }
        return commandeRepository.save(commande);
    }

    @Override
    public void deleteCommande(Long idCommande)
    {
        commandeRepository.deleteByIdCommande(idCommande);
    }
}
