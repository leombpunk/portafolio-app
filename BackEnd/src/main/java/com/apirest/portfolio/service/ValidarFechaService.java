/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.apirest.portfolio.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.validator.GenericValidator;
import org.springframework.stereotype.Service;

/**
 *
 * @author PCcito
 */
@Service
public class ValidarFechaService implements IValidarFechaService {
    @Override
    public boolean isValidDate(String fecha) {
        Pattern pattern = Pattern.compile("^(\\d{4}(\\/|-)(0[1-9]|1[0-2])\\2([0-2][0-9]|3[0-1]))$");
        Matcher match = pattern.matcher(fecha);
        if (!match.matches()){
            return false;
        }
        else if (!GenericValidator.isDate(fecha, "yyyy-MM-dd", true)){
            return false;
        }
        return true;
    }
}
