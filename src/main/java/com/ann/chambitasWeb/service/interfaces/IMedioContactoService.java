package com.ann.chambitasWeb.service.interfaces;

import java.util.List;

import com.ann.chambitasWeb.dtos.request.MedioContactoRequest;
import com.ann.chambitasWeb.dtos.response.MedioContactoResponse;

public interface IMedioContactoService {

    List<MedioContactoResponse> obtenerMedioContactoPorProfesionista (Long profesionistaId);

    void eliminarMedioContacto(Long id);

    MedioContactoResponse crearMedioContactoParaProfesionista(Long id,MedioContactoRequest medioContactoRequest);

    MedioContactoResponse actualizarMedioContacto(Long id, MedioContactoRequest medioContactoRequest);
    
} 
