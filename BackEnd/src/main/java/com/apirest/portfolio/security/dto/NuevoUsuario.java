/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.apirest.portfolio.security.dto;

import java.util.HashSet;
import java.util.Set;
import javax.validation.constraints.NotBlank;

/**
 *
 * @author PCcito
 */
public class NuevoUsuario {
    @NotBlank
    private String usuario;
    
    @NotBlank
    private String contrasena;
    private Set<String> roles = new HashSet<>();

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }
    
}
