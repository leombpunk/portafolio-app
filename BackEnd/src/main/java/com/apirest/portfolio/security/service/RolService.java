/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.apirest.portfolio.security.service;

import com.apirest.portfolio.security.enums.RolNombre;
import com.apirest.portfolio.security.model.Rol;
import com.apirest.portfolio.security.repository.RolRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author PCcito
 */
@Service
@Transactional
public class RolService implements IRolService {
    @Autowired
    private RolRepository rolRepository;

    @Override
    public Optional<Rol> getByRolNombre(RolNombre rolNombre) {
        return rolRepository.findByRolNombre(rolNombre);
    }

    @Override
    public void saveRol(Rol rol) {
        rolRepository.save(rol);
    }
    
    
}
