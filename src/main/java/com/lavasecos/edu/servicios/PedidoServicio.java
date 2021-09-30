/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lavasecos.edu.servicios;


import com.lavasecos.edu.entidades.Cliente;
import com.lavasecos.edu.entidades.Pedido;
import com.lavasecos.edu.entidades.Prenda;
import com.lavasecos.edu.errores.ErrorServicio;
import com.lavasecos.edu.repositorios.PedidoRepositorio;
import com.lavasecos.edu.repositorios.PrendaRepositorio;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author santi
 */
@Service
public class PedidoServicio {
    
    @Autowired
    private PedidoRepositorio pr;
    @Autowired
    private PrendaServicio prendaServicio;
    
    public Pedido crearPedido(Pedido pedido,String idCliente) throws ErrorServicio{
        validar(pedido.getPrecio(),pedido.getDescuento(),pedido.getPrenda());
        prendaServicio.crearPrenda(pedido.getPrenda(), idCliente);
        return pr.save(pedido);
    }
    
    public void validar(Double precio,Double descuento,Prenda prenda) throws ErrorServicio{
        if (precio == null){
            throw new ErrorServicio("El precio no puede ser nulo");
        }
//        if(descuento == null){
//            throw new ErrorServicio("El descuento no puede ser nulo");
//        }
//        if (prendaServicio.buscarPorDocumento(prenda.getCliente().getDocumento()) == null){
//            throw new ErrorServicio("La prenda no existe");
//        }   
    }
    public void eliminar(String id) throws ErrorServicio{
        Pedido respuesta = pr.buscarPorId(id);
        if (respuesta != null){
            pr.delete(respuesta);
        }else{
            throw new ErrorServicio("No se encontró el pedido");
        }
    }
    public void modificar(String id,Double precio,Double descuento,Prenda prenda) throws ErrorServicio{
        validar(precio,descuento,prenda);
        Pedido p = pr.buscarPorId(id);
        if(p != null){
            p.setPrecio(precio);
            p.setDescuento(descuento);
            p.setPrenda(prenda);
            pr.save(p);
        }else {
            throw new ErrorServicio("No se encontró el pedido solicitado");
        }
    }
    public Double precioDelPedido(Pedido pedido){
        Cliente c = pedido.getPrenda().getCliente();
        Date fecha = new Date();
        if(c.getCumpleanios().getMonth()==fecha.getMonth()){
            pedido.setDescuento(0.9);
            return pedido.getPrecio()*pedido.getDescuento();
        }else{
            pedido.setDescuento(0.0);
            return pedido.getPrecio();
        }
    }
    public List<Pedido> buscarPorDocumento(Long documento){
        return pr.buscarPorDocumentoCliente(documento);
    }
    public Pedido buscarPorId(String id){
        return pr.buscarPorId(id);
    }
    public List<Pedido> listarPedidos(){
        return pr.findAll();
    }
}
