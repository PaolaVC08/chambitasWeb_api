package com.ann.chambitasWeb.security.services;

import com.ann.chambitasWeb.models.Usuario;
import com.ann.chambitasWeb.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioDetailsServiceImpl implements UserDetailsService {

  @Autowired
  UsuarioRepository usuarioRepository;

  @Override
  @Transactional
  public UserDetails loadUserByUsername(String correo) throws UsernameNotFoundException {
    // CAMBIO: Se busca por correo, ya que es el campo principal de autenticación
    Usuario usuario = usuarioRepository.findByCorreo(correo)
        .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado con el correo: " + correo));

    return UsuarioDetailsImpl.build(usuario);
  }
}

