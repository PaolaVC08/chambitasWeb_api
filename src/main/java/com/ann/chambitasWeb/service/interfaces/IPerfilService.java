package com.ann.chambitasWeb.service.interfaces;

import com.ann.chambitasWeb.dtos.response.PerfilProfesionistaResponse;

public interface IPerfilService {

    PerfilProfesionistaResponse obtenerPerfil(Long profesionistaId);
}
