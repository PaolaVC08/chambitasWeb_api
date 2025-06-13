package com.ann.chambitasWeb.service.interfaces;

import com.ann.chambitasWeb.dtos.request.DeleteUserRequest;
import com.ann.chambitasWeb.dtos.response.BiografiaResponse;
import com.ann.chambitasWeb.dtos.request.BiografiaRequest;
import com.ann.chambitasWeb.dtos.request.DeleteProfesionistaRequest;

public interface IUserService {
    void deleteUserById(Long id);  // Eliminar usuario por ID
    void deleteProfesionistaById(Long id);  // Eliminar profesionista por ID



}