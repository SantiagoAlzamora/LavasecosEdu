/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lavasecos.edu.servicios;


import com.lavasecos.edu.entidades.Cliente;
import com.lavasecos.edu.errores.ErrorServicio;
import com.lavasecos.edu.repositorios.ClienteRepositorio;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class ClienteServicio {
    @Autowired
    private ClienteRepositorio cr;
    

    public Cliente cliente(Cliente cliente) throws ErrorServicio{
        validar(cliente.getDocumento(), cliente.getNombre(), cliente.getApellido(), cliente.getCumpleanios(), cliente.getDomicilio(), cliente.getTelefono(), cliente.getEmail());
        return cr.save(cliente);
    
    }
    public void validar(Long documento, String nombre, String apellido, Date cumpleanios, String domicilio, String telefono, String email) throws ErrorServicio{
        if(documento==null){
            throw new ErrorServicio("El documento del cliente no puede ser nulo");
        }
        if(nombre==null || nombre.isEmpty()){
            throw new ErrorServicio("El nombre del cliente no puede ser nulo");
        }
           if(apellido==null || apellido.isEmpty()){
            throw new ErrorServicio("El documento del cliente no puede ser nulo");
        }
        if(cumpleanios==null){
            throw new ErrorServicio("El nombre del cliente no puede ser nulo");
        }
           if(domicilio==null || domicilio.isEmpty()){
            throw new ErrorServicio("El documento del cliente no puede ser nulo");
        }
        if(telefono==null || telefono.isEmpty()){
            throw new ErrorServicio("El nombre del cliente no puede ser nulo");
        }
           if(email==null || email.isEmpty()){
            throw new ErrorServicio("El documento del cliente no puede ser nulo");
        }
    
    }
    public void buscarPorDni(Long documento) throws ErrorServicio{
        cr.buscarPorDocumento(documento);
    }
    
    public void eliminar (Long documento) throws ErrorServicio{
        Cliente respuesta = cr.buscarPorDocumento(documento);
        if(respuesta != null){
            cr.delete(respuesta);
            
        } else {
            throw new ErrorServicio("No se encontro el cliente");
        }
    }
    public void modificar(Long documento, String nombre, String apellido, Date cumpleanios, String domicilio, String telefono, String email) throws ErrorServicio{
        validar(documento,nombre,apellido,cumpleanios,domicilio,telefono,email);
        Cliente c = cr.buscarPorDocumento(documento);
        if ( c!= null){
        c.setDocumento(documento);
        c.setNombre(nombre);
        c.setApellido(apellido);
        c.setCumpleanios(cumpleanios);
        c.setDomicilio(domicilio);
        c.setTelefono(telefono);
        c.setEmail(email);
        cr.save(c);
        } else {
            throw new ErrorServicio("El cliente solicitado no existe en nuestra base");
        }
    }
}
