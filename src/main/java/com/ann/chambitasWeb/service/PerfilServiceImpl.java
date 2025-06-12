package com.ann.chambitasWeb.service;

import com.ann.chambitasWeb.dtos.response.*;
import com.ann.chambitasWeb.models.Profesionista;
import com.ann.chambitasWeb.repository.ProfesionistaRepository;
import com.ann.chambitasWeb.service.interfaces.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PerfilServiceImpl implements IPerfilService {

    private final IServiceService servicioService;
    private final ICertificadoService certificadoService;
    private final IEducationService educacionService;
    private final IProfesionesService profesionService;
    private final IMedioContactoService medioContactoService;
    private final IZonaService zonaService;
    private final IBiografiaService biografiaService;

    @Autowired
    public PerfilServiceImpl(
        IServiceService servicioService,
        ICertificadoService certificadoService,
        IEducationService educacionService,
        IProfesionesService profesionService,
        IMedioContactoService medioContactoService,
        IZonaService zonaService,
        IBiografiaService biografiaService
    ) {
        this.servicioService = servicioService;
        this.certificadoService = certificadoService;
        this.educacionService = educacionService;
        this.profesionService = profesionService;
        this.medioContactoService = medioContactoService;
        this.zonaService = zonaService;
        this.biografiaService = biografiaService;
    }

    @Autowired
    private ProfesionistaRepository profesionistaRepository;
    

@Override
public PerfilProfesionistaResponse obtenerPerfil(Long profesionistaId) {
    Profesionista profesionista = profesionistaRepository.findById(profesionistaId)
            .orElseThrow(() -> new RuntimeException("Profesionista no encontrado"));

 List<ServiceResponse> servicios = servicioService.obtenerServiciosPorProfesionista(profesionistaId);
    List<CertificadoResponse> certificados = certificadoService.obtenerCertificadosPorProfesionista(profesionistaId);
    List<EducationResponse> educaciones = educacionService.obtenerEducacionesPorProfesionista(profesionistaId);
    List<ProfesionistaProfesionResponse> relaciones = profesionService.obtenerProfesionesPorProfesionista(profesionistaId); // <- nuevo
    List<MedioContactoResponse> medioContacto = medioContactoService.obtenerMedioContactoPorProfesionista(profesionistaId);
    List<BiografiaResponse> biografia = biografiaService.obtenerBiografiaPorProfesionista(profesionistaId);
    List<ZonaResponse> zonas = zonaService.obtenerZonaDeProfesionista(profesionistaId);

    PerfilProfesionistaResponse perfil = new PerfilProfesionistaResponse();
    perfil.setId(profesionistaId);
    perfil.setNombre(profesionista.getUsuario().getNombre());
    perfil.setBiografia(profesionista.getBiografia());
    perfil.setZonas(zonas);
    perfil.setLikes(profesionista.getNumeroLikes());
    perfil.setServicios(servicios);
    perfil.setCertificados(certificados);
    perfil.setEducaciones(educaciones);
    perfil.setProfesionistaProfesiones(relaciones); // <--- esto es lo nuevo
    perfil.setMedioContactoResponses(medioContacto);
    perfil.setBiografiaResponses(biografia);

    return perfil;
}

    @Override
    public PerfilProfesionistaResponse obtenerPerfilPorCorreo(String correo) {
        Profesionista profesionista = profesionistaRepository.findByUsuario_Correo(correo)
                .orElseThrow(() -> new RuntimeException("Profesionista no encontrado"));

        Long profesionistaId = profesionista.getId();

 List<ServiceResponse> servicios = servicioService.obtenerServiciosPorProfesionista(profesionistaId);
    List<CertificadoResponse> certificados = certificadoService.obtenerCertificadosPorProfesionista(profesionistaId);
    List<EducationResponse> educaciones = educacionService.obtenerEducacionesPorProfesionista(profesionistaId);
    List<ProfesionistaProfesionResponse> relaciones = profesionService.obtenerProfesionesPorProfesionista(profesionistaId); // <- nuevo
    List<MedioContactoResponse> medioContacto = medioContactoService.obtenerMedioContactoPorProfesionista(profesionistaId);
    List<BiografiaResponse> biografia = biografiaService.obtenerBiografiaPorProfesionista(profesionistaId);
     List<ZonaResponse> zonas = zonaService.obtenerZonaDeProfesionista(profesionistaId);
        // List<ZonaResponse> zonas = List.of(zonaService.obtenerZonaPorId(profesionista.getZona().getId()));
   
        PerfilProfesionistaResponse perfil = new PerfilProfesionistaResponse();
        perfil.setId(profesionistaId);
        perfil.setNombre(profesionista.getUsuario().getNombre());
        perfil.setBiografia(profesionista.getBiografia());
        perfil.setLikes(profesionista.getNumeroLikes());
        perfil.setServicios(servicios);
        perfil.setCertificados(certificados);
        perfil.setEducaciones(educaciones);
        perfil.setProfesionistaProfesiones(relaciones); // <--- esto es lo nuevo
        perfil.setMedioContactoResponses(medioContacto);
        perfil.setBiografiaResponses(biografia);
        perfil.setZonas(zonas);

        return perfil;
    }
}