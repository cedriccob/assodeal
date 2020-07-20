package com.entrepreunariat.assodeal.dao.impl;

import com.entrepreunariat.assodeal.dao.CommandeCustomRepository;
import com.entrepreunariat.assodeal.dao.CommandeRepository;
import com.entrepreunariat.assodeal.model.Commande;
import com.entrepreunariat.assodeal.model.QCommande;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class CommandeCustomRepositoryImpl implements CommandeCustomRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Commande> findSearch(String searchValue) {
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
        QCommande commande = QCommande.commande;
        return queryFactory.selectFrom(commande).where(
                commande.quantiteCommande.like("%"+searchValue+"%")
                        .or(commande.statusCommande.like("%"+searchValue+"%").or(
                                commande.statusCommande.like("%"+searchValue+"%")
                        ))).fetch();

    }
}
