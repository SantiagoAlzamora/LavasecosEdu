/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lavasecos.edu.servicios;

import com.lavasecos.edu.entidades.Cliente;
import com.lavasecos.edu.entidades.Pedido;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 *
 * @author santi
 */
@Service
public class NotificacionServicio {

    @Autowired
    private JavaMailSender mailSender;
    
    @Autowired
    private PedidoServicio ps;

    @Async
    public void enviar(Double total, Cliente c) {
        SimpleMailMessage mensaje = new SimpleMailMessage();
        String titulo="Facturacion";
        List<Pedido> p = ps.buscarPorIdCliente(c.getId());
        String cuerpo="Su lista de pedidos es: \n"+p.toString()+" \n El total a pagar es: "+total;
        mensaje.setTo(c.getEmail());
        mensaje.setFrom("clasesgrabadaslamatanza@gmail.com");
        mensaje.setSubject(titulo);
        mensaje.setText(cuerpo);

        mailSender.send(mensaje);
    }
}
