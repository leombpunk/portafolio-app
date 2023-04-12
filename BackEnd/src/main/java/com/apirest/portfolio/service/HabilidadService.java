/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.apirest.portfolio.service;

import com.apirest.portfolio.model.Habilidad;
import com.apirest.portfolio.repository.HabilidadRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author PCcito
 */
@Service
public class HabilidadService implements IHabilidadService {
    @Autowired
    private HabilidadRepository habRepository;

    @Override
    public List<Habilidad> getHabilidades() {
        List<Habilidad> listaHabilidades = habRepository.findAll();
        return listaHabilidades;
    }

    @Override
    public void saveHabilidad(Habilidad hab) {
        habRepository.save(hab);
    }

    @Override
    public void deleteHabilidad(Long id) {
        habRepository.deleteById(id);
    }

    @Override
    public Habilidad findHabilidad(Long id) {
        Habilidad hab = habRepository.findById(id).orElse(null);
        return hab;
    }

    @Override
    public List<Habilidad> getHabilidadesByUserId(Long id) {
        List<Habilidad> listaByUserId = habRepository.listaHabilidadesByUserId(id);
        return listaByUserId;
    }

    @Override
    public List<Habilidad> getHabilidadesByUsuario(String usuario) {
        List<Habilidad> listaByUsuario = habRepository.listaHabilidadesByUsuario(usuario);
        return listaByUsuario;
    }

    @Override
    public Boolean existHabilidadById(Long id) {
        return habRepository.existsById(id);
    }
    
}
