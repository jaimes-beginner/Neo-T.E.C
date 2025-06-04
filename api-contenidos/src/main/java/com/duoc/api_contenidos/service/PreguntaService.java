package com.duoc.api_contenidos.service;

/*------------------------------------------*/

// Importaciones
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.duoc.api_contenidos.model.entity.Pregunta;
import com.duoc.api_contenidos.model.request.PreguntaCreate;
import com.duoc.api_contenidos.model.request.PreguntaUpdate;
import com.duoc.api_contenidos.repository.PreguntaRepository;

/*------------------------------------------*/

@Service
public class PreguntaService {
    
    // Autowired
    @Autowired
    private PreguntaRepository preguntaRepo;

    // Obtener todas las preguntas
    public List<Pregunta> obtenerTodos() {
        return preguntaRepo.findAll();
    }

    // Obtener una pregunta por su id 
    public Pregunta obtenerUno(int id) {
        return preguntaRepo.findById(id).orElse(null);
    }

    // Agregar una pregunta
    public Pregunta agregar(PreguntaCreate datosCrear) {
        Pregunta pregunta = new Pregunta();
        try {
            pregunta.setIdEvaluacionPregunta(datosCrear.getIdEvaluacionPregunta());
            pregunta.setEnunciadoPregunta(datosCrear.getEnunciadoPregunta());
            pregunta.setOpcionesPregunta(datosCrear.getOpcionesPregunta());
            pregunta.setRespCorrectaPregunta(datosCrear.getRespCorrectaPregunta());
            return preguntaRepo.save(pregunta);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    // Eliminar una pregunta
    public void eliminar(int id) {
        Pregunta pregunta = obtenerUno(id);
        if(pregunta == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        preguntaRepo.deleteById(id);
    }

    // Modificar una pregunta
    public Pregunta modificar(PreguntaUpdate datosModificar) {
        Pregunta pregunta = obtenerUno(datosModificar.getIdPregunta());
        if(pregunta == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        if(datosModificar.getEnunciadoPregunta() != null) {
            pregunta.setEnunciadoPregunta(datosModificar.getEnunciadoPregunta());
        }
        if(datosModificar.getOpcionesPregunta() != null) {
            pregunta.setOpcionesPregunta(datosModificar.getOpcionesPregunta());
        }
        if(datosModificar.getRespCorrectaPregunta() != null) {
            pregunta.setRespCorrectaPregunta(datosModificar.getRespCorrectaPregunta());
        }
        if(datosModificar.getIdEvaluacionPregunta() != null) {
            pregunta.setIdEvaluacionPregunta(datosModificar.getIdEvaluacionPregunta());
        }
        return preguntaRepo.save(pregunta);
    }

}
