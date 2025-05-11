package com.ann.chambitasWeb.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ann.chambitasWeb.models.VerificacionCorreo;

@Repository
 
public interface VerificacionCorreoRepository extends JpaRepository<VerificacionCorreo, Long>{
    Optional<VerificacionCorreo> findByToken(String token);
    
}
