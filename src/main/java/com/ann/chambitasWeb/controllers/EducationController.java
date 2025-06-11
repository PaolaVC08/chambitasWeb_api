package com.ann.chambitasWeb.controllers;

import com.ann.chambitasWeb.dtos.request.EducationRequest;
import com.ann.chambitasWeb.dtos.response.EducationResponse;
import com.ann.chambitasWeb.models.Profesionista;
import com.ann.chambitasWeb.service.interfaces.IEducationService;
import com.ann.chambitasWeb.service.interfaces.IUsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/education")
public class EducationController {

    private final IEducationService educationService;
    private final IUsuarioService usuarioService;

    @Autowired
    public EducationController(IEducationService educationService, IUsuarioService usuarioService) {
        this.educationService = educationService;
        this.usuarioService = usuarioService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<EducationResponse> obtenerEducacion(@PathVariable Long id) {
        EducationResponse education = educationService.obtenerPorId(id);
        return ResponseEntity.ok(education);
    }

    @GetMapping
    public ResponseEntity<List<EducationResponse>> obtenerTodasEducaciones() {
        List<EducationResponse> educations = educationService.obtenerTodos();
        return ResponseEntity.ok(educations);
    }

    @PostMapping
    public ResponseEntity<EducationResponse> crearEducacion(@RequestBody EducationRequest educationRequest) {
        String correo = SecurityContextHolder.getContext().getAuthentication().getName();
        Profesionista profesionista = usuarioService.obtenerProfesionistaPorCorreo(correo);
        Long profesionistaId = profesionista.getId();

        EducationResponse education = educationService.crearEducacionParaProfesionista(profesionistaId,
                educationRequest);
        return ResponseEntity.status(201).body(education);
    }

    @GetMapping("/mis-educaciones")
    public ResponseEntity<List<EducationResponse>> obtenerMisEducaciones() {
        String correo = SecurityContextHolder.getContext().getAuthentication().getName();
        Profesionista profesionista = usuarioService.obtenerProfesionistaPorCorreo(correo);
        Long profesionistaId = profesionista.getId();

        List<EducationResponse> educaciones = educationService.obtenerEducacionesPorProfesionista(profesionistaId);
        return ResponseEntity.ok(educaciones);
    }

    @PutMapping("/det/{id}")
    public ResponseEntity<EducationResponse> actualizarEducacion(@PathVariable Long id,
            @RequestBody EducationRequest educationRequestDTO) {
        EducationResponse education = educationService.actualizarEducacion(id, educationRequestDTO);
        return ResponseEntity.ok(education);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarEducacion(@PathVariable Long id) {
        educationService.eliminarEducacion(id);
        return ResponseEntity.noContent().build();
    }

    // Ruta para obtener las educaciones por id del profesionista
    @GetMapping("/profesionistas/{id}/educaciones")
    public List<EducationResponse> obtenerEducacionesPorProfesionista(@PathVariable Long id) {
        return educationService.obtenerEducacionesPorProfesionista(id);
    }
}
