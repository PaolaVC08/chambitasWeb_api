package com.ann.chambitasWeb.repository;

import com.ann.chambitasWeb.models.Servicio;
import com.ann.chambitasWeb.models.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServicioRepository extends JpaRepository<Servicio, Long> {
    List<Servicio> findByCategoria(Categoria categoria);

    // Método para obtener todos los servicios de un profesionista por su ID
    List<Servicio> findByProfesionistaProfesion_IdPp(Long profesionistaId);
}