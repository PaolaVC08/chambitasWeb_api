package com.ann.chambitasWeb.controllers;

import com.ann.chambitasWeb.dtos.request.BiografiaRequest;
import com.ann.chambitasWeb.dtos.response.BiografiaResponse;
import com.ann.chambitasWeb.models.Profesionista;
import com.ann.chambitasWeb.service.interfaces.IBiografiaService;
import com.ann.chambitasWeb.service.interfaces.IProfesionistaService;
import com.ann.chambitasWeb.service.interfaces.IUsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/biografia")
@CrossOrigin(origins = "*")
public class BiografiaController {

    @Autowired
    private IBiografiaService biografiaService;

    @Autowired
    private IProfesionistaService profesionistaService;

    @PostMapping("/crear")
    public ResponseEntity<BiografiaResponse> crearBiografia(@RequestBody BiografiaRequest request) {
        String correo = SecurityContextHolder.getContext().getAuthentication().getName();

        Long idProfesionista = profesionistaService.obtenerProfesionistaPorCorreo(correo)
                .orElseThrow(() -> new RuntimeException("Profesionista no encontrado"))
                .getId();

        BiografiaResponse response = biografiaService.crearBiografia(request, idProfesionista);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/actualizar")
    public ResponseEntity<BiografiaResponse> actualizarBiografia(@RequestBody BiografiaRequest request) {
        String correo = SecurityContextHolder.getContext().getAuthentication().getName();

        Long idProfesionista = profesionistaService.obtenerProfesionistaPorCorreo(correo)
                .orElseThrow(() -> new RuntimeException("Profesionista no encontrado"))
                .getId();

        BiografiaResponse response = biografiaService.actualizarBiografia(request, idProfesionista);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/eliminar")
    public ResponseEntity<Void> eliminarBiografia() {
        String correo = SecurityContextHolder.getContext().getAuthentication().getName();

        Long idProfesionista = profesionistaService.obtenerProfesionistaPorCorreo(correo)
                .orElseThrow(() -> new RuntimeException("Profesionista no encontrado"))
                .getId();

        biografiaService.eliminarBiografia(idProfesionista);
        return ResponseEntity.noContent().build();
    }
}