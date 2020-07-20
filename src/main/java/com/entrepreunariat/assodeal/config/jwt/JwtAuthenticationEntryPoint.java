package com.entrepreunariat.assodeal.config.jwt;

import com.entrepreunariat.assodeal.controller.RoleController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint, Serializable {

    private static final long serialVersionUID = 8586382934475859763L;

    private static final Logger LOGGER = LoggerFactory.getLogger(JwtAuthenticationEntryPoint.class);


    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        final String expired = (String) httpServletRequest.getAttribute("expired");
        if (expired!=null){
            httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED,expired);
        }else{
            httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED,"Invalid Login details");
        }

    }
}
