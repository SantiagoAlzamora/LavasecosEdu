
package com.lavasecos.edu.servicios;


import com.lavasecos.edu.entidades.Cliente;
import com.lavasecos.edu.entidades.Observacion;
import com.lavasecos.edu.entidades.Prenda;
import com.lavasecos.edu.enums.Color;
import com.lavasecos.edu.enums.Material;
import com.lavasecos.edu.errores.ErrorServicio;
import com.lavasecos.edu.repositorios.ClienteRepositorio;
import com.lavasecos.edu.repositorios.PrendaRepositorio;
import java.util.Date;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PrendaServicio {
    
    @Autowired
    private PrendaRepositorio pr;
    @Autowired
    private ClienteRepositorio cr;
    
    @Transactional
    public Prenda crearPrenda(Prenda prenda) throws ErrorServicio{
        validar(prenda.getEntrada(),prenda.getSalida(),prenda.getTipo(),prenda.getMancha(),prenda.getColor(),prenda.getMaterial(),prenda.getCliente(),prenda.getObservacion());
        return pr.save(prenda);
    }
    private void validar(Date entrada,Date salida,String tipo,Boolean mancha,Color color,Material material,Cliente cliente,Observacion observacion) throws ErrorServicio{
        
        if(entrada == null){
            throw new ErrorServicio("La fecha de entrada no puede ser nula");
        }
        if(salida == null){
            throw new ErrorServicio("La fecha de entrada no puede ser nula");
        }
        if(tipo == null || tipo.isEmpty()){
            throw new ErrorServicio("El tipo de prenda no puede ser nulo");
        }
        if(mancha == null){
            throw new ErrorServicio("");
        }
        if(color == null){
            throw new ErrorServicio("El color no puede ser nulo");
        }
        if(material == null){
            throw new ErrorServicio("El material no puede ser nulo");
        }
        if(cr.buscarPorDocumento(cliente.getDocumento()) == null){
            throw new ErrorServicio("El cliente no existe");
        }
        
    }
    public void eliminar(String id) throws ErrorServicio{
        Prenda respuesta = pr.buscarPorId(id);
        if (respuesta != null){
            pr.delete(respuesta);
        }else{
            throw new ErrorServicio("No se encontró la prenda");
        }
    }
    public void modificar(String id,Date entrada,Date salida,String tipo,Boolean mancha,Color color,Material material,Cliente cliente,Observacion observacion) throws ErrorServicio{
        validar(entrada,salida,tipo,mancha,color,material,cliente,observacion);
        Prenda p = pr.buscarPorId(id);
        if (p != null){
            p.setEntrada(entrada);
            p.setSalida(salida);
            p.setTipo(tipo);
            p.setMancha(mancha);
            p.setColor(color);
            p.setMaterial(material);
            p.setCliente(cliente);
            p.setObservacion(observacion);
            
            pr.save(p);
        }else {
            throw new ErrorServicio("No se encontró la prenda solicitada");
        }
    }
    public void buscarPorDocumento(Long documento){
        pr.buscarPorDocumento(documento);
    }
    public void buscarPorId(String id){
        pr.buscarPorId(id);
    }
}
