package com.entrepreunariat.assodeal.model.jwt;

import java.io.Serializable;

public class JwtResponse implements Serializable {
    private static final long serialVersionUID = 3367021186899500169L;

    private final String jwttoken;

    public JwtResponse(String jwttoken) {
        this.jwttoken = jwttoken;
    }

    public String getToken() {
        return this.jwttoken;
    }
}
