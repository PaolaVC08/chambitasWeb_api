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

    @Override
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

    @Override
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
