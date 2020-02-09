package com.entrepreunariat.assodeal.service.impl;

import com.entrepreunariat.assodeal.dao.CommandeRepository;
import com.entrepreunariat.assodeal.model.Commande;
import com.entrepreunariat.assodeal.model.dto.CommandeDTO;
import com.entrepreunariat.assodeal.service.CommandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CommandeServiceImpl implements CommandeService {
    @Autowired
    CommandeRepository commandeRepository;

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
        commande.setIdCommande(commandeDTO.getIdCommande());
        commande.setDateCommande(commandeDTO.getDateCommande());
        commande.setProduit(commandeDTO.getProduit());
        commande.setQuantiteCommande(commandeDTO.getQuantiteCommande());
        commande.setStatusCommande(commandeDTO.getStatusCommande());
        return commandeRepository.save(commande);
    }

    @Override
    public void deleteCommande(Long idCommande)
    {
        commandeRepository.deleteByIdCommande(idCommande);
    }
}
