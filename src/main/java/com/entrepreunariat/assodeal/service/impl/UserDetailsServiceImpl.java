package com.entrepreunariat.assodeal.service.impl;

import com.entrepreunariat.assodeal.dao.ApplicationRepository;
import com.entrepreunariat.assodeal.model.Application;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static java.util.Collections.emptyList;

    @Service
    public class UserDetailsServiceImpl implements UserDetailsService {
        private ApplicationRepository applicationRepository;

        public UserDetailsServiceImpl(ApplicationRepository applicationRepository) {
            this.applicationRepository = applicationRepository;
        }

        @Override
        public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            Application application = applicationRepository.findByUsername(username);
            if (application == null) {
                throw new UsernameNotFoundException(username);
            }
            return new User(application.getUsername(), application.getPassword(), emptyList());
        }
}
