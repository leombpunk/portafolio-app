/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.apirest.portfolio.dto;

/**
 *
 * @author PCcito
 */
public class Imagen {
    private String foto_nombre;
    private String foto_url;
    private String foto_public_id;

    public Imagen(String foto_nombre, String foto_url, String foto_public_id) {
        this.foto_nombre = foto_nombre;
        this.foto_url = foto_url;
        this.foto_public_id = foto_public_id;
    }

    public String getFoto_nombre() {
        return foto_nombre;
    }

    public void setFoto_nombre(String foto_nombre) {
        this.foto_nombre = foto_nombre;
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
}
