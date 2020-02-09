package com.entrepreunariat.assodeal.service;

import com.entrepreunariat.assodeal.model.Fournisseur;
import com.entrepreunariat.assodeal.model.dto.FournisseurDTO;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

public interface FournisseurService {
    List<Fournisseur> findAllFournisseur();
    Optional<Fournisseur> retrieveFournisseur(Long id);
    Fournisseur saveFournisseur(FournisseurDTO fournisseurDTO);
    void deleteFournisseur(Long idFournisseur);

}
