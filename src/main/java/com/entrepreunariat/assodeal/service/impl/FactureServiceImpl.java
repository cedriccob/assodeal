package com.entrepreunariat.assodeal.service.impl;

import com.entrepreunariat.assodeal.dao.FactureRepository;
import com.entrepreunariat.assodeal.model.Facture;
import com.entrepreunariat.assodeal.model.dto.FactureDTO;
import com.entrepreunariat.assodeal.service.FactureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class FactureServiceImpl implements FactureService {

    @Autowired
    FactureRepository factureRepository;

    @Override
    public List<Facture> findAllFacture() {
        return factureRepository.findAll();
    }

    @Override
    public Optional<Facture> retrieveFacture(Long id) {
        return factureRepository.findById(id);
    }

    @Override
    public Facture saveFacture(FactureDTO factureDTO) {
        Facture facture = new Facture();
        facture.setDateFacture(factureDTO.getDateFacture());
        facture.setIdFacture(factureDTO.getIdFacture());
        facture.setMontantFacture(factureDTO.getMontantFacture());
        facture.setNumeroFacture(factureDTO.getNumeroFacture());
        return factureRepository.save(facture);
    }

    @Override
    public void deleteFacture(Long id)
    {
        factureRepository.deleteByIdFacture(id);
    }
}
