package com.ann.chambitasWeb.service;

import com.ann.chambitasWeb.dtos.request.SignupProfesionistaRequest;
import com.ann.chambitasWeb.models.*;
import com.ann.chambitasWeb.repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class ProfesionistaService {

    private final ProfesionistaRepository profesionistaRepository;
    private final ZonaRepository zonaRepository;
    private final CategoriaRepository categoriaRepository;
    private final TipoContactoRepository tipoContactoRepository;
    private final MedioContactoRepository medioContactoRepository;
    private final UsuarioRepository usuarioRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public ProfesionistaService(
            ProfesionistaRepository profesionistaRepository,
            ZonaRepository zonaRepository,
            CategoriaRepository categoriaRepository,
            TipoContactoRepository tipoContactoRepository,
            MedioContactoRepository medioContactoRepository,
            UsuarioRepository usuarioRepository,
            RoleRepository roleRepository,
            PasswordEncoder passwordEncoder) {
        this.profesionistaRepository = profesionistaRepository;
        this.zonaRepository = zonaRepository;
        this.categoriaRepository = categoriaRepository;
        this.tipoContactoRepository = tipoContactoRepository;
        this.medioContactoRepository = medioContactoRepository;
        this.usuarioRepository = usuarioRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

/**
 * La función crea un nuevo usuario con el rol de "PROFESIONISTA" basado en los datos proporcionados en la solicitud de registro.
 * 
 * @param request El método `crearUsuarioProfesionista` toma un objeto `SignupProfesionistaRequest`
 * denominado `request` como parámetro. Este objeto probablemente contiene la información necesaria para crear una nueva cuenta
 * de usuario para un profesionista, como su nombre, fecha de nacimiento, correo electrónico, contraseña, etc.
 * @return El método `crearUsuarioProfesionista` devuelve un objeto `Usuario` que ha sido creado
 * y guardado en la base de datos.
 */
    public Usuario crearUsuarioProfesionista(SignupProfesionistaRequest request) {
        Usuario usuario = new Usuario();
        usuario.setNombre(request.getNombre());
        usuario.setApellidoPaterno(request.getApPaterno());
        usuario.setApellidoMaterno(request.getApMaterno());
        usuario.setFechaNacimiento(request.getFechaNacimiento());
        usuario.setCorreo(request.getCorreo());
        usuario.setPassword(passwordEncoder.encode(request.getPassword()));
        usuario.setEstado(EstadoUsuario.INACTIVO);

        Rol rol = roleRepository.findByNombre(ERole.ROLE_PRO)
                .orElseThrow(() -> new RuntimeException("Rol PROFESIONISTA no encontrado"));
        usuario.setRoles(Set.of(rol));

        return usuarioRepository.save(usuario);
    }

    /**
     * Guarda los datos adicionales del profesionista una vez creado el Usuario base.
     */
/**
 * La función `guardarDatosProfesionista` guarda los datos de un usuario profesionista en una base de datos.
 * 
 * @param request El parámetro `request` en el método `guardarDatosProfesionista` de tu código es de
 * tipo `SignupProfesionistaRequest`. Este objeto probablemente contiene los datos necesarios para crear una nueva
 * entidad `Profesionista`, como el `zonaId`, `categoriaId`, etc.
 * @param usuarioGuardado El parámetro `usuarioGuardado` es una instancia de la clase `Usuario` que
 * representa los datos del usuario que han sido guardados o registrados en el sistema. En el
 * método `guardarDatosProfesionista`, esta instancia de `usuarioGuardado` se asocia con un nuevo
 * `Profesionista`.
 */
    public void guardarDatosProfesionista(SignupProfesionistaRequest request, Usuario usuarioGuardado) {
        Profesionista prof = new Profesionista();
        prof.setUsuario(usuarioGuardado);
        prof.setZona(zonaRepository.findById(request.getZonaId())
                .orElseThrow(() -> new RuntimeException("Zona no encontrada")));
        prof.setCategoria(categoriaRepository.findById(request.getCategoriaId())
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada")));
        prof.setBiografia(request.getBiografia() != null ? request.getBiografia() : "Sin biografía");
        prof.setHorarioAtencion("Por definir");
        prof.setNumeroLikes(0);

        Profesionista profesionistaGuardado = profesionistaRepository.save(prof);

    }
}