/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.apirest.portfolio.repository;

import com.apirest.portfolio.model.Educacion;
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
public interface EducacionRepository extends JpaRepository<Educacion, Long> {
    @Query("SELECT e FROM educacion e INNER JOIN usuarios u ON e.usuarios_id = u.id AND u.usuario = :usuario ORDER BY e.desde DESC")
    List<Educacion> listaEducacionByUsuario(@Param("usuario") String usuario);
}
