package com.ann.chambitasWeb.controllers;

import com.ann.chambitasWeb.dtos.response.PerfilProfesionistaResponse;
import com.ann.chambitasWeb.service.interfaces.IPerfilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/perfilprofesionista")
public class PerfilProfesionistaController {

    private final IPerfilService perfilService;

    @Autowired
    public PerfilProfesionistaController(IPerfilService perfilService) {
        this.perfilService = perfilService;
    }

    @GetMapping("/{id}")
    public PerfilProfesionistaResponse obtenerPerfil(@PathVariable Long id) {
        return perfilService.obtenerPerfil(id);
    }

}
