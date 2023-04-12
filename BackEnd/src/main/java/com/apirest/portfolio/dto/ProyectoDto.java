/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.apirest.portfolio.dto;

import javax.persistence.Column;
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
public class ProyectoDto {
    @NotNull(message = "El campo Nombre no puede ser nulo")
    @NotBlank(message = "El campo Nombre no puede ser vacío")
    @Size(min=3, max=50, message = "Longitud aceptada de 3 a 50 caractéres")
    @Pattern(regexp="^[a-zA-Z0-9\\sÁáÉéÍíÓóÚúÑñÜü]+$", message = "Formato invalido para el campo Nombre")
    @Column(name="nombre")
    private String nombre;
    
    @NotNull(message = "El campo Desde no puede ser nulo")
    @NotBlank(message = "El campo Desde no puede ser vacío")
    @Size(min=10, max=10, message = "Longitud aceptada 10 caractéres")
    @Pattern(regexp="^(\\d{4}(\\/|-)(0[1-9]|1[0-2])\\2([0-2][0-9]|3[0-1]))$", message = "Formato invalido para el campo Desde")
    @Column(name="desde")
    private String desde;
    
    
    @Column(name="hasta", nullable = true)
    private String hasta;
    
    @Size(max=50, message = "Longitud maxima 50 caractéres")
    @Column(name="logo")
    private String logo;
    
    @Size(max=100, message = "Longitud maxima 100 caractéres")
    @Column(name="logo_url")
    private String logo_url;
    
    @Size(max=50, message = "Longitud maxima 50 caractéres")
    @Column(name="logo_public_id")
    private String logo_public_id;
    
    @NotNull(message = "El campo Descripcion no puede ser nulo")
    @NotBlank(message = "El campo Descripcion no puede ser vacío")
    @Size(min=5, max=500, message = "Longitud aceptada de 5 a 500 caractéres")
    @Pattern(regexp="^[a-zA-Z0-9\\sÁáÉéÍíÓóÚúÑñÜü/.,;:()\"'\\-¿?!¡]+$", message = "Formato invalido para el campo Descripcion")
    @Column(name="descripcion")
    private String descripcion;
    
    @Column(name="enlace", nullable = true)
    private String enlace;
    
    @Column(name="sitio", nullable = true)
    private String sitio;
    
    @NotNull(message = "El id de usuario no puede ser nulo")
    @Max(value=999999999, message = "Longitud maxima sobrepasada")
    @Positive(message = "El id de usuario debe ser un entero positivo")
    @Column(name="usuarios_id")
    private Long usuarios_id;

    public ProyectoDto() {
    }

    public ProyectoDto(String nombre, String desde, String hasta, String logo, String logo_url, String logo_public_id, String descripcion, String enlace, String sitio, Long usuarios_id) {
        this.nombre = nombre;
        this.desde = desde;
        this.hasta = hasta;
        this.logo = logo;
        this.logo_url = logo_url;
        this.logo_public_id = logo_public_id;
        this.descripcion = descripcion;
        this.enlace = enlace;
        this.sitio = sitio;
        this.usuarios_id = usuarios_id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDesde() {
        return desde;
    }

    public void setDesde(String desde) {
        this.desde = desde;
    }

    public String getHasta() {
        return hasta;
    }

    public void setHasta(String hasta) {
        this.hasta = hasta;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getLogo_url() {
        return logo_url;
    }

    public void setLogo_url(String logo_url) {
        this.logo_url = logo_url;
    }

    public String getLogo_public_id() {
        return logo_public_id;
    }

    public void setLogo_public_id(String logo_public_id) {
        this.logo_public_id = logo_public_id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEnlace() {
        return enlace;
    }

    public void setEnlace(String enlace) {
        this.enlace = enlace;
    }

    public String getSitio() {
        return sitio;
    }

    public void setSitio(String sitio) {
        this.sitio = sitio;
    }

    public Long getUsuarios_id() {
        return usuarios_id;
    }

    public void setUsuarios_id(Long usuarios_id) {
        this.usuarios_id = usuarios_id;
    }
    
}
