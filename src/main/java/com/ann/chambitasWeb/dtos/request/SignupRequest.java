package com.ann.chambitasWeb.dtos.request;

import java.time.LocalDate;
import jakarta.validation.constraints.*;

public class SignupRequest {
  @NotBlank
  @Size(min = 3, max = 20)
  private String nombre;

  @NotBlank
  @Size(min = 3, max = 20)
  private String apPaterno;

  @NotBlank
  @Size(min = 3, max = 20)
  private String apMaterno;

  @Past
  private LocalDate fechaNacimiento;

  @NotBlank
  @Size(min=6,max = 40)
  @Email
  private String correo;

  @NotBlank
  private String tipoUsuario;//cliente, profesional

  @NotBlank
  @Size(min = 5, max = 50)
  private String password;

  //GETTERS AND SETTERS 

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public String getApPaterno() {
    return apPaterno;
  }

  public void setApPaterno(String apPaterno) {
    this.apPaterno = apPaterno;
  }

  public String getApMaterno() {
    return apMaterno;
  }

  public void setApMaterno(String apMaterno) {
    this.apMaterno = apMaterno;
  }

  public LocalDate getFechaNacimiento() {
    return fechaNacimiento;
  }

  public void setFechaNacimiento(LocalDate fechaNacimiento) {
    this.fechaNacimiento = fechaNacimiento;
  }

  public String getCorreo() {
    return correo;
  }

  public void setCorreo(String correo) {
    this.correo = correo;
  }

  public String getTipoUsuario() {
    return tipoUsuario;
  }

  public void setTipoUsuario(String tipoUsuario) {
    this.tipoUsuario = tipoUsuario;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

}

