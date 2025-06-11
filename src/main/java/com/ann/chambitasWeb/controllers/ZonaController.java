package com.ann.chambitasWeb.controllers;

import com.ann.chambitasWeb.dtos.request.ZonaRequest;
import com.ann.chambitasWeb.dtos.response.ProfesionistaResponse;
import com.ann.chambitasWeb.dtos.response.ZonaResponse;
import com.ann.chambitasWeb.models.Profesionista;
import com.ann.chambitasWeb.service.interfaces.IZonaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auth/zona")
@CrossOrigin(origins = "*", maxAge = 3600)
public class ZonaController {

    private final IZonaService zonaService;

    public ZonaController(IZonaService zonaService) {
        this.zonaService = zonaService;
    }

    // Endpoint para obtener todas las zonas
@GetMapping("/all")
public ResponseEntity<List<ZonaResponse>> getAllZonas() {
    List<ZonaResponse> zonas = zonaService.getAllZonas().stream()
        .map(zona -> new ZonaResponse(zona.getId(), zona.getNombre()))
        .collect(Collectors.toList());
    return ResponseEntity.ok(zonas);
}

    // Endpoint para obtener los profesionistas de una zona
    @GetMapping("/profesionistas/{zonaId}")
    public ResponseEntity<List<ProfesionistaResponse>> getProfesionistasByZona(@PathVariable Long zonaId) {
        List<Profesionista> profesionistas = zonaService.obtenerProfesionistasPorZona(zonaId);
        List<ProfesionistaResponse> profesionistasResponse = profesionistas.stream()
                .map(profesionista -> new ProfesionistaResponse(
                        profesionista.getId(),
                        profesionista.getUsuario().getNombre(),
                        profesionista.getBiografia()
                ))
                .collect(Collectors.toList());
        return ResponseEntity.ok(profesionistasResponse);
    }
    
     // Endpoint para agregar una zona a un profesionista
    @PostMapping("/agregar-zona/{profesionistaId}")
    public ResponseEntity<List<ZonaResponse>> agregarZonaAProfesionista(
            @PathVariable Long profesionistaId, 
            @RequestBody ZonaRequest zonaRequest) {
        
        // Llamamos al servicio para agregar la zona al profesionista
        List<ZonaResponse> zonaResponse = zonaService.agregarZonasAProfesionista(profesionistaId, zonaRequest);
        
        return ResponseEntity.ok(zonaResponse);
    }
}

   