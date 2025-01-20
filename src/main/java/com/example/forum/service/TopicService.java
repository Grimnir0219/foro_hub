package com.example.forum.service;

import com.example.forum.dto.TopicRequestDTO;
import com.example.forum.dto.TopicResponseDTO;
import com.example.forum.model.Course;
import com.example.forum.model.Topic;
import com.example.forum.model.User;
import com.example.forum.repository.TopicRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.stream.Collectors;

// Servicio para manejar la lógica de negocio relacionada con Topic.

@Service
public class TopicService {

    private final TopicRepository topicRepository;

    public TopicService(TopicRepository topicRepository) {
        this.topicRepository = topicRepository;
    }

//      Registra un nuevo tema en la base de datos.
//      @param requestDTO Datos del tema a registrar.
//      @return El tema registrado.

    public Topic save(Topic topic) {
        return topicRepository.save(topic);
    }

    public Topic registerTopic(@Valid TopicRequestDTO requestDTO, User author, Course course) {
        Topic topic = new Topic();
        topic.setTitle(requestDTO.getTitle());
        topic.setMessage(requestDTO.getMessage());
        topic.setAuthor(author);
        topic.setCourse(course);

        return topicRepository.save(topic);
    }

    // Devuelve una página de temas en formato DTO.
    public Page<TopicResponseDTO> listAllTopics(Pageable pageable) {
        return topicRepository.findAll(pageable).map(topic ->
                new TopicResponseDTO(
                        topic.getId(),
                        topic.getTitle(),
                        topic.getMessage(),
                        topic.getCreationDate(),
                        topic.getStatus(),
                        topic.getAuthor().getName(),
                        topic.getCourse().getName()
                )
        );
    }

    //Filtra los temas por nombre del curso y año de creación
    //@param courseName nombre del curso
    //@param year año de crecación
    //@return Lista de temas filtrados en formato DTO

    public List<TopicResponseDTO> filterTopicsByCourseAndYear(String courseName, int year) {

        // Llamada al método del repositorio para obtener los tópicos filtrados.

        return topicRepository.findByCourseNameAndCreationDateYear(courseName, year)
                .stream()
                .map(topic -> new TopicResponseDTO(
                        topic.getId(),
                        topic.getTitle(),
                        topic.getMessage(),
                        topic.getCreationDate(),
                        topic.getStatus(),
                        topic.getAuthor().getName(),
                        topic.getCourse().getName()
                ))
                .collect(Collectors.toList());
    }
}

