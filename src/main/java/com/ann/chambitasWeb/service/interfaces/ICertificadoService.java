package com.ann.chambitasWeb.service.interfaces;

import java.util.List;

import com.ann.chambitasWeb.dtos.request.CertificadoRequest;
import com.ann.chambitasWeb.dtos.response.CertificadoResponse;

public interface ICertificadoService {
    CertificadoResponse obtenerPorId(Long id);

    List<CertificadoResponse> obtenerTodos();

    CertificadoResponse crearCertificado(CertificadoRequest certificadoRequestDTO);

    CertificadoResponse actualizarCertificado(Long id, CertificadoRequest certificadoRequestDTO);

    void eliminarCertificado(Long id);
}
