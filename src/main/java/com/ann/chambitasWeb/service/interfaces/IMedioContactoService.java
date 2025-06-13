package com.ann.chambitasWeb.service.interfaces;

import java.util.List;
import java.util.Optional;

import com.ann.chambitasWeb.dtos.request.MedioContactoRequest;
import com.ann.chambitasWeb.dtos.response.MedioContactoResponse;
import com.ann.chambitasWeb.models.MedioContacto;


public interface IMedioContactoService {

    List<MedioContactoResponse> obtenerMedioContactoPorProfesionista (Long profesionistaId);

    void eliminarMedioContacto(Long id);

  //  MedioContactoResponse crearMedioContactoParaProfesionista(Long id,MedioContactoRequest medioContactoRequest);

    MedioContactoResponse actualizarMedioContacto(Long id, MedioContactoRequest medioContactoRequest);

    // Obtener todos los medios de contacto
    List<MedioContacto> getAllMedioContacto();

    // Método para obtener medio de contacto por su id
    Optional<MedioContacto> getMedioContactoById(Long id);

    
} 
