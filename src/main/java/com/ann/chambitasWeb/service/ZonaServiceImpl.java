package com.ann.chambitasWeb.service;


import com.ann.chambitasWeb.dtos.request.ZonaRequest;
import com.ann.chambitasWeb.dtos.response.ProfesionistaResponse;
import com.ann.chambitasWeb.dtos.response.ZonaResponse;
import com.ann.chambitasWeb.models.Zona;
import com.ann.chambitasWeb.models.Profesionista;
import com.ann.chambitasWeb.repository.ZonaRepository;
import com.ann.chambitasWeb.repository.ProfesionistaRepository;
import com.ann.chambitasWeb.service.interfaces.IZonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class ZonaServiceImpl implements IZonaService {


    private final ZonaRepository zonaRepository;
    private final ProfesionistaRepository profesionistaRepository;

    @Autowired
    public ZonaServiceImpl(ZonaRepository zonaRepository, ProfesionistaRepository profesionistaRepository) {
        this.zonaRepository = zonaRepository;
        this.profesionistaRepository = profesionistaRepository;
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
    Zona zona = zonaRepository.findById(zonaId)
        .orElseThrow(() -> new RuntimeException("Zona no encontrada"));
        
    return profesionistaRepository.findByZona(zona);
}

@Override
public List<ZonaResponse> agregarZonasAProfesionista(Long id, ZonaRequest zonaRequest) {
    // Buscar al profesionista por su ID
    Profesionista profesionista = profesionistaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Profesionista no encontrado"));

    // Buscar la zona por su ID
    Zona zona = zonaRepository.findById(zonaRequest.getId())
            .orElseThrow(() -> new RuntimeException("Zona no encontrada"));

    // Asignar la zona al profesionista
    profesionista.setZona(zona);
    profesionistaRepository.save(profesionista);
    

    // Devolver la respuesta (aquí se puede agregar más información si se necesita)
    return List.of(new ZonaResponse(zona.getId(), zona.getNombre()));
}

}