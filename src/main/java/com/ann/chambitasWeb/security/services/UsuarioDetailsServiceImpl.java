package com.ann.chambitasWeb.security.services;

import com.ann.chambitasWeb.models.Usuario;
import com.ann.chambitasWeb.repository.UsuarioRepository;
import com.ann.chambitasWeb.validations.UsuarioValidator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioDetailsServiceImpl implements UserDetailsService {

  private final UsuarioRepository usuarioRepository;
  private final UsuarioValidator usuarioValidator;

@Autowired
public UsuarioDetailsServiceImpl(UsuarioRepository usuarioRepository,
                                 UsuarioValidator usuarioValidator) {
  this.usuarioRepository = usuarioRepository;
  this.usuarioValidator = usuarioValidator;
}

  @Override
  @Transactional
  public UserDetails loadUserByUsername(String correo) throws UsernameNotFoundException {
    // CAMBIO: Se busca por correo, ya que es el campo principal de autenticación
    Usuario usuario = usuarioRepository.findByCorreo(correo)
        .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado con el correo: " + correo));

        usuarioValidator.validar(usuario);

    return UsuarioDetailsImpl.build(usuario);
  }
}

