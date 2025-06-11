package com.ann.chambitasWeb.repository;

import com.ann.chambitasWeb.models.Profesionista;
import com.ann.chambitasWeb.models.Zona;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfesionistaRepository extends JpaRepository<Profesionista, Long> {

    Optional<Profesionista> findByUsuario_Correo(String correo);

    boolean existsByUsuario_Correo(String correo);

    // Buscar por id (ya está en JpaRepository pero puedes agregarlo por claridad)
    Optional<Profesionista> findById(Long id);

    // Buscar profesionista por zona (esto ahora debería funcionar si 'Zona' tiene un campo 'id')
    List<Profesionista> findByZona_Id(Long zonaId);

    // Cambia 'id' por 'idProfesion' para que coincida con el campo correcto en la entidad 'Profesion'
    List<Profesionista> findByProfesiones_Profesion_IdProfesion(Long profesionId);

    void deleteById(Long id);  // Método de eliminación por id

    List<Profesionista> findByZona(Zona zona);

}