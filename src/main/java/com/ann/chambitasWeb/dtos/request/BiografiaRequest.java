package com.ann.chambitasWeb.dtos.request;

public class BiografiaRequest {

    private Long profesionistaId;
    private String biografia;

    
    public Long getProfesionistaId() {
        return profesionistaId;
    }
    public void setProfesionistaId(Long profesionistaId) {
        this.profesionistaId = profesionistaId;
    }
    public String getBiografia() {
        return biografia;
    }
    public void setBiografia(String biografia) {
        this.biografia = biografia;
    }

    
    
}
