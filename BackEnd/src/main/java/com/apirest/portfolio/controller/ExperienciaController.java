/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.apirest.portfolio.controller;

import com.apirest.portfolio.cloudinary.service.CloudinaryService;
import com.apirest.portfolio.dto.ExperienciaDto;
import com.apirest.portfolio.dto.Imagen;
import com.apirest.portfolio.dto.Mensaje;
import com.apirest.portfolio.model.Experiencia;
import com.apirest.portfolio.security.service.UsuarioService;
import com.apirest.portfolio.service.ExperienciaService;
import com.apirest.portfolio.service.ValidarFechaService;
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
public class ExperienciaController {
    @Autowired
    private ExperienciaService interExperiencia;
    
    @Autowired
    private ValidarFechaService fechaService;
    
    @Autowired
    private CloudinaryService cloudinaryService;
    
    @Autowired
    private UsuarioService usuarioService;
    
    @Value("${image.default.experiencia.nombre}")
    private String imagen;
    
    @Value("${image.default.experiencia.public.id}")
    private String imagen_public_id;
    
    @Value("${image.default.experiencia.url}")
    private String imagen_url;
    
    /*@GetMapping("experiencia/traer")
    public List<Experiencia> getExperiencias(){
        return interExperiencia.getExperiencias();
    }*/

