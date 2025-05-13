package com.ann.chambitasWeb.controllers;

import com.ann.chambitasWeb.dtos.request.LoginRequest;
import com.ann.chambitasWeb.dtos.request.SignupRequest;
import com.ann.chambitasWeb.exceptions.ValidationServiceException;
import com.ann.chambitasWeb.security.jwt.JwtUtils;
import com.ann.chambitasWeb.security.services.UsuarioDetailsImpl;
import com.ann.chambitasWeb.service.AuthService;
import com.ann.chambitasWeb.dtos.response.JwtResponse;
import com.ann.chambitasWeb.dtos.response.MessageResponse;

import java.util.stream.Collectors;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import org.springframework.security.core.Authentication;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*", maxAge = 3600)

public class AuthController {
     @Autowired
  AuthenticationManager authenticationManager;

  @Autowired
private JwtUtils jwtUtils;

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }


    @PostMapping("/login")
  public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

    Authentication authentication = authenticationManager
        .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getCorreo(), loginRequest.getPassword()));

    SecurityContextHolder.getContext().setAuthentication(authentication);
    String jwt = jwtUtils.generateJwtToken(authentication);

    UsuarioDetailsImpl userDetails = (UsuarioDetailsImpl) authentication.getPrincipal();
    List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
        .collect(Collectors.toList());

    return ResponseEntity
        .ok(new JwtResponse(jwt, userDetails.getId(), userDetails.getNombre(), userDetails.getCorreo(), roles));
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

