package com.ann.chambitasWeb.repository;

import com.ann.chambitasWeb.models.Zona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

@Repository
public interface ZonaRepository extends JpaRepository<Zona, Long> {

    // Método para obtener una zona por su nombre
    Optional<Zona> findByNombre(String nombre);

    // Método para obtener todas las zonas
    List<Zona> findAll();

    boolean existsByNombre(String nombre);

    

}