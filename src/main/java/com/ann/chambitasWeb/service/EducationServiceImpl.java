package com.ann.chambitasWeb.service;

import com.ann.chambitasWeb.dtos.request.EducationRequest;
import com.ann.chambitasWeb.dtos.response.EducationResponse;
import com.ann.chambitasWeb.mappers.EducationMapper;
import com.ann.chambitasWeb.models.Education;
import com.ann.chambitasWeb.models.Profesionista;
import com.ann.chambitasWeb.repository.EducationRepository;
import com.ann.chambitasWeb.repository.ProfesionistaRepository;
import com.ann.chambitasWeb.service.interfaces.IEducationService;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EducationServiceImpl implements IEducationService {

    private final EducationRepository educationRepository;
    private final EducationMapper educationMapper;
    private final ProfesionistaRepository profesionistaRepository;

    @Autowired
    public EducationServiceImpl(EducationRepository educationRepository, EducationMapper educationMapper,
            ProfesionistaRepository profesionistaRepository) {
        this.educationRepository = educationRepository;
        this.educationMapper = educationMapper;
        this.profesionistaRepository = profesionistaRepository;
    }

    @Override
    public EducationResponse obtenerPorId(Long id) {
        Education education = educationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Educación no encontrada"));
        return educationMapper.toDTO(education);
    }

    @Override
    public List<EducationResponse> obtenerTodos() {
        List<Education> educations = educationRepository.findAll();
        return educations.stream()
                .map(educationMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public EducationResponse crearEducacion(EducationRequest educationRequest) {
        Education education = educationMapper.toEntity(educationRequest);
        education = educationRepository.save(education);
        return educationMapper.toDTO(education);
    }

    @Override
    public EducationResponse actualizarEducacion(Long id, EducationRequest educationRequest) {
        Education education = educationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Educación no encontrada"));
        education.setSchool(educationRequest.getSchool());
        education.setTitulo(educationRequest.getTitulo());
        education.setStartDate(educationRequest.getStartDate());
        education.setEndDate(educationRequest.getEndDate());
        education = educationRepository.save(education);
        return educationMapper.toDTO(education);
    }

    @Override
    public void eliminarEducacion(Long id) {
        Education education = educationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Educación no encontrada"));
        educationRepository.delete(education);
    }

    @Override
    public List<EducationResponse> obtenerEducacionesPorProfesionista(Long profesionistaId) {
        // Obtener todas las educaciones asociadas al profesionista
        List<Education> educaciones = educationRepository.findByProfesionista_Id(profesionistaId);

        // Convertir las educaciones a DTOs
        return educaciones.stream()
                .map(educationMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public EducationResponse crearEducacionParaProfesionista(Long profesionistaId, EducationRequest educationRequest) {
        Profesionista profesionista = profesionistaRepository.findById(profesionistaId)
                .orElseThrow(() -> new EntityNotFoundException("Profesionista no encontrado"));

        Education education = educationMapper.toEntity(educationRequest);
        education.setProfesionista(profesionista); // Asigna el profesionista

        education = educationRepository.save(education);
        return educationMapper.toDTO(education);
    }
}
