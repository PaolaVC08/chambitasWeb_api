package com.ann.chambitasWeb.models;

import java.time.LocalDateTime;
import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "verificacion_correo")
public class VerificacionCorreo {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_verificacion")
  private Long id;

  @ManyToOne(optional = false, fetch = FetchType.LAZY)
  @JoinColumn(name = "usuario_id", nullable = false)
  @OnDelete(action = OnDeleteAction.CASCADE)
  private Usuario usuario;

  @Column(length = 255, nullable = false)
  private String token;

  private LocalDateTime fechaCreacion;
  private LocalDateTime fechaExpiracion;

  @Enumerated(EnumType.STRING)
  private EstadoVerificacion estado;

  // Getters y Setters

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Usuario getUsuario() {
    return usuario;
  }

  public void setUsuario(Usuario usuario) {
    this.usuario = usuario;
  }

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }

  public LocalDateTime getFechaCreacion() {
    return fechaCreacion;
  }

  public void setFechaCreacion(LocalDateTime fechaCreacion) {
    this.fechaCreacion = fechaCreacion;
  }

  public LocalDateTime getFechaExpiracion() {
    return fechaExpiracion;
  }

  public void setFechaExpiracion(LocalDateTime fechaExpiracion) {
    this.fechaExpiracion = fechaExpiracion;
  }

  public EstadoVerificacion getEstado() {
    return estado;
  }

  public void setEstado(EstadoVerificacion estado) {
    this.estado = estado;
  }
}
