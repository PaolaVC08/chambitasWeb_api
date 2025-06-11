package com.ann.chambitasWeb.service.interfaces;

import com.ann.chambitasWeb.models.Zona;
import java.util.List;
import java.util.Optional;

public interface IZonaService {

    List<Zona> getAllZonas();  // Método para obtener todas las zonas

    Optional<Zona> getZonaById(Long id);  // Método para obtener una zona por su id

    // Aquí puedes agregar más métodos si es necesario
}