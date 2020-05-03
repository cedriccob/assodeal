package com.entrepreunariat.assodeal.dao;

import com.entrepreunariat.assodeal.model.AttributsProduit;

import java.util.List;

public interface AttributsProduitCustomRepository {

    List<AttributsProduit> findSearch(String searchValue);

}
