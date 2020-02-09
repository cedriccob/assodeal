package com.entrepreunariat.assodeal.service.impl;

import com.entrepreunariat.assodeal.dao.UserRepository;
import com.entrepreunariat.assodeal.model.User;
import com.entrepreunariat.assodeal.model.dto.UserDTO;
import com.entrepreunariat.assodeal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findUser(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public User saveUser(UserDTO userDTO)
    {
        User user = new User();
        user.setIdUser(userDTO.getIdUser());
        user.setAdresse(userDTO.getAdresse());
        user.setContact(userDTO.getContact());
        user.setDateDernierLogin(userDTO.getDateDernierLogin());
        user.setDateEnregistrement(userDTO.getDateEnregistrement());
        user.setMail(userDTO.getMail());
        user.setNationalite(userDTO.getNationalite());
        user.setVille(userDTO.getVille());
        user.setPaysResidence(userDTO.getPaysResidence());
        user.setNom(userDTO.getNom());
        user.setPseudo(userDTO.getPseudo());
        user.setPrenom(userDTO.getPrenom());
        user.setStatus(userDTO.getStatus());
        user.setPassword(bcryptEncoder.encode(userDTO.getPassword()));
        user.setConfirmPassword(bcryptEncoder.encode(userDTO.getConfirmPassword()));
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(Long idUser) {
        userRepository.deleteByIdUser(idUser);
    }
}
