package com.ann.chambitasWeb.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ann.chambitasWeb.dtos.request.ZonaRequest;
import com.ann.chambitasWeb.dtos.response.ZonaResponse;
import com.ann.chambitasWeb.mappers.ZonaMapper;
import com.ann.chambitasWeb.models.Profesionista;
import com.ann.chambitasWeb.models.Zona;
import com.ann.chambitasWeb.repository.ProfesionistaRepository;
import com.ann.chambitasWeb.repository.ZonaRepository;
import com.ann.chambitasWeb.service.interfaces.IZonaService;

@Service
@Transactional
public class ZonaServiceImpl implements IZonaService {

    private final ZonaRepository zonaRepository;
    private final ProfesionistaRepository profesionistaRepository;
    private final ZonaMapper zonaMapper;

    public ZonaServiceImpl(ZonaRepository zonaRepository,
                           ProfesionistaRepository profesionistaRepository,
                           ZonaMapper zonaMapper) {
        this.zonaRepository = zonaRepository;
        this.profesionistaRepository = profesionistaRepository;
        this.zonaMapper = zonaMapper;
    }

    @Override
    public List<Zona> getAllZonas() {
        return zonaRepository.findAll();
    }

    @Override
    public Optional<Zona> getZonaById(Long id) {
        return zonaRepository.findById(id);
    }

    @Override
    public List<Profesionista> obtenerProfesionistasPorZona(Long zonaId) {
        return profesionistaRepository.findByZona_Id(zonaId);
    }

    @Override
    public List<ZonaResponse> agregarZonasAProfesionista(Long id, ZonaRequest zonaRequest) {
        Zona zona = zonaMapper.toEntity(zonaRequest);
        Profesionista prof = profesionistaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Profesionista no encontrado"));

        prof.setZona(zona);
        profesionistaRepository.save(prof);

        return zonaMapper.toDTOList(zonaRepository.findAll());
    }
}