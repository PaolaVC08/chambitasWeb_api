package com.ann.chambitasWeb.controllers;

import com.ann.chambitasWeb.models.Usuario;
import com.ann.chambitasWeb.service.FavoriteService;
import com.ann.chambitasWeb.repository.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/favorites")
@CrossOrigin(origins = "*", maxAge = 3600)
public class FavoriteController {

    private final FavoriteService favoriteService;
    private final UsuarioRepository usuarioRepository;

    @Autowired
    public FavoriteController(FavoriteService favoriteService, UsuarioRepository usuarioRepository) {
        this.favoriteService = favoriteService;
        this.usuarioRepository = usuarioRepository;
    }

    // Endpoint para dar o quitar like
    @PostMapping("/toggle/{idReceptor}")
    public ResponseEntity<String> toggleLike(@PathVariable Long idReceptor, Principal principal) {
        // Obtener el usuario emisor desde el token (correo)
        Usuario emisor = usuarioRepository.findByCorreo(principal.getName())
                .orElseThrow(() -> new RuntimeException("Usuario emisor no encontrado"));

        // Obtener el receptor por su ID
        Usuario receptor = usuarioRepository.findById(idReceptor)
                .orElseThrow(() -> new RuntimeException("Usuario receptor no encontrado"));

        // Ejecutar lógica de like
        String resultado = favoriteService.toggleLike(emisor, receptor);

        return ResponseEntity.ok(resultado);
    }
}
