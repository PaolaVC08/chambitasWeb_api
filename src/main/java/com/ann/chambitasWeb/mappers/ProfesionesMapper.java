package com.ann.chambitasWeb.mappers;

import com.ann.chambitasWeb.models.Profesion;
import com.ann.chambitasWeb.dtos.response.ProfesionResponse;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ann.chambitasWeb.dtos.request.ProfesionRequest;


@Component
public class ProfesionesMapper extends AbstractMapper<Profesion, ProfesionResponse> {

    @Override
    public ProfesionResponse toDTO(Profesion entity) {
       ProfesionResponse response = new ProfesionResponse(); 
       response.setIdProfesion(entity.getIdProfesion());
       response.setNombre(entity.getNombre());
       return response;

       } 

           @Override
    public Profesion toEntity(ProfesionResponse dto) {
        Profesion profesion = new Profesion();
        profesion.setIdProfesion(dto.getIdProfesion());
        profesion.setNombre(dto.getNombre());
        return profesion;
    }

    // manejar listas de profesiones y convertirlos 
    public List<ProfesionResponse> toDTOList(List<Profesion>entities) {
        return  super.toDTOList((entities));
    }

    public List<Profesion> toEntityList(List<ProfesionResponse>dtos) {
        return super.toEntityList(dtos);
    }

     //método específico para convertir de ProfesionRequest a Profesion si necesitas
     public Profesion toEntity(ProfesionRequest requestDTO) {
        Profesion profesion = new Profesion();
        profesion.setNombre(requestDTO.getNombre());
        return profesion;
     }
    }