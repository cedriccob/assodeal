package com.entrepreunariat.assodeal.dao;

import com.entrepreunariat.assodeal.model.Commande;

import java.util.List;

public interface CommandeCustomRepository {

    List<Commande> findSearch(String searchValue);

}
