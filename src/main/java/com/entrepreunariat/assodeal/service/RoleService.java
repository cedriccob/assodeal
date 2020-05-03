package com.entrepreunariat.assodeal.service;

import com.entrepreunariat.assodeal.model.Role;
import com.entrepreunariat.assodeal.model.dto.RoleDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RoleService {
    List<Role> findAllRole();
    List<Role> findAllRoleForRegister();
    Role saveRole(RoleDTO roleDTO);
    Optional<Role> findRole(Long idRole);
    Role findRoleByName(String nameRole);
}
