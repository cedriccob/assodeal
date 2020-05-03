package com.entrepreunariat.assodeal.service.impl;

import com.entrepreunariat.assodeal.dao.RoleCustomRepository;
import com.entrepreunariat.assodeal.dao.RoleRepository;
import com.entrepreunariat.assodeal.model.QRole;
import com.entrepreunariat.assodeal.model.Role;
import com.entrepreunariat.assodeal.model.dto.RoleDTO;
import com.entrepreunariat.assodeal.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleRepository roleRepository;
    @Autowired
    RoleCustomRepository roleCustomRepository;

    @Override
    public List<Role> findAllRole() {
        return roleRepository.findAll();
    }

    @Override
    public List<Role> findAllRoleForRegister() {
        return roleCustomRepository.findAllNotAdmin();
    }

    @Override
    public Role saveRole(RoleDTO roleDTO) {
        Role role = new Role();
        role.setIdRole(roleDTO.getIdRole());
        role.setNameRole(roleDTO.getNameRole());
        return roleRepository.save(role);
    }

    @Override
    public Optional<Role> findRole(Long idRole) {
        return roleRepository.findById(idRole);
    }

    @Override
    public Role findRoleByName(String nameRole) {
        return roleRepository.findByNameRole(nameRole);
    }

}
