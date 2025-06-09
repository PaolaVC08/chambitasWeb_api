package com.ann.chambitasWeb.service;

import com.ann.chambitasWeb.models.Servicio;
import com.ann.chambitasWeb.models.Categoria;
import com.ann.chambitasWeb.repository.ServicioRepository;
import com.ann.chambitasWeb.repository.CategoriaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicioService {

    private final ServicioRepository serviceRepository;
    private final CategoriaRepository categoriaRepository;

    @Autowired
    public ServicioService(ServicioRepository servicioRepository, CategoriaRepository categoriaRepository) {
        this.serviceRepository = servicioRepository;
        this.categoriaRepository = categoriaRepository;
    }

    // Obtener todos los servicios
    public List<Servicio> obtenerTodos() {
        return serviceRepository.findAll();
    }

    // Obtener servicios por categoría
    public List<Servicio> obtenerPorCategoria(Long categoriaId) {
        Categoria categoria = categoriaRepository.findById(categoriaId)
            .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));
        return serviceRepository.findByCategoria(categoria);
    }

    // Crear un servicio asignando categoría
    public Servicio crearServicio(Servicio servicio, Long categoriaId) {
        Categoria categoria = categoriaRepository.findById(categoriaId)
            .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));
        servicio.setCategoria(categoria);
        return serviceRepository.save(servicio);
    }

    // Actualizar servicio
    public Servicio actualizarServicio(Long servicioId, Servicio datosActualizados) {
        Servicio servicio = serviceRepository.findById(servicioId)
            .orElseThrow(() -> new RuntimeException("Servicio no encontrado"));

        servicio.setNombre(datosActualizados.getNombre());
        servicio.setDescripcion(datosActualizados.getDescripcion());

        if (datosActualizados.getCategoria() != null) {
            Long catId = datosActualizados.getCategoria().getIdCategoria();
            Categoria categoria = categoriaRepository.findById(catId)
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));
            servicio.setCategoria(categoria);
        }

        return serviceRepository.save(servicio);
    }

    // Eliminar servicio
    public void eliminarServicio(Long servicioId) {
        Servicio servicio = serviceRepository.findById(servicioId)
            .orElseThrow(() -> new RuntimeException("Servicio no encontrado"));
        serviceRepository.delete(servicio);
    }
}