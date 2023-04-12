/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.apirest.portfolio.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import org.springframework.lang.Nullable;

/**
 *
 * @author PCcito
 */
public class EducacionDto {
    @NotNull(message = "El campo Institución no puede ser nulo")
    @NotBlank(message = "El campo Institución no puede ser vacío")
    @Size(min=3, max=50, message = "Longitud aceptada de 3 a 50 caractéres")
    @Pattern(regexp="^[a-zA-Z0-9\\sÁáÉéÍíÓóÚúÑñÜü]+$", message = "Formato invalido para el campo Institución")
    private String institucion;
    
    @NotNull(message = "El campo Lugar no puede ser nulo")
    @NotBlank(message = "El campo Lugar no puede ser vacío")
    @Size(min=3, max=50, message = "Longitud aceptada de 3 a 50 caractéres")
    @Pattern(regexp="^[a-zA-Z\\d\\s]*\\-[a-zA-Z\\d\\s]*\\-[a-zA-Z\\d\\s]+$", message = "Formato invalido para el campo Lugar")
    private String locacion;
    
    @NotNull(message = "El campo Habilidades no puede ser nulo")
    @NotBlank(message = "El campo Habilidades no puede ser vacío")
    @Size(min=5, max=500, message = "Longitud aceptada de 5 a 500 caractéres")
    @Pattern(regexp="^[a-zA-Z0-9\\sÁáÉéÍíÓóÚúÑñÜü/.,;:()\"'\\-¿?!¡]+$", message = "Formato invalido para el campo Habilidades")
    private String habilidades;
    
    //los campos relacionados con logo a mi parecer no necesitarian validarse
    //ya que un usuario no puede modificar estos datos de ningun modo, se modifian
    //programaticamente, tambien segun los datos devueltos por es servicio de
    //cloudinaty
    @Size(max=50, message = "Longitud maxima 50 caractéres")
    private String logo;
    
    @Size(max=100, message = "Longitud maxima 100 caractéres")
    private String logo_url;
    
    @Size(max=50, message = "Longitud maxima 50 caractéres")
    private String logo_public_id;
    
    @NotNull(message = "El campo Titulo no puede ser nulo")
    @NotBlank(message = "El campo Titulo no puede ser vacío")
    @Size(min=3, max=50, message = "Longitud aceptada de 3 a 50 caractéres")
    @Pattern(regexp="^[a-zA-Z\\sÁáÉéÍíÓóÚúÑñÜü]+$", message = "Formato invalido para el campo Titulo")
    private String titulo;
    
    @NotNull(message = "El campo Desde no puede ser nulo")
    @NotBlank(message = "El campo Desde no puede ser vacío")
    @Size(min=10, max=10, message = "Longitud aceptada 10 caractéres")
    @Pattern(regexp="^(\\d{4}(\\/|-)(0[1-9]|1[0-2])\\2([0-2][0-9]|3[0-1]))$", message = "Formato invalido para el campo Desde")
    private String desde;
    
    @Nullable
    private String hasta;
    
    @NotNull(message = "El id de usuario no puede ser nulo")
    @Max(value=999999999, message = "Longitud maxima sobrepasada")
    @Positive(message = "El id de usuario debe ser un entero positivo")
    private Long usuarios_id;
    
    public EducacionDto() { }

    public EducacionDto(String institucion, String locacion, String habilidades, String logo, String logo_url, String logo_public_id, String titulo, String desde, String hasta, Long usuarios_id) {
        this.institucion = institucion;
        this.locacion = locacion;
        this.habilidades = habilidades;
        this.logo = logo;
        this.logo_url = logo_url;
        this.logo_public_id = logo_public_id;
        this.titulo = titulo;
        this.desde = desde;
        this.hasta = hasta;
        this.usuarios_id = usuarios_id;
    }

    public String getInstitucion() {
        return institucion;
    }

    public void setInstitucion(String institucion) {
        this.institucion = institucion;
    }

    public String getLocacion() {
        return locacion;
    }

    public void setLocacion(String locacion) {
        this.locacion = locacion;
    }

    public String getHabilidades() {
        return habilidades;
    }

    public void setHabilidades(String habilidades) {
        this.habilidades = habilidades;
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

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
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

    public Long getUsuarios_id() {
        return usuarios_id;
    }

    public void setUsuarios_id(Long usuarios_id) {
        this.usuarios_id = usuarios_id;
    }
}
