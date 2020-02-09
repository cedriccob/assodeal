package com.entrepreunariat.assodeal.service;

import com.entrepreunariat.assodeal.model.Commande;
import com.entrepreunariat.assodeal.model.dto.CommandeDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface CommandeService {
    List<Commande> findAllCommande();
    Optional<Commande> retrieveCommande(Long idCommande);
    Commande saveCommande(CommandeDTO commande);
    void deleteCommande(Long idCommande);
}
