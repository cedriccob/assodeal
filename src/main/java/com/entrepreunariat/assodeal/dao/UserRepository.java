package com.entrepreunariat.assodeal.dao;

import com.entrepreunariat.assodeal.model.Produit;
import com.entrepreunariat.assodeal.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);
    List<User> findAll();
    Optional<User> findById(Long id);
    User save(User user);
    void deleteByIdUser(Long idUser);
    User findByMailIgnoreCase(String mail);
}
