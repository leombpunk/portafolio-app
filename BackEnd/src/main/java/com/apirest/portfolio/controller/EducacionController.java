/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.apirest.portfolio.controller;

import com.apirest.portfolio.cloudinary.service.CloudinaryService;
import com.apirest.portfolio.dto.EducacionDto;
import com.apirest.portfolio.dto.Imagen;
import com.apirest.portfolio.dto.Mensaje;
import com.apirest.portfolio.model.Educacion;
import com.apirest.portfolio.security.service.UsuarioService;
import com.apirest.portfolio.service.EducacionService;
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
public class EducacionController {
    @Autowired
    private EducacionService interEducacion;
    
    @Autowired
    private ValidarFechaService fechaService;
    
    @Autowired
    private CloudinaryService cloudinaryService;
    
    @Autowired
    private UsuarioService usuarioService;
    
    @Value("${image.default.educacion.nombre}")
    private String imagen;
    
    @Value("${image.default.educacion.public.id}")
    private String imagen_public_id;
    
    @Value("${image.default.educacion.url}")
    private String imagen_url;
    
    //usado para testeos con postman, luego de las pruebas desabilitarlo
    /*@GetMapping("educacion/traer")
    public ResponseEntity<List<Educacion>> getEducaciones(){
        List<Educacion> listaEducacion = interEducacion.getEducaciones();
        return new ResponseEntity(listaEducacion, HttpStatus.OK);
    }*/

