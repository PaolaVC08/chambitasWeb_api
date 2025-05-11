package com.ann.chambitasWeb.repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ann.chambitasWeb.models.ERole;
import com.ann.chambitasWeb.models.Rol;

@Repository
public interface RoleRepository extends JpaRepository<Rol, Long> {
  Optional<Rol> findByNombre(ERole nombre);
}
