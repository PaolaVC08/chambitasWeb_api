package com.ann.chambitasWeb.validations;

import com.ann.chambitasWeb.models.EstadoUsuario;
import com.ann.chambitasWeb.exceptions.UsuarioNoVerificadoException;
import com.ann.chambitasWeb.exceptions.UsuarioNoActivoException;
import com.ann.chambitasWeb.models.EstadoVerificacion;
import com.ann.chambitasWeb.models.Usuario;
import com.ann.chambitasWeb.repository.VerificacionCorreoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UsuarioValidator {

    private final VerificacionCorreoRepository verificacionCorreoRepository;

    @Autowired
    public UsuarioValidator(VerificacionCorreoRepository verificacionCorreoRepository) {
        this.verificacionCorreoRepository = verificacionCorreoRepository;
    }

    public void validar(Usuario usuario) {
        validarActivo(usuario);
        validarCorreoVerificado(usuario);
    }

    private void validarActivo(Usuario usuario) {
        if (!EstadoUsuario.ACTIVO.equals(usuario.getEstado())) {
            throw new UsuarioNoActivoException("El usuario no está activo.");
        }
    }

    private void validarCorreoVerificado(Usuario usuario) {
        boolean verificado = verificacionCorreoRepository
            .findByUsuarioAndEstado(usuario, EstadoVerificacion.VERIFICADO)
            .isPresent();

        if (!verificado) {
            throw new UsuarioNoVerificadoException("El correo del usuario no ha sido verificado.");
        }
    }
}
