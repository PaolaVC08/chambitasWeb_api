package com.ann.chambitasWeb.dtos.request;

public class MedioContactoRequest {

    private Long tipoContactoId;  // ID del tipo de contacto (WhatsApp, Email, etc.)
    private String valor;         // El valor del contacto (número, email, etc.)
    private Long profesionistaId; // ID del profesionista dueño del contacto

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

    public Long getProfesionistaId() {
        return profesionistaId;
    }

    public void setProfesionistaId(Long profesionistaId) {
        this.profesionistaId = profesionistaId;
    }
}