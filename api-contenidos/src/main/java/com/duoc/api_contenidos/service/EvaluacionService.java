package com.duoc.api_contenidos.service;

/*------------------------------------------*/

// Importaciones
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.duoc.api_contenidos.model.entity.Evaluacion;
import com.duoc.api_contenidos.model.entity.Pregunta;
import com.duoc.api_contenidos.model.request.EvaluacionCreate;
import com.duoc.api_contenidos.model.request.EvaluacionUpdate;

import com.duoc.api_contenidos.repository.EvaluacionRepository;

/*------------------------------------------*/

@Service
public class EvaluacionService {
    
    // Atributos
    @Autowired
    private EvaluacionRepository evaluacionRepo;

    // Obtener todas las evaluaciones
    public List<Evaluacion> obtenerTodos() {
        return evaluacionRepo.findAll();
    }

    // Obtener todas las evaluaciones por el id del contenido
    public List<Evaluacion> obtenerPorContenido(int idContenido) {
        return evaluacionRepo.findAllByIdContenido(idContenido);
    }

    // Obtener una evaluacion por su id 
    public Evaluacion obtenerUno(int id) {
        return evaluacionRepo.findById(id).orElse(null);
    }

    // Agregar una evaluacion con sus respectivas pregúntas
    public Evaluacion agregar(EvaluacionCreate datosCrear) {
        Evaluacion evaluacion = new Evaluacion();
        try {

            // Setteando los datos de la evaluación
            evaluacion.setCreacionEvaluacion(new Date());
            evaluacion.setTipoEvaluacion(datosCrear.getTipoEvaluacion());

            // Setteando los datos de la pregúnta/s, que están como un atributo en EvaluacionCreate
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
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    // Eliminar una evaluacion
    public void eliminar(int id) {
        Evaluacion evaluacion = obtenerUno(id);
        if(evaluacion == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        evaluacionRepo.deleteById(id);
    }

    // Modificar una evaluacion
    public Evaluacion modificar(EvaluacionUpdate datosModificar) {
        Evaluacion evaluacion = obtenerUno(datosModificar.getIdEvaluacion());
        if(evaluacion == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        if(datosModificar.getTipoEvaluacion() != null) {
            evaluacion.setTipoEvaluacion(datosModificar.getTipoEvaluacion());
        }
        return evaluacionRepo.save(evaluacion);
    }

}

