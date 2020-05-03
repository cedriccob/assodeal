package com.entrepreunariat.assodeal.dao.impl;

import com.entrepreunariat.assodeal.dao.RoleCustomRepository;
import com.entrepreunariat.assodeal.model.QRole;
import com.entrepreunariat.assodeal.model.Role;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
@Repository
public class RoleCustomRepositoryImpl implements RoleCustomRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Role> findAllNotAdmin() {
        QRole role = QRole.role;
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
        return queryFactory.selectFrom(role).where(role.codeRole.ne("adm")).fetch();
     }
}
