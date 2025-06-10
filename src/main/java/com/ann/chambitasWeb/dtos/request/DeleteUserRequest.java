package com.ann.chambitasWeb.dtos.request;

public class DeleteUserRequest {
    private Long id;  // ID para eliminar usuario

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}