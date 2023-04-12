/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.apirest.portfolio.cloudinary.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author PCcito
 */
@Service
public class CloudinaryService {
    //datos necesarios para conectarse con la api de cloudinary
    Cloudinary cloudinary;
    @Value("${cloudinay.cloud.name}")
    private String cloudName;
    @Value("${cloudinary.api.key}")
    private String apiKey;
    @Value("${cloudinary.api.secret}")
    private String apiSecret;
    
    private Map<String, String> valueMap = new HashMap<>();
    
    public CloudinaryService(){
        valueMap.put("cloud_name", cloudName);
        valueMap.put("api_key", apiKey);
        valueMap.put("api_secret", apiSecret);
        cloudinary = new Cloudinary(valueMap);
    }
    
    //metodo para subir un archivo imagen
    public Map upload(MultipartFile img) throws IOException{
        File file = convert(img);
        Map result = cloudinary.uploader().upload(file, ObjectUtils.emptyMap());
        file.delete();
        return result;
    }
    
    public Map delete(String id) throws IOException{
        Map result = cloudinary.uploader().destroy(id, ObjectUtils.emptyMap());
        return result;
    }
    
    //convertir el multipartfile a un file
    private File convert(MultipartFile img) throws IOException{
        File file = new File(img.getOriginalFilename());
        try (FileOutputStream fileoutputstream = new FileOutputStream(file)) {
            fileoutputstream.write(img.getBytes());
        }
        return file;
    }
}
