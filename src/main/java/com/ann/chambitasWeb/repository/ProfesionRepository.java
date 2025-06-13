package com.ann.chambitasWeb.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ann.chambitasWeb.models.Profesion;
import com.ann.chambitasWeb.models.ProfesionistaProfesion;

@Repository
public interface ProfesionRepository extends JpaRepository<Profesion, Long> {
    List<Profesion> findAllByCategoria_IdCategoria(Long idCategoria); // Consulta personalizada para obtener profesiones por categoría


    
}