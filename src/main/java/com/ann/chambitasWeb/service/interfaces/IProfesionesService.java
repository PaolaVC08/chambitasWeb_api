package com.ann.chambitasWeb.service.interfaces;

import java.util.List;

import com.ann.chambitasWeb.dtos.response.CategoriaProfesionesResponse;
import com.ann.chambitasWeb.dtos.response.ProfesionResponse;
import com.ann.chambitasWeb.dtos.response.ProfesionistaProfesionResponse;
import com.ann.chambitasWeb.models.ProfesionistaProfesion;

public interface IProfesionesService {
    
    List<CategoriaProfesionesResponse> obtenerProfesionesAgrupadas();

    List<ProfesionResponse> obtenerPorCategoria(Long idCategoria);

    List<ProfesionistaProfesionResponse> obtenerProfesionesPorProfesionista(Long profesionistaId);
}

