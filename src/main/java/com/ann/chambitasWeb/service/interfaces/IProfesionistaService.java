package com.ann.chambitasWeb.service.interfaces;

import com.ann.chambitasWeb.dtos.request.BiografiaRequest;
import com.ann.chambitasWeb.dtos.request.SignupProfesionistaRequest;
import com.ann.chambitasWeb.dtos.response.BiografiaResponse;
import com.ann.chambitasWeb.dtos.response.SignupProfesionistaResponse;
import com.ann.chambitasWeb.dtos.response.ZonaResponse;
import com.ann.chambitasWeb.models.Profesionista;
import com.ann.chambitasWeb.models.Usuario;

import java.util.List;
import java.util.Optional;

public interface IProfesionistaService {

        // Método para registrar un nuevo profesionista
    Usuario crearUsuarioProfesionista(SignupProfesionistaRequest request);

    // Método para guardar los datos adicionales del profesionista
    void guardarDatosProfesionista(SignupProfesionistaRequest request, Usuario usuarioGuardado);


    // Método para registrar un nuevo profesionista
    Profesionista registrarUsuarioProfesional(SignupProfesionistaRequest request);

    // Método para obtener un profesionista por correo
    Optional<Profesionista> obtenerProfesionistaPorCorreo(String correo);

    // Método para obtener todos los profesionistas por zona
    List<Profesionista> obtenerProfesionalesPorZona(Long zonaId);

    // Método para obtener todos los profesionistas por profesión
    List<Profesionista> obtenerProfesionalesPorProfesion(Long profesionId);

     // Crear biografia obteniendo el id de profesionista
    BiografiaResponse crearBiografia(BiografiaRequest request, Long idProfesionista);
}

