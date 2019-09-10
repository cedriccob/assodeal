package com.entrepreunariat.assodeal.dao;

import com.entrepreunariat.assodeal.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByPseudo(String pseudo);

    User findFirstByOrderByIdUserDesc();
}
