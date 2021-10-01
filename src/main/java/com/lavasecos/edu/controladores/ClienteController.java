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
    public String registrarCliente(Model model, RedirectAttributes redirectAttributes, @ModelAttribute Cliente cliente, @RequestParam("accion") String accion) {
        Cliente c = null;
        try {
            if (accion.equals("crear")) {
                c = cs.cliente(cliente);

            } else {
                c = cs.modificar(cliente);
            }
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", e.getMessage());
            model.addAttribute("cliente", cliente);
            model.addAttribute("accion", accion != null ? accion : "crear");
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "crear-cliente";
        }

        return "redirect:/pedido/form?accion=" + accion + "&idCliente=" + c.getId();
    }

    @GetMapping("/list")
    public String listarClientes(Model model, RedirectAttributes redirectAttributes, @RequestParam(required = false) String q) {
        try {
            if (q != null) {
                Cliente c = cs.buscarPorDni(Long.valueOf(q));
                if (c == null) {
                    model.addAttribute("error", "El cliente no existe");
                } else {
                    model.addAttribute("clientes", c);
                }

            } else {
                model.addAttribute("clientes", cs.listarClientes());
            }

        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", e.getMessage());
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }

        return "pedidos-paso1";
    }

    @GetMapping("/form")
    public String crearCliente(Model model, RedirectAttributes redirectAttributes, @RequestParam(required = false) String id, @RequestParam(required = true) String accion) {
        try {
            if (id != null) {
                Cliente cl = cs.buscarPorId(id);
                if (cl != null) {
                    model.addAttribute("cliente", cl);
                    model.addAttribute("accion", accion);
                } else {
                    return "redirect:/cliente";
                }
            } else {
                model.addAttribute("cliente", new Cliente());
                model.addAttribute("accion", "crear");
            }
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", e.getMessage());
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        model.addAttribute("accion", accion);

        return "crear-cliente";
    }

}
