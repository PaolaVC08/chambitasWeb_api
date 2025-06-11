package com.ann.chambitasWeb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ann.chambitasWeb.dtos.request.CertificadoRequest;
import com.ann.chambitasWeb.dtos.response.CertificadoResponse;
import com.ann.chambitasWeb.mappers.CertificadoMapper;
import com.ann.chambitasWeb.models.Certificado;
import com.ann.chambitasWeb.repository.CertificadoRepository;
import com.ann.chambitasWeb.service.interfaces.ICertificadoService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class CertificadoServiceImpl implements ICertificadoService {

    private final CertificadoRepository certificadoRepository;
    private final CertificadoMapper certificadoMapper;

    @Autowired
    public CertificadoServiceImpl(CertificadoRepository certificadoRepository, CertificadoMapper certificadoMapper) {
        this.certificadoRepository = certificadoRepository;
        this.certificadoMapper = certificadoMapper;

    }

    @Override
    public CertificadoResponse obtenerPorId(Long id) {
        Certificado certificado = certificadoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Certificado no encontrado"));
        return certificadoMapper.toDTO(certificado);
    }

    @Override
    public List<CertificadoResponse> obtenerTodos() {
        List<Certificado> certificados = certificadoRepository.findAll();
        return certificadoMapper.toDTOList(certificados);
    }

    @Override
    public CertificadoResponse crearCertificado(CertificadoRequest certificadoRequest) {
        Certificado certificado = certificadoMapper.toEntity(certificadoRequest);
        certificado = certificadoRepository.save(certificado);
        return certificadoMapper.toDTO(certificado);
    }

    @Override
    public CertificadoResponse actualizarCertificado(Long id, CertificadoRequest certificadoRequest) {
        Certificado certificado = certificadoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Certificado no encontrado"));
        certificado.setTitulo(certificadoRequest.getTitulo());
        certificado.setInstitucion(certificadoRequest.getInstitucion());
        certificado.setAnio(certificadoRequest.getAnio());
        certificado = certificadoRepository.save(certificado);
        return certificadoMapper.toDTO(certificado);

    }

    @Override
    public void eliminarCertificado(Long id) {
        Certificado certificado = certificadoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Certificado no encontrado"));
        certificadoRepository.delete(certificado);
    }
}
