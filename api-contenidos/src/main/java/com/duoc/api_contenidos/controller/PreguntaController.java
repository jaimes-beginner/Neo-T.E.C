package com.duoc.api_contenidos.controller;

/*------------------------------------------*/

// Importaciones
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.duoc.api_contenidos.model.entity.Pregunta;
import com.duoc.api_contenidos.model.request.PreguntaCreate;
import com.duoc.api_contenidos.model.request.PreguntaUpdate;
import com.duoc.api_contenidos.service.PreguntaService;
import jakarta.validation.Valid;

/*------------------------------------------*/

@RestController
@RequestMapping("/questions")
public class PreguntaController {
    
    // Atributos
    @Autowired
    private PreguntaService preguntaServ;

    // Obtener todos las preguntas
    @GetMapping("/all")
    public List<Pregunta> obtenerTodos() {
        return preguntaServ.obtenerTodos();
    }

    // Obtener todos las preguntas por el id de la evaluación
    @GetMapping("/allByEvaluations/{idEvaluacion}")
    public List<Pregunta> obtenerPorEvaluacion(@PathVariable int idEvaluacion) {
        return preguntaServ.obtenerPorEvaluacion(idEvaluacion);
    }

    // Obtener una pregunta por su id 
    @GetMapping("/{id}")
    public Pregunta obtenerUno(@PathVariable int id) {
        return preguntaServ.obtenerUno(id);
    }

    // Agregar una pregunta
    @PostMapping("/add")
    public Pregunta agregar(@Valid @RequestBody PreguntaCreate datosCrear) {
        return preguntaServ.agregar(datosCrear);
    }

    // Eliminar una pregunta
    @DeleteMapping("/remove/{id}")
    public String eliminar(@PathVariable int id) {
        preguntaServ.eliminar(id);
        return "Pregunta eliminada!";
    }

    // Modificar una pregunta
    @PutMapping("/{id}")
    public String modificar(@Valid @RequestBody PreguntaUpdate datosModificar) {
        preguntaServ.modificar(datosModificar);
        return "Pregunta modificada";
    }

}
