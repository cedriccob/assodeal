package com.entrepreunariat.assodeal.dao;

import com.entrepreunariat.assodeal.model.Role;

import java.util.List;

public interface RoleCustomRepository {
    List<Role> findAllNotAdmin();
}
