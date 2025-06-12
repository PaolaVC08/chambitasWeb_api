package com.ann.chambitasWeb.mappers;

import org.springframework.stereotype.Component;

import com.ann.chambitasWeb.models.ProfesionistaProfesion;
import com.ann.chambitasWeb.dtos.response.ProfesionistaProfesionResponse;

@Component
public class ProfesionistaProfesionMapper {

    public ProfesionistaProfesionResponse toDTO(ProfesionistaProfesion entity) {
        ProfesionistaProfesionResponse dto = new ProfesionistaProfesionResponse();
        dto.setIdPp(entity.getIdPp());
        dto.setProfesionistaId(entity.getProfesionista().getId());
        dto.setProfesionId(entity.getProfesion().getIdProfesion());
        dto.setProfesionNombre(entity.getProfesion().getNombre()); // ✅ nuevo campo
        return dto;
    }
}