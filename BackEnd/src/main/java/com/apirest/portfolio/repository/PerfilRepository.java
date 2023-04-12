/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.apirest.portfolio.repository;

import com.apirest.portfolio.model.Perfil;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author PCcito
 */
@Repository
public interface PerfilRepository extends JpaRepository<Perfil, Long> {
    /*agregar las querys con la annotation @Query para poder hacer consultas
    personalizadas a la base de datos*/
    @Query("SELECT p FROM perfiles p INNER JOIN usuarios u ON p.usuarios_id = u.id AND u.usuario = :usuario")
    Perfil perfilByUsuario(@Param("usuario") String usuario);
}
