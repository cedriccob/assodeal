package com.entrepreunariat.assodeal.service.impl;

import com.entrepreunariat.assodeal.dao.RoleRepository;
import com.entrepreunariat.assodeal.model.QRole;
import com.entrepreunariat.assodeal.model.Role;
import com.entrepreunariat.assodeal.model.dto.RoleDTO;
import com.entrepreunariat.assodeal.service.RoleService;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleRepository roleRepository;

    @Override
    public List<Role> findAllRole() {
        List<Role> listRole = new ArrayList<>();
        BooleanExpression booleanExpression = QRole.role.codeRole.ne("adm");
        Iterable<Role> roles = roleRepository.findAll(booleanExpression);

        roles.forEach(listRole::add);

        return listRole;
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
