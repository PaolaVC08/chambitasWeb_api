package com.ann.chambitasWeb.service;


import com.ann.chambitasWeb.dtos.request.SignupRequest;
import com.ann.chambitasWeb.models.Usuario;
import com.ann.chambitasWeb.models.UsuarioRol;
import com.ann.chambitasWeb.models.ERole;
import com.ann.chambitasWeb.models.EstadoUsuario;
import com.ann.chambitasWeb.models.Profesionista;
import com.ann.chambitasWeb.models.Rol;
import com.ann.chambitasWeb.repository.ProfesionistaRepository;
import com.ann.chambitasWeb.repository.RoleRepository;
import com.ann.chambitasWeb.repository.UsuarioRepository;
import com.ann.chambitasWeb.utils.RolMapper;
import com.ann.chambitasWeb.service.interfaces.IUsuarioService;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService implements IUsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final ProfesionistaRepository profesionistaRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository,ProfesionistaRepository profesionistaRepository, RoleRepository roleRepository,

            PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.profesionistaRepository = profesionistaRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    @Override
    public boolean existeCorreo(String correo) {
        return usuarioRepository.existsByCorreo(correo);
    }

    /**
     * La función crea un usuario inactivo basado en los datos proporcionados en la
     * solicitud de registro en Java.
     * 
     * @param request El método `crearUsuarioInactivo` toma un objeto
     *                `SignupRequest` como parámetro.
     *                Este objeto probablemente contiene la información necesaria
     *                para crear un nuevo usuario, como nombre, fecha de nacimiento,
     *                correo electrónico, contraseña y tipo de usuario.
     * @return El método `crearUsuarioInactivo` devuelve un objeto `Usuario` que ha
     *         sido creado y
     *         guardado en la base de datos.
     */

    // Crea un nuevo usuario en estado INACTIVO y guarda en la BD.

    public Usuario crearUsuarioInactivo(SignupRequest request) {
        Usuario usuario = new Usuario();
        usuario.setNombre(request.getNombre());
        usuario.setApellidoPaterno(request.getApPaterno());
        usuario.setApellidoMaterno(request.getApMaterno());
        usuario.setFechaNacimiento(request.getFechaNacimiento());
        usuario.setCorreo(request.getCorreo());
        usuario.setPassword(passwordEncoder.encode(request.getPassword()));
        usuario.setEstado(EstadoUsuario.INACTIVO);

        ERole rolEnum = RolMapper.fromTipoUsuario(request.getTipoUsuario());
        Rol rol = roleRepository.findByNombre(rolEnum)
                .orElseThrow(() -> new RuntimeException("Error: Rol no encontrado"));


        UsuarioRol usuarioRol = new UsuarioRol(usuario, rol);
        usuario.setUsuarioRoles(Set.of(usuarioRol));
        return usuarioRepository.save(usuario);
    }

    // Cambia el estado del usuario a ACTIVO después de la verificación de correo.
    /**
     * The `activarUsuario` function activates a user by setting their state to
     * active in the database.
     * 
     * @param correoUsuario The method `activarUsuario` takes a `String` parameter
     *                      `correoUsuario`, which
     *                      represents the email address of the user whose account
     *                      needs to be activated. The method retrieves
     *                      the user from the repository based on the provided email
     *                      address, sets the user's state to `ACTIVO`
     *                      (active),
     */

    public void activarUsuario(String correoUsuario) {
        Usuario usuario = usuarioRepository.findByCorreo(correoUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        usuario.setEstado(EstadoUsuario.ACTIVO);
        usuarioRepository.save(usuario);
    }

    @Override
    public Usuario obtenerPorCorreo(String correo) {
        return usuarioRepository.findByCorreo(correo)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }
    @Override
    public Profesionista obtenerProfesionistaPorCorreo(String correo) {
    return profesionistaRepository.findByUsuario_Correo(correo)
        .orElseThrow(() -> new RuntimeException("Profesionista no encontrado para el usuario con correo: " + correo));
}

}