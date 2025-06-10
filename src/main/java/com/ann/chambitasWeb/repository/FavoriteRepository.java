package com.ann.chambitasWeb.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ann.chambitasWeb.models.HistorialLike;
import com.ann.chambitasWeb.models.Usuario;

@Repository
public interface FavoriteRepository extends JpaRepository<HistorialLike, Long> {

    Optional<HistorialLike> findByEmisorAndReceptor(Usuario emisor, Usuario receptor);

    List<HistorialLike> findByReceptor(Usuario receptor);

    List<HistorialLike> findByEmisor(Usuario emisor);

}