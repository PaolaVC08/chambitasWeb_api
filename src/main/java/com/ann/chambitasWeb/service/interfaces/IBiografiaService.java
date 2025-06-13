package com.ann.chambitasWeb.service.interfaces;

import com.ann.chambitasWeb.dtos.request.BiografiaRequest;
import com.ann.chambitasWeb.dtos.response.BiografiaResponse;

import java.util.List;

public interface IBiografiaService {

    // Obtener biografía de un profesionista (puede haber solo una por profesionista)
    List<BiografiaResponse> obtenerBiografiaPorProfesionista(Long profesionistaId);

    // Crear biografía para un profesionista autenticado
    BiografiaResponse crearBiografia(BiografiaRequest biografiaRequest, Long profesionistaId);

    // Actualizar biografía para un profesionista autenticado
    BiografiaResponse actualizarBiografia(BiografiaRequest biografiaRequest, Long profesionistaId);

    // Eliminar biografía para un profesionista autenticado
    void eliminarBiografia(Long profesionistaId);
}