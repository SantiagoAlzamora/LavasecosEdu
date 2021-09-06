/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lavasecos.edu.repositorios;

import com.lavasecos.edu.entidades.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author santi
 */
public interface ClienteRepositorio extends JpaRepository<Cliente,String>{
    
}
