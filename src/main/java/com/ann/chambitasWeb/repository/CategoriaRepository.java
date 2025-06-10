package com.ann.chambitasWeb.repository;

import com.ann.chambitasWeb.models.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
        Categoria findByNombre(String nombre); // si quisieras buscar por nombre

} 