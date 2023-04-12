/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.apirest.portfolio.security.model;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 *
 * @author PCcito
 */

public class UsuarioPrincipal implements UserDetails {
    //implementa los privilegios de cada usuario
    private String usuario;
    private String contrasena;
    //convierto la clase rol en aothorities
    //clase especifica de seguridad propia de sprin boot/spring security
    private Collection<? extends GrantedAuthority> authorities;

    public UsuarioPrincipal(String usuario, String contrasena, Collection<? extends GrantedAuthority> authorities) {
        this.usuario = usuario;
        this.contrasena = contrasena;
        this.authorities = authorities;
    }

    //devuelve un usuarioPrincipal con los permisos
    public static UsuarioPrincipal build(Usuario usuario){
        List<GrantedAuthority> authorities = usuario
                .getRoles()
                .stream()
                .map(rol -> new SimpleGrantedAuthority(rol.getRolNombre().name()))
                .collect(Collectors.toList());
        return new UsuarioPrincipal(usuario.getUsuario(), usuario.getContrasena(), authorities);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return contrasena;
    }

    @Override
    public String getUsername() {
        return usuario;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
    
    
}
