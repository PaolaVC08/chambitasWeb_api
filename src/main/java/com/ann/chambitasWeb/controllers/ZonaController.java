package com.ann.chambitasWeb.controllers;

import com.ann.chambitasWeb.models.Zona;
import com.ann.chambitasWeb.service.interfaces.IZonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth/zona")
@CrossOrigin(origins = "*", maxAge = 3600)
public class ZonaController {

    private final IZonaService zonaService;

    @Autowired
    public ZonaController(IZonaService zonaService) {
        this.zonaService = zonaService;
    }

    // Endpoint para obtener todas las zonas
    @GetMapping("/all")
    public ResponseEntity<List<Zona>> getAllZonas() {
        List<Zona> zonas = zonaService.getAllZonas();
        return ResponseEntity.ok(zonas);
    }

    // Endpoint para obtener una zona por ID
    @GetMapping("/{id}")
    public ResponseEntity<Zona> getZonaById(@PathVariable Long id) {
        Optional<Zona> zona = zonaService.getZonaById(id);
        return zona.map(ResponseEntity::ok)
                   .orElseGet(() -> ResponseEntity.notFound().build());
    }
}