package com.ann.chambitasWeb.mappers;

import com.ann.chambitasWeb.dtos.response.ProfesionistaZonaResponse;
import com.ann.chambitasWeb.models.Profesionista;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProfesionistaZonaMapper {

    public ProfesionistaZonaResponse toDTO(Profesionista entity) {
        return new ProfesionistaZonaResponse(
            entity.getId(),
            entity.getUsuario().getNombre(),
            entity.getUsuario().getCorreo(),
            entity.getBiografia()
        );
    }

    public List<ProfesionistaZonaResponse> toDTOList(List<Profesionista> entities) {
        return entities.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
}