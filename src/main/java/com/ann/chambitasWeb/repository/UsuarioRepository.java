package com.ann.chambitasWeb.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ann.chambitasWeb.models.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
  Optional<Usuario> findByCorreo(String correoUsuario);
  Boolean existsByCorreo(String correo);
  
}
