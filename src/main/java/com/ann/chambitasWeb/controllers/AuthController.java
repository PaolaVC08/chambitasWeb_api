package com.ann.chambitasWeb.controllers;

import com.ann.chambitasWeb.dtos.request.SignupRequest;
import com.ann.chambitasWeb.exceptions.ValidationServiceException;
import com.ann.chambitasWeb.service.AuthService;
import com.ann.chambitasWeb.dtos.response.MessageResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*", maxAge = 3600)
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registrarUsuario(@Valid @RequestBody SignupRequest request) {
        try {
            authService.registrarUsuario(request);
            return ResponseEntity.ok(new MessageResponse("Usuario registrado. Revisa tu correo para verificar tu cuenta."));
        } catch (ValidationServiceException e) {
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
        }
    }

    @PostMapping("/verify")
    public ResponseEntity<?> verificarCorreo(@RequestParam("token") String token) {
        try {
            authService.verificarCorreo(token);
            return ResponseEntity.ok(new MessageResponse("Cuenta verificada correctamente."));
        } catch (ValidationServiceException e) {
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
        }
    }
}

