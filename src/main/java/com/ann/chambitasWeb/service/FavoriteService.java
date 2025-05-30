package com.ann.chambitasWeb.service;

import com.ann.chambitasWeb.models.HistorialLike;
import com.ann.chambitasWeb.models.Usuario;
import com.ann.chambitasWeb.repository.FavoriteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FavoriteService {
 
    private final FavoriteRepository favoriteRepository;

    @Autowired
    public FavoriteService(FavoriteRepository favoriteRepository) {
        this.favoriteRepository = favoriteRepository;
    }

    // Obtener todos los likes (historial completo)
    public List<HistorialLike> obtenerTodos() {
        return favoriteRepository.findAll();
    }

    // Obtener likes que un usuario emitió
    public List<HistorialLike> obtenerLikesEmitidosPorUsuario(Usuario usuario) {
        return favoriteRepository.findByEmisor(usuario);
    }

    // Obtener likes que un usuario recibió
    public List<HistorialLike> obtenerLikesRecibidosPorUsuario(Usuario usuario) {
        return favoriteRepository.findByReceptor(usuario);
    }

    // Comprobar si un usuario le dio like a otro
    public Optional<HistorialLike> existeLikeEntreUsuarios(Usuario emisor, Usuario receptor) {
        return favoriteRepository.findByEmisorAndReceptor(emisor, receptor);
    }
}