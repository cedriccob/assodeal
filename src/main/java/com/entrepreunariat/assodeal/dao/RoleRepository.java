package com.entrepreunariat.assodeal.dao;

import com.entrepreunariat.assodeal.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByRole(String role);
}
