
package com.lavasecos.edu.servicios;

import com.lavasecos.edu.entidades.Prenda;
import com.lavasecos.edu.repositorios.PrendaRepositorio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PrendaServicio {
    
    @Autowired
    private PrendaRepositorio pr;
    
    public Prenda prenda(Prenda prenda){
        return pr.save(prenda);
    }
}
