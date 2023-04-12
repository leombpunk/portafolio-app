/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.apirest.portfolio.controller;

import com.apirest.portfolio.dto.HabilidadDto;
import com.apirest.portfolio.dto.Mensaje;
import com.apirest.portfolio.model.Habilidad;
import com.apirest.portfolio.security.service.UsuarioService;
import com.apirest.portfolio.service.IHabilidadService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author PCcito
 */
@RestController
@CrossOrigin(origins="http://localhost:4200")
public class HabilidadController {
    @Autowired
    private IHabilidadService interHabilidad;
    
    @Autowired 
    private UsuarioService usuarioService;
    
    /**
     *
     * @return
     */
//    @GetMapping("habilidad/traer")
//    public ResponseEntity<List<Habilidad>> getHabilidades(){
//        try {
//            List<Habilidad> hab = interHabilidad.getHabilidades();
//            if (hab.isEmpty()){
//                return new ResponseEntity(new Mensaje("No hay habilidades"), HttpStatus.NO_CONTENT);
//            }
//            else {
//                return new ResponseEntity<>(hab, HttpStatus.OK);
//            }
//        } catch (Exception e){
//            return new ResponseEntity(new Mensaje(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
    
    /**
     *
     * @param hab
     * @return
     */
    @PostMapping("habilidad/crear")
    public ResponseEntity<Habilidad> createHabilidad(@Valid @RequestBody HabilidadDto hab){
        try {
            Habilidad habilidad = new Habilidad();
            habilidad.setDescripcion(hab.getDescripcion());
            habilidad.setNivel(hab.getNivel());
            habilidad.setTipo_habilidad_id(hab.getTipo_habilidad_id());
            habilidad.setUsuarios_id(hab.getUsuarios_id());
            interHabilidad.saveHabilidad(habilidad);
            return new ResponseEntity<>(habilidad, HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity(new Mensaje(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    /**
     *
     * @param id
     * @return
     */
    @DeleteMapping("habilidad/borrar/{id}")
    public ResponseEntity<Habilidad> deleteHabilidad(@PathVariable Long id){
        try {
            if (interHabilidad.existHabilidadById(id)){
                Habilidad hab = interHabilidad.findHabilidad(id);
                interHabilidad.deleteHabilidad(id);
                return new ResponseEntity<>(hab, HttpStatus.OK);
            } else {
                return new ResponseEntity(new Mensaje("No se borro nada porque no existe un registro con ese id"), HttpStatus.NOT_FOUND);
            }
        } catch (Exception e){
            return new ResponseEntity(new Mensaje(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }  
    }
    
    /*@PutMapping("habilidad/editar/{id}")
    public ResponseEntity<Habilidad> editHabilidad(
            @PathVariable Long id,
            @RequestParam("descripcion") String descripcion,
            @RequestParam("nivel") int nivel,
            @RequestParam("tipo_habilidad_id") Long tipo_habilidad_id){
        try {
            Habilidad hab = interHabilidad.findHabilidad(id);
            hab.setDescripcion(descripcion);
            hab.setNivel(nivel);
            hab.setTipo_habilidad_id(tipo_habilidad_id);
            interHabilidad.saveHabilidad(hab);
            return new ResponseEntity<>(hab, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity(new Mensaje(""), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }*/

    /**
     *
     * @param id
     * @param hab
     * @return
     */
    @PutMapping("habilidad/editar/{id}")
    public ResponseEntity<Habilidad> editHabilidad(
            @PathVariable Long id,
            @Valid @RequestBody HabilidadDto hab){
        try {
            if (interHabilidad.existHabilidadById(id)){
                Habilidad habilidad = interHabilidad.findHabilidad(id);
                habilidad.setDescripcion(hab.getDescripcion());
                habilidad.setNivel(hab.getNivel());
                habilidad.setTipo_habilidad_id(hab.getTipo_habilidad_id());
                interHabilidad.saveHabilidad(habilidad);
                return new ResponseEntity<>(habilidad, HttpStatus.OK);
            } else {
                return new ResponseEntity(new Mensaje("No existen registros para el id: " + id), HttpStatus.NOT_FOUND);
            }
        } catch (Exception e){
            return new ResponseEntity(new Mensaje(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    //hecho por mi y ya esta funcionando
    /*@GetMapping("habilidad/traerById/{id}")
    public List<Habilidad> findHabilidadByUserId(@PathVariable Long id){
        List<Habilidad> lista = interHabilidad.getHabilidadesByUserId(id);
        return lista;
    }*/

    /**
     *
     * @param usuario
     * @return
     */
    @GetMapping("habilidad/buscarByUsuario/{usuario}")
    public ResponseEntity<List<Habilidad>> bucarByUsuario(@PathVariable("usuario") String usuario){
        try{
            if (usuarioService.existsByUsuario(usuario)){
                List<Habilidad> listaHabilidad = interHabilidad.getHabilidadesByUsuario(usuario);   
                return new ResponseEntity<>(listaHabilidad, HttpStatus.OK);
            } else {
                return new ResponseEntity(new Mensaje("Usuario no encontrado ("+usuario+")"), HttpStatus.NOT_FOUND);
            }
        } catch (Exception e){
            return new ResponseEntity(new Mensaje(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
