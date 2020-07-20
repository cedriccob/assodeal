package com.entrepreunariat.assodeal.dao;

import com.entrepreunariat.assodeal.model.Produit;

import java.util.List;

public interface ProduitCustomRepository {

    List<Produit> findSearch(String searchValue);
}
