package com.ann.chambitasWeb.exceptions;

public class UsuarioNoActivoException extends RuntimeException{
    public UsuarioNoActivoException(String mensaje) {
        super(mensaje);
    }
}
