package com.ann.chambitasWeb.models;

import jakarta.persistence.*;

@Entity
@Table(name = "roles")
public class Rol {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Enumerated(EnumType.STRING)
  @Column(length = 20)
  private ERole nombre;

  public Rol() {

  }

  public Rol(ERole nombre) {
    this.nombre = nombre;
  }

  // getters and setters

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public ERole getNombre() {
    return nombre;
  }

  public void setNombre(ERole name) {
    this.nombre = name;
  }

}
