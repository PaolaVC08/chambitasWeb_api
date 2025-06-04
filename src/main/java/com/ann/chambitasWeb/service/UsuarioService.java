package com.ann.chambitasWeb.service;


import com.ann.chambitasWeb.dtos.request.SignupProfesionistaRequest;
import com.ann.chambitasWeb.dtos.request.SignupRequest;
import com.ann.chambitasWeb.models.Usuario;
import com.ann.chambitasWeb.models.ERole;
import com.ann.chambitasWeb.models.EstadoUsuario;
import com.ann.chambitasWeb.models.Rol;
import com.ann.chambitasWeb.repository.RoleRepository;
import com.ann.chambitasWeb.repository.UsuarioRepository;
import com.ann.chambitasWeb.utils.RolMapper;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;

    }

    /**
     * Verifica si ya existe un correo en la base de datos.
     */
    public boolean existeCorreo(String correo) {
        return usuarioRepository.existsByCorreo(correo);
    }

      //Crea un nuevo usuario en estado INACTIVO y guarda en la BD.
     
    public Usuario crearUsuarioInactivo(SignupRequest request) {
        Usuario usuario = new Usuario();
        usuario.setNombre(request.getNombre());
        usuario.setApellidoPaterno(request.getApPaterno());
        usuario.setApellidoMaterno(request.getApMaterno());
        usuario.setFechaNacimiento(request.getFechaNacimiento());
        usuario.setCorreo(request.getCorreo());
        usuario.setPassword(passwordEncoder.encode(request.getPassword()));
        usuario.setEstado(EstadoUsuario.INACTIVO);

        // Convertir tipoUsuario a ERole y asignar el rol
        ERole rolEnum = RolMapper.fromTipoUsuario(request.getTipoUsuario());
        Rol rol = roleRepository.findByNombre(rolEnum)
                .orElseThrow(() -> new RuntimeException("Error: Rol no encontrado"));
        usuario.setRoles(Set.of(rol));

        return usuarioRepository.save(usuario);
    }
    
     // Cambia el estado del usuario a ACTIVO después de la verificación de correo.
    public void activarUsuario(String correoUsuario) {
        Usuario usuario = usuarioRepository.findByCorreo(correoUsuario)
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        usuario.setEstado(EstadoUsuario.ACTIVO);
        usuarioRepository.save(usuario);
    }


}