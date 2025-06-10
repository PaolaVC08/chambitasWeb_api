package com.ann.chambitasWeb.repository;

import com.ann.chambitasWeb.models.Profesionista;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfesionistaRepository extends JpaRepository<Profesionista, Long> {

    Optional<Profesionista> findByUsuario_Correo(String correo);

    boolean existsByUsuario_Correo(String correo);
}