package com.ann.chambitasWeb.dtos.response;

import java.util.List;

public class ZonaProfesionistaResponse {
    private Long profesionistaId;
    private List<ZonaResponse> zonas;

    // Constructor
    public ZonaProfesionistaResponse(Long profesionistaId, List<ZonaResponse> zonas) {
        this.profesionistaId = profesionistaId;
        this.zonas = zonas;
    }

    // Getters y Setters
    public Long getProfesionistaId() {
        return profesionistaId;
    }

    public void setProfesionistaId(Long profesionistaId) {
        this.profesionistaId = profesionistaId;
    }

    public List<ZonaResponse> getZonas() {
        return zonas;
    }

    public void setZonas(List<ZonaResponse> zonas) {
        this.zonas = zonas;
    }
}