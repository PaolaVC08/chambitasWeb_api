package com.ann.chambitasWeb.dtos.request;

import jakarta.validation.constraints.NotBlank;

public class SignupClienteRequest extends BaseSignupRequest {
 

    @NotBlank
    private String tipoUsuario;
    // Getters y setters

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    
}
