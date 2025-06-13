package com.ann.chambitasWeb.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ann.chambitasWeb.dtos.request.MedioContactoRequest;
import com.ann.chambitasWeb.dtos.response.MedioContactoResponse;
import com.ann.chambitasWeb.dtos.response.ZonaResponse;
import com.ann.chambitasWeb.models.MedioContacto;
import com.ann.chambitasWeb.models.Zona;
import com.ann.chambitasWeb.service.interfaces.IMedioContactoService;
import com.ann.chambitasWeb.mappers.MedioContactoMapper;



@RestController
@RequestMapping("/api/auth/contactos")
@CrossOrigin("*")
public class MedioContactoController {

    private final IMedioContactoService medioContactoService;
    private final MedioContactoMapper medioContactoMapper;

    public MedioContactoController(IMedioContactoService medioContactoService, MedioContactoMapper medioContactoMapper) {
        this.medioContactoService = medioContactoService;
        this.medioContactoMapper = medioContactoMapper;
    }

    @GetMapping("/profesionista/{id}")
    public List<MedioContactoResponse> obtenerContactosPorProfesionista(@PathVariable Long id) {
        return medioContactoService.obtenerMedioContactoPorProfesionista(id);
    }

   // @PostMapping("/crear/{id}")
    //public MedioContactoResponse crearContacto(@PathVariable Long id, @RequestBody MedioContactoRequest request) {
    //    return medioContactoService.crearMedioContactoParaProfesionista(id, request);
    //}

    @PutMapping("/actualizar/{id}")
    public MedioContactoResponse actualizarContacto(@PathVariable Long id, @RequestBody MedioContactoRequest request) {
        return medioContactoService.actualizarMedioContacto(id, request);
    }

    @DeleteMapping("/eliminar/{id}")
    public void eliminarContacto(@PathVariable Long id) {
        medioContactoService.eliminarMedioContacto(id);
    }

    //Get : medio contacto por ID
    @GetMapping("/{id}")
    public ResponseEntity<MedioContactoResponse>obtenerMedioContactoPorId(@PathVariable Long id){
    Optional <MedioContacto> medioContactoOptional = medioContactoService.getMedioContactoById(id);
    
    if (medioContactoOptional.isPresent()) {
        MedioContactoResponse response = medioContactoMapper.toDTO(medioContactoOptional.get());
        return ResponseEntity.ok(response);
    } else {
        return ResponseEntity.notFound().build();
    }
 }

     // GET: todas las zonas
    @GetMapping("/todoslosmediosdecontacto")
    public List<MedioContactoResponse> getAllMedioContactos() {
        List<MedioContacto> medioContactos = medioContactoService.getAllMedioContacto();
        return medioContactoMapper.toDTOList(medioContactos);
    }

}
    
