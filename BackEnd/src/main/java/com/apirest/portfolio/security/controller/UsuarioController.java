/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.apirest.portfolio.security.controller;

import com.apirest.portfolio.security.model.Usuario;
import com.apirest.portfolio.security.service.IUsuarioService;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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

/*NO USAR*/
@RestController
public class UsuarioController {
    @Autowired 
    private IUsuarioService interUsuario;
    
    @GetMapping ("/usuario/traer")
    public List<Usuario> getUsuarios(){
        return interUsuario.getUsuarios();
    }
    
    @PostMapping ("/usuario/crear")
    public String createUser(@RequestBody Usuario usu){
        interUsuario.saveUsuario(usu);
        return "El usuario ha side dado de alta.";
    }
    
    @DeleteMapping ("/usuario/borrar/{id}")
    public String deleteUser(@PathVariable Long id){
        interUsuario.deleteUsuario(id);
        return "El usuario ha sido borrado.";
    }
    
    @PutMapping ("/usuario/editar/{id}")
    public Usuario editUser(
            @PathVariable Long id,
            @RequestParam("usuario") String usuario,
            @RequestParam ("contrasena") String contrasena){
        
        Usuario usu = interUsuario.findUsuario(id);
        usu.setUsuario(usuario);
        usu.setContrasena(contrasena);
        interUsuario.saveUsuario(usu);
        return usu;
    }
    
    @GetMapping ("/usuario/buscar/{id}")
    public Usuario findUser(@PathVariable Long id){
        Usuario usu = interUsuario.findUsuario(id);
        return usu;
    }
}
