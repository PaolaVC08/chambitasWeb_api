package com.ann.chambitasWeb.controllers;



import com.ann.chambitasWeb.dtos.response.DeleteUserResponse;
import com.ann.chambitasWeb.dtos.response.DeleteProfesionistaResponse;
import com.ann.chambitasWeb.service.interfaces.IUserService;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth/user")
@CrossOrigin(origins = "*", maxAge = 3600)
public class UserController {

    private final IUserService userService;



    public UserController(IUserService userService) {
        this.userService = userService;
    }

    // Endpoint para eliminar cuenta de usuario (cliente)
    @DeleteMapping("/delete-user/{id}")
    public ResponseEntity<DeleteUserResponse> deleteUser(@PathVariable Long id) {
        try {
            userService.deleteUserById(id);
            return ResponseEntity.ok(new DeleteUserResponse("Usuario eliminado correctamente", true));
        } catch (Exception e) {
            return ResponseEntity.status(400).body(new DeleteUserResponse("Error al eliminar el usuario: " + e.getMessage(), false));
        }
    }

    // Endpoint para eliminar cuenta de profesionista
    @DeleteMapping("/delete-professionista/{id}")
    public ResponseEntity<DeleteProfesionistaResponse> deleteProfesionista(@PathVariable Long id) {
        try {
            userService.deleteProfesionistaById(id);
            return ResponseEntity.ok(new DeleteProfesionistaResponse("Profesionista eliminado correctamente", true));
        } catch (Exception e) {
            return ResponseEntity.status(400).body(new DeleteProfesionistaResponse("Error al eliminar el profesionista: " + e.getMessage(), false));
        }
    }

    // Endpoint para cerrar sesión
    @PostMapping("/logout")
    public String logout() {
        // Limpiar el contexto de seguridad
        SecurityContextHolder.clearContext();
        return "Sesión cerrada correctamente";
    }
}