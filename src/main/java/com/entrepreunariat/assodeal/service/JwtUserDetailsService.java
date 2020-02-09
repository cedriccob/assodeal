package com.entrepreunariat.assodeal.service;

import com.entrepreunariat.assodeal.dao.ApplicationRepository;
import com.entrepreunariat.assodeal.model.Application;
import com.entrepreunariat.assodeal.model.dto.ApplicationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class JwtUserDetailsService implements UserDetailsService {
    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Application app = applicationRepository.findByUsername(username);
        if (app == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return new org.springframework.security.core.userdetails.User(app.getUsername(), app.getPassword(),
                new ArrayList<>());
    }

    public Application save(ApplicationDTO app) {
        Application newApp = new Application();
        newApp.setUsername(app.getUsername());
        newApp.setPassword(bcryptEncoder.encode(app.getPassword()));
        return applicationRepository.save(newApp);
    }
}
