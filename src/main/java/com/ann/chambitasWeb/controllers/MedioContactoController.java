package com.ann.chambitasWeb.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.ann.chambitasWeb.dtos.request.MedioContactoRequest;
import com.ann.chambitasWeb.dtos.response.MedioContactoResponse;
import com.ann.chambitasWeb.service.interfaces.IMedioContactoService;

@RestController
@RequestMapping("/api/contactos")
@CrossOrigin("*")
public class MedioContactoController {

    private final IMedioContactoService medioContactoService;

    public MedioContactoController(IMedioContactoService medioContactoService) {
        this.medioContactoService = medioContactoService;
    }

    @GetMapping("/profesionista/{id}")
    public List<MedioContactoResponse> obtenerContactosPorProfesionista(@PathVariable Long id) {
        return medioContactoService.obtenerMedioContactoPorProfesionista(id);
    }

    @PostMapping("/crear/{id}")
    public MedioContactoResponse crearContacto(@PathVariable Long id, @RequestBody MedioContactoRequest request) {
        return medioContactoService.crearMedioContactoParaProfesionista(id, request);
    }

    @PutMapping("/actualizar/{id}")
    public MedioContactoResponse actualizarContacto(@PathVariable Long id, @RequestBody MedioContactoRequest request) {
        return medioContactoService.actualizarMedioContacto(id, request);
    }

    @DeleteMapping("/eliminar/{id}")
    public void eliminarContacto(@PathVariable Long id) {
        medioContactoService.eliminarMedioContacto(id);
    }
}