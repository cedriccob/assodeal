package com.entrepreunariat.assodeal.dao.impl;

import com.entrepreunariat.assodeal.dao.AttributsProduitCustomRepository;
import com.entrepreunariat.assodeal.model.AttributsProduit;
import com.entrepreunariat.assodeal.model.QAttributsProduit;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
@Repository
public class AttributsProduitCustomRepositoryImpl implements AttributsProduitCustomRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<AttributsProduit> findSearch(String searchValue) {
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
        QAttributsProduit attributsProduit = QAttributsProduit.attributsProduit;
        return queryFactory.selectFrom(attributsProduit).where(
                attributsProduit.couleurProduit.like("%"+searchValue+"%").or(attributsProduit.poidsProduit.like("%"+searchValue+"%")
                        .or(attributsProduit.abreviationProduit.like("%"+searchValue+"%")))).fetch();
    }
}
