package com.ann.chambitasWeb.controllers;

import com.ann.chambitasWeb.dtos.request.EducationRequest;
import com.ann.chambitasWeb.dtos.response.EducationResponse;
import com.ann.chambitasWeb.service.interfaces.IEducationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/education")
public class EducationController {

    private final IEducationService educationService;

    @Autowired
    public EducationController(IEducationService educationService) {
        this.educationService = educationService;
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
    public ResponseEntity<EducationResponse> crearEducacion(@RequestBody EducationRequest educationRequestDTO) {
        EducationResponse education = educationService.crearEducacion(educationRequestDTO);
        return ResponseEntity.status(201).body(education);
    }

    @PutMapping("/{id}")
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
}
