/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.apirest.portfolio.service;

import com.apirest.portfolio.dto.Imagen;
import com.apirest.portfolio.model.Experiencia;
import com.apirest.portfolio.repository.ExperienciaRepository;
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
public class ExperienciaService implements IExperienciaService {
    @Autowired
    private ExperienciaRepository expeRepository;
    
    @Override
    public List<Experiencia> getExperiencias() {
        List<Experiencia> listaExperiencias = expeRepository.findAll();
        return listaExperiencias;
    }

    @Override
    public void saveExperiencia(Experiencia expe) {
        expeRepository.save(expe);
    }

    @Override
    public void deleteExperiencia(Long id) {
        expeRepository.deleteById(id);
    }

    @Override
    public Experiencia findExperiencia(Long id) {
        Experiencia expe = expeRepository.findById(id).orElse(null);
        return expe;
    }

    @Override
    public void loadImage(Imagen img, Long id) {
        Experiencia expe = expeRepository.getById(id);
        expe.setLogo(img.getFoto_nombre());
        expe.setLogo_public_id(img.getFoto_public_id());
        expe.setLogo_url(img.getFoto_url());
        expeRepository.save(expe);
    }

    @Override
    public List<Experiencia> getExperienciaByUsuario(String usuario) {
        List<Experiencia> listaExperiencia = expeRepository.listaExperienciaByUsuario(usuario);
        return listaExperiencia;
    }

    @Override
    public Boolean existExperienciaById(Long id) {
        return expeRepository.existsById(id);
    }
}
