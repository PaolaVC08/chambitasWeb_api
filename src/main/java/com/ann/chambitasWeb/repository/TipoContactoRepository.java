package com.ann.chambitasWeb.repository;

import com.ann.chambitasWeb.models.TipoContacto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoContactoRepository extends JpaRepository<TipoContacto, Long> {
    java.util.Optional<TipoContacto> findByNombre(String nombre);
}