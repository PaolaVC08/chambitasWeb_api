package com.ann.chambitasWeb.service;

import com.ann.chambitasWeb.dtos.request.DeleteUserRequest;
import com.ann.chambitasWeb.dtos.request.DeleteProfesionistaRequest;
import com.ann.chambitasWeb.repository.ProfesionistaRepository;
import com.ann.chambitasWeb.repository.UsuarioRepository;
import com.ann.chambitasWeb.service.interfaces.IUserService;
import com.ann.chambitasWeb.models.Usuario;
import com.ann.chambitasWeb.models.Profesionista;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserCountServiceImpl implements IUserService {

    private final UsuarioRepository usuarioRepository;
    private final ProfesionistaRepository profesionistaRepository;

    @Autowired
    public UserCountServiceImpl(UsuarioRepository usuarioRepository, ProfesionistaRepository profesionistaRepository) {
        this.usuarioRepository = usuarioRepository;
        this.profesionistaRepository = profesionistaRepository;
    }

    @Override
    public void deleteUserById(Long id) {
        // Buscar y eliminar al Usuario por id
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        usuarioRepository.delete(usuario);
    }

@Override
public void deleteProfesionistaById(Long id) {
    // Buscar al profesionista por ID
    Profesionista profesionista = profesionistaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Profesionista no encontrado"));

    // Obtener el usuario asociado al profesionista
    Usuario usuario = profesionista.getUsuario();

    // Eliminar el profesionista (esto elimina también las relaciones gracias al CascadeType.ALL)
    profesionistaRepository.delete(profesionista);

    // Si el profesionista tiene un usuario asociado, se elimina también
    if (usuario != null) {
        // Verificar si el usuario es un profesionista y eliminarlo si es necesario
        usuarioRepository.delete(usuario);
    }
}

    @Override
    public void logout() {
        // Implementa la lógica de cierre de sesión si lo deseas.
    }
}