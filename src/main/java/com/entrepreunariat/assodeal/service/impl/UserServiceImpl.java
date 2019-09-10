package com.entrepreunariat.assodeal.service.impl;

import com.entrepreunariat.assodeal.dao.RoleRepository;
import com.entrepreunariat.assodeal.dao.UserRepository;
import com.entrepreunariat.assodeal.model.User;
import com.entrepreunariat.assodeal.service.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }
    @Override
    public User findUserByName(String pseudo) {
        return userRepository.findByPseudo(pseudo);
    }
    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Override
    public User findLastUser() {
        return userRepository.findFirstByOrderByIdUserDesc();
    }
}
