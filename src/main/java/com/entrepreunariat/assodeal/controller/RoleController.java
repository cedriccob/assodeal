package com.entrepreunariat.assodeal.controller;

import com.entrepreunariat.assodeal.model.Role;
import com.entrepreunariat.assodeal.model.dto.RoleDTO;
import com.entrepreunariat.assodeal.service.RoleService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/roles")
public class RoleController {

    @Autowired
    RoleService roleService;

    private static final Logger LOGGER = LoggerFactory.getLogger(RoleController.class);

    @ResponseBody
    @GetMapping("/all")
    @ApiOperation(value = "Afficher tous les rôles")
    List<Role> findAll() {

        return roleService.findAllRole();
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Ajouter un rôle", authorizations = @Authorization(value = "Bearer"))
    ResponseEntity<Role> addRole(@RequestBody RoleDTO roleDTO) {
        ResponseEntity<Role> response = new ResponseEntity<>(HttpStatus.CREATED);
        try {
            Role role = roleService.findRoleByName(roleDTO.getNameRole());
            if(role == null){
                roleService.saveRole(roleDTO);
            }
            else{
                response = new ResponseEntity<>(HttpStatus.CONFLICT);
            }
        } catch (Exception exception) {
            LOGGER.error("Erreur ajout role", exception);
            response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Modifier un role", authorizations = @Authorization(value = "Bearer"))
    ResponseEntity<Role> updateRole(@RequestBody RoleDTO newRole, @PathVariable("id") long idRole) {
        ResponseEntity<Role> response = new ResponseEntity<>(HttpStatus.OK);
        Optional<Role> role = roleService.findRole(idRole);
        if (!role.isPresent()) {
            LOGGER.error("Mise à jour impossible, ce role n'existe pas");
            response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
                role.get().setNameRole(newRole.getNameRole());
        }
        return response;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Retrouver un rôle")
    Optional<Role> findRole(@PathVariable("id") long idRole) {
        return roleService.findRole(idRole);
    }
}
