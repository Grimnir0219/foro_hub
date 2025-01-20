package com.example.forum.controller;

import com.example.forum.dto.TopicRequestDTO;
import com.example.forum.dto.TopicResponseDTO;
import com.example.forum.model.Course;
import com.example.forum.model.Topic;
import com.example.forum.model.User;
import com.example.forum.service.TopicService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


// Controlador para manejar las solicitudes relacionadas con temas.

@RestController
@RequestMapping("/topics")
public class TopicController {

    private final TopicService topicService;

    public TopicController(TopicService topicService) {
        this.topicService = topicService;
    }

//     Endpoint para registrar un nuevo Topic
//     @param requestDTO Datos del tema en formato JSON.
//     @return Respuesta con el tema registrado.

    @PostMapping
    public ResponseEntity<Topic> registerTopic(@RequestBody @Valid TopicRequestDTO requestDTO) {

        // Simulación: Busca al autor y al curso en la base de datos
        User author = new User(); // Reemplaza con lógica real para buscar el autor
        Course course = new Course(); // Reemplaza con lógica real para buscar el curso
        author.setId(requestDTO.getAuthorId());
        course.setId(requestDTO.getCourseId());

        // Crear el objeto Topic y asignar valores
        Topic topic = new Topic();
        topic.setTitle(requestDTO.getTitle());
        topic.setMessage(requestDTO.getMessage());
        topic.setStatus(requestDTO.getStatus()); // Asignar el estado
        topic.setAuthor(author);
        topic.setCourse(course);

        Topic savedTopic = topicService.save(topic); // Asegúrate de que tu servicio maneje este método
        return ResponseEntity.ok(savedTopic);
    }

    //Endpoint para listar todos los temas.
    //@param parámetros de paginación y ordenamiento
    //@return página de los temas en formato DTO.

    @GetMapping
    public ResponseEntity<Page<TopicResponseDTO>> listAllTopics(Pageable pageable) {
        Page<TopicResponseDTO> topics = topicService.listAllTopics(pageable);
        return ResponseEntity.ok(topics);
    }


//     Endpoint para filtrar tópicos por curso y año.
//     @param courseName Nombre del curso.
//     @param year Año de creación.
//     @return Lista de tópicos filtrados en formato DTO.

    @GetMapping("/filter")
    public ResponseEntity<List<TopicResponseDTO>> filterTopics(
            @RequestParam String courseName,
            @RequestParam int year
    ) {
        List<TopicResponseDTO> topics = topicService.filterTopicsByCourseAndYear(courseName, year);
        return ResponseEntity.ok(topics);
    }
}

