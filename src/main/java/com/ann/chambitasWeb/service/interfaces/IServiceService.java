package com.ann.chambitasWeb.service.interfaces;

import com.ann.chambitasWeb.dtos.request.ServiceRequest;
import com.ann.chambitasWeb.dtos.response.ServiceResponse;

import java.util.List;

public interface IServiceService {

    // Método para obtener un servicio por su ID
    ServiceResponse obtenerPorId(Long id);

    // Método para obtener todos los servicios
    List<ServiceResponse> obtenerTodos();

    // Método para crear un nuevo servicio
    ServiceResponse crearServicio(ServiceRequest serviceRequest);

    // Método para obtener los servicios de un profesionista específico
    List<ServiceResponse> obtenerServiciosPorProfesionista(Long profesionistaId);

    // Método para actualizar un servicio existente
    ServiceResponse actualizarServicio(Long id, ServiceRequest serviceRequest);

    // Método para eliminar un servicio
    void eliminarServicio(Long id);
}
