package com.ann.chambitasWeb.service.interfaces;

import com.ann.chambitasWeb.dtos.request.SignupRequest;
import com.ann.chambitasWeb.models.Profesionista;
import com.ann.chambitasWeb.models.Usuario;

public interface IUsuarioService {

    boolean existeCorreo(String correo);

    Usuario crearUsuarioInactivo(SignupRequest request);

    void activarUsuario(String correoUsuario);

    Usuario obtenerPorCorreo(String correo);

    Profesionista obtenerProfesionistaPorCorreo(String correo);
}

