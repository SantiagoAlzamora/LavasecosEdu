/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lavasecos.edu.servicios;

import com.lavasecos.edu.entidades.Observacion;
import com.lavasecos.edu.errores.ErrorServicio;
import com.lavasecos.edu.repositorios.ObservacionRepositorio;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author santi
 */
public class ObservacionServicio {
    @Autowired
    private ObservacionRepositorio or;
    public Observacion crearObservacion(Observacion observacion) throws ErrorServicio{
      return or.save(observacion);
    
    }
    public void eliminar (String ig) throws ErrorServicio{
    Observacion respuesta = or.buscarPorId(ig);
    if(respuesta!=null){
    or.delete(respuesta);
    }else{
    throw new ErrorServicio("No se encuentra la observacion buscada");
            
    }
    }
       public void modificar(String id, String mensaje)throws ErrorServicio{
           Observacion c = or.buscarPorId(id);
           if(c!=null){
           c.setId(id);
           c.setMensaje(mensaje);
           or.save(c);
           }else{
               throw new ErrorServicio ("No se encuentra la observacion buscada");
           }
    
    }
}
