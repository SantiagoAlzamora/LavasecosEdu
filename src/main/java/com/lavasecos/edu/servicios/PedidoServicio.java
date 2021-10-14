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

    public Pedido crearPedido(Pedido pedido, String idCliente) throws ErrorServicio {
        validar(pedido.getPrecio(), pedido.getDescuento(), pedido.getPrenda());
        prendaServicio.crearPrenda(pedido.getPrenda(), idCliente);
        return pr.save(pedido);
    }

    public void validar(Double precio, Double descuento, Prenda prenda) throws ErrorServicio {
        if (precio == null) {
            throw new ErrorServicio("El precio no puede ser nulo");
        }
//        if(descuento == null){
//            throw new ErrorServicio("El descuento no puede ser nulo");
//        }
//        if (prendaServicio.buscarPorDocumento(prenda.getCliente().getDocumento()) == null){
//            throw new ErrorServicio("La prenda no existe");
//        }   
    }

    public void eliminar(String id) throws ErrorServicio {
        Pedido respuesta = pr.buscarPorId(id);
        if (respuesta != null) {
            pr.delete(respuesta);
        } else {
            throw new ErrorServicio("No se encontró el pedido");
        }
    }

    public void modificar(Pedido pedido, String idCliente) throws ErrorServicio {
        validar(pedido.getPrecio(), pedido.getDescuento(), pedido.getPrenda());
        Pedido pbd = pr.buscarPorId(pedido.getId());
        prendaServicio.modificar2(pedido.getPrenda(), idCliente);
        pbd.setPrecio(pedido.getPrecio());
        pr.save(pbd);
    }

    public Double calcularTotal(List<Pedido> p) {
        Date fecha = new Date();
        Cliente c = p.get(0).getPrenda().getCliente();
        double total = 0;
        double descuento =0;
        for (int i = 0; i < p.size(); i++) {
            Pedido pe = p.get(i);
            total += pe.getPrecio();
            if (c.getCumpleanios().getMonth() == fecha.getMonth()) {
                descuento=0.9;
                pe.setDescuento(0.9);
            } else {
                pe.setDescuento(0.0);
            }
        }
        if (descuento!=0) {
            return (total*descuento);
        }else{
            return (total);
        }
        
    }
    
    public Boolean hayDescuento(Cliente c){
        Date fecha = new Date();
        return (c.getCumpleanios().getMonth() == fecha.getMonth());
    }

    public List<Pedido> buscarPorDocumento(Long documento) {
        return pr.buscarPorDocumentoCliente(documento);
    }

    public Pedido buscarPorId(String id) {
        return pr.buscarPorId(id);
    }

    public List<Pedido> listarPedidos() {
        return pr.findAll();
    }

    public List<Pedido> buscarPorIdCliente(String id) {
        return pr.buscarPorIdCliente(id);
    }
}
