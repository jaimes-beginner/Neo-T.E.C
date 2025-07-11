package com.duoc.api_contenidos.service;

/*------------------------------------------*/

// Importaciones
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.duoc.api_contenidos.model.entity.Contenido;
import com.duoc.api_contenidos.model.entity.Evaluacion;
import com.duoc.api_contenidos.model.entity.Pregunta;
import com.duoc.api_contenidos.model.request.EvaluacionCreate;
import com.duoc.api_contenidos.repository.ContenidoRepository;
import com.duoc.api_contenidos.repository.EvaluacionRepository;

/*------------------------------------------*/

@Service
public class EvaluacionService {
    
    // Atributos
    @Autowired
    private EvaluacionRepository evaluacionRepo;

    // Atributos
    @Autowired
    private ContenidoRepository contenidoRepo;

    // OBTENER TODOS: devuelve todas las evaluaciones
    public List<Evaluacion> obtenerTodos() {
        return evaluacionRepo.findAll();
    }

    // OBTENER UNO: devuelve una evaluacion por su ID
    public Evaluacion obtenerUno(int id) {
        return evaluacionRepo.findById(id).orElse(null);
    }

    // OBTENER POR CONTENIDO: obtiene todas las evaluaciones por el ID del contenido
    public List<Evaluacion> obtenerPorContenido(int idContenido) {
        return evaluacionRepo.findByContenido_IdContenido(idContenido);
    }

    // AGREGAR: crear-agregar una evaluacion con sus respectivas pregúntas
    public Evaluacion agregar(EvaluacionCreate datosCrear) {
        Evaluacion evaluacion = new Evaluacion();
        try {

            // Setteando los datos de la evaluación
            evaluacion.setCreacionEvaluacion(new Date());
            evaluacion.setTipoEvaluacion(datosCrear.getTipoEvaluacion());

            // Buscar el contenido por ID
            Contenido contenido = contenidoRepo.findById(datosCrear.getIdContenido())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Contenido no encontrado"));

            // Asociarlo a la evaluación
            evaluacion.setContenido(contenido);

            // Setteando los datos de la/s pregunta/s, que están como un atributo en EvaluacionCreate
            List<Pregunta> listaPreguntas = datosCrear.getListaPreguntas().stream().map(pdto -> {
                Pregunta pregunta = new Pregunta();
                pregunta.setEnunciadoPregunta(pdto.getEnunciadoPregunta());
                pregunta.setOpcionesPregunta(pdto.getOpcionesPregunta());
                pregunta.setOpcionesCorrectaPregunta(pdto.getRespCorrectaPregunta());
                pregunta.setEvaluacionRelacion(evaluacion);
                return pregunta;
            }).toList();

            // Asignando la lista de preguntas a la evaluacion
            evaluacion.setListaPreguntas(listaPreguntas);

            // Guardando los cambios de la evaluación
            return evaluacionRepo.save(evaluacion);

        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    // ELIMINAR: elimina una evaluacion por su ID
    public void eliminar(int id) {
        Evaluacion evaluacion = obtenerUno(id);
        if(evaluacion == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        evaluacionRepo.deleteById(id);
    }

}

