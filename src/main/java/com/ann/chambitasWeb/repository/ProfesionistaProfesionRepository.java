package com.ann.chambitasWeb.repository;

import com.ann.chambitasWeb.models.ProfesionistaProfesion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfesionistaProfesionRepository extends JpaRepository<ProfesionistaProfesion, Long> {

    List<ProfesionistaProfesion> findByProfesionista_Id(Long profesionistaId);
}