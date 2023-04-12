/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.apirest.portfolio.controller;

import com.apirest.portfolio.cloudinary.service.CloudinaryService;
import com.apirest.portfolio.dto.Imagen;
import com.apirest.portfolio.dto.Mensaje;
import com.apirest.portfolio.dto.ProyectoDto;
import com.apirest.portfolio.model.Proyecto;
import com.apirest.portfolio.security.service.UsuarioService;
import com.apirest.portfolio.service.ProyectoService;
import com.apirest.portfolio.service.ValidarFechaService;
import com.apirest.portfolio.service.ValidarURLService;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author PCcito
 */
@RestController
@CrossOrigin(origins="http://localhost:4200")
public class ProyectoController {
    @Autowired 
    private ProyectoService interProyecto;
    
    @Autowired
    private ValidarFechaService fechaService;
    
    @Autowired
    private ValidarURLService urlService;
    
    @Autowired
    private CloudinaryService cloudinaryService;
    
    @Autowired
    private UsuarioService usuarioService;
    
    @Value("${image.default.proyecto.nombre}")
    private String imagen;
    
    @Value("${image.default.proyecto.public.id}")
    private String imagen_public_id;
    
    @Value("${image.default.proyecto.url}")
    private String imagen_url;
    
    /**
     *
     * @return
     */
    @GetMapping("proyecto/traer")
    public ResponseEntity<List<Proyecto>> getProyectos(){
        try {
            List<Proyecto> pro = interProyecto.getProyectos();
            if (pro.isEmpty()){
                return new ResponseEntity(new Mensaje("No se encontraron registros"), HttpStatus.NO_CONTENT);
            }
            else {
                return new ResponseEntity<>(pro, HttpStatus.OK);
            }
        } catch (Exception e){
            return new ResponseEntity(new Mensaje(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    /**
     *
     * @param pro
     * @return
     */
    @PostMapping("proyecto/crear")
    public ResponseEntity<Proyecto> createProyecto(@Valid @RequestBody ProyectoDto pro){
        try {
            //verificar fecha
            if (!pro.getHasta().isBlank()){
                if (!fechaService.isValidDate(pro.getHasta())){
                    return new ResponseEntity(new Mensaje("Datos incorrectos, verifique el campo hasta, no es una fecha valida"), HttpStatus.BAD_REQUEST);
                }
            } else {
                pro.setHasta(null);
            }
            //verificar enlace
            if (!pro.getEnlace().isBlank()){
                if (!urlService.isValidURL(pro.getEnlace())){
                    return new ResponseEntity(new Mensaje("El formato del campo Enlace no es correcto"), HttpStatus.BAD_REQUEST);
                }
            } else {
                pro.setEnlace(null);
            }
            //verificar sitio
            if (!pro.getSitio().isBlank()){
                if (!urlService.isValidURL(pro.getSitio())){
                    return new ResponseEntity(new Mensaje("El formato del campo Sitio no es correcto"), HttpStatus.BAD_REQUEST);
                }
            } else {
                pro.setSitio(null);
            }
            
            Proyecto proyecto = new Proyecto();
            proyecto.setDescripcion(pro.getDescripcion());
            proyecto.setDesde(pro.getDesde());
            proyecto.setHasta(pro.getHasta());
            proyecto.setEnlace(pro.getEnlace());
            proyecto.setSitio(pro.getSitio());
            proyecto.setNombre(pro.getNombre());
            proyecto.setUsuarios_id(pro.getUsuarios_id());
            proyecto.setLogo(imagen);
            proyecto.setLogo_public_id(imagen_public_id);
            proyecto.setLogo_url(imagen_url);
            interProyecto.saveProyecto(proyecto);
            return new ResponseEntity<>(proyecto, HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity(new Mensaje(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    /**
     *
     * @param id
     * @return
     */
    @DeleteMapping("proyecto/borrar/{id}")
    public ResponseEntity<Proyecto> deleteProyecto(@PathVariable Long id){
        try {
            if (interProyecto.existProyectoById(id)){
                Proyecto pro = interProyecto.findProyecto(id);
                interProyecto.deleteProyecto(id);
                return new ResponseEntity<>(pro, HttpStatus.OK);
            } else {
                return new ResponseEntity(new Mensaje("No se borro nada porque no existe un registro con ese id"), HttpStatus.NOT_FOUND);
            }
        } catch (Exception e){
            return new ResponseEntity(new Mensaje(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    /*@PutMapping("proyecto/editar/{id}")
    public ResponseEntity<Proyecto> editProyecto(
            @PathVariable Long id,
            @RequestParam("nombre") String nombre,
            @RequestParam("descripcion") String desc,
            @RequestParam("desde") String desde,
            @RequestParam("hasta") String hasta,
            @RequestParam("enlace") String enlace,
            @RequestParam("sitio") String sitio){
        
        try {
            Proyecto pro = interProyecto.findProyecto(id);
            pro.setNombre(nombre);
            pro.setDescripcion(desc);
            pro.setDesde(desde);
            pro.setHasta(hasta);
            pro.setEnlace(enlace);
            pro.setSitio(sitio);
            interProyecto.saveProyecto(pro);
            return new ResponseEntity<>(pro, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }*/
    
    @PutMapping("proyecto/editar/{id}")
    public ResponseEntity<Proyecto> editProyecto(
            @PathVariable Long id,
            @Valid @RequestBody ProyectoDto pro){
        try {
            if (interProyecto.existProyectoById(id)){
                Proyecto proyecto = interProyecto.findProyecto(id);
                if (pro.getHasta() != null){
                    if (!pro.getHasta().isBlank()){
                        if (fechaService.isValidDate(pro.getHasta())){
                            proyecto.setHasta(pro.getHasta());
                        } else {
                            return new ResponseEntity(new Mensaje("Datos incorrectos, verifique el campo hasta, no es una fecha valida"), HttpStatus.BAD_REQUEST);
                        }
                    } else {
                        pro.setHasta(null);
                    }
                } else {
                    pro.setHasta(null);
                }
                
                //verificar enlace
                if (!pro.getEnlace().isBlank()){
                    if (!urlService.isValidURL(pro.getEnlace())){
                        return new ResponseEntity(new Mensaje("El formato del campo Enlace no es correcto"), HttpStatus.BAD_REQUEST);
                    }
                    else {
                        proyecto.setEnlace(pro.getEnlace());
                    }
                } else {
                    pro.setEnlace(null);
                }
                //verificar sitio
                if (!pro.getSitio().isBlank()){
                    if (!urlService.isValidURL(pro.getSitio())){
                        return new ResponseEntity(new Mensaje("El formato del campo Sitio no es correcto"), HttpStatus.BAD_REQUEST);
                    }
                    else {
                        proyecto.setSitio(pro.getSitio());
                    }
                } else {
                    pro.setSitio(null);
                }
                proyecto.setNombre(pro.getNombre());
                proyecto.setDescripcion(pro.getDescripcion());
                proyecto.setDesde(pro.getDesde());
                interProyecto.saveProyecto(proyecto);
                return new ResponseEntity<>(proyecto, HttpStatus.OK);
            } else {
                return new ResponseEntity(new Mensaje("Datos no encontrados para id: "+id), HttpStatus.NOT_FOUND);
            }
        } catch (Exception e){
            return new ResponseEntity(new Mensaje(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    //sin usar
    /*@GetMapping("proyecto/buscar/{id}")
    public Proyecto findProyecto(@PathVariable Long id){
        Proyecto pro = interProyecto.findProyecto(id);
        return pro;
    }*/
    
    //agregado para testeos

    /**
     *
     * @param id
     * @param img
     * @return
     */
    @PutMapping ("proyecto/agregarImg/{id}")
    public ResponseEntity<Map> saveImagen(
            @PathVariable Long id,
            @RequestParam("img") MultipartFile img){
        try {
            BufferedImage bufferedImg = ImageIO.read(img.getInputStream());
            if (bufferedImg == null){ //si es nulo no es una imagen
                return new ResponseEntity(new Mensaje("El archivo no es una imagen!"), HttpStatus.BAD_REQUEST);
            }
            if (!interProyecto.existProyectoById(id)){
                return new ResponseEntity(new Mensaje("El proyecto indicado no existe!"), HttpStatus.NOT_FOUND);
            }
            Map result = cloudinaryService.upload(img);
            Imagen imagen = new Imagen("proyecto_foto_"+id.hashCode()+".jpg", (String) result.get("url"), (String) result.get("public_id"));
            interProyecto.loadImage(imagen, id);
            return new ResponseEntity(new Mensaje("Imagen actualizada con exito!"), HttpStatus.OK);
        } catch (IOException e){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity(new Mensaje("Algo salio mal"), HttpStatus.NOT_FOUND);
        }
    }
    
    /**
     *
     * @param id
     * @return
     */
    @DeleteMapping ("proyecto/borrarImg/{id}")
    public ResponseEntity<Proyecto> deleteImagen(
            @PathVariable Long id //id de registro experiencia
        ){
        try{
            if (interProyecto.existProyectoById(id)){
                Proyecto pro = interProyecto.findProyecto(id);
                if (imagen_public_id != pro.getLogo_public_id()){
                    Map result = cloudinaryService.delete(pro.getLogo_public_id());
                    if (!result.isEmpty()){
                        pro.setLogo(imagen);
                        pro.setLogo_public_id(imagen_public_id);
                        pro.setLogo_url(imagen_url);
                        interProyecto.saveProyecto(pro);
                        return new ResponseEntity<> (pro, HttpStatus.OK);
                    } else {
                        return new ResponseEntity(new Mensaje("Problemas al intentar borrar la imagen."), HttpStatus.NOT_FOUND);
                    }
                } else {
                    pro.setLogo(imagen);
                    pro.setLogo_public_id(imagen_public_id);
                    pro.setLogo_url(imagen_url);
                    interProyecto.saveProyecto(pro);
                    return new ResponseEntity<> (pro, HttpStatus.OK);
                }
            } else {
                return new ResponseEntity(new Mensaje("Proyecto no encontrado!"), HttpStatus.NOT_FOUND);
            }
        } catch (IOException e){
            return new ResponseEntity(new Mensaje(e.getMessage()), HttpStatus.NOT_FOUND);
        } catch (Exception e){
            return new ResponseEntity(new Mensaje("Algo salio mal " + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }     
    }

    /**
     *
     * @param usuario
     * @return
     */
    @GetMapping("proyecto/buscarByUsuario/{usuario}")
    public ResponseEntity<List<Proyecto>> findProyectoByUsuario(@PathVariable("usuario") String usuario){
        try {
            if (usuarioService.existsByUsuario(usuario)){
                List<Proyecto> listaProyecto = interProyecto.getProyectosByUsuario(usuario);
                return new ResponseEntity<>(listaProyecto, HttpStatus.OK);
            } else {
                return new ResponseEntity(new Mensaje("Usuario no encontrado ("+usuario+")"), HttpStatus.NOT_FOUND);
            }
            
        } catch (Exception e){
            return new ResponseEntity(new Mensaje(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
