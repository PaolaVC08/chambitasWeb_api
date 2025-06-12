package com.ann.chambitasWeb.service;

import com.ann.chambitasWeb.dtos.response.CategoriaProfesionesResponse;
import com.ann.chambitasWeb.dtos.response.ProfesionResponse;
import com.ann.chambitasWeb.dtos.response.ProfesionistaProfesionResponse;
import com.ann.chambitasWeb.mappers.ProfesionistaProfesionMapper;
import com.ann.chambitasWeb.models.Categoria;
import com.ann.chambitasWeb.models.ProfesionistaProfesion;
import com.ann.chambitasWeb.repository.CategoriaRepository;
import com.ann.chambitasWeb.repository.ProfesionRepository;
import com.ann.chambitasWeb.repository.ProfesionistaProfesionRepository;
import com.ann.chambitasWeb.service.interfaces.IProfesionesService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfesionesServiceImpl implements IProfesionesService {

    private final ProfesionRepository profesionRepo;
    private final CategoriaRepository categoriaRepo;
    private final ProfesionistaProfesionRepository profesionistaProfesionRepository;



    @Autowired
    public ProfesionesServiceImpl(ProfesionRepository profesionRepo, CategoriaRepository categoriaRepo,
     ProfesionistaProfesionRepository profesionistaProfesionRepository) {
        this.profesionRepo = profesionRepo;
        this.categoriaRepo = categoriaRepo;
        this.profesionistaProfesionRepository = profesionistaProfesionRepository;
    }

    @Override
    public List<CategoriaProfesionesResponse> obtenerProfesionesAgrupadas() {
        List<Categoria> categorias = categoriaRepo.findAll();

        return categorias.stream().map(categoria -> {
            List<ProfesionResponse> profesiones = profesionRepo
                    .findAllByCategoria_IdCategoria(categoria.getIdCategoria())
                    .stream()
                    .map(p -> new ProfesionResponse(p.getIdProfesion(), p.getNombre()))
                    .toList();

            return new CategoriaProfesionesResponse(categoria.getIdCategoria(),categoria.getNombre(), profesiones);
        }).toList();
    }

    @Override
    public List<ProfesionResponse> obtenerPorCategoria(Long idCategoria) {
        return profesionRepo.findAllByCategoria_IdCategoria(idCategoria)
                .stream()
                .map(p -> new ProfesionResponse(p.getIdProfesion(), p.getNombre()))
                .toList();
    }



@Autowired
private ProfesionistaProfesionMapper profesionistaProfesionMapper;

@Override
public List<ProfesionistaProfesionResponse> obtenerProfesionesPorProfesionista(Long profesionistaId) {
    List<ProfesionistaProfesion> relaciones = profesionistaProfesionRepository.findByProfesionista_Id(profesionistaId);
    return relaciones.stream()
                     .map(profesionistaProfesionMapper::toDTO)
                     .toList();
}
}
