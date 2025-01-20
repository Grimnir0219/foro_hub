package com.example.forum.dto;

import java.time.LocalDateTime;

// DTO para enviar información sobre un tema al cliente.

public class TopicResponseDTO {

    private Long id; // ID del tópico
    private String title; // Título del tópico
    private String message; // Contenido del tópico
    private LocalDateTime creationDate; // Fecha de creación
    private String status; // Estado del tópico
    private String authorName; // Nombre del autor
    private String courseName; // Nombre del curso

    // Constructor
    public TopicResponseDTO(Long id, String title, String message, LocalDateTime creationDate, String status, String authorName, String courseName) {
        this.id = id;
        this.title = title;
        this.message = message;
        this.creationDate = creationDate;
        this.status = status;
        this.authorName = authorName;
        this.courseName = courseName;
    }


    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public String getStatus() {
        return status;
    }

    public String getAuthorName() {
        return authorName;
    }

    public String getCourseName() {
        return courseName;
    }
}

