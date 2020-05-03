package com.entrepreunariat.assodeal.dao.impl;

import com.entrepreunariat.assodeal.dao.CategorieProduitCustomRepository;
import com.entrepreunariat.assodeal.model.CategorieProduit;
import com.entrepreunariat.assodeal.model.QCategorieProduit;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
@Repository
public class CategorieProduitCustomRepositoryImpl implements CategorieProduitCustomRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<CategorieProduit> findSearch(String searchValue) {
            JPAQueryFactory queryFactory = new JPAQueryFactory(em);
        QCategorieProduit categorieProduit = QCategorieProduit.categorieProduit;
        return queryFactory.selectFrom(categorieProduit).where(
                categorieProduit.idCategorieProduit.like("%"+searchValue+"%")
                        .or(categorieProduit.libelleCategorieProduit.like("%"+searchValue+"%"))).fetch();

    }
}
