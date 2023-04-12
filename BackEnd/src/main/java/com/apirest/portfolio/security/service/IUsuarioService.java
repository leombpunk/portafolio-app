/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.apirest.portfolio.security.service;

import com.apirest.portfolio.security.model.Usuario;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author PCcito
 */
public interface IUsuarioService {
    public List<Usuario> getUsuarios();
    public void saveUsuario(Usuario usu);
    public void deleteUsuario(Long id);
    public Usuario findUsuario(Long id);
    
    //agregados
    public Optional<Usuario> getByUsuario(String usuario);
    public boolean existsByUsuario(String usuario);
}
