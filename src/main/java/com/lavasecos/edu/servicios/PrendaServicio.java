
package com.lavasecos.edu.servicios;


import com.lavasecos.edu.entidades.Cliente;
import com.lavasecos.edu.entidades.Prenda;
import com.lavasecos.edu.errores.ErrorServicio;
import com.lavasecos.edu.repositorios.PrendaRepositorio;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PrendaServicio {
    
    @Autowired
    private PrendaRepositorio pr;
    @Autowired
    private ClienteServicio cs;
    
    
    
    @Transactional
    public Prenda crearPrenda(Prenda prenda, String idCliente) throws ErrorServicio{
        Cliente c = cs.buscarPorId(idCliente);
        validar2(prenda.getTipo(),c,prenda.getDetalle());
        prenda.setCliente(c);
        return pr.save(prenda);
    }
    public void validar2(String tipo, Cliente cliente, String detalle) throws ErrorServicio{
        if (tipo==null || tipo.isEmpty()) {
            throw new ErrorServicio("El tipo no puede ser nulo");
        }
        if(cliente == null){
            throw new ErrorServicio("El cliente no existe");
        }
        if(detalle.isEmpty() || detalle==null){
            throw new ErrorServicio("Debes escribir una observacion de la prenda");
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
    
    public void modificar2(Prenda p,String idCliente) throws ErrorServicio{
        Cliente c = cs.buscarPorId(idCliente);
        Prenda prendabd = pr.buscarPorId(p.getId());
        validar2(p.getTipo(),c,p.getDetalle());
        prendabd.setTipo(p.getTipo());
        prendabd.setDetalle(p.getDetalle());
        pr.save(prendabd);
    }
    public List<Prenda> buscarPorDocumento(Long documento){
        return pr.buscarPorDocumento(documento);
        
    }
    public Prenda buscarPorId(String id){
        return pr.buscarPorId(id);   
    }
    public List<Prenda> listarPrendas(){
        return pr.findAll();
    }
}
