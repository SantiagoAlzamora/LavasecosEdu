/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lavasecos.edu.controladores;

import com.lavasecos.edu.entidades.Cliente;
import com.lavasecos.edu.entidades.Pedido;

import com.lavasecos.edu.servicios.ClienteServicio;
import com.lavasecos.edu.servicios.NotificacionServicio;
import com.lavasecos.edu.servicios.PedidoServicio;
import java.util.List;
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

    @Autowired
    private PedidoServicio ps;

    @Autowired
    private NotificacionServicio ns;

    @GetMapping("")
    public String index(Model model, @RequestParam(required = false) String idCliente, @RequestParam(required = false) String accion) {
        try {
            if (idCliente != null) {
                model.addAttribute("cliente", cs.buscarPorId(idCliente));
                model.addAttribute("pedidos", ps.buscarPorIdCliente(idCliente));

                if (ps.buscarPorIdCliente(idCliente) != null) {
                    model.addAttribute("total", ps.calcularTotal(ps.buscarPorIdCliente(idCliente)));
                    model.addAttribute("icono", ps.hayDescuento(cs.buscarPorId(idCliente)));
                    double total=ps.calcularTotal(ps.buscarPorIdCliente(idCliente));
                    
                    if (accion != null && accion.equals("facturar")) {
                        model.addAttribute("alerta","Factura enviada correctamente");
                        ns.enviar(total,cs.buscarPorId(idCliente));
                    }
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", e.getMessage());
        }
        return "Facturar";
    }

    @PostMapping("/registrar")
    public String registrarCliente(Model model, RedirectAttributes redirectAttributes, @ModelAttribute Cliente cliente, @RequestParam String accion, @RequestParam(required = false) String pedido) {
        Cliente c = null;
        try {
            if (accion.equals("crear")) {
                c = cs.cliente(cliente);
                return "redirect:/pedido/form?accion=" + accion + "&idCliente=" + c.getId();
            } else {
                c = cs.modificar(cliente);
                System.out.println(pedido);
                if (pedido != null && !pedido.isEmpty()) {
                    return "redirect:/pedido/form?accion=" + accion + "&idCliente=" + c.getId();
                }
                redirectAttributes.addFlashAttribute("success", "Cliente modificado con exito");
                return "redirect:/cliente/form2?accion=" + accion;
            }
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", e.getMessage());
            model.addAttribute("cliente", cliente);
            model.addAttribute("accion", accion != null ? accion : "crear");
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "crear-cliente";
        }

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

    @GetMapping("/form2")
    public String editarCliente(Model model, RedirectAttributes redirectAttributes, @RequestParam(required = false) String id, @RequestParam(required = true) String accion, @RequestParam(required = false) Long q) {
        try {
            if (q != null) {
                Cliente cl = cs.buscarPorDni(q);
                if (cl != null) {
                    model.addAttribute("cliente", cl);
                    model.addAttribute("accion", accion);
                } else {
                    model.addAttribute("error", "No se encontro un Cliente");
                    model.addAttribute("cliente", new Cliente());
                }
            } else {
                model.addAttribute("cliente", new Cliente());
            }
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", e.getMessage());
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        model.addAttribute("accion", accion);

        return "editar-cliente";
    }

}
