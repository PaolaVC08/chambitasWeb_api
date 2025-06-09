package com.ann.chambitasWeb.dtos.response;

import java.util.List;

public class JwtResponse {
  private String token;
  private String type = "Bearer";
  private Long id;
  private String nombre;
  private String correo;
  private List<String> roles;

  public JwtResponse(String accessToken, Long id, String nombre, String correo, List<String> roles) {
    this.token = accessToken;
    this.id = id;
    this.nombre = nombre;
    this.correo = correo;
    this.roles = roles;
  }

  //GETTERS AND SETTERS

  public String getAccessToken() {
    return token;
  }

  public void setAccessToken(String accessToken) {
    this.token = accessToken;
  }

  public String getTokenType() {
    return type;
  }

  public void setTokenType(String tokenType) {
    this.type = tokenType;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getCorreo() {
    return correo;
  }

  public void setCorreo(String correo) {
    this.correo = correo;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombreCompleto(String nombre) {
    this.nombre = nombre;
  }

  public List<String> getRoles() {
    return roles;
  }
}