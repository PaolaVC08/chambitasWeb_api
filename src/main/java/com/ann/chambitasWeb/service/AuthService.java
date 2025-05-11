package com.ann.chambitasWeb.service;

import com.ann.chambitasWeb.dtos.request.SignupRequest;
import com.ann.chambitasWeb.models.Usuario;
import com.ann.chambitasWeb.models.VerificacionCorreo;
import com.ann.chambitasWeb.models.EstadoVerificacion;
import com.ann.chambitasWeb.exceptions.ValidationServiceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class AuthService {

    private final UsuarioService usuarioService;
    private final VerificacionCorreoService verificacionCorreoService;
    private final EmailService emailService;

    @Autowired
    public AuthService(
            UsuarioService usuarioService,
            VerificacionCorreoService verificacionCorreoService,
            EmailService emailService) {
        this.usuarioService = usuarioService;
        this.verificacionCorreoService = verificacionCorreoService;
        this.emailService = emailService;
    }

    /**
     * Registra un nuevo usuario en estado inactivo y envía correo de verificación
     */
    public void registrarUsuario(SignupRequest request) {
        if (usuarioService.existeCorreo(request.getCorreo())) {
            throw new ValidationServiceException("El correo ya está registrado.");
        }

        // 1. Crear el usuario con estado INACTIVO
        Usuario nuevoUsuario = usuarioService.crearUsuarioInactivo(request);

        // 2. Generar token y guardar registro de verificación
        String token = UUID.randomUUID().toString();
        LocalDateTime ahora = LocalDateTime.now();
        LocalDateTime expiracion = ahora.plusMinutes(30);

        verificacionCorreoService.guardarRegistroVerificacion(
            nuevoUsuario,
            token,
            ahora,
            expiracion
        );

        // 3.Enviar correo
        emailService.enviarCorreoVerificacion(
            nuevoUsuario.getCorreo(),
            nuevoUsuario.getNombre(),
            token // este token se usará internamente para armar el link
        );
    }   
    
    // Verifica el token recibido desde el enlace del correo
    public void verificarCorreo(String token) {
        VerificacionCorreo verificacion = verificacionCorreoService.buscarPorToken(token);

        if (verificacion == null || verificacion.getEstado() != EstadoVerificacion.PENDIENTE) {
            throw new ValidationServiceException("Token inválido o ya utilizado.");
        }

        if (verificacion.getFechaExpiracion().isBefore(LocalDateTime.now())) {
            verificacionCorreoService.marcarComoExpirado(verificacion);
            throw new ValidationServiceException("El token ha expirado.");
        }

        verificacionCorreoService.marcarComoVerificado(verificacion);
        usuarioService.activarUsuario(verificacion.getUsuario().getCorreo());
    }
}

