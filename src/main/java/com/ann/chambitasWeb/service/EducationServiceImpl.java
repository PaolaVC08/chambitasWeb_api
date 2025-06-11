package com.ann.chambitasWeb.service;

import com.ann.chambitasWeb.dtos.request.EducationRequest;
import com.ann.chambitasWeb.dtos.response.EducationResponse;
import com.ann.chambitasWeb.mappers.EducationMapper;
import com.ann.chambitasWeb.models.Education;
import com.ann.chambitasWeb.repository.EducationRepository;
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

    @Autowired
    public EducationServiceImpl(EducationRepository educationRepository, EducationMapper educationMapper) {
        this.educationRepository = educationRepository;
        this.educationMapper = educationMapper;
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
}
