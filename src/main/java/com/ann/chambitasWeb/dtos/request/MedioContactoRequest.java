package com.ann.chambitasWeb.dtos.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class MedioContactoRequest {

    @NotNull
    private Long tipoContactoId;  // ID que hace referencia al tipo de contacto (WhatsApp, Email, etc.)

    @NotNull
    @Size(min = 5, message = "El valor debe ser válido")
    private String valor;  // El valor del contacto (como número de teléfono, email, etc.)

    // Getters y Setters

    public Long getTipoContactoId() {
        return tipoContactoId;
    }

    public void setTipoContactoId(Long tipoContactoId) {
        this.tipoContactoId = tipoContactoId;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }
}