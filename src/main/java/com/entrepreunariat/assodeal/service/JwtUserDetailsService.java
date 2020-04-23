package com.entrepreunariat.assodeal.service;

import com.entrepreunariat.assodeal.dao.UserRepository;
import com.entrepreunariat.assodeal.model.User;
import com.entrepreunariat.assodeal.model.dto.UserDTO;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.HashSet;
import java.util.Set;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;


    @Autowired
    private ModelMapper modelMapper;

    private static final Logger LOGGER = LoggerFactory.getLogger(JwtUserDetailsService.class);


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.entrepreunariat.assodeal.model.User user = userRepository.findByUsername(username);

        final Set<GrantedAuthority> grantedAuthorities = new HashSet<>();

        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        grantedAuthorities.add(new SimpleGrantedAuthority(user.getRole().getNameRole()));
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), user.isEnabled(),
                true, true, true, grantedAuthorities);
    }

    public User save(UserDTO userDTO) {
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
        user.setUsername(userDTO.getUsername());
        user.setPrenom(userDTO.getPrenom());
        user.setStatus(userDTO.getStatus());
        user.setPassword(userDTO.getPassword());
        user.setEnabled(userDTO.isEnabled());
        user.setRole(userDTO.getRole());
        return userRepository.save(user);
    }

    public User findExistingMailOrUsername(String mail, String username) {
        return userRepository.findByMailOrUsernameIgnoreCase(mail, username);
    }

    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

}
