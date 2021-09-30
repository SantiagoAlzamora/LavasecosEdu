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
import java.util.List;
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
    public Cliente buscarPorDni(Long documento) throws ErrorServicio{
        return cr.buscarPorDocumento(documento);
    }
    
    public List<Cliente> listarClientes(){
        return cr.findAll();
    }
    
    public Cliente buscarPorId(String id){
        return cr.getById(id);
    }
    
    public void eliminar (Long documento) throws ErrorServicio{
        Cliente respuesta = cr.buscarPorDocumento(documento);
        if(respuesta != null){
            cr.delete(respuesta);
            
        } else {
            throw new ErrorServicio("No se encontro el cliente");
        }
    }
    public Cliente modificar(Cliente cliente) throws ErrorServicio{
        Cliente c = cr.buscarPorDocumento(cliente.getDocumento());
        if ( c!= null){
        c.setDocumento(cliente.getDocumento());
        c.setNombre(cliente.getNombre());
        c.setApellido(cliente.getApellido());
        c.setCumpleanios(cliente.getCumpleanios());
        c.setDomicilio(cliente.getDomicilio());
        c.setTelefono(cliente.getTelefono());
        c.setEmail(cliente.getEmail());
        cr.save(c);
        } else {
            throw new ErrorServicio("El cliente solicitado no existe en nuestra base");
        }
        return c;
    }

}