    /**
     *
     * @param expe
     * @return
     */
    @PostMapping("experiencia/crear")
    public ResponseEntity<Experiencia> createExperiencia(@RequestBody ExperienciaDto expe){
        try {
            if (!expe.getHasta().isBlank()){ //el campo Desde se valida desde el modelo
                if (!fechaService.isValidDate(expe.getHasta())){
                    return new ResponseEntity(new Mensaje("Datos incorrectos, verifique el campo hasta, no es una fecha valida"), HttpStatus.BAD_REQUEST);
                }
            } else {
                //si es nulo o vacio, seteo el campo como nulo
                //lo que quiere decir que la fecha hasta es hasta el presente
                expe.setHasta(null);
            }
            
            Experiencia experiencia = new Experiencia();
            experiencia.setCargo(expe.getCargo());
            experiencia.setTarea(expe.getTarea());
            experiencia.setDesde(expe.getDesde());
            experiencia.setHasta(expe.getHasta());
            experiencia.setEmpresa(expe.getEmpresa());
            experiencia.setReftelef(expe.getReftelef());
            experiencia.setRefnombre(expe.getRefnombre());
            experiencia.setLogo(imagen);
            experiencia.setLogo_url(imagen_url);
            experiencia.setLogo_public_id(imagen_public_id);
            experiencia.setUsuarios_id(expe.getUsuarios_id());
            interExperiencia.saveExperiencia(experiencia);
            return new ResponseEntity<>(experiencia, HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity(new Mensaje(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    /**
     *
     * @param id
     * @return
     */
    @DeleteMapping("experiencia/borrar/{id}")
    public ResponseEntity<Experiencia> deleteExperiencia(@PathVariable Long id){
        try{
            if (interExperiencia.existExperienciaById(id)){
                Experiencia expe = interExperiencia.findExperiencia(id);
                interExperiencia.deleteExperiencia(id);
                return new ResponseEntity<>(expe, HttpStatus.CREATED);
            } else {
                return new ResponseEntity(new Mensaje("No se borro nada porque no existe un registro con ese id"), HttpStatus.NOT_FOUND);
            }
            
        } catch (Exception e){
            return new ResponseEntity(new Mensaje(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    /*@PutMapping("experiencia/editar/{id}")
    public ResponseEntity<Experiencia> editExperiencia(
            @PathVariable Long id,
            @RequestParam("cargo") String cargo,
            @RequestParam("tarea") String tarea,
            @RequestParam("desde") String desde,
            @RequestParam("hasta") String hasta,
            //@RequestParam("logo") String logo,
            @RequestParam("empresa") String empresa,
            @RequestParam("reftelef") String reftelef,
            @RequestParam("refnombre") String refnombre){
        
        try {
            Experiencia expe = interExperiencia.findExperiencia(id);
        
            expe.setCargo(cargo);
            expe.setTarea(tarea);
            expe.setDesde(desde);
            expe.setHasta(hasta);
            //expe.setLogo(logo);
            expe.setEmpresa(empresa);
            expe.setReftelef(reftelef);
            expe.setRefnombre(refnombre);
            interExperiencia.saveExperiencia(expe);

            return new ResponseEntity<>(expe, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        
    }*/

    /**
     *
     * @param id
     * @param expe
     * @return
     */
    @PutMapping("experiencia/editar/{id}")
    public ResponseEntity<Experiencia> editExperiencia(
            @PathVariable Long id,
            @Valid @RequestBody ExperienciaDto expe){
        try {
            if (interExperiencia.existExperienciaById(id)){
                Experiencia experiencia = interExperiencia.findExperiencia(id);
                if (expe.getHasta() != null){
                    if (!expe.getHasta().isBlank()){
                        if (fechaService.isValidDate(expe.getHasta())){
                            experiencia.setHasta(expe.getHasta());
                        } else {
                            return new ResponseEntity(new Mensaje("Datos incorrectos, verifique el campo hasta, no es una fecha valida"), HttpStatus.BAD_REQUEST);
                        }
                    } else {
                        expe.setHasta(null);
                    }
                } else {
                    expe.setHasta(null);
                }
                experiencia.setCargo(expe.getCargo());
                experiencia.setTarea(expe.getTarea());
                experiencia.setDesde(expe.getDesde());
                experiencia.setEmpresa(expe.getEmpresa());
                experiencia.setReftelef(expe.getReftelef());
                experiencia.setRefnombre(expe.getRefnombre());
                interExperiencia.saveExperiencia(experiencia);
                return new ResponseEntity<>(experiencia, HttpStatus.OK);
            } else {
                return new ResponseEntity(new Mensaje("Datos no encontrados para id: "+id), HttpStatus.NOT_FOUND);
            }
        } catch (Exception e){
            return new ResponseEntity(new Mensaje(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    //sin usar
    /*@GetMapping("experiencia/buscar/{id}")
    public Experiencia findExperiencia(@PathVariable Long id){
        Experiencia expe = interExperiencia.findExperiencia(id);
        return expe;
    }*/

    /**
     *
     * @param id
     * @param img
     * @return
     */
    @PutMapping ("experiencia/agregarImg/{id}")
    public ResponseEntity<Map> saveImagen(
            @PathVariable Long id,
            @RequestParam("img") MultipartFile img){
        try {
            BufferedImage bufferedImg = ImageIO.read(img.getInputStream());
            if (bufferedImg == null){ //si es nulo no es una imagen
                return new ResponseEntity(new Mensaje("El archivo no es una imagen!"), HttpStatus.BAD_REQUEST);
            }
            if (!interExperiencia.existExperienciaById(id)){
                return new ResponseEntity(new Mensaje("La experiencia laboral indicada no existe!"), HttpStatus.NOT_FOUND);
            }
            Map result = cloudinaryService.upload(img);
            Imagen image = new Imagen("experiencia_foto_"+id.hashCode(), (String) result.get("url"), (String) result.get("public_id"));
            interExperiencia.loadImage(image, id);
            return new ResponseEntity(new Mensaje("Imagen actualizada con exito!"), HttpStatus.OK);
        } catch (IOException e){
            return new ResponseEntity(new Mensaje(e.getMessage()), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity(new Mensaje("Algo salio mal"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    /**
     *
     * @param id
     * @return
     */
    @DeleteMapping ("experiencia/borrarImg/{id}")
    public ResponseEntity<Experiencia> deleteImagen(
            @PathVariable("id") Long id //id de registro experiencia
        ){
        try{
            if (interExperiencia.existExperienciaById(id)){
                Experiencia expe = interExperiencia.findExperiencia(id);
                if (imagen_public_id != expe.getLogo_public_id()){
                    Map result = cloudinaryService.delete(expe.getLogo_public_id());
                    if (!result.isEmpty()){
                        expe.setLogo(imagen);
                        expe.setLogo_public_id(imagen_public_id);
                        expe.setLogo_url(imagen_url);
                        interExperiencia.saveExperiencia(expe);
                        return new ResponseEntity<> (expe, HttpStatus.OK);
                    } else {
                        return new ResponseEntity(new Mensaje("Problemas al intentar borrar la imagen."), HttpStatus.NOT_FOUND);
                    }
                } else {
                    expe.setLogo(imagen);
                    expe.setLogo_public_id(imagen_public_id);
                    expe.setLogo_url(imagen_url);
                    interExperiencia.saveExperiencia(expe);
                    return new ResponseEntity<> (expe, HttpStatus.OK);
                }
            } else {
                return new ResponseEntity(new Mensaje("Experiencia laboral no encontrada!"), HttpStatus.NOT_FOUND);
            }
        } catch (IOException e){
            return new ResponseEntity(new Mensaje(e.getMessage()), HttpStatus.NOT_FOUND);
        } catch (Exception e){
            return new ResponseEntity(new Mensaje("Algo salio mal "+e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }     
    }
    
    /**
     *
     * @param usuario
     * @return
     */
    @GetMapping("experiencia/buscarByUsuario/{usuario}")
    public ResponseEntity<List<Experiencia>> bucarByUsuario(@PathVariable("usuario") String usuario){
        try {
            if (usuarioService.existsByUsuario(usuario)){
                List<Experiencia> listaExperiencia = interExperiencia.getExperienciaByUsuario(usuario);
                return new ResponseEntity<>(listaExperiencia, HttpStatus.OK);
            } else {
                return new ResponseEntity(new Mensaje("Usuario no encontrado ("+usuario+")"), HttpStatus.NOT_FOUND);
            }
        } catch(Exception e){
            return new ResponseEntity(new Mensaje(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
