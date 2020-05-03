package com.entrepreunariat.assodeal.dao;

import com.entrepreunariat.assodeal.model.CategorieProduit;

import java.util.List;

public interface CategorieProduitCustomRepository {

    List<CategorieProduit> findSearch(String searchValue);
}
