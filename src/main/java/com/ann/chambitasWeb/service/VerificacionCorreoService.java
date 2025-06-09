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

/**
 * La función `guardarRegistroVerificacion` guarda un registro de verificación para un usuario con un token,
 * fechas de creación y expiración, y un estado pendiente.
 * 
 * @param usuario El parámetro `usuario` es un objeto de tipo `Usuario`, que probablemente representa una
 * entidad de usuario en tu sistema. Contiene información sobre el usuario, como su nombre de usuario, correo electrónico,
 * contraseña, etc.
 * @param token Un token único generado para verificar la dirección de correo electrónico del usuario.
 * @param creacion El parámetro `creacion` en el método `guardarRegistroVerificacion` es de tipo
 * `LocalDateTime` y representa la fecha y hora cuando se crea el registro de verificación. Se usa
 * para establecer la propiedad `fechaCreacion` del objeto `VerificacionCorreo` antes de guardarlo en
 * la base de datos.
 * @param expiracion El parámetro `expiracion` en el método `guardarRegistroVerificacion` representa
 * la fecha y hora cuando el token de verificación expirará. Esto se usa para determinar hasta cuándo
 * el enlace o token de verificación es válido para que el usuario verifique su dirección de correo electrónico.
 * @return El método devuelve un objeto de tipo `VerificacionCorreo`, que representa una
 * entidad de correo electrónico de verificación que ha sido guardada en la base de datos.
 */
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


    
/**
 * La función genera un token de verificación para la dirección de correo electrónico de un usuario y lo guarda en la base de datos.
 * 
 * @param usuario El parámetro `usuario` es un objeto de tipo `Usuario`, que probablemente contiene
 * información sobre un usuario, como su nombre, correo electrónico, contraseña, etc. En el método `generarTokenVerificacion`,
 * este objeto `usuario` se usa para crear un token de verificación para la dirección de correo electrónico del usuario.
 * @return Una instancia de `VerificacionCorreo` con los detalles configurados para el usuario dado, incluyendo un
 * token generado, fechas de creación y expiración, y un estado de verificación pendiente, que es guardada en
 * el `verificacionCorreoRepository`.
 */

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

    
/**
 * Esta función en Java valida un token para la verificación de correo electrónico y devuelve un Optional que contiene el
 * objeto de verificación si el token es válido.
 * 
 * @param token El método `validarToken` toma un `token` como parámetro. Este token se usa para buscar
 * una entidad `VerificacionCorreo` en el `verificacionCorreoRepository`. Si el token se encuentra y
 * cumple con ciertas condiciones (como estar en estado pendiente y no haber expirado), el
 * @return Se devuelve un Optional que contiene el objeto `VerificacionCorreo` si el token es válido
 * y cumple con las condiciones necesarias. Si el token no se encuentra, el estado no es pendiente, o la
 * fecha de expiración ha pasado, entonces se devuelve un Optional vacío.
 */

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
