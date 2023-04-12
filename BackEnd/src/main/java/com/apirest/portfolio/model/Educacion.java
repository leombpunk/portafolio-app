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
//import javax.persistence.Table;
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
@Entity(name="educacion")
//@Table(name = "educacion")
public class Educacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
//    @NotNull(message = "El campo Institución no puede ser nulo")
//    @NotBlank(message = "El campo Institución no puede ser vacío")
//    @Size(min=3, max=50, message = "Longitud aceptada de 3 a 50 caractéres")
//    @Pattern(regexp="^[a-zA-Z0-9\\sÁáÉéÍíÓóÚúÑñÜü]+$", message = "Formato invalido para el campo Institución")
    @Column(name="institucion")
    private String institucion;
    
//    @NotNull(message = "El campo Lugar no puede ser nulo")
//    @NotBlank(message = "El campo Lugar no puede ser vacío")
//    @Size(min=3, max=50, message = "Longitud aceptada de 3 a 50 caractéres")
//    @Pattern(regexp="^[a-zA-Z\\d\\s]*\\-[a-zA-Z\\d\\s]*\\-[a-zA-Z\\d\\s]+$", message = "Formato invalido para el campo Lugar")
    @Column(name="locacion")
    private String locacion;
    
//    @NotNull(message = "El campo Habilidades no puede ser nulo")
//    @NotBlank(message = "El campo Habilidades no puede ser vacío")
//    @Size(min=5, max=500, message = "Longitud aceptada de 5 a 500 caractéres")
//    @Pattern(regexp="^[a-zA-Z0-9\\sÁáÉéÍíÓóÚúÑñÜü/.,;:()\"'\\-]+$", message = "Formato invalido para el campo Habilidades")
    @Column(name="habilidades")
    private String habilidades;
    
    //los campos relacionados con logo a mi parecer no necesitarian validarse
    //ya que un usuario no puede modificar estos datos de ningun modo, se modifian
    //programaticamente, tambien segun los datos devueltos por es servicio de
    //cloudinaty
//    @Size(max=50, message = "Longitud maxima 50 caractéres")
    @Column(name="logo")
    private String logo;
    
//    @Size(max=100, message = "Longitud maxima 100 caractéres")
    @Column(name="logo_url")
    private String logo_url;
    
//    @Size(max=50, message = "Longitud maxima 50 caractéres")
    @Column(name="logo_public_id")
    private String logo_public_id;
    
//    @NotNull(message = "El campo Titulo no puede ser nulo")
//    @NotBlank(message = "El campo Titulo no puede ser vacío")
//    @Size(min=3, max=50, message = "Longitud aceptada de 3 a 50 caractéres")
//    @Pattern(regexp="^[a-zA-Z\\sÁáÉéÍíÓóÚúÑñÜü]+$", message = "Formato invalido para el campo Titulo")
    @Column(name="titulo")
    private String titulo;
    
//    @NotNull(message = "El campo Desde no puede ser nulo")
//    @NotBlank(message = "El campo Desde no puede ser vacío")
//    @Size(min=10, max=10, message = "Longitud aceptada 10 caractéres")
//    @Pattern(regexp="^(\\d{4}(\\/|-)(0[1-9]|1[0-2])\\2([0-2][0-9]|3[0-1]))$", message = "Formato invalido para el campo Desde")
    @Column(name="desde")
    private String desde;
    
    
    @Column(name="hasta", nullable = true, insertable = true)
    private String hasta;
    
//    @NotNull(message = "El id de usuario no puede ser nulo")
//    @Max(value=999999999, message = "Longitud maxima sobrepasada")
//    @Positive(message = "El id de usuario debe ser un entero positivo")
    @Column(name="usuarios_id")
    private Long usuarios_id;
}
