package com.ann.chambitasWeb.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ann.chambitasWeb.dtos.request.BiografiaRequest;
import com.ann.chambitasWeb.dtos.response.BiografiaResponse;
import com.ann.chambitasWeb.mappers.BiografiaMapper;
import com.ann.chambitasWeb.models.Profesionista;
import com.ann.chambitasWeb.repository.ProfesionistaRepository;
import com.ann.chambitasWeb.service.interfaces.IBiografiaService;

@Service
public class BiografiaImpl implements IBiografiaService {

    private final ProfesionistaRepository profesionistaRepository;
    private final BiografiaMapper biografiaMapper;

    public BiografiaImpl(ProfesionistaRepository profesionistaRepository, BiografiaMapper biografiaMapper) {
        this.profesionistaRepository = profesionistaRepository;
        this.biografiaMapper = biografiaMapper;
    }

@Override
@Transactional
public BiografiaResponse crearBiografia(BiografiaRequest biografiaRequest, Long profesionistaId) {
    Profesionista prof = profesionistaRepository.findById(profesionistaId)
        .orElseThrow(() -> new RuntimeException("Profesionista no encontrado"));
    
    prof.setBiografia(biografiaRequest.getBiografia());
    Profesionista actualizado = profesionistaRepository.save(prof);

    BiografiaResponse response = new BiografiaResponse();
    response.setBiografia(actualizado.getBiografia());
    response.setProfesionistaId(actualizado.getId()); // <- aquí se asigna correctamente el ID

    return response;
}

@Override
@Transactional
public BiografiaResponse actualizarBiografia(BiografiaRequest biografiaRequest, Long profesionistaId) {
    Profesionista prof = profesionistaRepository.findById(profesionistaId)
        .orElseThrow(() -> new RuntimeException("Profesionista no encontrado"));
    prof.setBiografia(biografiaRequest.getBiografia());
    Profesionista actualizado = profesionistaRepository.save(prof);
    return biografiaMapper.toDTO(actualizado);
}

    @Override
    @Transactional
    public void eliminarBiografia(Long id) {
        Profesionista prof = profesionistaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Profesionista no encontrado"));
        prof.setBiografia(null);
        profesionistaRepository.save(prof);
    }

    @Override
    public List<BiografiaResponse> obtenerBiografiaPorProfesionista(Long profesionistaId) {
        Optional<Profesionista> optional = profesionistaRepository.findById(profesionistaId);
        if (optional.isEmpty()) {
            throw new RuntimeException("Profesionista no encontrado");
        }

        Profesionista prof = optional.get();
        if (prof.getBiografia() == null || prof.getBiografia().isBlank()) {
            return Collections.emptyList();
        }

        BiografiaResponse respuesta = new BiografiaResponse();
        respuesta.setProfesionistaId(prof.getId());
        respuesta.setBiografia(prof.getBiografia());

        return List.of(respuesta);
    }
}