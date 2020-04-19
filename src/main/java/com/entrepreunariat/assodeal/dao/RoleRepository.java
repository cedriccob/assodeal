package com.entrepreunariat.assodeal.dao;

import com.entrepreunariat.assodeal.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    List<Role> findAll();
    Role findByNameRole(String nameRole);
    Role save(Role role);

    @Override
    Optional<Role> findById(Long id);
}
