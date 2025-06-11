package com.ann.chambitasWeb.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ann.chambitasWeb.dtos.request.CertificadoRequest;
import com.ann.chambitasWeb.dtos.response.CertificadoResponse;
import com.ann.chambitasWeb.service.interfaces.ICertificadoService;

@RestController
@RequestMapping("/certificados")
@CrossOrigin("*")
public class CertificadoController {

    private final ICertificadoService certificadoService;

    @Autowired
    public CertificadoController(ICertificadoService certificadoService) {
        this.certificadoService = certificadoService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<CertificadoResponse> obtenerCertificado(@PathVariable Long id) {
        CertificadoResponse certificado = certificadoService.obtenerPorId(id);
        return ResponseEntity.ok(certificado);
    }

    @GetMapping
    public ResponseEntity<List<CertificadoResponse>> obtenerTodosCertificados() {
        List<CertificadoResponse> certificados = certificadoService.obtenerTodos();
        return ResponseEntity.ok(certificados);
    }

    @PostMapping
    public ResponseEntity<CertificadoResponse> crearCertificado(@RequestBody CertificadoRequest certificadoRequestDTO) {
        CertificadoResponse certificado = certificadoService.crearCertificado(certificadoRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(certificado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CertificadoResponse> actualizarCertificado(@PathVariable Long id,
            @RequestBody CertificadoRequest certificadoRequestDTO) {
        CertificadoResponse certificado = certificadoService.actualizarCertificado(id, certificadoRequestDTO);
        return ResponseEntity.ok(certificado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCertificado(@PathVariable Long id) {
        certificadoService.eliminarCertificado(id);
        return ResponseEntity.noContent().build();
    }
}
