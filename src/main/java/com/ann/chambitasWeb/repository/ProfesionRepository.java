package com.ann.chambitasWeb.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ann.chambitasWeb.models.Profesion;

@Repository
public interface ProfesionRepository extends JpaRepository<Profesion, Long> {
    List<Profesion> findAllByCategoria_IdCategoria(Long idCategoria); // ← método personalizado
}

