package com.ann.chambitasWeb.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ann.chambitasWeb.models.Zona;

@Repository
public interface ZonaRepository extends JpaRepository<Zona, Long> {
    Optional<Zona> findByNombre(String nombre);
    boolean existsByNombre(String nombre);
}