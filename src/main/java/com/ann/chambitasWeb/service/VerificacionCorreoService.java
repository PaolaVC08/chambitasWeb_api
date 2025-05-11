package com.ann.chambitasWeb.service;

import com.ann.chambitasWeb.models.Usuario;
import com.ann.chambitasWeb.models.VerificacionCorreo;
import com.ann.chambitasWeb.models.EstadoVerificacion;
import com.ann.chambitasWeb.repository.VerificacionCorreoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class VerificacionCorreoService {

    private final VerificacionCorreoRepository verificacionCorreoRepository;

    @Autowired
    public VerificacionCorreoService(VerificacionCorreoRepository verificacionCorreoRepository) {
        this.verificacionCorreoRepository = verificacionCorreoRepository;
    }

    public VerificacionCorreo guardarRegistroVerificacion(Usuario usuario, String token, LocalDateTime creacion,
            LocalDateTime expiracion) {
        VerificacionCorreo v = new VerificacionCorreo();
        v.setUsuario(usuario);
        v.setToken(token);
        v.setFechaCreacion(creacion);
        v.setFechaExpiracion(expiracion);
        v.setEstado(EstadoVerificacion.PENDIENTE);
        return verificacionCorreoRepository.save(v);
    }

    // Crea y guarda un nuevo token de verificación asociado al usuario.
    public VerificacionCorreo generarTokenVerificacion(Usuario usuario) {
        VerificacionCorreo verificacion = new VerificacionCorreo();
        verificacion.setUsuario(usuario);
        verificacion.setToken(UUID.randomUUID().toString());
        verificacion.setFechaCreacion(LocalDateTime.now());
        verificacion.setFechaExpiracion(LocalDateTime.now().plusMinutes(15));
        verificacion.setEstado(EstadoVerificacion.PENDIENTE);
        return verificacionCorreoRepository.save(verificacion);
    }

     // Busca el token y valida si está vigente y pendiente.
    public Optional<VerificacionCorreo> validarToken(String token) {
        Optional<VerificacionCorreo> verificacionOpt = verificacionCorreoRepository.findByToken(token);
        if (verificacionOpt.isEmpty()) {
            return Optional.empty();
        }

        VerificacionCorreo verificacion = verificacionOpt.get();

        if (verificacion.getEstado() != EstadoVerificacion.PENDIENTE) {
            return Optional.empty();
        }

        if (verificacion.getFechaExpiracion().isBefore(LocalDateTime.now())) {
            verificacion.setEstado(EstadoVerificacion.EXPIRADO);
            verificacionCorreoRepository.save(verificacion);
            return Optional.empty();
        }

        return Optional.of(verificacion);
    }

    
    // Marca el token como verificado.
    public void marcarComoVerificado(VerificacionCorreo verificacion) {
        verificacion.setEstado(EstadoVerificacion.VERIFICADO);
        verificacionCorreoRepository.save(verificacion);
    }
    
    // Marca el token como expirado.
    public void marcarComoExpirado(VerificacionCorreo verificacion) {
        verificacion.setEstado(EstadoVerificacion.EXPIRADO);
        verificacionCorreoRepository.save(verificacion);
    }

    // Busca un token sin validar su vigencia o estado.
    public VerificacionCorreo buscarPorToken(String token) {
        return verificacionCorreoRepository.findByToken(token)
                .orElse(null);
    }

}
