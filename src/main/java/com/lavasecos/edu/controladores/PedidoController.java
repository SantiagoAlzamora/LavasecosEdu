/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lavasecos.edu.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author santi
 */
@Controller
@RequestMapping("/pedido")
public class PedidoController {
    
    @GetMapping("")
    public String index(){
        return "";
    }
    
    @GetMapping("/list")
    public String listarPedidos(){
        return "";
    }
    
    @GetMapping("/mostrar")
    public String mostrarPedido(){
        return "";
    }
    
    @GetMapping("/form")
    public String cargarPedido(){
        return "";
    }
    
    @PostMapping("/save")
    public String guardarPedido(){
        return "redirect:/pedido";
    }
    
    @GetMapping("/delete")
    public String eliminarPedido(){
        return "redirect:/pedido";
    }
}
