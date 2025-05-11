package com.ann.chambitasWeb.dtos.request;

import jakarta.validation.constraints.NotBlank;

public class LoginRequest {
    @NotBlank
    private String correo;
  
    @NotBlank
    private String password;
}