    /**
     *
     * @param edu
     * @return
     */
    @PostMapping("educacion/crear")
    public ResponseEntity<Educacion> createEducacion(@Valid @RequestBody EducacionDto edu){
        try {
            if (!edu.getHasta().isBlank()){ //el campo Desde se valida desde el modelo
                if (!fechaService.isValidDate(edu.getHasta())){
                    return new ResponseEntity(new Mensaje("Datos incorrectos, verifique el campo hasta, no es una fecha valida"), HttpStatus.BAD_REQUEST);
                }
            } else {
                //si es nulo o vacio, seteo el campo como nulo
                //lo que quiere decir que la fecha hasta es hasta el presente
                edu.setHasta(null);
            }       
            //una vez validados los datos los paso al modelo y proceso a guardarlos
            Educacion educacion = new Educacion();
            educacion.setInstitucion(edu.getInstitucion());
            educacion.setLocacion(edu.getLocacion());
            educacion.setHabilidades(edu.getHabilidades());
            educacion.setLogo(imagen);
            educacion.setLogo_url(imagen_url);
            educacion.setLogo_public_id(imagen_public_id);
            educacion.setTitulo(edu.getTitulo());
            educacion.setDesde(edu.getDesde());
            educacion.setHasta(edu.getHasta());
            educacion.setUsuarios_id(edu.getUsuarios_id());
            interEducacion.saveEducacion(educacion);
            return new ResponseEntity<>(educacion, HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity(new Mensaje("Ocurrió un error inesperado! "+e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    /**
     *
     * @param id
     * @return
     */
    @DeleteMapping("educacion/borrar/{id}")
    public ResponseEntity<Educacion> deleteEducacion(@PathVariable Long id){
        try {
            if (interEducacion.existEducacionById(id)){
                Educacion edu = interEducacion.findEducacion(id);
                interEducacion.deleteEducacion(id);
                return new ResponseEntity<>(edu, HttpStatus.OK);
            } else {
                return new ResponseEntity(new Mensaje("No se borro nada porque no existe un registro con ese id"), HttpStatus.NOT_FOUND);
            }
        } catch (Exception e){
            return new ResponseEntity(new Mensaje(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    /*@PutMapping("educacion/editar/{id}")
    public ResponseEntity<Educacion> editEducacion(
            @PathVariable Long id,
            @RequestParam("institucion") String institucion,
            @RequestParam("titulo") String titulo,
            @RequestParam("locacion") String locacion,
            @RequestParam("habilidades") String habilidades,
            @RequestParam("desde") String desde,
            @RequestParam("hasta") String hasta){
        
        try{
            if(interEducacion.existEducacionById(id)){
                Educacion edu = interEducacion.findEducacion(id);
                edu.setInstitucion(institucion);
                if (fechaService.isValidDate(desde)){
                    edu.setDesde(desde);
                } else {
                    return new ResponseEntity(new Mensaje("Datos incorrectos, verifique el campo desde, no es una fecha valida"), HttpStatus.BAD_REQUEST);
                }
                if (fechaService.isValidDate(hasta)){
                    edu.setHasta(hasta);
                } else {
                    return new ResponseEntity(new Mensaje("Datos incorrectos, verifique el campo hasta, no es una fecha valida"), HttpStatus.BAD_REQUEST);
                }
                //edu.setLogo(logo);
                edu.setTitulo(titulo);
                edu.setHabilidades(habilidades);
                edu.setLocacion(locacion);
                interEducacion.saveEducacion(edu);
                return new ResponseEntity<>(edu, HttpStatus.OK);
            } else {
                return new ResponseEntity(new Mensaje("Datos no encontrados para id: "+id), HttpStatus.NOT_FOUND);
            }
            
        } catch (Exception e) {
            return new ResponseEntity(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }*/

    /**
     *
     * @param id
     * @param edu
     * @return
     */
    @PutMapping("educacion/editar/{id}")
    public ResponseEntity<Educacion> editEducacion(
            @PathVariable Long id,
            @Valid @RequestBody EducacionDto edu){
        try{
            if(interEducacion.existEducacionById(id)){
                Educacion educacion = interEducacion.findEducacion(id);
                //si no es nulo o es distinto de blanco
                if (edu.getHasta() != null){
                    if (!edu.getHasta().isBlank()){
                        if (fechaService.isValidDate(edu.getHasta())){
                        educacion.setHasta(edu.getHasta());
                        } else {
                            return new ResponseEntity(new Mensaje("Datos incorrectos, verifique el campo hasta, no es una fecha valida"), HttpStatus.BAD_REQUEST);
                        }
                    } else {
                        edu.setHasta(null);
                    }
                } else {
                    edu.setHasta(null);
                }
                educacion.setDesde(edu.getDesde()); //no hace falta validar porque ya se valida en el modelo
                educacion.setInstitucion(edu.getInstitucion());
                educacion.setTitulo(edu.getTitulo());
                educacion.setHabilidades(edu.getHabilidades());
                educacion.setLocacion(edu.getLocacion());
                interEducacion.saveEducacion(educacion);
                return new ResponseEntity<>(educacion, HttpStatus.OK);
            } else {
                return new ResponseEntity(new Mensaje("Datos no encontrados para id: "+id), HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity(new Mensaje(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    //sin usar
    /*@GetMapping("educacion/buscar/{id}")
    public Educacion findEducacion(@PathVariable Long id){
        return interEducacion.findEducacion(id);
    }*/

    /**
     *
     * @param id
     * @param img
     * @return
     */
    @PutMapping ("educacion/agregarImg/{id}")
    public ResponseEntity<Map> saveImagen(
            @PathVariable Long id,
            @RequestParam("img") MultipartFile img){
        try {
            BufferedImage bufferedImg = ImageIO.read(img.getInputStream());
            if (bufferedImg == null){ //si es nulo no es una imagen
                return new ResponseEntity(new Mensaje("El archivo no es una imagen!"), HttpStatus.BAD_REQUEST);
            }
            if (!interEducacion.existEducacionById(id)){
                return new ResponseEntity(new Mensaje("El estudio/curso indicado no existe!"), HttpStatus.NOT_FOUND);
            }
            Map result = cloudinaryService.upload(img);
            Imagen image = new Imagen("educacion_foto_"+id.hashCode(), (String) result.get("url"), (String) result.get("public_id"));
            interEducacion.loadImage(image, id);
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
    @DeleteMapping ("educacion/borrarImg/{id}")
    public ResponseEntity<Educacion> deleteImagen(
            @PathVariable Long id //id de registro educacion
        ){
        try{
            if (interEducacion.existEducacionById(id)){
                Imagen image = new Imagen(imagen, imagen_url, imagen_public_id);
                Educacion edu = interEducacion.findEducacion(id);
                if (imagen_public_id != edu.getLogo_public_id()){
                    Map result = cloudinaryService.delete(edu.getLogo_public_id());
                    if (!result.isEmpty()){
                        interEducacion.loadImage(image, id);
                        return new ResponseEntity<> (edu, HttpStatus.OK);
                    } else {
                        return new ResponseEntity(new Mensaje("Problemas al intentar borrar la imagen."), HttpStatus.NOT_FOUND);
                    }
                } else {
                    interEducacion.loadImage(image, id);
                    return new ResponseEntity<> (edu, HttpStatus.OK);
                }
            } else {
                return new ResponseEntity(new Mensaje("Estudio/Curso no encontrado!"), HttpStatus.NOT_FOUND);
            }
        } catch (IOException e){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (Exception e){
            return new ResponseEntity(new Mensaje("Algo salio mal"), HttpStatus.NOT_FOUND);
        }       
    }
    
    /**
     *
     * @param usuario
     * @return
     */
    @GetMapping("educacion/buscarByUsuario/{usuario}")
    public ResponseEntity<List<Educacion>> buscarByUsuario(@PathVariable("usuario") String usuario){
        try{
            if (usuarioService.existsByUsuario(usuario)){
                List<Educacion> listaEducacion = interEducacion.getEducacionByUsuario(usuario);
                return new ResponseEntity<>(listaEducacion, HttpStatus.OK);
            } else {
                return new ResponseEntity(new Mensaje("Usuario no encontrado ("+usuario+")"), HttpStatus.NOT_FOUND);
            }
            
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
