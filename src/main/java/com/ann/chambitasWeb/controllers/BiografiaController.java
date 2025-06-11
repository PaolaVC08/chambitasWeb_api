package com.ann.chambitasWeb.controllers;

import com.ann.chambitasWeb.dtos.request.BiografiaRequest;
import com.ann.chambitasWeb.dtos.response.BiografiaResponse;
import com.ann.chambitasWeb.service.interfaces.IBiografiaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/biografia")
@CrossOrigin(origins = "*") // si tienes frontend, permite CORS
public class BiografiaController {

    @Autowired
    private IBiografiaService biografiaService;


    @PostMapping("/crear")
    public ResponseEntity<BiografiaResponse> crearBiografia(@RequestBody BiografiaRequest request) {
        BiografiaResponse response = biografiaService.crearBiografia(request);
        return ResponseEntity.ok(response);
    }


    @PutMapping("/actualizar/{id}")
    public ResponseEntity<BiografiaResponse> actualizarBiografia(
            @PathVariable Long id,
            @RequestBody BiografiaRequest request) {
        BiografiaResponse response = biografiaService.actualizarBiografia(id, request);
        return ResponseEntity.ok(response);
    }


    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminarBiografia(@PathVariable Long id) {
        biografiaService.eliminarBiografia(id);
        return ResponseEntity.noContent().build();
    }
}