package com.entrepreunariat.assodeal.service.impl;

import com.entrepreunariat.assodeal.dao.CategorieProduitCustomRepository;
import com.entrepreunariat.assodeal.dao.CategorieProduitRepository;
import com.entrepreunariat.assodeal.model.CategorieProduit;
import com.entrepreunariat.assodeal.model.dto.CategorieProduitDTO;
import com.entrepreunariat.assodeal.service.CategorieProduitService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CategorieProduitServiceImpl implements CategorieProduitService {

    @Autowired
    CategorieProduitRepository categorieProduitRepository;

    @Autowired
    CategorieProduitCustomRepository categorieProduitCustomRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public List<CategorieProduit> findAllCategoriesProduit() {
        return categorieProduitRepository.findAll();
    }

    @Override
    public List<CategorieProduit> findAllSearch(String searchValue) {
        return categorieProduitCustomRepository.findSearch(searchValue);
    }

    @Override
    public Optional<CategorieProduit> retrieveCategorieProduit(Long id) {
        return categorieProduitRepository.findById(id);
    }

    @Override
    public CategorieProduit saveCategorieProduit(CategorieProduitDTO categorieProduitDTO) {
        CategorieProduit categorieProduit = new CategorieProduit();
        categorieProduit.setIdCategorieProduit(categorieProduitDTO.getIdCategorieProduit());
        categorieProduit.setLibelleCategorieProduit(categorieProduitDTO.getLibelleCategorieProduit());
        return categorieProduitRepository.save(categorieProduit);
    }

    @Override
    public void deleteCategorieProduit(Long idCategorieProduit) {
        categorieProduitRepository.deleteByIdCategorieProduit(idCategorieProduit);
    }

    @Override
    public CategorieProduitDTO convertCategorieProduitToDTO(CategorieProduit categorieProduit) throws ParseException {
        return modelMapper.map(categorieProduit, CategorieProduitDTO.class);
    }

    @Override
    public CategorieProduit convertCategorieProduitToEntity(CategorieProduitDTO categorieProduitDto) throws ParseException {
        return modelMapper.map(categorieProduitDto, CategorieProduit.class);
    }
}
