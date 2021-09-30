/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lavasecos.edu.entidades;

import com.lavasecos.edu.enums.Color;
import com.lavasecos.edu.enums.Material;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author santi
 */
@Entity
public class Prenda {
    
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date entrada;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date salida;
    
    private String tipo;
    private String detalle;
//    private Boolean mancha;
//    @Enumerated(EnumType.STRING)
//    private Color color;
//    @Enumerated(EnumType.STRING)
//    private Material material;
    
    @ManyToOne
    private Cliente cliente;
//    @ManyToOne
//    private Observacion observacion;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getEntrada() {
        return entrada;
    }

    public void setEntrada(Date entrada) {
        this.entrada = entrada;
    }

    public Date getSalida() {
        return salida;
    }

    public void setSalida(Date salida) {
        this.salida = salida;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }
    
//
//    public Boolean getMancha() {
//        return mancha;
//    }
//
//    public void setMancha(Boolean mancha) {
//        this.mancha = mancha;
//    }
//
//    public Color getColor() {
//        return color;
//    }
//
//    public void setColor(Color color) {
//        this.color = color;
//    }
//
//    public Material getMaterial() {
//        return material;
//    }
//
//    public void setMaterial(Material material) {
//        this.material = material;
//    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

//    public Observacion getObservacion() {
//        return observacion;
//    }
//
//    public void setObservacion(Observacion observacion) {
//        this.observacion = observacion;
//    }
//    
    
}
