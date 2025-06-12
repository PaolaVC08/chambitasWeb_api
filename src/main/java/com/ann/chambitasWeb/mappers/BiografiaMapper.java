package com.ann.chambitasWeb.mappers;

import org.springframework.stereotype.Component;

import com.ann.chambitasWeb.dtos.request.BiografiaRequest;
import com.ann.chambitasWeb.dtos.response.BiografiaResponse;
import com.ann.chambitasWeb.models.Profesionista;

@Component
public class BiografiaMapper {

    public BiografiaResponse toDTO(Profesionista entity) {
        BiografiaResponse response = new BiografiaResponse();
       // response.setIdBiografia(entity.getId());
        response.setBiografia(entity.getBiografia());
        return response;
    }

    public Profesionista toEntity(BiografiaRequest request) {
        Profesionista profesionista = new Profesionista();
        profesionista.setId(request.getProfesionistaId());
        profesionista.setBiografia(request.getBiografia());
        return profesionista;
    }
}