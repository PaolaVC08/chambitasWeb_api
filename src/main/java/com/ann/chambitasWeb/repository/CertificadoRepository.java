package com.ann.chambitasWeb.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ann.chambitasWeb.models.Certificado;

@Repository
public interface CertificadoRepository extends JpaRepository<Certificado, Long> {

    // Método para obtener certificados por profesionista
    List<Certificado> findByProfesionista_Id(Long profesionistaId);

}
