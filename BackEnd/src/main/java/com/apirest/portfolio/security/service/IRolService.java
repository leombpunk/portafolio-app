/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.apirest.portfolio.security.service;

import com.apirest.portfolio.security.enums.RolNombre;
import com.apirest.portfolio.security.model.Rol;
import java.util.Optional;

/**
 *
 * @author PCcito
 */
public interface IRolService  {
    public Optional<Rol> getByRolNombre(RolNombre rolNombre);
    public void saveRol(Rol rol);
}
