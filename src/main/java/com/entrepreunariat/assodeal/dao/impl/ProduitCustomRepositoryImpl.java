package com.entrepreunariat.assodeal.dao.impl;

import com.entrepreunariat.assodeal.dao.ProduitCustomRepository;
import com.entrepreunariat.assodeal.model.Produit;
import com.entrepreunariat.assodeal.model.QProduit;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
@Repository
public class ProduitCustomRepositoryImpl implements ProduitCustomRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Produit> findSearch(String searchValue) {
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
        QProduit produit = QProduit.produit;
        return queryFactory.selectFrom(produit).where(
                produit.detailProduit.like("%"+searchValue+"%")
                        .or(produit.libelleProduit.like("%"+searchValue+"%")
                                .or(produit.prixReelProduit.like("%"+searchValue+"%")
                                .or(produit.prixVenteProduit.like("%"+searchValue+"%")
                                .or(produit.qtStockProduit.like("%"+searchValue+"%")
                                .or(produit.categorieProduit.libelleCategorieProduit.like("%"+searchValue+"%"))))))).fetch();
    }
}
