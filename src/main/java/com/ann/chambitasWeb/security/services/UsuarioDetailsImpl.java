package com.ann.chambitasWeb.security.services;

import com.ann.chambitasWeb.models.Usuario;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class UsuarioDetailsImpl implements UserDetails {

  private static final long serialVersionUID = 1L;

  private Long id;

  private String correo;

  @JsonIgnore
  private String password;

  private Collection<? extends GrantedAuthority> authorities;

  public UsuarioDetailsImpl(Long id, String correo, String password,
                            Collection<? extends GrantedAuthority> authorities) {
    this.id = id;
    this.correo = correo;
    this.password = password;
    this.authorities = authorities;
  }

  public static UsuarioDetailsImpl build(Usuario usuario) {
    List<GrantedAuthority> authorities = usuario.getRoles().stream()
        .map(rol -> new SimpleGrantedAuthority(rol.getNombre().name()))
        .collect(Collectors.toList());

    return new UsuarioDetailsImpl(
        usuario.getId(),
        usuario.getCorreo(),
        usuario.getPassword(),
        authorities);
  }

  public Long getId() {
    return id;
  }

  public String getCorreo() {
    return correo;
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    // CAMBIO: se usa el correo como campo principal de autenticación
    return correo;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return authorities;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    UsuarioDetailsImpl that = (UsuarioDetailsImpl) o;
    return Objects.equals(id, that.id);
  }
}

