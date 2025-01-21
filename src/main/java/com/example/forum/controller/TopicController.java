package com.example.forum.controller;

import com.example.forum.dto.ResponseDTO;
import com.example.forum.dto.ResponseRequestDTO;
import com.example.forum.dto.TopicRequestDTO;
import com.example.forum.dto.TopicResponseDTO;
import com.example.forum.model.Course;
import com.example.forum.model.Response;
import com.example.forum.model.Topic;
import com.example.forum.model.User;
import com.example.forum.service.TopicService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

@RestController
@RequestMapping("/topics")
public class TopicController {

    private final TopicService topicService;

    public TopicController(TopicService topicService) {
        this.topicService = topicService;
    }

    /**
     * Endpoint para registrar un nuevo Topic.
     *
     * @param requestDTO Datos del tema en formato JSON.
     * @return Respuesta con el tema registrado.
     */
    @PostMapping
    public ResponseEntity<TopicResponseDTO> registerTopic(@RequestBody @Valid TopicRequestDTO requestDTO) {
        TopicResponseDTO savedTopic = topicService.registerTopic(requestDTO);
        return ResponseEntity.ok(savedTopic);
    }

    /**
     * Endpoint para listar todos los temas.
     *
     * @param pageable Parámetros de paginación y ordenamiento.
     * @return Página de los temas en formato DTO.
     */
    @GetMapping
    public ResponseEntity<Page<TopicResponseDTO>> listAllTopics(Pageable pageable) {
        Page<TopicResponseDTO> topics = topicService.listAllTopics(pageable);
        return ResponseEntity.ok(topics);
    }

    /**
     * Endpoint para filtrar tópicos por curso y año.
     *
     * @param courseName Nombre del curso.
     * @param year Año de creación.
     * @return Lista de tópicos filtrados en formato DTO.
     */
    @GetMapping("/filter")
    public ResponseEntity<List<TopicResponseDTO>> filterTopics(
            @RequestParam(required = false, defaultValue = "") String courseName,
            @RequestParam(required = false, defaultValue = "2025") int year
    ) {
        List<TopicResponseDTO> topics = topicService.filterTopicsByCourseAndYear(courseName, year);
        return ResponseEntity.ok(topics);
    }

    /**
     * Endpoint para filtrar tópicos por estado.
     *
     * @param status Estado del tópico.
     * @return Lista de tópicos filtrados en formato DTO.
     */
    @GetMapping("/filter-by-status")
    public ResponseEntity<List<TopicResponseDTO>> filterByStatus(@RequestParam String status) {
        List<TopicResponseDTO> topics = topicService.filterTopicsByStatus(status);
        return ResponseEntity.ok(topics);
    }

    /**
     * Endpoint para actualizar un tópico.
     *
     * @param id ID del tópico.
     * @param requestDTO Datos del tópico en formato JSON.
     * @return Tópico actualizado en formato DTO.
     */
    @PutMapping("/{id}")
    public ResponseEntity<TopicResponseDTO> updateTopic(
            @PathVariable Long id,
            @RequestBody @Valid TopicRequestDTO requestDTO
    ) {
        TopicResponseDTO updatedTopic = topicService.updateTopic(id, requestDTO);
        return ResponseEntity.ok(updatedTopic);
    }

    /**
     * Endpoint para eliminar un tópico.
     *
     * @param id ID del tópico.
     * @return Respuesta vacía con código HTTP 204.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTopic(@PathVariable Long id) {
        topicService.deleteTopic(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Endpoint para añadir una respuesta a un tópico.
     *
     * @param id ID del tópico.
     * @param requestDTO Datos de la respuesta en formato JSON.
     * @return Respuesta añadida en formato JSON.
     */
    @PostMapping("/{id}/responses")
    public ResponseEntity<ResponseDTO> addResponse(
            @PathVariable Long id,
            @RequestBody @Valid ResponseRequestDTO requestDTO
    ) {
        Response response = topicService.addResponse(id, requestDTO);
        return ResponseEntity.ok(new ResponseDTO(response));
    }

    @GetMapping("/{id}/responses")
    public ResponseEntity<List<ResponseDTO>> listResponses(@PathVariable Long id) {
        List<Response> responses = topicService.listResponsesByTopicId(id);
        List<ResponseDTO> responseDTOs = responses.stream()
                .map(ResponseDTO::new)
                .toList();
        return ResponseEntity.ok(responseDTOs);
    }


}


