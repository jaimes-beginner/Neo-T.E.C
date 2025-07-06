package com.duoc.api_contenidos.controller;

/*------------------------------------------*/

// Importaciones
import java.util.List;
import java.util.stream.Collectors;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.duoc.api_contenidos.assemblers.EvaluacionModelAssembler;
import com.duoc.api_contenidos.model.entity.Evaluacion;
import com.duoc.api_contenidos.model.request.EvaluacionCreate;
import com.duoc.api_contenidos.service.EvaluacionService;
import jakarta.validation.Valid;

/*------------------------------------------*/

@RestController
@RequestMapping(value = "/evaluations", produces = MediaType.APPLICATION_JSON_VALUE)
public class EvaluacionController {
    
    // Atributos
    @Autowired
    private EvaluacionService evaluacionServ;

    // Atributos
    @Autowired
    private EvaluacionModelAssembler assembler;

    // OBTENER UNO: devuelve una evaluacion por su ID
    @GetMapping("/{idEvaluacion}")
    public ResponseEntity<EntityModel<Evaluacion>> obtenerUno(@PathVariable int idEvaluacion) {
        Evaluacion evaluacion = evaluacionServ.obtenerUno(idEvaluacion);
        if (evaluacion == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(assembler.toModel(evaluacion));
    }
    
    // OBTENER POR CONTENIDOS: devuelve las evaluaciones según el ID del contenido
    @GetMapping("/porContenido/{idContenido}")
    public ResponseEntity<List<Evaluacion>> obtenerPorContenido(@PathVariable int idContenido) {
        List<Evaluacion> evaluaciones = evaluacionServ.obtenerPorContenido(idContenido);
        return ResponseEntity.ok(evaluaciones);
    }

    // OBTENER TODOS: devuelve todas las evaluaciones en general
    @GetMapping("/todos")
    public CollectionModel<EntityModel<Evaluacion>> obtenerTodos() {
        List<EntityModel<Evaluacion>> evaluaciones = evaluacionServ.obtenerTodos().stream()
            .map(assembler::toModel)
            .collect(Collectors.toList());

        return CollectionModel.of(
            evaluaciones,
            linkTo(methodOn(EvaluacionController.class).obtenerTodos()).withSelfRel()
        );
    }

    // AGREGAR: agrega una evaluacion según los datos (datosCrear)
    @PostMapping("/agregar")
    public ResponseEntity<EntityModel<Evaluacion>> agregar(@Valid @RequestBody EvaluacionCreate datosCrear) {
        Evaluacion evaluacion = evaluacionServ.agregar(datosCrear);
        return ResponseEntity.ok(assembler.toModel(evaluacion));
    }

    // ELIMINAR: eliminar una evaluacion según su ID
    @DeleteMapping("/eliminar/{id}")
    public String eliminar(@PathVariable int id) {
        evaluacionServ.eliminar(id);
        return "Pregunta eliminada!";
    }

}
