package com.entrepreunariat.assodeal.service.impl;

import com.entrepreunariat.assodeal.dao.UserRepository;
import com.entrepreunariat.assodeal.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.HashSet;
import java.util.Set;

public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(UserDetailsService.class);

    @Override
    public UserDetails loadUserByUsername(String pseudo){
        User user = userRepository.findByPseudo(pseudo);
        LOGGER.info("user pseudo {}", user.getPseudo());
        final Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        grantedAuthorities.add(new SimpleGrantedAuthority(user.getRoleUser().getRole()));
        return new org.springframework.security.core.userdetails.User(user.getPseudo(), user.getPassword(),
                grantedAuthorities);

    }
}
