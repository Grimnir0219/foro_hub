package com.example.forum.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

// DTO para recibir datos de una respuesta al crearla.

public class ResponseRequestDTO {

    @NotNull(message = "El ID del autor es obligatorio.")
    private Long authorId;

    @NotBlank(message = "El mensaje no puede estar vacío.")
    private String message;

    // Getters y Setters
    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
