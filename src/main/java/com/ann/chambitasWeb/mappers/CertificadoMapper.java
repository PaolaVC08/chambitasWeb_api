package com.ann.chambitasWeb.mappers;

import com.ann.chambitasWeb.models.Certificado;
import com.ann.chambitasWeb.dtos.response.CertificadoResponse;
import com.ann.chambitasWeb.dtos.request.CertificadoRequest;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CertificadoMapper extends AbstractMapper<Certificado, CertificadoResponse> {

    @Override
    public CertificadoResponse toDTO(Certificado entity) {
        CertificadoResponse response = new CertificadoResponse();
        response.setIdCertificate(entity.getIdCertificate());
        response.setTitulo(entity.getTitulo());
        response.setInstitucion(entity.getInstitucion());
        response.setAnio(entity.getAnio());
        return response;
    }

    @Override
    public Certificado toEntity(CertificadoResponse dto) {
        Certificado certificado = new Certificado();
        certificado.setIdCertificate(dto.getIdCertificate());
        certificado.setTitulo(dto.getTitulo());
        certificado.setInstitucion(dto.getInstitucion());
        certificado.setAnio(dto.getAnio());
        return certificado;
    }

    //manejar listas de Certificados y convertirlos
    public List<CertificadoResponse> toDTOList(List<Certificado> entities) {
        return super.toDTOList(entities);
    }

    public List<Certificado> toEntityList(List<CertificadoResponse> dtos) {
        return super.toEntityList(dtos);
    }

    //método específico para convertir de CertificadoRequest a Certificado si necesitas
    public Certificado toEntity(CertificadoRequest requestDTO) {
        Certificado certificado = new Certificado();
        certificado.setTitulo(requestDTO.getTitulo());
        certificado.setInstitucion(requestDTO.getInstitucion());
        certificado.setAnio(requestDTO.getAnio());
        return certificado;
    }
}
