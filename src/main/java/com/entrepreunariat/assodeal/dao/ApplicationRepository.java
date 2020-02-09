package com.entrepreunariat.assodeal.dao;

import com.entrepreunariat.assodeal.model.Application;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
        Application findByUsername(String username);
}
