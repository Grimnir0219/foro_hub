package com.example.forum.dto;

import com.example.forum.model.Topic;

import java.time.LocalDateTime;

public class TopicResponseDTO {

    private Long id;
    private String title;
    private String message;
    private LocalDateTime creationDate;
    private String status;
    private String authorName;
    private String courseName;

    // Constructor que toma un objeto Topic
    public TopicResponseDTO(Topic topic) {
        this.id = topic.getId();
        this.title = topic.getTitle();
        this.message = topic.getMessage();
        this.creationDate = topic.getCreationDate();
        this.status = topic.getStatus();
        this.authorName = topic.getAuthor() != null ? topic.getAuthor().getName() : "Autor desconocido";
        this.courseName = topic.getCourse() != null ? topic.getCourse().getName() : "Curso desconocido";
    }

    public TopicResponseDTO(Long id, String title, String message, LocalDateTime creationDate, String status, String authorName, String courseName) {
        this.id = id;
        this.title = title;
        this.message = message;
        this.creationDate = creationDate;
        this.status = status;
        this.authorName = authorName != null ? authorName : "Autor desconocido";
        this.courseName = courseName != null ? courseName : "Curso desconocido";
    }

    // Getters
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
