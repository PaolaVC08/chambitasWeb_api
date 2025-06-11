package com.ann.chambitasWeb.dtos.response;

import com.ann.chambitasWeb.models.TipoContacto;

public class MedioContactoResponse {

    private Long idMcontacto;
    private Long tipoContactoId;          // ← Debe ser Long
    private String tipoContactoNombre;    // ← Nombre legible
    private String valor;

    // Getters y Setters
    public Long getIdMcontacto() {
        return idMcontacto;
    }

    public void setIdMcontacto(Long idMcontacto) {
        this.idMcontacto = idMcontacto;
    }

    public Long getTipoContactoId() {
        return tipoContactoId;
    }

    public void setTipoContactoId(Long tipoContactoId) {
        this.tipoContactoId = tipoContactoId;
    }

    public String getTipoContactoNombre() {
        return tipoContactoNombre;
    }

    public void setTipoContactoNombre(String tipoContactoNombre) {
        this.tipoContactoNombre = tipoContactoNombre;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }
}