package com.ann.chambitasWeb.service;

import com.ann.chambitasWeb.dtos.request.SignupProfesionistaRequest;
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
    private final ProfesionistaService profesionistaService;

    @Autowired
    public AuthService(
            UsuarioService usuarioService,
            VerificacionCorreoService verificacionCorreoService,
            EmailService emailService,
            ProfesionistaService profesionistaService) {
        this.usuarioService = usuarioService;
        this.verificacionCorreoService = verificacionCorreoService;
        this.emailService = emailService;
        this.profesionistaService = profesionistaService;
    }

    /**
     * Registra un nuevo usuario cliente
     */
    public void registrarUsuario(SignupRequest request) {
        if (usuarioService.existeCorreo(request.getCorreo())) {
            throw new ValidationServiceException("El correo ya está registrado.");
        }

        Usuario nuevoUsuario = usuarioService.crearUsuarioInactivo(request);

        generarTokenYEnviarCorreo(nuevoUsuario);
    }

    /**
     * Registra un nuevo usuario profesionista
     */
    public void registrarUsuarioProfesional(SignupProfesionistaRequest request) {
        if (usuarioService.existeCorreo(request.getCorreo())) {
            throw new ValidationServiceException("El correo ya está registrado.");
        }

        // 1. Crear el usuario con rol PROFESIONISTA
        Usuario nuevoUsuario = profesionistaService.crearUsuarioProfesionista(request);

        // 2. Guardar datos adicionales del profesionista (zona, categoría, contacto, etc.)
        profesionistaService.guardarDatosProfesionista(request, nuevoUsuario);

        // 3. Enviar verificación
        generarTokenYEnviarCorreo(nuevoUsuario);
    }

    /**
     * Reutilizable para ambos tipos de usuario
     */
    private void generarTokenYEnviarCorreo(Usuario nuevoUsuario) {
        String token = UUID.randomUUID().toString();
        LocalDateTime ahora = LocalDateTime.now();
        LocalDateTime expiracion = ahora.plusMinutes(30);

        verificacionCorreoService.guardarRegistroVerificacion(
                nuevoUsuario,
                token,
                ahora,
                expiracion);

        emailService.enviarCorreoVerificacion(
                nuevoUsuario.getCorreo(),
                nuevoUsuario.getNombre(),
                token
        );
    }

    /**
     * Verifica el token del correo
     */
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