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
    private Boolean mancha;
    @Enumerated(EnumType.STRING)
    private Color color;
    @Enumerated(EnumType.STRING)
    private Material material;
    
    @ManyToOne
    private Cliente cliente;
    @ManyToOne
    private Observacion observacion;
    
}
