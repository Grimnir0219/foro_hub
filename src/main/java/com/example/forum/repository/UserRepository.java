package com.example.forum.repository;

import com.example.forum.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//Repositorio para manejar operaciones con la entidad User.

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);
}

