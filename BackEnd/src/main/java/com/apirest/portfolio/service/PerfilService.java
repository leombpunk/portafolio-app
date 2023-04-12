/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.apirest.portfolio.service;

import com.apirest.portfolio.dto.Imagen;
import com.apirest.portfolio.model.Perfil;
import com.apirest.portfolio.repository.PerfilRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author PCcito
 */
@Service
public class PerfilService implements IPerfilService {
    @Autowired
    private PerfilRepository perfRepository;
    
    
    @Override
    public List<Perfil> getPerfiles() {
        List<Perfil> listaPerfiles = perfRepository.findAll();
        return listaPerfiles;
    }

    @Override
    public void savePerfil(Perfil perf) {
        //me sercioro de que la foto quede en blanco
        //String foto = "perfil_foto_default" + perf.getUsuarios_id();
        //perf.setFoto(foto+".jpg");
        perfRepository.save(perf);
    }

    @Override
    public void deletePerfil(Long id) {
        perfRepository.deleteById(id);
    }

    @Override
    public Perfil findPerfil(Long id) {
        Perfil perf = perfRepository.findById(id).orElse(null);
        return perf;
    }

    @Override
    public void loadImage(Imagen img, Long id) {
        //busco el perfil por el id
        Perfil perf = perfRepository.getById(id);
        perf.setFoto(img.getFoto_nombre());
        perf.setFoto_public_id(img.getFoto_public_id());
        perf.setFoto_url(img.getFoto_url());
        perfRepository.save(perf);
    }

    @Override
    public Perfil buscarPerilByUsuario(String usuario) {
        Perfil perfil = perfRepository.perfilByUsuario(usuario);
        return perfil;
    }
    
    //este codigo me viene bien para ponerlo en perfilcontroller en el metodo saveImagen y deleteImagen
    //comprueba que existe un registro con dicho id pasado por parametro
    @Override
    public Boolean existPerfilById(Long id){
        return perfRepository.existsById(id);
    }
}
