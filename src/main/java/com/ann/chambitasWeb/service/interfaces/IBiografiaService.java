package com.ann.chambitasWeb.service.interfaces;

import com.ann.chambitasWeb.dtos.request.BiografiaRequest;
import com.ann.chambitasWeb.dtos.response.BiografiaResponse;

import java.util.List;

public interface IBiografiaService {


    //Método para ver biografia
    List<BiografiaResponse> obtenerBiografiaPorProfesionista(Long profesionistaId);
        // Método para crear una biografia
    BiografiaResponse crearBiografia(BiografiaRequest biografiaRequest);

    // Método para actualizar biografía
    BiografiaResponse actualizarBiografia(Long id, BiografiaRequest biografiaRequest );

    // Método para eliminar biografía
    void eliminarBiografia(Long id); 

}
