package com.ann.chambitasWeb.controllers;

import com.ann.chambitasWeb.dtos.response.CategoriaProfesionesResponse;
import com.ann.chambitasWeb.dtos.response.ProfesionResponse;
import com.ann.chambitasWeb.dtos.response.ProfesionistaProfesionResponse;
import com.ann.chambitasWeb.service.interfaces.IProfesionesService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categorias")
@CrossOrigin("*")
public class ProfesionesController {

    private final IProfesionesService profesionesService;

    @Autowired
    public ProfesionesController(IProfesionesService profesionesService) {
        this.profesionesService = profesionesService;
    }

    // Lista agrupada por categoría
    @GetMapping
    public List<CategoriaProfesionesResponse> obtenerProfesionesAgrupadas() {
        return profesionesService.obtenerProfesionesAgrupadas();
    }

    // Lista solo de una categoría específica
    @GetMapping("/{id}")
    public List<ProfesionResponse> obtenerProfesionesPorCategoria(@PathVariable Long id) {
        return profesionesService.obtenerPorCategoria(id);
    }

    @GetMapping("/profesionistas-por-profesion/{id}")
    public ResponseEntity<List<ProfesionistaProfesionResponse>> obtenerPorProfesion(@PathVariable Long id) {
        List<ProfesionistaProfesionResponse> lista = profesionesService.obtenerProfesionistaPorProfesion(id);
        return ResponseEntity.ok(lista);
    }
}
