/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.apirest.portfolio.dto;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

/**
 *
 * @author PCcito
 */
public class PerfilDto {
    @NotNull(message = "El campo Nombre no puede ser nulo")
    @NotBlank(message = "El campo Nombre no puede ser vacío")
    @Size(min=3, max=50, message = "Longitud aceptada de 3 a 50 caractéres")
    @Pattern(regexp="^[a-zA-Z\\sÁáÉéÍíÓóÚúÑñÜü]+$", message = "Formato invalido para el campo Nombre")
    @Column(name="nombre")
    private String nombre;
    
    @NotNull(message = "El campo Apellido no puede ser nulo")
    @NotBlank(message = "El campo Apellido no puede ser vacío")
    @Size(min=3, max=50, message = "Longitud aceptada de 3 a 50 caractéres")
    @Pattern(regexp="^[a-zA-Z\\sÁáÉéÍíÓóÚúÑñÜü]+$", message = "Formato invalido para el campo Apellido")
    @Column(name="apellido")
    private String apellido;
    
    @NotNull(message = "El campo Titulo no puede ser nulo")
    @NotBlank(message = "El campo Titulo no puede ser vacío")
    @Size(min=3, max=50, message = "Longitud aceptada de 3 a 50 caractéres")
    @Pattern(regexp="^[a-zA-Z\\sÁáÉéÍíÓóÚúÑñÜü]+$", message = "Formato invalido para el campo Titulo")
    @Column(name="titulo")
    private String titulo;
    
    @NotNull(message = "El campo Correo no puede ser nulo")
    @NotBlank(message = "El campo Correo no puede ser vacío")
    @Email(message = "El campo Correo es incorrecto, verifiquelo")
    @Column(name="correo")
    private String correo;
    
    @Size(min=3, max=100, message = "Longitud aceptada de 3 a 50 caractéres")
    @Pattern(regexp="^(ht|f)tp(s?)\\:\\/\\/[0-9a-zA-Z]([-.\\w]*[0-9a-zA-Z-])*(:(0-9)*)*(\\/?)([a-zA-Z0-9\\-\\.\\?\\,\\'\\/\\\\\\+&%\\$#_]*)?$", message = "Formato invalido para el campo Linkedin")
    @Column(name="linkedin")
    private String linkedin;
    
    @Size(min=3, max=100, message = "Longitud aceptada de 3 a 50 caractéres")
    @Pattern(regexp="^(ht|f)tp(s?)\\:\\/\\/[0-9a-zA-Z]([-.\\w]*[0-9a-zA-Z-])*(:(0-9)*)*(\\/?)([a-zA-Z0-9\\-\\.\\?\\,\\'\\/\\\\\\+&%\\$#_]*)?$", message = "Formato invalido para el campo GitHub")
    @Column(name="github")
    private String github;
    
    @Column(name="foto")
    private String foto;
    
    @Column(name="foto_url")
    private String foto_url;
    
    @Column(name="foto_public_id")
    private String foto_public_id;
    
    @NotNull(message = "El campo Correo no puede ser nulo")
    @NotBlank(message = "El campo Correo no puede ser vacío")
    @Size(min=3, max=500, message = "Longitud aceptada de 3 a 50 caractéres")
    @Pattern(regexp="^[a-zA-Z0-9\\sÁáÉéÍíÓóÚúÑñÜü/.,;:()\"'\\-¿?!¡]+$", message = "Formato invalido para campo Acerca de")
    @Column(name="acercade")
    private String acercade;
    
    @NotNull(message = "El id de usuario no puede ser nulo")
    @Max(value=999999999, message = "Longitud maxima sobrepasada")
    @Positive(message = "El id de usuario debe ser un entero positivo")
    @Column(name="usuarios_id")
    private Long usuarios_id;

    public PerfilDto() {
    }

    public PerfilDto(String nombre, String apellido, String titulo, String correo, String linkedin, String github, String foto, String foto_url, String foto_public_id, String acercade, Long usuarios_id) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.titulo = titulo;
        this.correo = correo;
        this.linkedin = linkedin;
        this.github = github;
        this.foto = foto;
        this.foto_url = foto_url;
        this.foto_public_id = foto_public_id;
        this.acercade = acercade;
        this.usuarios_id = usuarios_id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getLinkedin() {
        return linkedin;
    }

    public void setLinkedin(String linkedin) {
        this.linkedin = linkedin;
    }

    public String getGithub() {
        return github;
    }

    public void setGithub(String github) {
        this.github = github;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getFoto_url() {
        return foto_url;
    }

    public void setFoto_url(String foto_url) {
        this.foto_url = foto_url;
    }

    public String getFoto_public_id() {
        return foto_public_id;
    }

    public void setFoto_public_id(String foto_public_id) {
        this.foto_public_id = foto_public_id;
    }

    public String getAcercade() {
        return acercade;
    }

    public void setAcercade(String acercade) {
        this.acercade = acercade;
    }

    public Long getUsuarios_id() {
        return usuarios_id;
    }

    public void setUsuarios_id(Long usuarios_id) {
        this.usuarios_id = usuarios_id;
    }
    
}
