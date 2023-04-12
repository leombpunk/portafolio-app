/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.apirest.portfolio.security.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


/**
 *
 * @author PCcito
 */
@Entity(name="usuarios")
public class Usuario {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "El usuario no puede ser vacio")
    @Size(min=4, max=16, message = "Entre 4 a 16 caracteres de lonqitud")
    @Pattern(regexp="^[a-zA-Z0-9]+$")
    @Column(name="usuario", unique=true)
    private String usuario;
    
    @NotBlank(message = "La contraseña no puede ser vacia")
    @Size(min=6, max=255, message = "Entre 6 a 16 caracteres de lonqitud")
    @Column(name="contrasena")
    private String contrasena;
    
    //este campo es opcional
    @NotNull(message = "El rol no puede ser nulo")
    @ManyToMany(fetch = FetchType.EAGER)
    //hago un join a la tabla usuarios_roles
    @JoinTable(name="usuarios_roles", 
            joinColumns=@JoinColumn(name="usuarios_id"), 
            inverseJoinColumns=@JoinColumn(name="roles_id"))
    private Set<Rol> roles = new HashSet<>();

    public Usuario() {
    }

    public Usuario(String usuario, String contrasena) {
        this.usuario = usuario;
        this.contrasena = contrasena;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Set<Rol> getRoles() {
        return roles;
    }

    public void setRoles(Set<Rol> roles) {
        this.roles = roles;
    }
}

