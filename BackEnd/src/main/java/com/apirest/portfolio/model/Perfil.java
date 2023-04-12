/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.apirest.portfolio.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
//import javax.validation.constraints.Email;
//import javax.validation.constraints.Max;
//import javax.validation.constraints.NotBlank;
//import javax.validation.constraints.NotNull;
//import javax.validation.constraints.Pattern;
//import javax.validation.constraints.Positive;
//import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author PCcito
 */
@Getter @Setter
@Entity(name="perfiles")
public class Perfil {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
//    @NotNull(message = "El campo Nombre no puede ser nulo")
//    @NotBlank(message = "El campo Nombre no puede ser vacío")
//    @Size(min=3, max=50, message = "Longitud aceptada de 3 a 50 caractéres")
//    @Pattern(regexp="^[a-zA-Z\\sÁáÉéÍíÓóÚúÑñÜü]+$", message = "Formato invalido para el campo Nombre")
    @Column(name="nombre")
    private String nombre;
    
//    @NotNull(message = "El campo Apellido no puede ser nulo")
//    @NotBlank(message = "El campo Apellido no puede ser vacío")
//    @Size(min=3, max=50, message = "Longitud aceptada de 3 a 50 caractéres")
//    @Pattern(regexp="^[a-zA-Z\\sÁáÉéÍíÓóÚúÑñÜü]+$", message = "Formato invalido para el campo Apellido")
    @Column(name="apellido")
    private String apellido;
    
//    @NotNull(message = "El campo Titulo no puede ser nulo")
//    @NotBlank(message = "El campo Titulo no puede ser vacío")
//    @Size(min=3, max=50, message = "Longitud aceptada de 3 a 50 caractéres")
//    @Pattern(regexp="^[a-zA-Z\\sÁáÉéÍíÓóÚúÑñÜü]+$", message = "Formato invalido para el campo Titulo")
    @Column(name="titulo")
    private String titulo;
    
//    @NotNull(message = "El campo Correo no puede ser nulo")
//    @NotBlank(message = "El campo Correo no puede ser vacío")
//    @Email(message = "El campo Correo es incorrecto, verifiquelo")
    @Column(name="correo")
    private String correo;
    
//    @Size(min=3, max=100, message = "Longitud aceptada de 3 a 50 caractéres")
//    @Pattern(regexp="^(ht|f)tp(s?)\\:\\/\\/[0-9a-zA-Z]([-.\\w]*[0-9a-zA-Z-])*(:(0-9)*)*(\\/?)([a-zA-Z0-9\\-\\.\\?\\,\\'\\/\\\\\\+&%\\$#_]*)?$", message = "Formato invalido para el campo Linkedin")
    @Column(name="linkedin")
    private String linkedin;
    
//    @Size(min=3, max=100, message = "Longitud aceptada de 3 a 50 caractéres")
//    @Pattern(regexp="^(ht|f)tp(s?)\\:\\/\\/[0-9a-zA-Z]([-.\\w]*[0-9a-zA-Z-])*(:(0-9)*)*(\\/?)([a-zA-Z0-9\\-\\.\\?\\,\\'\\/\\\\\\+&%\\$#_]*)?$", message = "Formato invalido para el campo GitHub")
    @Column(name="github")
    private String github;
    
    @Column(name="foto")
    private String foto;
    
    @Column(name="foto_url")
    private String foto_url;
    
    @Column(name="foto_public_id")
    private String foto_public_id;
    
//    @NotNull(message = "El campo Correo no puede ser nulo")
//    @NotBlank(message = "El campo Correo no puede ser vacío")
//    @Size(min=3, max=500, message = "Longitud aceptada de 3 a 50 caractéres")
//    @Pattern(regexp="^[a-zA-Z0-9\\sÁáÉéÍíÓóÚúÑñÜü/.,;:()\"'\\-]+$", message = "Formato invalido para campo Acerca de")
    @Column(name="acercade")
    private String acercade;
    
//    @NotNull(message = "El id de usuario no puede ser nulo")
//    @Max(value=999999999, message = "Longitud maxima sobrepasada")
//    @Positive(message = "El id de usuario debe ser un entero positivo")
    @Column(name="usuarios_id")
    private Long usuarios_id;
}
