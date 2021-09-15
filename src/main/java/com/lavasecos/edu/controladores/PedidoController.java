/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lavasecos.edu.controladores;

import com.lavasecos.edu.entidades.Pedido;
import com.lavasecos.edu.servicios.PedidoServicio;
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
@RequestMapping("/pedido")
public class PedidoController {

    @Autowired
    private PedidoServicio ps;

    @GetMapping("")
    public String index() {
        return "Menu";
    }
    
    @GetMapping("/create")
    public String paginaNuevoPedido(){
        return "nuevopedido";
    }

    @GetMapping("/list")
    public String listarPedidos(Model model, RedirectAttributes redirectAttributes) {
        try {
            model.addAttribute("pedidos", ps.listarPedidos());
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", e.getMessage());
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        
        return "";
    }

    @GetMapping("/mostrar")
    public String mostrarPedido(Model model, @RequestParam Pedido pedido, RedirectAttributes redirectAttributes) {
        try {
            model.addAttribute("pedido", ps.buscarPorId(pedido.getId()));
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", e.getMessage());
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        
        return "";
    }

    @GetMapping("/form")
    public String cargarPedido(Model model, RedirectAttributes redirectAttributes, @RequestParam(required = false) String id, @RequestParam(required = true) String action) {
        try {
            if (id != null) {
                Pedido p = ps.buscarPorId(id);
                if (p!=null) {
                    model.addAttribute("pedido", p);
                }else{
                    return "redirect:/pedido";
                }
            } else {
                model.addAttribute("pedido", new Pedido());
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
    public String guardarPedido(Model model, RedirectAttributes redirectAttributes, @ModelAttribute Pedido pedido, @RequestParam String action) {
        try {
            if (action.equals("crear")) {
                ps.crearPedido(pedido);
            }else{
                ps.modificar(pedido.getId(),pedido.getPrecio(), pedido.getDescuento(), pedido.getPrenda());
            }
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", e.getMessage());
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            
        }
        return "redirect:/pedido/list";
    }

    @GetMapping("/delete")
    public String eliminarPedido(Model model, RedirectAttributes redirectAttributes, @RequestParam String id) {
        try {
            ps.eliminar(id);
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", e.getMessage());
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/pedido";
    }
}
