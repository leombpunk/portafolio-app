/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.apirest.portfolio.controller;

import com.apirest.portfolio.cloudinary.service.CloudinaryService;
import com.apirest.portfolio.dto.Imagen;
import com.apirest.portfolio.dto.Mensaje;
import com.apirest.portfolio.dto.PerfilDto;
import com.apirest.portfolio.model.Perfil;
import com.apirest.portfolio.security.service.UsuarioService;
import com.apirest.portfolio.service.PerfilService;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
@CrossOrigin(origins = "http://localhost:4200")
public class PerfilController {
    @Autowired
    private PerfilService interPerfil;

    //injectar el servicio de cloudinary creado
    @Autowired
    private CloudinaryService cloudinaryService;
    
    @Autowired
    private UsuarioService usuarioService;

    @Value("${image.default.perfil.nombre}")
    private String imagen;

    @Value("${image.default.perfil.public.id}")
    private String imagen_public_id;

    @Value("${image.default.perfil.url}")
    private String imagen_url;

    //sin usar
    /*@GetMapping ("perfil/traer")
    public List<Perfil> getPerfiles(){
        return interPerfil.getPerfiles();
    }*/
    //sin usar
    /*@PostMapping ("perfil/crear")
    public String createPerfil(@RequestBody Perfil perf){
        interPerfil.savePerfil(perf);
        return "El perfil se creo correctamente.";
    }*/
    //sin usar
    /*@DeleteMapping ("perfil/borrar/{id}")
    public String deletePerfil (@PathVariable("id") Long id){
        interPerfil.deletePerfil(id);
        return "El perfil ha sido borrado correctamente.";
    }*/
 /*@PreAuthorize("hasRole('USER')") // para que el administrador sea el que pueda editar el resto de roles no
    @PutMapping ("perfil/editar/{id}")
    public ResponseEntity<Perfil> editPerfil (
            @PathVariable("id") Long id,
            @RequestParam("nombre") String nombre,
            @RequestParam("apellido") String apellido,
            @RequestParam("titulo") String titulo,
            @RequestParam("correo") String correo,
            @RequestParam("linkedin") String linkedin,
            @RequestParam("github") String github,
            @RequestParam("acercade") String acercade
    ){
        try {
            //verificar que los campos sean correctos, si git o linkedin son vacios jpa lanza una excepcion
            //cuando ambos campos aceptarian nulos, falta revizar y testear
            if (interPerfil.existPerfilById(id)){
                Perfil perf = interPerfil.findPerfil(id);
                perf.setNombre(nombre);
                perf.setAcercade(acercade);
                perf.setApellido(apellido);
                perf.setTitulo(titulo);
                //perf.setFoto(foto);
                perf.setGithub(github);
                perf.setCorreo(correo);
                perf.setLinkedin(linkedin);
                interPerfil.savePerfil(perf);
                return new ResponseEntity<>(perf, HttpStatus.OK);
            } else {
                return new ResponseEntity(new Mensaje("Perfil no encontrado con el ID: "+id), HttpStatus.NOT_FOUND);
            }
        } catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }*/
    @PreAuthorize("hasRole('USER')") // para que el administrador sea el que pueda editar el resto de roles no
    @PutMapping("perfil/editar/{id}")
    public ResponseEntity<Perfil> editPerfil(
            @PathVariable("id") Long id,
            @Valid @RequestBody PerfilDto perf
    ) {
        try {
            //verificar que los campos sean correctos, si git o linkedin son vacios jpa lanza una excepcion
            //cuando ambos campos aceptarian nulos, falta revizar y testear
            if (interPerfil.existPerfilById(id)) {
                Perfil perfil = interPerfil.findPerfil(id);
                perfil.setNombre(perf.getNombre());
                perfil.setAcercade(perf.getAcercade());
                perfil.setApellido(perf.getApellido());
                perfil.setTitulo(perf.getTitulo());
                perfil.setGithub(perf.getGithub());
                perfil.setCorreo(perf.getCorreo());
                perfil.setLinkedin(perf.getLinkedin());
                interPerfil.savePerfil(perfil);
                return new ResponseEntity<>(perfil, HttpStatus.OK);
            } else {
                return new ResponseEntity(new Mensaje("Perfil no encontrado con el ID: " + id), HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    /*@GetMapping ("perfil/buscar/{id}")
    public Perfil findPerfil(@PathVariable("id") Long id){
        Perfil perf = interPerfil.findPerfil(id);
        return perf;
    }*/
    //testeado -> testear los demas endpoints de los demas controller
    @PutMapping("perfil/agregarImg/{id}")
    public ResponseEntity<Map> saveImagen(
            @PathVariable("id") Long id,
            @RequestParam("img") MultipartFile img) {
        try {
            //verificar que sea una imagen lo que recibe el endpoint
            BufferedImage bufferedImg = ImageIO.read(img.getInputStream());
            if (bufferedImg == null) { //si es nulo no es una imagen
                return new ResponseEntity(new Mensaje("El archivo no es una imagen soportada!"), HttpStatus.BAD_REQUEST);
            }
            //uso el servicio para verificar si el perfil existe
            if (!interPerfil.existPerfilById(id)) {
                return new ResponseEntity(new Mensaje("El perfil indicado no existe!"), HttpStatus.NOT_FOUND);
            }
            //tambien puedo usar el servicio de perfil para buscar el perfil por el id, si devuelve un registro procedo a hacer el resto
            Map result = cloudinaryService.upload(img);
            //una vez que el servicio sube la imagen verificar el response
            //y con los pares public_id y format armar el nombre de la imagen
            //verificar el par url
            Imagen image = new Imagen("perfil_foto_" + id.hashCode(), (String) result.get("url"), (String) result.get("public_id"));
            interPerfil.loadImage(image, id);
            return new ResponseEntity(new Mensaje("Imagen actualizada con exito!"), HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity(new Mensaje("Algo salio mal"), HttpStatus.NOT_FOUND);
        }
    }

    //falta testear
    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("perfil/borrarImg/{id}")
    public ResponseEntity<Perfil> deleteImagen(
            @PathVariable("id") Long id //id del perfil
    ) {
        try {
            //hacer lo mismo que en el metodo saveImagen
            if (interPerfil.existPerfilById(id)) {
                Perfil perf = interPerfil.findPerfil(id);
                //comprobar que la imagen_public_id sea distinta que la que devuelve perf.getFoto_public_id
                //para no borrar la imagen por defecto del proyecto que se encuentra en cloudinary
                if (imagen_public_id != perf.getFoto_public_id()) {
                    Map result = cloudinaryService.delete(perf.getFoto_public_id());
                    if (!result.isEmpty()) {
                        perf.setFoto(imagen);
                        perf.setFoto_public_id(imagen_public_id);
                        perf.setFoto_url(imagen_url);
                        interPerfil.savePerfil(perf);
                        return new ResponseEntity<>(perf, HttpStatus.OK);
                    } else {
                        return new ResponseEntity(new Mensaje("Problemas al intentar borrar la imagen."), HttpStatus.NOT_FOUND);
                    }
                } else {
                    perf.setFoto(imagen);
                    perf.setFoto_public_id(imagen_public_id);
                    perf.setFoto_url(imagen_url);
                    interPerfil.savePerfil(perf);
                    return new ResponseEntity<>(perf, HttpStatus.OK);
                }
            } else {
                return new ResponseEntity(new Mensaje("Perfil no encontrado!"), HttpStatus.NOT_FOUND);
            }
        } catch (IOException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity(new Mensaje("Algo salio mal"), HttpStatus.NOT_FOUND);
        }
    }

    /**
     *
     * @param usuario
     * @return
     */
    @GetMapping("perfil/buscarByUsuario/{usuario}")
    public ResponseEntity<Perfil> buscarByUsuario(@PathVariable("usuario") String usuario) {
        try {
            if (usuarioService.existsByUsuario(usuario)){
                Perfil perfil = interPerfil.buscarPerilByUsuario(usuario);
                return new ResponseEntity<>(perfil, HttpStatus.OK);
            } else {
                return new ResponseEntity(new Mensaje("Usuario no encontrado"), HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity(new Mensaje("Surgió algún error inesperado. " + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
