package com.ann.chambitasWeb.service;

import com.ann.chambitasWeb.dtos.request.ServiceRequest;
import com.ann.chambitasWeb.dtos.response.ServiceResponse;
import com.ann.chambitasWeb.mappers.ServiceMapper;
import com.ann.chambitasWeb.models.Profesionista;
import com.ann.chambitasWeb.models.Servicio;
import com.ann.chambitasWeb.repository.ServicioRepository;
import com.ann.chambitasWeb.service.interfaces.IServiceService;
import com.ann.chambitasWeb.service.interfaces.IUsuarioService;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ServicioServiceImpl implements IServiceService {

    private final ServicioRepository servicioRepository;
    private final ServiceMapper serviceMapper;
    private final IUsuarioService usuarioService;

    @Autowired
    public ServicioServiceImpl(ServicioRepository servicioRepository, ServiceMapper serviceMapper,
            IUsuarioService usuarioService) {
        this.servicioRepository = servicioRepository;
        this.serviceMapper = serviceMapper;
        this.usuarioService = usuarioService;
    }

    @Override
    public ServiceResponse obtenerPorId(Long id) {
        Servicio servicio = servicioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Servicio no encontrado"));
        return serviceMapper.toDTO(servicio);
    }

    @Override
    public List<ServiceResponse> obtenerTodos() {
        List<Servicio> servicios = servicioRepository.findAll();
        return servicios.stream()
                .map(serviceMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ServiceResponse> obtenerServiciosPorProfesionista(Long profesionistaId) {
        // Buscar todos los servicios que están asociados a un profesionista específico
        List<Servicio> servicios = servicioRepository.findByProfesionistaProfesion_Profesionista_Id(profesionistaId);

        // Convertir las entidades a DTOs
        return servicios.stream()
                .map(serviceMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ServiceResponse crearServicio(ServiceRequest serviceRequest) {
        Servicio servicio = serviceMapper.toEntity(serviceRequest);
        servicio = servicioRepository.save(servicio);
        return serviceMapper.toDTO(servicio);
    }

    @Override
    public ServiceResponse actualizarServicio(Long id, ServiceRequest serviceRequest) {
        Servicio servicio = servicioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Servicio no encontrado"));
        servicio.setNombre(serviceRequest.getNombre());
        servicio.setDescripcion(serviceRequest.getDescripcion());
        servicio = servicioRepository.save(servicio);
        return serviceMapper.toDTO(servicio);
    }

    @Override
    public void eliminarServicio(Long id) {
        Servicio servicio = servicioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Servicio no encontrado"));
        servicioRepository.delete(servicio);
    }

    @Override
    public ServiceResponse crearServicioParaUsuario(String correo, ServiceRequest serviceRequest) {
        Profesionista profesionista = usuarioService.obtenerProfesionistaPorCorreo(correo);
        Servicio servicio = serviceMapper.toEntity(serviceRequest);
        servicio.setProfesionistaProfesion(profesionista.getProfesiones().get(0)); // o elige uno según lógica
        servicio = servicioRepository.save(servicio);
        return serviceMapper.toDTO(servicio);
    }

}