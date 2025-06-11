package com.ann.chambitasWeb.service.interfaces;

import java.util.List;

import com.ann.chambitasWeb.dtos.response.CategoriaProfesionesResponse;
import com.ann.chambitasWeb.dtos.response.ProfesionResponse;

public interface IProfesionesService {


    
    List<CategoriaProfesionesResponse> obtenerProfesionesAgrupadas();

    List<ProfesionResponse> obtenerPorCategoria(Long idCategoria);
}

