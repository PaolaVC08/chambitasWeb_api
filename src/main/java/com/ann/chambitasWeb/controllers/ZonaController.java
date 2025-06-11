package com.ann.chambitasWeb.controllers;

import com.ann.chambitasWeb.dtos.request.ZonaRequest;
import com.ann.chambitasWeb.dtos.response.ProfesionistaZonaResponse;
import com.ann.chambitasWeb.dtos.response.ZonaResponse;
import com.ann.chambitasWeb.mappers.ProfesionistaZonaMapper;
import com.ann.chambitasWeb.mappers.ZonaMapper;
import com.ann.chambitasWeb.models.Profesionista;
import com.ann.chambitasWeb.models.Zona;
import com.ann.chambitasWeb.service.interfaces.IZonaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth/zonas")
@CrossOrigin("*")
public class ZonaController {

    private final IZonaService zonaService;
    private final ZonaMapper zonaMapper;
    private final ProfesionistaZonaMapper profesionistaZonaMapper;

    @Autowired
    public ZonaController(IZonaService zonaService, ZonaMapper zonaMapper, ProfesionistaZonaMapper profesionistaZonaMapper) {
        this.zonaService = zonaService;
        this.zonaMapper = zonaMapper;
        this.profesionistaZonaMapper = profesionistaZonaMapper;
    }

    // GET: todas las zonas
    @GetMapping("/todaslaszonas")
    public List<ZonaResponse> getAllZonas() {
        List<Zona> zonas = zonaService.getAllZonas();
        return zonaMapper.toDTOList(zonas);
    }

    // GET: una zona por ID
    // GET: una zona por ID
    @GetMapping("/{id}")
    public ResponseEntity<ZonaResponse> obtenerZonaPorId(@PathVariable Long id) {
        Optional<Zona> zonaOptional = zonaService.getZonaById(id);

        if (zonaOptional.isPresent()) {
            ZonaResponse response = zonaMapper.toDTO(zonaOptional.get());
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // GET: profesionistas por zona
    @GetMapping("/{id}/profesionistas")
    public List<ProfesionistaZonaResponse> obtenerPorZona(@PathVariable Long id) {
        return profesionistaZonaMapper.toDTOList(zonaService.obtenerProfesionistasPorZona(id));
    }

    // PUT: asignar zona a un profesionista
    @PutMapping("/asignar/{profesionistaId}")
    public List<ZonaResponse> asignarZonaAProfesionista(
            @PathVariable Long profesionistaId,
            @RequestBody ZonaRequest zonaRequest) {
        return zonaService.agregarZonasAProfesionista(profesionistaId, zonaRequest);
    }
}