package com.duoc.api_contenidos.service;

/*------------------------------------------*/

// Importaciones
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.duoc.api_contenidos.model.entity.Pregunta;
import com.duoc.api_contenidos.repository.PreguntaRepository;

/*------------------------------------------*/

@Service
public class PreguntaService {
    
    // Autowired
    @Autowired
    private PreguntaRepository preguntaRepo;

    // OBTENER TODOS: Obtener todas las preguntas
    public List<Pregunta> obtenerTodos() {
        return preguntaRepo.findAll();
    }

    // OBTENER UNO: Obtener una pregunta por su id 
    public Pregunta obtenerUno(int id) {
        return preguntaRepo.findById(id).orElse(null);
    }

    // ELIMINAR: Eliminar una pregunta
    public void eliminar(int id) {
        Pregunta pregunta = obtenerUno(id);
        if(pregunta == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        preguntaRepo.deleteById(id);
    }

}
