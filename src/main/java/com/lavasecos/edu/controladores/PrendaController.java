/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lavasecos.edu.controladores;

import com.lavasecos.edu.entidades.Prenda;
import com.lavasecos.edu.errores.ErrorServicio;
import com.lavasecos.edu.servicios.PrendaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author santi
 */
@Controller
@RequestMapping("/prenda")
public class PrendaController {
    
    @Autowired
    private PrendaServicio ps;
    
    @GetMapping("")
    public String index(){
        return "";
    }
     @GetMapping("/list")
    public String listarPedidos(Model model, RedirectAttributes redirectAttributes) {
        try {
            model.addAttribute("prendas", ps.listarPrendas());
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", e.getMessage());
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        
        return "";
    }
    
     @GetMapping("/mostrar")
    public String mostrarPrenda(Model model, @RequestParam Prenda prenda, RedirectAttributes redirectAttributes) {
        try {
            model.addAttribute("prenda", ps.buscarPorId(prenda.getId()));
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", e.getMessage());
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        
        return "";
    }
    
    @GetMapping("/form")
    public String cargarPrenda(Model model, RedirectAttributes redirectAttributes, @RequestParam(required = false) String id, @RequestParam(required = true) String action){
        try {
            if (id != null) {
                Prenda p = ps.buscarPorId(id);
                if (p!=null) {
                    model.addAttribute("prenda", p);
                }else{
                    return "redirect:/prenda";
                }
            } else {
                model.addAttribute("prenda", new Prenda());
            }
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", e.getMessage());
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        model.addAttribute("action", action);
        return "";
    }
    
    @PostMapping("/save")
    public String guardarPrenda(Model model,RedirectAttributes ra,@ModelAttribute Prenda prenda,@RequestParam String action){
        try {
            if (action.equals("crear")) {
                ps.crearPrenda(prenda);
            }else{
                ps.modificar(prenda.getId(),prenda.getEntrada(),prenda.getSalida(),prenda.getTipo(),
                        prenda.getMancha(),prenda.getColor(),prenda.getMaterial(),prenda.getCliente(),
                        prenda.getObservacion());
            }
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", e.getMessage());
            ra.addFlashAttribute("error", e.getMessage());
            
        }
        return "redirect:/prenda/list";
    }
    
    @GetMapping("/delete")
    public String eliminarPrenda(Model model, RedirectAttributes redirectAttributes, @RequestParam String id) throws ErrorServicio{
        try {
            ps.eliminar(id);
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", e.getMessage());
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        
        return "redirect:/prenda";
    }
}