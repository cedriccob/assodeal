package com.entrepreunariat.assodeal.service;

import com.entrepreunariat.assodeal.model.Facture;
import com.entrepreunariat.assodeal.model.dto.FactureDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface FactureService {
    List<Facture> findAllFacture();
    Optional<Facture> retrieveFacture(Long id);
    Facture saveFacture(FactureDTO factureDTO);
    void deleteFacture(Long id);

}
