/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.apirest.portfolio.security.jwt;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

/**
 *
 * @author PCcito
 */

//comprueba si hay un token valido, si no devuelve un respuesta
//de que no esta autorizado
@Component
public class JwtEntryPoint implements AuthenticationEntryPoint {
    
    //logger para uso en el desarrollo, para ver que metodo da error
    private final static Logger logger = LoggerFactory.getLogger(JwtEntryPoint.class);
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        logger.error("error en el metodo commence del jwtentrypoint");
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "No autorizado.");
    }
    
}
