package com.example.forum.repository;

import com.example.forum.model.Response;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Repositorio para manejar operaciones con la entidad Response.

@Repository
public interface ResponseRepository extends JpaRepository<Response, Long> {
}

