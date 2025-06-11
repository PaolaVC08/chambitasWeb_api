package com.ann.chambitasWeb.controllers;

import com.ann.chambitasWeb.dtos.request.ServiceRequest;
import com.ann.chambitasWeb.dtos.response.ServiceResponse;
import com.ann.chambitasWeb.service.interfaces.IServiceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/servicios")
public class ServiceController {

    private final IServiceService serviceService;

    public ServiceController(IServiceService serviceService) {
        this.serviceService = serviceService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServiceResponse> obtenerServicio(@PathVariable Long id) {
        ServiceResponse servicio = serviceService.obtenerPorId(id);
        return ResponseEntity.ok(servicio);
    }

    @GetMapping
    public ResponseEntity<List<ServiceResponse>> obtenerTodosServicios() {
        List<ServiceResponse> servicios = serviceService.obtenerTodos();
        return ResponseEntity.ok(servicios);
    }

    @GetMapping("/profesionista/{profesionistaId}")
    public ResponseEntity<List<ServiceResponse>> obtenerServiciosPorProfesionista(@PathVariable Long profesionistaId) {
        List<ServiceResponse> servicios = serviceService.obtenerServiciosPorProfesionista(profesionistaId);
        return ResponseEntity.ok(servicios);
    }

    @PostMapping
    public ResponseEntity<ServiceResponse> crearServicio(@RequestBody ServiceRequest serviceRequest) {
        ServiceResponse servicio = serviceService.crearServicio(serviceRequest);
        return ResponseEntity.status(201).body(servicio);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServiceResponse> actualizarServicio(@PathVariable Long id,
            @RequestBody ServiceRequest serviceRequest) {
        ServiceResponse servicio = serviceService.actualizarServicio(id, serviceRequest);
        return ResponseEntity.ok(servicio);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarServicio(@PathVariable Long id) {
        serviceService.eliminarServicio(id);
        return ResponseEntity.noContent().build();
    }
}
