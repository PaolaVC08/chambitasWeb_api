package com.ann.chambitasWeb.models;

import java.time.Year;
import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "certificados")
public class Certificado {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long idCertificate;

  @ManyToOne(optional = false, fetch = FetchType.LAZY)
  @JoinColumn(name = "profesionista_id", nullable = false)
  @OnDelete(action = OnDeleteAction.CASCADE)
  private Profesionista profesionista;

  @Column(nullable = false)
  private String titulo;

  @Column(nullable = false)
  private String institucion;

  private Year anio;

  // Getters y Setters (opcional si los necesitas)
  public Long getIdCertificate() {
    return idCertificate;
  }

  public void setIdCertificate(Long idCertificate) {
    this.idCertificate = idCertificate;
  }

  public Profesionista getProfesionista() {
    return profesionista;
  }

  public void setProfesionista(Profesionista profesionista) {
    this.profesionista = profesionista;
  }

  public String getTitulo() {
    return titulo;
  }

  public void setTitulo(String titulo) {
    this.titulo = titulo;
  }

  public String getInstitucion() {
    return institucion;
  }

  public void setInstitucion(String institucion) {
    this.institucion = institucion;
  }

  public Year getAnio() {
    return anio;
  }

  public void setAnio(Year anio) {
    this.anio = anio;
  }
}
