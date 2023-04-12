/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.apirest.portfolio.service;

import com.apirest.portfolio.dto.Imagen;
import com.apirest.portfolio.model.Proyecto;
import com.apirest.portfolio.repository.ProyectoRepository;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author PCcito
 */
@Service
public class ProyectoService implements IProyectoService {
    @Autowired
    private ProyectoRepository proyectoRepository;
    
    @Override
    public List<Proyecto> getProyectos() {
        List<Proyecto> listaProyectos = proyectoRepository.findAll();
        return listaProyectos;
    }

    @Override
    public void saveProyecto(Proyecto proy) {
        proyectoRepository.save(proy);
    }

    @Override
    public void deleteProyecto(Long id) {
        proyectoRepository.deleteById(id);
    }

    @Override
    public Proyecto findProyecto(Long id) {
        Proyecto pro = proyectoRepository.findById(id).orElse(null);
        return pro;
    }

    @Override
    public void loadImage(Imagen img, Long id) {
        Proyecto pro = proyectoRepository.getById(id);
        pro.setLogo(img.getFoto_nombre());
        pro.setLogo_public_id(img.getFoto_public_id());
        pro.setLogo_url(img.getFoto_url());
        proyectoRepository.save(pro);
    }

    //metodo nuevo para la query personalizada
    @Override
    public List<Proyecto> getProyectosByUsuario(String usuario) {
        List<Proyecto> listaProyecto = proyectoRepository.listaProyectosByUsuario(usuario);
        return listaProyecto;
    }

    @Override
    public Boolean existProyectoById(Long id) {
        return proyectoRepository.existsById(id);
    }
}
