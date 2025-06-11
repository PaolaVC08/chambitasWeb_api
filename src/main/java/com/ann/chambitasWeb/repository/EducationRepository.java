package com.ann.chambitasWeb.repository;


import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;

import com.ann.chambitasWeb.models.Education;

public interface EducationRepository extends JpaRepository<Education, Long> {

    // Método para obtener educaciones por profesionista
    List<Education> findByProfesionista_Id(Long profesionistaId);

}
