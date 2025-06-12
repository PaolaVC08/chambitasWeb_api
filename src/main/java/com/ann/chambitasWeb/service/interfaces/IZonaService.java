package com.ann.chambitasWeb.service.interfaces;


import com.ann.chambitasWeb.dtos.request.ZonaRequest;
import com.ann.chambitasWeb.dtos.response.ProfesionistaResponse;
import com.ann.chambitasWeb.dtos.response.ZonaResponse;
import com.ann.chambitasWeb.models.Profesionista;
import com.ann.chambitasWeb.models.Zona;

import java.util.List;
import java.util.Optional;

public interface IZonaService {


    // Obtener todas las zonas
    List<Zona> getAllZonas();

    // Obtener una zona por su id
    Optional<Zona> getZonaById(Long id);

    List<Profesionista> obtenerProfesionistasPorZona(Long zonaId);  // Agregar este método

    // Asignar una zona a un profesionista
    List<ZonaResponse> agregarZonasAProfesionista(Long id, ZonaRequest zonaRequest);

        //Método para obtener la zona del profesionista.
    List<ZonaResponse> obtenerZonaDeProfesionista(Long profesionistaId); 
    



}