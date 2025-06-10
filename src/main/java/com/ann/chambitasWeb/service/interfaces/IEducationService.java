package com.ann.chambitasWeb.service.interfaces;

import com.ann.chambitasWeb.dtos.request.EducationRequest;
import com.ann.chambitasWeb.dtos.response.EducationResponse;

import java.util.List;

public interface IEducationService {

    EducationResponse obtenerPorId(Long id);

    List<EducationResponse> obtenerTodos();

    EducationResponse crearEducacion(EducationRequest educationRequest);

    EducationResponse actualizarEducacion(Long id, EducationRequest educationRequest);

    void eliminarEducacion(Long id);
}
