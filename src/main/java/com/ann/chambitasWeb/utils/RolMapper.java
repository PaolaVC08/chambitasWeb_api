package com.ann.chambitasWeb.utils;
import com.ann.chambitasWeb.models.ERole;

public class RolMapper {

    public static ERole fromTipoUsuario(String tipoUsuario) {
        return switch (tipoUsuario.toLowerCase()) {
            case "cliente" -> ERole.ROLE_CLIENT;
            case "profesional" -> ERole.ROLE_PRO;
            case "admin" -> ERole.ROLE_ADMIN;
            default -> throw new IllegalArgumentException("Tipo de usuario inválido: " + tipoUsuario);
        };
    }
}
