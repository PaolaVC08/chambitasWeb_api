package com.ann.chambitasWeb.service;

import com.ann.chambitasWeb.dtos.response.PerfilProfesionistaResponse;
import com.ann.chambitasWeb.dtos.response.ServiceResponse;
import com.ann.chambitasWeb.dtos.response.CertificadoResponse;
import com.ann.chambitasWeb.dtos.response.EducationResponse;
//import com.ann.chambitasWeb.dtos.response.ProfesionResponse;
//import com.ann.chambitasWeb.dtos.response.MedioContactoResponse;
//import com.ann.chambitasWeb.dtos.response.ZonaResponse;
import com.ann.chambitasWeb.service.interfaces.IPerfilService;
import com.ann.chambitasWeb.service.interfaces.IServiceService;
import com.ann.chambitasWeb.service.interfaces.ICertificadoService;
import com.ann.chambitasWeb.service.interfaces.IEducationService;
//import com.ann.chambitasWeb.service.interfaces.IProfesionesService;
//import com.ann.chambitasWeb.service.interfaces.IMedioContactoService;
//import com.ann.chambitasWeb.service.interfaces.IZonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PerfilServiceImpl implements IPerfilService {

    private final IServiceService servicioService;
    private final ICertificadoService certificadoService;
    private final IEducationService educacionService;
    //private final IProfesionesService profesionService;
    //private final IMedioContactoService medioContactoService;
    //private final IZonaService zonaService;

    @Autowired
    public PerfilServiceImpl(IServiceService servicioService,
                             ICertificadoService certificadoService,
                             IEducationService educacionService
                             //IProfesionesService profesionService,
                             //IMedioContactoService medioContactoService,
                             //IZonaService zonaService
                             ) {
        this.servicioService = servicioService;
        this.certificadoService = certificadoService;
        this.educacionService = educacionService;
        //this.profesionService = profesionService;
        //this.medioContactoService = medioContactoService;
        //this.zonaService = zonaService;
    }

    @Override
    public PerfilProfesionistaResponse obtenerPerfil(Long profesionistaId) {
        // Obtener los datos de los servicios, certificados, educación y profesiones
        List<ServiceResponse> servicios = servicioService.obtenerServiciosPorProfesionista(profesionistaId);
        List<CertificadoResponse> certificados = certificadoService.obtenerCertificadosPorProfesionista(profesionistaId);
        List<EducationResponse> educaciones = educacionService.obtenerEducacionesPorProfesionista(profesionistaId);
        //List<ProfesionResponse> profesiones = profesionService.obtenerProfesionesPorProfesionista(profesionistaId);
        //List<MedioContactoResponse> medioContacto = medioContactoService.obtenerMediosContactoPorProfesionista(profesionistaId);
        //List<ZonaResponse> zona = zonaService.obtenerZonasPorProfesionista(profesionistaId);

        // Crear el PerfilProfesionistaResponse con todos los datos obtenidos
        PerfilProfesionistaResponse perfil = new PerfilProfesionistaResponse();
        perfil.setId(profesionistaId);
        perfil.setLikes(10);  //llamar al servicio para obtener el número de likes
        perfil.setServicios(servicios);
        perfil.setCertificados(certificados);
        perfil.setEducaciones(educaciones);
        //perfil.setProfesiones(profesiones);
        //perfil.setMediosContacto(mediosContacto);
        //perfil.setZonas(zonas);

        return perfil;
    }
}

