package com.ann.chambitasWeb.mappers;

import com.ann.chambitasWeb.models.Education;
import com.ann.chambitasWeb.models.Profesionista;
import com.ann.chambitasWeb.dtos.response.EducationResponse;
import com.ann.chambitasWeb.dtos.request.EducationRequest;
import org.springframework.stereotype.Component;

@Component
public class EducationMapper extends AbstractMapper<Education, EducationResponse> {

    @Override
    public EducationResponse toDTO(Education entity) {
        EducationResponse response = new EducationResponse();
        response.setIdEdu(entity.getIdEdu());
        response.setSchool(entity.getSchool());
        response.setTitulo(entity.getTitulo());
        response.setStartDate(entity.getStartDate());
        response.setEndDate(entity.getEndDate());
        return response;
    }

    @Override
    public Education toEntity(EducationResponse dto) {
        Education education = new Education();
        education.setIdEdu(dto.getIdEdu());
        education.setSchool(dto.getSchool());
        education.setTitulo(dto.getTitulo());
        education.setStartDate(dto.getStartDate());
        education.setEndDate(dto.getEndDate());
        return education;
    }

    // Método específico para convertir de EducationRequestDTO a Education
    public Education toEntity(EducationRequest requestDTO) {
        Education education = new Education();
        education.setSchool(requestDTO.getSchool());
        education.setTitulo(requestDTO.getTitulo());
        education.setStartDate(requestDTO.getStartDate());
        education.setEndDate(requestDTO.getEndDate());

        // Asociamos el profesionista usando el ID
        Profesionista profesionista = new Profesionista();
        profesionista.setId(requestDTO.getProfesionistaId());
        education.setProfesionista(profesionista);

        return education;
    }
}

