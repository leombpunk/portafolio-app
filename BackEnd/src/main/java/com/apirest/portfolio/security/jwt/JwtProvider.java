/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.apirest.portfolio.security.jwt;

import com.apirest.portfolio.security.model.UsuarioPrincipal;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

/**
 *
 * @author PCcito
 */

//genera el token y valida que este bien formado, no expirdao, firmado, etc
@Component
public class JwtProvider {
    private final static Logger logger = LoggerFactory.getLogger(JwtProvider.class);
    
    //valores en el aplicationproperty
    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.expiration}")
    private int expiration;
    
    public String generateToken(Authentication auth){
        //castea auth a UsuarioPrincipal
        UsuarioPrincipal usuPrincipal = (UsuarioPrincipal) auth.getPrincipal();
        //construccion del token
        return Jwts.builder()
                .setSubject(usuPrincipal.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + expiration * 1000))
                .signWith(SignatureAlgorithm.HS512, secret).compact();
    }
    
    public String getUsuarioFromToken(String token){
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
    
    public boolean validateToken(String token){
        try{
            Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token);
            return true;
        } catch(MalformedJwtException e){
            logger.error("El token esta mal formado");
        } catch(UnsupportedJwtException e){
            logger.error("El token no esta soportado");
        } catch(ExpiredJwtException e){
            logger.error("El token ha expirado");
        } catch(IllegalArgumentException e){
            logger.error("El token esta vacio");
        } catch(SignatureException e){
            logger.error("El token tiene un fallo en la firma");
        }
        return false;
    }
}
