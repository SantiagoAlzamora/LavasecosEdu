/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lavasecos.edu.repositorios;

import com.lavasecos.edu.entidades.Pedido;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author santi
 */
@Repository
public interface PedidoRepositorio extends JpaRepository<Pedido, String>{
    
    @Query("SELECT p FROM Pedido p WHERE p.id = :id")
    public Pedido buscarPorId(@Param("id") String id);
    
    @Query("SELECT p FROM Pedido p WHERE p.prenda.cliente.documento = :documento")
    public List<Pedido> buscarPorDocumentoCliente(@Param("documento") Long documento);
    
    @Query("SELECT p FROM Pedido p WHERE p.prenda.cliente.id = :idCliente")
    public List<Pedido> buscarPorIdCliente(@Param("idCliente") String idCliente);
    
}
