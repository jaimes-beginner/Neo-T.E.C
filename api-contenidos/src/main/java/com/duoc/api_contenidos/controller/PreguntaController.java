package com.duoc.api_contenidos.controller;

/*------------------------------------------*/

// Importaciones
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.duoc.api_contenidos.model.entity.Pregunta;
import com.duoc.api_contenidos.service.PreguntaService;

/*------------------------------------------*/

@RestController
@RequestMapping(value = "/questions", produces = MediaType.APPLICATION_JSON_VALUE)
public class PreguntaController {

    // Atributos
    @Autowired
    private PreguntaService preguntaServ;

    // OBTENER TODOS: Obtener todos las preguntas
    @GetMapping("/all")
    public List<Pregunta> obtenerTodos() {
        return preguntaServ.obtenerTodos();
    }

    // ELIMINAR: Eliminar una pregunta
    @DeleteMapping("/remove/{id}")
    public String eliminar(@PathVariable int id) {
        preguntaServ.eliminar(id);
        return "Pregunta eliminada!";
    }

}
