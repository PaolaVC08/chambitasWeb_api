package com.ann.chambitasWeb.service;

import com.ann.chambitasWeb.models.Categoria;
import com.ann.chambitasWeb.repository.CategoriaRepository;
import com.ann.chambitasWeb.service.interfaces.ICategoriaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaServiceImpl implements ICategoriaService {

    private final CategoriaRepository categoriaRepo;

    @Autowired
    public CategoriaServiceImpl(CategoriaRepository categoriaRepo) {
        this.categoriaRepo = categoriaRepo;
    }

    @Override
    public List<Categoria> obtenerTodas() {
        return categoriaRepo.findAll();
    }
}

