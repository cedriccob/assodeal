package com.entrepreunariat.assodeal.service.impl;

import com.entrepreunariat.assodeal.dao.FournisseurRepository;
import com.entrepreunariat.assodeal.model.Fournisseur;
import com.entrepreunariat.assodeal.model.dto.FournisseurDTO;
import com.entrepreunariat.assodeal.service.FournisseurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class FournisseurServiceImpl implements FournisseurService {

    @Autowired
    FournisseurRepository fournisseurRepository;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Override
    public List<Fournisseur> findAllFournisseur() {
        return fournisseurRepository.findAll();
    }

    @Override
    public Optional<Fournisseur> retrieveFournisseur(Long id) {
        return fournisseurRepository.findById(id);
    }

    @Override
    public Fournisseur saveFournisseur(FournisseurDTO fournisseurDTO)
    {   Fournisseur fournisseur = new Fournisseur();
        fournisseur.setIdFournisseur(fournisseurDTO.getIdFournisseur());
        fournisseur.setNomFournisseur(fournisseurDTO.getNomFournisseur());
        fournisseur.setNumeroSiretFournisseur(fournisseurDTO.getNumeroSiretFournisseur());
        fournisseur.setStatutFournisseur(fournisseurDTO.getStatutFournisseur());
        fournisseur.setUserFournisseur(fournisseurDTO.getUserFournisseur());
        fournisseur.setPasswordFournisseur(bcryptEncoder.encode(fournisseurDTO.getPasswordFournisseur()));
        fournisseur.setConfirmPasswordFournisseur(bcryptEncoder.encode(fournisseurDTO.getConfirmPasswordFournisseur()));
        return fournisseurRepository.save(fournisseur);
    }

    @Override
    public void deleteFournisseur(Long idFournisseur) {
        fournisseurRepository.deleteByIdFournisseur(idFournisseur);
    }
}
