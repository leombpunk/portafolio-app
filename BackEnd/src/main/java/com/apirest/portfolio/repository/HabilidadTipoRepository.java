/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.apirest.portfolio.repository;

import com.apirest.portfolio.model.HabilidadTipo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author PCcito
 */
@Repository
public interface HabilidadTipoRepository extends JpaRepository<HabilidadTipo, Long> {
    
}
