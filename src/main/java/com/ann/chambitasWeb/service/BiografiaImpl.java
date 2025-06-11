package com.ann.chambitasWeb.service;

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
    public BiografiaResponse crearBiografia(BiografiaRequest biografiaRequest) {
        Profesionista prof = profesionistaRepository.findById(biografiaRequest.getProfesionistaId())
            .orElseThrow(() -> new RuntimeException("Profesionista no encontrado"));
        prof.setBiografia(biografiaRequest.getBiografia());
        Profesionista actualizado = profesionistaRepository.save(prof);
        return biografiaMapper.toDTO(actualizado);
    }

    @Override
    @Transactional
    public BiografiaResponse actualizarBiografia(Long id, BiografiaRequest biografiaRequest) {
        Profesionista prof = profesionistaRepository.findById(id)
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
}