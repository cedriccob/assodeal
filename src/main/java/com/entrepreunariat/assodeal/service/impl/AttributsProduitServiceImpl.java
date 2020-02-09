package com.entrepreunariat.assodeal.service.impl;

import com.entrepreunariat.assodeal.dao.AttributsProduitRepository;
import com.entrepreunariat.assodeal.model.AttributsProduit;
import com.entrepreunariat.assodeal.model.dto.AttributsProduitDTO;
import com.entrepreunariat.assodeal.service.AttributsProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.Attr;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AttributsProduitServiceImpl implements AttributsProduitService {

    @Autowired
    AttributsProduitRepository attributsProduitRepository;

    @Override
    public List<AttributsProduit> findAllAttributsProduit() {
        return attributsProduitRepository.findAll();
    }

    @Override
    public Optional<AttributsProduit> retrieveAttributProduit(Long id) {
        return attributsProduitRepository.findById(id);
    }

    @Override
    public AttributsProduit saveAttributProduit(AttributsProduitDTO attributsProduitDTO) {
        AttributsProduit attributsProduit = new AttributsProduit();
        attributsProduit.setIdAttributProduit(attributsProduitDTO.getIdAttributProduit());
        attributsProduit.setCouleurProduit(attributsProduitDTO.getCouleurProduit());
        attributsProduit.setPoidsProduit(attributsProduitDTO.getPoidsProduit());
        return attributsProduitRepository.save(attributsProduit);
    }

    @Override
    public void deleteAttributProduit(Long idAttributsProduit) {
        attributsProduitRepository.deleteByIdAttributProduit(idAttributsProduit);
    }
}
