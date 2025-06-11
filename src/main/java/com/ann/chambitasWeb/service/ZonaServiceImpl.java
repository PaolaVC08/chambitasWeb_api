package com.ann.chambitasWeb.service;

import com.ann.chambitasWeb.models.Zona;
import com.ann.chambitasWeb.repository.ZonaRepository;
import com.ann.chambitasWeb.service.interfaces.IZonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ZonaServiceImpl implements IZonaService {

    @Autowired
    private ZonaRepository zonaRepository;

    @Override
    public List<Zona> getAllZonas() {
        return zonaRepository.findAll();  // Devuelve todas las zonas
    }

    @Override
    public Optional<Zona> getZonaById(Long id) {
        return zonaRepository.findById(id);  // Encuentra zona por ID
    }
}