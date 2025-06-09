package com.ann.chambitasWeb.repository;

import com.ann.chambitasWeb.models.MedioContacto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedioContactoRepository extends JpaRepository<MedioContacto, Long> {
    // Puedes agregar métodos personalizados si los necesitas
//    List<MedioContacto> FindBy
    
}