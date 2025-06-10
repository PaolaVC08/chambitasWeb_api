package com.ann.chambitasWeb.service;

import java.util.Set;

import com.ann.chambitasWeb.dtos.request.MedioContactoRequest;
import com.ann.chambitasWeb.dtos.request.SignupProfesionistaRequest;
import com.ann.chambitasWeb.dtos.response.SignupProfesionistaResponse;
import com.ann.chambitasWeb.models.ERole;
import com.ann.chambitasWeb.models.EstadoUsuario;
import com.ann.chambitasWeb.models.MedioContacto;
import com.ann.chambitasWeb.models.Profesion;
import com.ann.chambitasWeb.models.Profesionista;
import com.ann.chambitasWeb.models.ProfesionistaProfesion;
import com.ann.chambitasWeb.models.Rol;
import com.ann.chambitasWeb.models.TipoContacto;
import com.ann.chambitasWeb.models.Usuario;
import com.ann.chambitasWeb.models.UsuarioRol;
import com.ann.chambitasWeb.models.Zona;
import com.ann.chambitasWeb.repository.ProfesionistaRepository;
import com.ann.chambitasWeb.repository.ProfesionRepository;
import com.ann.chambitasWeb.repository.ZonaRepository;
import com.ann.chambitasWeb.repository.TipoContactoRepository;
import com.ann.chambitasWeb.repository.MedioContactoRepository;
import com.ann.chambitasWeb.repository.UsuarioRepository;
import com.ann.chambitasWeb.repository.RoleRepository;
import com.ann.chambitasWeb.service.interfaces.IProfesionistaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class ProfesionistaServiceImpl implements IProfesionistaService {

    private final ProfesionistaRepository profesionistaRepository;
    private final ProfesionRepository profesionRepository;
    private final ZonaRepository zonaRepository;
    private final TipoContactoRepository tipoContactoRepository;
    private final MedioContactoRepository medioContactoRepository;
    private final UsuarioRepository usuarioRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public ProfesionistaServiceImpl(
            ProfesionistaRepository profesionistaRepository,
            ProfesionRepository profesionRepository,
            ZonaRepository zonaRepository,
            TipoContactoRepository tipoContactoRepository,
            MedioContactoRepository medioContactoRepository,
            UsuarioRepository usuarioRepository,
            RoleRepository roleRepository,
            PasswordEncoder passwordEncoder) {
        this.profesionistaRepository = profesionistaRepository;
        this.profesionRepository = profesionRepository;
        this.zonaRepository = zonaRepository;
        this.tipoContactoRepository = tipoContactoRepository;
        this.medioContactoRepository = medioContactoRepository;
        this.usuarioRepository = usuarioRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
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
        
        UsuarioRol usuarioRol = new UsuarioRol(usuario, rol);
        usuario.setUsuarioRoles(Set.of(usuarioRol));
        return usuarioRepository.save(usuario);
    }

@Override
public void guardarDatosProfesionista(SignupProfesionistaRequest request, Usuario usuarioGuardado) {
    // Validar si el `zonaId` es válido
    if (request.getZonaId() == null || !zonaRepository.existsById(request.getZonaId())) {
        throw new RuntimeException("Zona no encontrada o no válida");
    }
    
    Profesionista profesionista = new Profesionista();
    profesionista.setUsuario(usuarioGuardado);

    // Buscar la zona usando el `zonaId`
    profesionista.setZona(zonaRepository.findById(request.getZonaId())
            .orElseThrow(() -> new RuntimeException("Zona no encontrada")));

    profesionista.setBiografia(request.getBiografia() != null ? request.getBiografia() : "Sin biografía");
    profesionista.setHorarioAtencion(request.getHorarioAtencion() != null ? request.getHorarioAtencion() : "Horario no definido");
    profesionista.setNumeroLikes(0);

    if (request.getMedioContactos() != null && !request.getMedioContactos().isEmpty()) {
        List<MedioContacto> contactos = new ArrayList<>();
        for (MedioContactoRequest medioContactoRequest : request.getMedioContactos()) {
            MedioContacto medioContacto = new MedioContacto();
            medioContacto.setValor(medioContactoRequest.getValor());
            TipoContacto tipoContacto = tipoContactoRepository.findById(medioContactoRequest.getTipoContactoId())
                    .orElseThrow(() -> new RuntimeException("Tipo de contacto no encontrado"));
            medioContacto.setTipo(tipoContacto);
            medioContacto.setProfesionista(profesionista);
            contactos.add(medioContacto);
        }
        profesionista.setContactos(contactos);
    }

    profesionistaRepository.save(profesionista);

}

    @Override
    public Profesionista registrarUsuarioProfesional(SignupProfesionistaRequest request) {
        Usuario usuario = crearUsuarioProfesionista(request);
        guardarDatosProfesionista(request, usuario);
        return profesionistaRepository.findByUsuario_Correo(usuario.getCorreo())
                .orElseThrow(() -> new RuntimeException("Profesionista no encontrado"));
    }

    @Override
    public Optional<Profesionista> obtenerProfesionistaPorCorreo(String correo) {
        return profesionistaRepository.findByUsuario_Correo(correo);
    }

    @Override
    public List<Profesionista> obtenerProfesionalesPorZona(Long zonaId) {
        return profesionistaRepository.findByZona_Id(zonaId);
    }

    @Override
    public List<Profesionista> obtenerProfesionalesPorProfesion(Long profesionId) {
        return profesionistaRepository.findByProfesiones_Profesion_IdProfesion(profesionId);
    }
}