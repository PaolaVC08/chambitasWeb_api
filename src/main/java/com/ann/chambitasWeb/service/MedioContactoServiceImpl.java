package com.ann.chambitasWeb.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ann.chambitasWeb.dtos.request.MedioContactoRequest;
import com.ann.chambitasWeb.dtos.response.MedioContactoResponse;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import com.ann.chambitasWeb.mappers.MedioContactoMapper;
import com.ann.chambitasWeb.models.MedioContacto;
import com.ann.chambitasWeb.repository.MedioContactoRepository;
import com.ann.chambitasWeb.service.interfaces.IMedioContactoService;

@Service
@Transactional
public class MedioContactoServiceImpl implements IMedioContactoService {

    private final MedioContactoRepository medioContactoRepository;
    private final MedioContactoMapper medioContactoMapper;

    public MedioContactoServiceImpl(MedioContactoRepository medioContactoRepository,
                                    MedioContactoMapper medioContactoMapper) {
        this.medioContactoRepository = medioContactoRepository;
        this.medioContactoMapper = medioContactoMapper;
    }

    @Override
    public List<MedioContactoResponse> obtenerMedioContactoPorProfesionista(Long profesionistaId) {
        List<MedioContacto> contactos = medioContactoRepository.findByProfesionista_Id(profesionistaId);
        return medioContactoMapper.toDTOList(contactos);
    }


  @Override
public void eliminarMedioContacto(Long id) {
    if (!medioContactoRepository.existsById(id)) {
        throw new IllegalArgumentException("Contacto con ID " + id + " no existe");
    }
    medioContactoRepository.deleteById(id);
}

    @Override
    public MedioContactoResponse crearMedioContactoParaProfesionista(Long id, MedioContactoRequest request) {
        request.setProfesionistaId(id);
        MedioContacto contacto = medioContactoMapper.toEntity(request);
        return medioContactoMapper.toDTO(medioContactoRepository.save(contacto));
    }

    @Override
    public MedioContactoResponse actualizarMedioContacto(Long id, MedioContactoRequest request) {
        Optional<MedioContacto> optional = medioContactoRepository.findById(id);
        if (optional.isEmpty()) {
            throw new IllegalArgumentException("Contacto no encontrado con ID: " + id);
        }

        MedioContacto contactoExistente = optional.get();
        contactoExistente.setValor(request.getValor());
        contactoExistente.getTipo().setIdTcontacto(request.getTipoContactoId());

        return medioContactoMapper.toDTO(medioContactoRepository.save(contactoExistente));
    }
}