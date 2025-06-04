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

    // Obtener una evaluacion por su id 
    public Evaluacion obtenerUno(int id) {
        return evaluacionRepo.findById(id).orElse(null);
    }

    // Agregar una evaluacion
    public Evaluacion agregar(EvaluacionCreate datosCrear) {
        Evaluacion evaluacion = new Evaluacion();
        try {
            evaluacion.setCreacionEvaluacion(new Date());
            evaluacion.setTipoEvaluacion(datosCrear.getTipoEvaluacion());
            evaluacion.setIdCursoEvaluacion(datosCrear.getIdCursoEvaluacion());
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
        if(datosModificar.getIdCursoEvaluacion() != null) {
            evaluacion.setIdCursoEvaluacion(datosModificar.getIdCursoEvaluacion());
        }
        if(datosModificar.getTipoEvaluacion() != null) {
            evaluacion.setTipoEvaluacion(datosModificar.getTipoEvaluacion());
        }
        return evaluacionRepo.save(evaluacion);
    }

}

