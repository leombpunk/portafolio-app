/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.apirest.portfolio.service;

import com.apirest.portfolio.dto.Imagen;
import com.apirest.portfolio.model.Proyecto;
import java.util.List;

/**
 *
 * @author PCcito
 */
public interface IProyectoService {
    public List<Proyecto> getProyectos();
    public void saveProyecto(Proyecto proy);
    public void deleteProyecto(Long id);
    public Proyecto findProyecto(Long id);
    
    //prueba para imagenes
    public void loadImage(Imagen img, Long id);
    
    //nuevo metodo
    //interfaz para @Query personalizada
    public List<Proyecto> getProyectosByUsuario(String usuario);
    
    public Boolean existProyectoById(Long id);
}
