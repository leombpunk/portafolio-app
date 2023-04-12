/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.apirest.portfolio.service;

import com.apirest.portfolio.model.HabilidadTipo;
import java.util.List;

/**
 *
 * @author PCcito
 */
public interface IHabilidadTipoService {
    public List<HabilidadTipo> getHabilidadesTipo();
    public void saveHabilidadTipo(HabilidadTipo habTipo);
    public void deleteHabilidadTipo(Long id);
    public HabilidadTipo findHabilidadTipo(Long id);
}
