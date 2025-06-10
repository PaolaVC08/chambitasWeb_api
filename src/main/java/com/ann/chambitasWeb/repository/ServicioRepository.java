package com.ann.chambitasWeb.repository;

import com.ann.chambitasWeb.models.Servicio;
import com.ann.chambitasWeb.models.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServicioRepository extends JpaRepository<Servicio, Long> {
    List<Servicio> findByCategoria(Categoria categoria);
}