package com.example.forum.service;

import com.example.forum.dto.TopicRequestDTO;
import com.example.forum.dto.TopicResponseDTO;
import com.example.forum.dto.ResponseRequestDTO;
import com.example.forum.model.Course;
import com.example.forum.model.Topic;
import com.example.forum.model.Response;
import com.example.forum.model.User;
import com.example.forum.repository.TopicRepository;
import com.example.forum.repository.UserRepository;
import com.example.forum.repository.ResponseRepository;
import com.example.forum.repository.CourseRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Service
public class TopicService {

    private final TopicRepository topicRepository;
    private final UserRepository userRepository;
    private final ResponseRepository responseRepository;
    private final CourseRepository courseRepository; // Agregado

    // Constructor con inyección de dependencias
    public TopicService(TopicRepository topicRepository, UserRepository userRepository, ResponseRepository responseRepository, CourseRepository courseRepository) {
        this.topicRepository = topicRepository;
        this.userRepository = userRepository;
        this.responseRepository = responseRepository;
        this.courseRepository = courseRepository; // Inicialización
    }

    public Topic save(Topic topic) {
        return topicRepository.save(topic);
    }

    // Método actualizado para registrar un nuevo tópico
    public TopicResponseDTO registerTopic(@Valid TopicRequestDTO requestDTO) {
        // Buscar el autor por ID
        User author = userRepository.findById(requestDTO.getAuthorId())
                .orElseThrow(() -> new IllegalArgumentException("Autor no encontrado"));

        // Buscar el curso por ID
        Course course = courseRepository.findById(requestDTO.getCourseId())
                .orElseThrow(() -> new IllegalArgumentException("Curso no encontrado"));

        // Crear un nuevo tópico
        Topic topic = new Topic();
        topic.setTitle(requestDTO.getTitle());
        topic.setMessage(requestDTO.getMessage());
        topic.setStatus(requestDTO.getStatus());
        topic.setAuthor(author);
        topic.setCourse(course);

        // Guardar el tópico en la base de datos
        Topic savedTopic = topicRepository.save(topic);

        // Devolver un TopicResponseDTO
        return new TopicResponseDTO(
                savedTopic.getId(),
                savedTopic.getTitle(),
                savedTopic.getMessage(),
                savedTopic.getCreationDate(),
                savedTopic.getStatus(),
                savedTopic.getAuthor().getName(),
                savedTopic.getCourse().getName()
        );
    }

    public Page<TopicResponseDTO> listAllTopics(Pageable pageable) {
        return topicRepository.findAll(pageable)
                .map(TopicResponseDTO::new);
    }

    public List<TopicResponseDTO> filterTopicsByCourseAndYear(String courseName, int year) {
        List<Topic> topics = topicRepository.findByCourseNameAndCreationDateYear(courseName, year);
        return topics.stream().map(TopicResponseDTO::new).toList();
    }

    public List<TopicResponseDTO> filterTopicsByStatus(String status) {
        List<Topic> topics = topicRepository.findByStatusIgnoreCase(status);
        return topics.stream()
                .map(TopicResponseDTO::new)
                .toList();
    }

    public Response addResponse(Long topicId, ResponseRequestDTO requestDTO) {
        // Verificar el tópico
        Topic topic = topicRepository.findById(topicId)
                .orElseThrow(() -> new IllegalArgumentException("Tópico no encontrado"));
        System.out.println("Tópico encontrado: " + topic);

        // Verificar el autor
        User author = userRepository.findById(requestDTO.getAuthorId())
                .orElseThrow(() -> new IllegalArgumentException("Autor no encontrado"));
        System.out.println("Autor encontrado: " + author);

        // Crear la respuesta
        Response response = new Response();
        response.setMessage(requestDTO.getMessage());
        response.setAuthor(author);
        response.setTopic(topic);
        System.out.println("Respuesta creada: " + response);

        // Guardar la respuesta
        return responseRepository.save(response);
    }


    public TopicResponseDTO updateTopic(Long id, TopicRequestDTO requestDTO) {
        Topic topic = topicRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Tópico no encontrado"));

        topic.setTitle(requestDTO.getTitle());
        topic.setMessage(requestDTO.getMessage());
        topic.setStatus(requestDTO.getStatus());

        Topic updatedTopic = topicRepository.save(topic);

        return new TopicResponseDTO(
                updatedTopic.getId(),
                updatedTopic.getTitle(),
                updatedTopic.getMessage(),
                updatedTopic.getCreationDate(),
                updatedTopic.getStatus(),
                updatedTopic.getAuthor().getName(),
                updatedTopic.getCourse().getName()
        );
    }

    public void deleteTopic(Long id) {
        if (!topicRepository.existsById(id)) {
            throw new IllegalArgumentException("Tópico no encontrado");
        }
        topicRepository.deleteById(id);
    }

    public List<Response> listResponsesByTopicId(Long topicId) {
        Topic topic = topicRepository.findById(topicId)
                .orElseThrow(() -> new IllegalArgumentException("Tópico no encontrado"));
        return topic.getResponses();
    }


}
