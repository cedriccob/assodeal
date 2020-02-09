package com.entrepreunariat.assodeal.service;

import com.entrepreunariat.assodeal.model.Produit;
import com.entrepreunariat.assodeal.model.User;
import com.entrepreunariat.assodeal.model.dto.UserDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UserService {

    List<User> findAllUsers();
    Optional<User> findUser(Long id);
    User saveUser(UserDTO userDTO);
    void deleteUser(Long idUser);

}
