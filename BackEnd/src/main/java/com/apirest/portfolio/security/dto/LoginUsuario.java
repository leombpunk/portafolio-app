/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.apirest.portfolio.security.dto;

import javax.validation.constraints.NotBlank;

/**
 *
 * @author PCcito
 */
public class LoginUsuario {
    @NotBlank
    private String usuario;
    
    @NotBlank
    private String contrasena;

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String constrasena) {
        this.contrasena = constrasena;
    }
}
