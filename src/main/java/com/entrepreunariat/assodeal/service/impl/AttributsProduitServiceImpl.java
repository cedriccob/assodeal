package com.entrepreunariat.assodeal.service.impl;

import com.entrepreunariat.assodeal.dao.AttributsProduitCustomRepository;
import com.entrepreunariat.assodeal.dao.AttributsProduitRepository;
import com.entrepreunariat.assodeal.model.AttributsProduit;
import com.entrepreunariat.assodeal.model.dto.AttributsProduitDTO;
import com.entrepreunariat.assodeal.service.AttributsProduitService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.text.ParseException;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AttributsProduitServiceImpl implements AttributsProduitService {

    @Autowired
    AttributsProduitRepository attributsProduitRepository;

    @Autowired
    AttributsProduitCustomRepository attributsProduitCustomRepository;

    @Autowired
    ModelMapper modelMapper;

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
        attributsProduit.setIdAttributProduit(attributsProduitDTO.getIdAttributsProduit());
        attributsProduit.setCouleurProduit(attributsProduitDTO.getCouleurProduit());
        attributsProduit.setPoidsProduit(attributsProduitDTO.getPoidsProduit());
        attributsProduit.setAbreviationProduit(attributsProduitDTO.getAbreviationProduit());
        return attributsProduitRepository.save(attributsProduit);
    }

    @Override
    public void deleteAttributProduit(Long idAttributsProduit) {
        attributsProduitRepository.deleteByIdAttributProduit(idAttributsProduit);
    }

    @Override
    public List<AttributsProduit> findAllSearch(String searchValue) {
        return attributsProduitCustomRepository.findSearch(searchValue);
    }

    @Override
    public AttributsProduitDTO convertAttributsProduitToDTO(AttributsProduit attributsProduit) throws ParseException {
        return modelMapper.map(attributsProduit, AttributsProduitDTO.class);
    }

    @Override
    public AttributsProduit convertAttributsProduitToEntity(AttributsProduitDTO attributsProduitDto) throws ParseException {
        return modelMapper.map(attributsProduitDto, AttributsProduit.class);
    }
}
