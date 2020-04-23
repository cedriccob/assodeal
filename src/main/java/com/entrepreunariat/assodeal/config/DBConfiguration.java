package com.entrepreunariat.assodeal.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@ConfigurationProperties("spring.datasource")
public class DBConfiguration {

    private String driverClassName;
    private String url;
    private String userName;
    private String password;
    private static final Logger LOGGER = LoggerFactory.getLogger(DBConfiguration.class);

    @Profile("dev")
    @Bean
    public String devDataBaseConnection(){
        LOGGER.info("DB connection for dev MYSQL");
        LOGGER.info("username {}", userName);
        return "url dev "+url;
    }

    @Profile("prod")
    @Bean
    public String prodDataBaseConnection(){
        LOGGER.info("DB connection for prod MYSQL");
        LOGGER.info("username {}", userName);
        return "url prod "+url;
    }


}
