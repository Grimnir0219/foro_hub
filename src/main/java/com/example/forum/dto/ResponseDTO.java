package com.example.forum.dto;

import com.example.forum.model.Response;

import java.time.LocalDateTime;

public class ResponseDTO {
    private Long id;
    private String message;
    private LocalDateTime creationDate;
    private boolean solution;
    private String authorName;

    public ResponseDTO(Response response) {
        this.id = response.getId();
        this.message = response.getMessage();
        this.creationDate = response.getCreationDate();
        this.solution = response.isSolution();
        this.authorName = response.getAuthor().getName();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public boolean isSolution() {
        return solution;
    }

    public void setSolution(boolean solution) {
        this.solution = solution;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }
}

