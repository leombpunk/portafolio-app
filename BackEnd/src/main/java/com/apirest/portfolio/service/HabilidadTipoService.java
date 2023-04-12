/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.apirest.portfolio.service;

import com.apirest.portfolio.model.HabilidadTipo;
import com.apirest.portfolio.repository.HabilidadTipoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author PCcito
 */
@Service
public class HabilidadTipoService implements IHabilidadTipoService {
    @Autowired
    private HabilidadTipoRepository habilidadTipoRepository;
    
    @Override
    public List<HabilidadTipo> getHabilidadesTipo() {
         List<HabilidadTipo> listaHabilidadesTipo = habilidadTipoRepository.findAll();
         return listaHabilidadesTipo;
    }

    @Override
    public void saveHabilidadTipo(HabilidadTipo habTipo) {
        habilidadTipoRepository.save(habTipo);
    }

    @Override
    public void deleteHabilidadTipo(Long id) {
        habilidadTipoRepository.deleteById(id);
    }

    @Override
    public HabilidadTipo findHabilidadTipo(Long id) {
        HabilidadTipo habTipo = habilidadTipoRepository.findById(id).orElse(null);
        return habTipo;
    }
    
}
