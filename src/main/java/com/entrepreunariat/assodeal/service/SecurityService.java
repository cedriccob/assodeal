package com.entrepreunariat.assodeal.service;

public interface SecurityService {

    public void autologin(final String pseudo, final String password);

    public String findLoggedInUsername();

}
