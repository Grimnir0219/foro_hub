package com.example.forum.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

//DTO para recibir los datos necesarios para registrar un nuevo tema.

public class TopicRequestDTO {

    @NotBlank(message = "El título no puede estar vacío.")
    private String title; // Título del tópico

    @NotBlank(message = "El mensaje no puede estar vacío.")
    private String message; // Contenido del tópico

    @NotNull(message = "El ID del autor es obligatorio.")
    private Long authorId; // ID del autor

    @NotNull(message = "El ID del curso es obligatorio.")
    private Long courseId; // ID del curso

    @NotBlank(message = "El estado es obligatorio.")
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // Getters y Setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }
}
