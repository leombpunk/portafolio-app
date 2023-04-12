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
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author PCcito
 */
@Getter @Setter
@Entity(name="habilidades")
public class Habilidad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
//    @NotNull(message = "El campo Descripcion no puede ser nulo")
//    @NotBlank(message = "El campo Descripcion no puede ser vacío")
//    @Size(min=2, max=50, message = "Longitud aceptada de 2 a 50 caractéres")
//    @Pattern(regexp="^[a-zA-Z\\sÁáÉéÍíÓóÚúÑñÜü]+$", message = "Formato invalido para el campo Descripcion")
    @Column(name="descripcion")
    private String descripcion;
    
//    @NotNull(message = "El id de usuario no puede ser nulo")
//    @Min(value=1, message = "Valor minimo 1")
//    @Max(value=10, message = "Valor maximo 10")
//    @Positive(message = "El nivel es incorrecto, debe ser entero positivo")
    @Column(name="nivel")
    private int nivel;
    
//    @NotNull(message = "El id de usuario no puede ser nulo")
//    @Max(value=999999999, message = "Longitud maxima sobrepasada")
//    @Positive(message = "El id de usuario debe ser un entero positivo")
    @Column(name="usuarios_id")
    private Long usuarios_id;
    
//    @NotNull(message = "El id de usuario no puede ser nulo")
//    @Min(value=1, message = "Valor minimo 1")
//    @Max(value=99, message = "Valor maximo 99")
//    @Positive(message = "El tipo de habilidad es incorrecto")
    @Column(name="tipo_habilidad_id")
    private Long tipo_habilidad_id;
}
