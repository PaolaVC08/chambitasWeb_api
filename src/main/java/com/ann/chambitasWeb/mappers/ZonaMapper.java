package com.ann.chambitasWeb.mappers;

import java.util.List;

import org.springframework.stereotype.Component;




import com.ann.chambitasWeb.dtos.request.ZonaRequest;
import com.ann.chambitasWeb.dtos.response.ZonaResponse;
import com.ann.chambitasWeb.models.Zona;

@Component
public class ZonaMapper extends AbstractMapper<Zona, ZonaResponse> {

    @Override
    public ZonaResponse toDTO(Zona entity) {
        ZonaResponse response = new ZonaResponse(entity.getId(), entity.getNombre());
        return response;
    }

    @Override
    public Zona toEntity(ZonaResponse dto) {
        Zona zona = new Zona();
        zona.setId(dto.getId());
        zona.setNombre(dto.getNombre());
        return zona;
    }

    public Zona toEntity(ZonaRequest request) {
        Zona zona = new Zona();
        zona.setId(request.getId());         // Puede venir null en creación
        zona.setNombre(request.getNombre());
        return zona;
    }

    public List<ZonaResponse> toDTOList(List<Zona> entities) {
        return super.toDTOList(entities);
    }

    public List<Zona> toEntityList(List<ZonaResponse> dtos) {
        return super.toEntityList(dtos);
    }
}