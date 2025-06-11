package com.ann.chambitasWeb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ann.chambitasWeb.models.Education;

public interface EducationRepository extends JpaRepository<Education, Long> {
    // Métodos de consulta adicionales si es necesario
}
