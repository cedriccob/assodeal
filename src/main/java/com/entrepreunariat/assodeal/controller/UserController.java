package com.entrepreunariat.assodeal.controller;

import com.entrepreunariat.assodeal.model.AttributsProduit;
import com.entrepreunariat.assodeal.model.User;
import com.entrepreunariat.assodeal.model.dto.AttributsProduitDTO;
import com.entrepreunariat.assodeal.model.dto.UserDTO;
import com.entrepreunariat.assodeal.service.AttributsProduitService;
import com.entrepreunariat.assodeal.service.UserService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    ModelMapper modelMapper;

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @ResponseBody
    @GetMapping("/all")
    List<User> findAll() {
        return userService.findAllUsers();
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    ResponseEntity<User> addUser(@RequestBody UserDTO userDTO) {
        ResponseEntity<User> response = new ResponseEntity<>(HttpStatus.CREATED);
        try {
            if(userDTO.getPassword().equals(userDTO.getConfirmPassword())) {
                userService.saveUser(userDTO);
            }
            else{
                LOGGER.error("Les mots de passe sont différents");
                response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
            userService.saveUser(userDTO);
        } catch (Exception exception) {
            LOGGER.error("Erreur ajout user", exception);
            response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }

    @PutMapping("/{id}")
    ResponseEntity<User> updateUser(@RequestBody UserDTO userDTO, @PathVariable("id") long idUser) {
        ResponseEntity<User> response = new ResponseEntity<>(HttpStatus.OK);
        Optional<User> user =  userService.findUser(idUser);
        if(!user.isPresent()){
            LOGGER.error("Mise à jour impossible, cet utilisateur n'existe pas");
            response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else {
            user.get().setStatus(userDTO.getStatus());
            user.get().setPassword(userDTO.getPassword());
            user.get().setPrenom(userDTO.getPrenom());
            user.get().setPseudo(userDTO.getPseudo());
            user.get().setNom(userDTO.getNom());
            user.get().setPaysResidence(userDTO.getPaysResidence());
            user.get().setVille(userDTO.getVille());
            user.get().setNationalite(userDTO.getNationalite());
            user.get().setMail(userDTO.getMail());
            user.get().setDateEnregistrement(userDTO.getDateEnregistrement());
            user.get().setDateDernierLogin(userDTO.getDateDernierLogin());
            user.get().setContact(userDTO.getContact());
            user.get().setAdresse(userDTO.getAdresse());
            try {
                if(convertToDTO(user.get()).getPassword().equals(convertToDTO(user.get()).getConfirmPassword())) {
                    userService.saveUser(convertToDTO(user.get()));
                }
                else{
                    LOGGER.error("Les mots de passe sont différents");
                    response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
                }
            } catch (ParseException e) {
                LOGGER.error("erreur parse update");
                response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
        return response;

    }

    @GetMapping("/{id}")
    Optional<User> findUser(@PathVariable("id") long idUser) {
        return userService.findUser(idUser);
    }

    @DeleteMapping("/delete/{id}")
    ResponseEntity<User> deleteUser(@PathVariable("id") long idUser){
        ResponseEntity<User> response = new ResponseEntity<>(HttpStatus.OK);
        Optional<User> user= userService.findUser(idUser);
        if (!user.isPresent()){
            response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else {
            userService.deleteUser(idUser);
        }
        return response;
    }

    private UserDTO convertToDTO(User user) throws ParseException {
        return modelMapper.map(user, UserDTO.class);
    }

}
