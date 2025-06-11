package com.ann.chambitasWeb.mappers;

import com.ann.chambitasWeb.models.Servicio;
import com.ann.chambitasWeb.dtos.response.ServiceResponse;
import com.ann.chambitasWeb.dtos.request.ServiceRequest;
//import com.ann.chambitasWeb.models.Categoria;
import com.ann.chambitasWeb.models.ImagenServicio;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ServiceMapper extends AbstractMapper<Servicio, ServiceResponse> {

    @Override
    public ServiceResponse toDTO(Servicio entity) {
        ServiceResponse response = new ServiceResponse();
        response.setIdServicio(entity.getIdServicio());
        response.setNombre(entity.getNombre());
        response.setDescripcion(entity.getDescripcion());
        //response.setCategoria(entity.getCategoria().getNombre())
        
        // Convertimos las imágenes a Base64
        List<String> imagenesBase64 = entity.getImagenes().stream()
                .map(ImagenServicio::getImagenB64)  // Asumimos que `ImagenServicio` tiene el campo `imagenB64`
                .collect(Collectors.toList());
        response.setImagenesBase64(imagenesBase64);

        return response;
    }

    @Override
    public Servicio toEntity(ServiceResponse dto) {
        Servicio servicio = new Servicio();
        servicio.setIdServicio(dto.getIdServicio());
        servicio.setNombre(dto.getNombre());
        servicio.setDescripcion(dto.getDescripcion());
        // Aquí asociamos la categoría y otros detalles si es necesario

        // Convertir las imágenes de Base64 a entidades
        List<ImagenServicio> imagenes = dto.getImagenesBase64().stream()
                .map(base64 -> {
                    ImagenServicio imagen = new ImagenServicio();
                    imagen.setImagenB64(base64);
                    return imagen;
                })
                .collect(Collectors.toList());
        servicio.setImagenes(imagenes);

        return servicio;
    }

    // Método específico para convertir de ServicioRequestDTO a Servicio
    public Servicio toEntity(ServiceRequest requestDTO) {
        Servicio servicio = new Servicio();
        servicio.setNombre(requestDTO.getNombre());
        servicio.setDescripcion(requestDTO.getDescripcion());

        // Asocia la categoría por ID
        //Categoria categoria = new Categoria();
        //categoria.setIdCategoria(requestDTO.getCategoriaId());
        //servicio.setCategoria(categoria);

        // Convertir las imágenes Base64 a entidades ImagenServicio
        List<ImagenServicio> imagenes = requestDTO.getImagenesBase64().stream()
                .map(base64 -> {
                    ImagenServicio imagen = new ImagenServicio();
                    imagen.setImagenB64(base64);
                    return imagen;
                })
                .collect(Collectors.toList());
        servicio.setImagenes(imagenes);

        return servicio;
    }
}

