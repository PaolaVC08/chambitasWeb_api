package com.ann.chambitasWeb.service;

import com.ann.chambitasWeb.models.HistorialLike;
import com.ann.chambitasWeb.models.Profesionista;
import com.ann.chambitasWeb.models.Usuario;
import com.ann.chambitasWeb.repository.FavoriteRepository;
import com.ann.chambitasWeb.repository.ProfesionistaRepository;
import com.ann.chambitasWeb.repository.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FavoriteService {

    private final ProfesionistaRepository profesionistaRepository;

    private final UsuarioRepository usuarioRepository;

    private final FavoriteRepository favoriteRepository;

    public FavoriteService(FavoriteRepository favoriteRepository, ProfesionistaRepository profesionistaRepository,
            UsuarioRepository usuarioRepository) {
        this.favoriteRepository = favoriteRepository;
        this.profesionistaRepository = profesionistaRepository;
        this.usuarioRepository = usuarioRepository;
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


/**
 * La función `toggleLike` en Java cambia el estado del like entre dos usuarios, ya sea agregando o
 * eliminando una entrada de like en la base de datos y actualizando el conteo total de likes.
 * 
 * @param emisor El parámetro `emisor` representa al usuario que está enviando el like. En el contexto de
 * tu método `toggleLike`, el `emisor` es el usuario que realiza la acción de dar like o quitar el like a otro usuario (`receptor`).
 * @param receptor El parámetro `receptor` en el método `toggleLike` representa al usuario que recibe la acción del like. Este método 
 * cambia el estado del like entre el `emisor` (quien envía el like) y el
 * `receptor` (quien recibe el like). Si se agrega un like desde el `emisor` al
 * @return El método `toggleLike` devuelve un mensaje indicando si se agregó o eliminó un like. Devuelve
 * "Like agregado" si se creó un nuevo like, o "Like eliminado" si se eliminó un like existente.
 */

    public String toggleLike(Usuario emisor, Usuario receptor) {
        Optional<HistorialLike> existente = favoriteRepository.findByEmisorAndReceptor(emisor, receptor);

        if (existente.isPresent()) {
            // Si ya existe el like, se elimina
            favoriteRepository.delete(existente.get());
            actualizarLikes(receptor, -1);
            return "Like eliminado";
        } else {
            // Si no existe, se crea
            HistorialLike nuevoLike = new HistorialLike();
            nuevoLike.setEmisor(emisor);
            nuevoLike.setReceptor(receptor);
            favoriteRepository.save(nuevoLike);
            actualizarLikes(receptor, 1);
            return "Like agregado";
        }
    }

    private void actualizarLikes(Usuario receptor, int delta) {
        Optional<Profesionista> profOpt = profesionistaRepository.findByUsuario_Correo(receptor.getCorreo());
        if (profOpt.isPresent()) {
            Profesionista prof = profOpt.get();
            int nuevosLikes = prof.getNumeroLikes() + delta;
            prof.setNumeroLikes(Math.max(0, nuevosLikes)); // Evita negativos
            profesionistaRepository.save(prof);
        }
    }

}