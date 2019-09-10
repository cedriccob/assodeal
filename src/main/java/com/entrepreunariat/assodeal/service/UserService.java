package com.entrepreunariat.assodeal.service;

import com.entrepreunariat.assodeal.model.User;

public interface UserService {

    User findUserByName(String pseudo);
    void saveUser(User user);
    User findLastUser();

}
