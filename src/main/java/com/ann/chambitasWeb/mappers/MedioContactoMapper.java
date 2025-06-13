package com.ann.chambitasWeb.mappers;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ann.chambitasWeb.dtos.request.MedioContactoRequest;
import com.ann.chambitasWeb.dtos.response.MedioContactoResponse;
import com.ann.chambitasWeb.models.MedioContacto;
import com.ann.chambitasWeb.models.Profesionista;
import com.ann.chambitasWeb.models.TipoContacto;

@Component
public class MedioContactoMapper extends AbstractMapper<MedioContacto, MedioContactoResponse> {

    @Override
    public MedioContactoResponse toDTO(MedioContacto entity) {
        MedioContactoResponse response = new MedioContactoResponse();
        response.setIdMcontacto(entity.getIdMcontacto());

        if (entity.getTipo() != null) {
            response.setTipoContactoId(entity.getTipo().getIdTcontacto());
            response.setTipoContactoNombre(entity.getTipo().getNombre());
        }

        response.setValor(entity.getValor());
        return response;
    }

    @Override
    public MedioContacto toEntity(MedioContactoResponse dto) {
        MedioContacto contacto = new MedioContacto();
        contacto.setIdMcontacto(dto.getIdMcontacto());
        contacto.setValor(dto.getValor());

        // Agrega el tipo solo si hay ID
        if (dto.getTipoContactoId() != null) {
            TipoContacto tipo = new TipoContacto();
            tipo.setIdTcontacto(dto.getTipoContactoId());
            contacto.setTipo(tipo);
        }

        return contacto;
    }

    // Manejar listas
    public List<MedioContactoResponse> toDTOList(List<MedioContacto> entities) {
        return super.toDTOList(entities);
    }

    public List<MedioContacto> toEntityList(List<MedioContactoResponse> dtos) {
        return super.toEntityList(dtos);
    }

    // Conversión desde request
    public MedioContacto toEntity(MedioContactoRequest requestDTO) {
        MedioContacto contacto = new MedioContacto();
        contacto.setValor(requestDTO.getValor());

        TipoContacto tipo = new TipoContacto();
        tipo.setIdTcontacto(requestDTO.getTipoContactoId());
        contacto.setTipo(tipo);

        Profesionista profesionista = new Profesionista();
      //  profesionista.setId(requestDTO.getProfesionistaId());
        contacto.setProfesionista(profesionista);

        return contacto;
    }
}