/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.apirest.portfolio.service;

import com.apirest.portfolio.model.Habilidad;
import java.util.List;

/**
 *
 * @author PCcito
 */
public interface IHabilidadService {
    public List<Habilidad> getHabilidades();
    public void saveHabilidad(Habilidad hab);
    public void deleteHabilidad(Long id);
    public Habilidad findHabilidad(Long id);
    
    //interfaz con @Query personalizada -- funciona bien
    public List<Habilidad> getHabilidadesByUserId(Long id);
    public List<Habilidad> getHabilidadesByUsuario(String usuario);
    
    public Boolean existHabilidadById(Long id);
}
