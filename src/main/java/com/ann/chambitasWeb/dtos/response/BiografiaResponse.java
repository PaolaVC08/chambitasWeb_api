package com.ann.chambitasWeb.dtos.response;

public class BiografiaResponse {

    //private Long idBiografia;
    private String biografia;
    private Long profesionistaId;
    
    public String getBiografia() {
        return biografia;
    }
    public void setBiografia(String biografia) {
        this.biografia = biografia;
    }
    public Long getProfesionistaId() {
        return profesionistaId;
    }
    public void setProfesionistaId(Long profesionistaId) {
        this.profesionistaId = profesionistaId;
    }



    
    
}
