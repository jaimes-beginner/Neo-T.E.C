package com.duoc.api_contenidos.controller;

/*------------------------------------------*/

// Importaciones
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.duoc.api_contenidos.model.entity.Evaluacion;
import com.duoc.api_contenidos.model.request.EvaluacionCreate;
import com.duoc.api_contenidos.service.EvaluacionService;
import jakarta.validation.Valid;

/*------------------------------------------*/

@RestController
@RequestMapping("/evaluations")
public class EvaluacionController {
    
    // Atributos
    @Autowired
    private EvaluacionService evaluacionServ;

    // Obtener todas las evaluaciones
    @GetMapping("/all")
    public List<Evaluacion> obtenerTodos() {
        return evaluacionServ.obtenerTodos();
    }

    // Obtener todas las evaluaciones por el id del contenido
    @GetMapping("/allByContent/{idContent}")
    public List<Evaluacion> obtenerPorContenido(int idContent) {
        return evaluacionServ.obtenerPorContenido(idContent);
    }

    // Obtener una evaluacion por su id 
    @GetMapping("/{id}")
    public Evaluacion obtenerUno(@PathVariable int id) {
        return evaluacionServ.obtenerUno(id);
    }

    // Agregar una evaluacion
    @PostMapping("/add")
    public Evaluacion agregar(@Valid @RequestBody EvaluacionCreate datosCrear) {
        return evaluacionServ.agregar(datosCrear);
    }

    // Eliminar una evaluacion
    @DeleteMapping("/remove/{id}")
    public String eliminar(@PathVariable int id) {
        evaluacionServ.eliminar(id);
        return "Pregunta eliminada!";
    }

}
