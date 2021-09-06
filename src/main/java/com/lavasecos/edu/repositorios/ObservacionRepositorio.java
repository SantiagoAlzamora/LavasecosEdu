/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lavasecos.edu.repositorios;

import com.lavasecos.edu.entidades.Observacion;
import com.lavasecos.edu.entidades.Prenda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
/**
 *
 * @author santi
 */
@Repository
public interface ObservacionRepositorio extends JpaRepository<Observacion, String>{
 
    @Query("SELECT o FROM Observacion o WHERE o.id = :id")
    public Observacion buscarPorId(@Param("id") String id);
    
}
