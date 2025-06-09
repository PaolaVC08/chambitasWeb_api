package com.ann.chambitasWeb.service;

import com.ann.chambitasWeb.dtos.response.CategoriaProfesionesResponse;
import com.ann.chambitasWeb.dtos.response.ProfesionResponse;
import com.ann.chambitasWeb.models.Categoria;
import com.ann.chambitasWeb.repository.CategoriaRepository;
import com.ann.chambitasWeb.repository.ProfesionRepository;
import com.ann.chambitasWeb.service.interfaces.IProfesionesService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfesionesServiceImpl implements IProfesionesService {

    private final ProfesionRepository profesionRepo;
    private final CategoriaRepository categoriaRepo;

    @Autowired
    public ProfesionesServiceImpl(ProfesionRepository profesionRepo, CategoriaRepository categoriaRepo) {
        this.profesionRepo = profesionRepo;
        this.categoriaRepo = categoriaRepo;
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

            return new CategoriaProfesionesResponse(categoria.getNombre(), profesiones);
        }).toList();
    }

    @Override
    public List<ProfesionResponse> obtenerPorCategoria(Long idCategoria) {
        return profesionRepo.findAllByCategoria_IdCategoria(idCategoria)
                .stream()
                .map(p -> new ProfesionResponse(p.getIdProfesion(), p.getNombre()))
                .toList();
    }
}
