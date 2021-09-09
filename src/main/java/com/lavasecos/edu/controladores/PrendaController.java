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
@RequestMapping("/prenda")
public class PrendaController {
    
    @GetMapping("")
    public String index(){
        return "";
    }
    
    @GetMapping("/form")
    public String cargarPrenda(){
        return "";
    }
    
    @PostMapping("/save")
    public String guardarPrenda(){
        return "redirect:/prenda";
    }
    
    @GetMapping("/delete")
    public String eliminarPrenda(){
        return "redirect:/prenda";
    }
}