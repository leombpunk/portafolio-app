/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.apirest.portfolio.security.controller;

import com.apirest.portfolio.dto.Mensaje;
import com.apirest.portfolio.model.Perfil;
import com.apirest.portfolio.security.dto.JwtDto;
import com.apirest.portfolio.security.dto.LoginUsuario;
import com.apirest.portfolio.security.dto.NuevoUsuario;
import com.apirest.portfolio.security.enums.RolNombre;
import com.apirest.portfolio.security.jwt.JwtProvider;
import com.apirest.portfolio.security.model.Rol;
import com.apirest.portfolio.security.model.Usuario;
import com.apirest.portfolio.security.service.RolService;
import com.apirest.portfolio.security.service.UsuarioService;
import com.apirest.portfolio.service.PerfilService;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author PCcito
 */
@RestController
@RequestMapping("/auth")
@CrossOrigin(origins="http://localhost:4200")
public class AuthController {
    //injeccion de clases que haran falta
    @Autowired
    PasswordEncoder passwordEncoder;
    
    @Autowired
    AuthenticationManager authenticacionManager;
    
    @Autowired
    UsuarioService usuarioService;
    
    @Autowired
    RolService rolService;
    
    @Autowired
    JwtProvider jwtProvider;
    
    //AGREGADO
    @Autowired 
    PerfilService perfilService;
    
    @Value("${image.default.perfil.nombre}")
    private String imagen;

    @Value("${image.default.perfil.public.id}")
    private String imagen_public_id;

    @Value("${image.default.perfil.url}")
    private String imagen_url;
    //FIN
    
    @PostMapping("/nuevoUsuario")
    public ResponseEntity<?> nuevoUsuario(@Valid @RequestBody NuevoUsuario nuevoUsuario, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return new ResponseEntity(bindingResult.getFieldErrors(), HttpStatus.BAD_REQUEST);
        }
        if (usuarioService.existsByUsuario(nuevoUsuario.getUsuario())){
            return new ResponseEntity("El nombre de usuario ya existe.", HttpStatus.BAD_REQUEST);
        }
        Usuario usu = new Usuario(nuevoUsuario.getUsuario(), passwordEncoder.encode(nuevoUsuario.getContrasena()));
        Set<Rol> roles = new HashSet<>();
        roles.add(rolService.getByRolNombre(RolNombre.ROLE_USER).get());
        if (nuevoUsuario.getRoles().contains("admin")){
            roles.add(rolService.getByRolNombre(RolNombre.ROLE_ADMIN).get());
        }
        usu.setRoles(roles);
        usuarioService.saveUsuario(usu);
        
        //AGREGADO
        //ya que en clever cloud no puedo usar triggers en la db lo hago a manopla aquí
        Perfil perfil = new Perfil();
        perfil.setNombre("Inserte su nombre");
        perfil.setApellido("Inserte su apellido");
        perfil.setAcercade("Escriba algo sobre usted");
        perfil.setCorreo("Inserte su correo");
        perfil.setGithub("Inserte su GitHub");
        perfil.setLinkedin("Inserte su LinkedIn");
        perfil.setTitulo("Inserte su título");
        perfil.setFoto(imagen);
        perfil.setFoto_url(imagen_url);
        perfil.setFoto_public_id(imagen_public_id);
        //busco el id del usuario "recien creado"
        Optional<Usuario> usuario = usuarioService.getByUsuario(usu.getUsuario());
        perfil.setUsuarios_id(usuario.get().getId());//no lo tengo
        perfilService.savePerfil(perfil);
        //FIN 
        
        return new ResponseEntity(new Mensaje("Usuario creado."), HttpStatus.CREATED);
    }
    
    
    @PostMapping("/login")
    public ResponseEntity<JwtDto> login(@Valid @RequestBody LoginUsuario loginUsuario, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return new ResponseEntity("Los campos estan mal puestos.", HttpStatus.BAD_REQUEST);
        }
        Authentication authentication = 
                authenticacionManager.authenticate(
                        new UsernamePasswordAuthenticationToken(loginUsuario.getUsuario(), loginUsuario.getContrasena()));
        SecurityContextHolder.getContext().setAuthentication(authentication); //autenticamos al usuario
        //puesta en marcha del tokenfilter
        String jwt = jwtProvider.generateToken(authentication); //json web token a devolver, a partir de la autenticacion se genera el token
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        JwtDto jwtDto = new JwtDto(jwt, userDetails.getUsername(), userDetails.getAuthorities());
  
        return new ResponseEntity(jwtDto, HttpStatus.OK);
    } 
    
    //nuevo metodo agregado
    @GetMapping("/buscarUsuario/{usuario}")
    public ResponseEntity<String> buscarUsuario(@PathVariable("usuario") String usuario){
        try{
            if (usuario.isBlank() || usuario.isEmpty()){
                return new ResponseEntity(new Mensaje("El nombre de usuario esta vacio."), HttpStatus.NO_CONTENT);
            } 
            if (!usuarioService.existsByUsuario(usuario)){
                return new ResponseEntity(new Mensaje("El usuario: '" + usuario + "' no existe."), HttpStatus.NOT_FOUND);
            }
            Optional<Usuario> usu = usuarioService.getByUsuario(usuario);
            //usu.get().getUsuario();
            return new ResponseEntity<>("{ \"usuario\": \""+usu.get().getUsuario()+"\" }", HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
}
