/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lavasecos.edu.controladores;

import com.lavasecos.edu.entidades.Cliente;

import com.lavasecos.edu.servicios.ClienteServicio;
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
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    private ClienteServicio cs;

    @GetMapping("")
    public String index() {
        return "Facturar";
    }

    @PostMapping("/registrar")
    public String registrarCliente(Model model, RedirectAttributes redirectAttributes, @ModelAttribute Cliente cliente, @RequestParam String action) {
        try {
            if (action.equals("crear")) {
                cs.cliente(cliente);

            } else {
                cs.modificar(cliente.getId(), cliente.getDocumento(), cliente.getNombre(), cliente.getApellido(), cliente.getCumpleanios(), cliente.getDomicilio(), cliente.getTelefono(), cliente.getEmail());
            }
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", e.getMessage());
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/pedido/form";
    }

    @GetMapping("/list")
    public String listarClientes(Model model, RedirectAttributes redirectAttributes) {
        try {
            model.addAttribute("clientes", cs.listarClientes());
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", e.getMessage());
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }

        return "pedidos-paso1";
    }

    @GetMapping("/form")
    public String crearCliente(Model model, RedirectAttributes redirectAttributes, @RequestParam(required = false) String id, @RequestParam(required = true) String action) {
        try {
            if (id != null) {
                Cliente cl = cs.buscarPorId(id);
                if (cl != null) {
                    model.addAttribute("cliente", cl);
                } else {
                    return "redirect:/cliente";
                }
            } else {
                model.addAttribute("cliente", new Cliente());
            }
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", e.getMessage());
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        model.addAttribute("action", action);

        return "crear-cliente";
    }

}
