/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.apirest.portfolio.controller;

import com.apirest.portfolio.model.HabilidadTipo;
import com.apirest.portfolio.service.IHabilidadTipoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author PCcito
 */
@RestController
public class HabilidadTipoController {
    @Autowired
    private IHabilidadTipoService interHabilidadTipo;
    
    @GetMapping("habilidadTipo/traer")
    public List<HabilidadTipo> getHabilidadesTipo(){
        return interHabilidadTipo.getHabilidadesTipo();
    }
    
    @PostMapping("habilidadTipo/crear")
    public String createHabilidadTipo(@RequestBody HabilidadTipo habTipo){
        interHabilidadTipo.saveHabilidadTipo(habTipo);
        return "El tipo de habilidad ha sido creado correctamente.";
    }
    
    @DeleteMapping("habilidadTipo/borrar/{id}")
    public String deleteHabilidadTipo(@PathVariable Long id){
        interHabilidadTipo.deleteHabilidadTipo(id);
        return "El tipo de habilidad ha sido borrado correctamente.";
    }
    
    @PutMapping("habilidadTipo/editar/{id}")
    public HabilidadTipo editHabilidadTipo(
            @PathVariable Long id,
            @RequestParam("nombre") String nombre){
        
        HabilidadTipo habTipo = interHabilidadTipo.findHabilidadTipo(id);
        habTipo.setNombre(nombre);
        
        return habTipo;
    }
    
    @GetMapping("habilidadTipo/buscar/{id}")
    public HabilidadTipo findHabilidadTipo(@PathVariable Long id){
        HabilidadTipo habTipo = interHabilidadTipo.findHabilidadTipo(id);
        return habTipo;
    }
}
