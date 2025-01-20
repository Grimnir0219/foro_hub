package com.example.forum.repository;

import com.example.forum.model.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

// Repositorio para la entidad Topic.
public interface TopicRepository extends JpaRepository<Topic, Long> {

    @Query("SELECT t FROM Topic t WHERE t.course.name = :courseName AND FUNCTION('YEAR', t.creationDate) = :year")
    List<Topic> findByCourseNameAndCreationDateYear(@Param("courseName") String courseName, @Param("year") int year);
}
