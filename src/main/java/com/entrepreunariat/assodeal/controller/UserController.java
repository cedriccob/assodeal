package com.entrepreunariat.assodeal.controller;

import com.entrepreunariat.assodeal.model.User;
import com.entrepreunariat.assodeal.model.dto.UserDTO;
import com.entrepreunariat.assodeal.service.JwtUserDetailsService;
import com.entrepreunariat.assodeal.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
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
@Api(value="User", description = "Opération sur les utilisateurs",
        authorizations = @Authorization(value = "Bearer"))
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    private JwtUserDetailsService userDetailsService;

    @Autowired
    ModelMapper modelMapper;

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @ResponseBody
    @GetMapping("/all")
    @ApiOperation(value = "Récupérer tous les utilisateurs", authorizations = @Authorization(value = "Bearer"))
    List<User> findAll() {
        return userService.findAllUsers();
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Modifier un utilisateur", authorizations = @Authorization(value = "Bearer"))
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
            user.get().setConfirmPassword(userDTO.getConfirmPassword());
            user.get().setPrenom(userDTO.getPrenom());
            user.get().setUsername(userDTO.getUsername());
            user.get().setNom(userDTO.getNom());
            user.get().setMail(userDTO.getMail());
            user.get().setDateEnregistrement(userDTO.getDateEnregistrement());
            user.get().setEnabled(userDTO.isEnabled());
            user.get().setContact(userDTO.getContact());
            try {
                if(convertToDTO(user.get()).getPassword().equals(convertToDTO(user.get()).getConfirmPassword())) {
                    userDetailsService.save(convertToDTO(user.get()));
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


    @GetMapping("/{username}")
    @ApiOperation(value = "Retrouver un utilisateur par son username", authorizations = @Authorization(value = "Bearer"))
    ResponseEntity<User> findUserByName(@PathVariable("username") String username) {
        User user = userService.findUserByUsername(username);
        ResponseEntity<User> response = ResponseEntity.ok(user);
        if(user==null){
            response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Supprimer un utilisateur", authorizations = @Authorization(value = "Bearer"))
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
