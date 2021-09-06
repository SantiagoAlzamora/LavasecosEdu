/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lavasecos.edu.repositorios;

import com.lavasecos.edu.entidades.Prenda;
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
public interface PrendaRepositorio extends JpaRepository<Prenda, String>{
    
    @Query("SELECT p FROM Prenda p WHERE p.id = :id")
    public Prenda buscarPorId(@Param("id") String id);
    
    @Query("SELECT p FROM Prenda p WHERE p.cliente.documento = :documento")
    public List<Prenda> buscarPorDocumento(@Param("documento")Long documento);
    
}
