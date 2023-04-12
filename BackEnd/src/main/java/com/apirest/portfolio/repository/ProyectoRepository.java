/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.apirest.portfolio.repository;

import com.apirest.portfolio.model.Proyecto;
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
public interface ProyectoRepository extends JpaRepository<Proyecto, Long> {
    @Query("SELECT p FROM proyectos p INNER JOIN usuarios u ON p.usuarios_id = u.id AND u.usuario = :usuario ORDER BY p.desde DESC")
    List<Proyecto> listaProyectosByUsuario(@Param("usuario") String usuario);
}
