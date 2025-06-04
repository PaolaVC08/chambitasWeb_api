package com.ann.chambitasWeb.exceptions;

public class UsuarioNoVerificadoException extends RuntimeException {
    public UsuarioNoVerificadoException(String mensaje) {
        super(mensaje);
    }
}