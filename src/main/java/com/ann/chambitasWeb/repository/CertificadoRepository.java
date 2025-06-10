package com.ann.chambitasWeb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ann.chambitasWeb.models.Certificado;

@Repository
public interface CertificadoRepository extends JpaRepository<Certificado, Long> {
    // Métodos de consulta adicionales si es necesario
}
