package com.duoc.api_inscripciones.controller;

/*------------------------------------------*/

// Importaciones
import java.util.List;
import java.util.stream.Collectors;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.duoc.api_inscripciones.assemblers.InscripcionModelAssembler;
import com.duoc.api_inscripciones.model.entity.Inscripcion;
import com.duoc.api_inscripciones.model.request.InscripcionCreate;
import com.duoc.api_inscripciones.model.request.PagoRequestDTO;
import com.duoc.api_inscripciones.service.InscripcionService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

/*------------------------------------------*/

@RestController
@RequestMapping("/inscriptions")
public class InscripcionController {
    
    // Atributos
    @Autowired
    private InscripcionService inscripcionServ;

    // Atributos
    @Autowired
    private InscripcionModelAssembler assembler;

    // SWAGGER: documentar cada endpoint
    @Operation(
        summary = "Obtener una inscripción",
        description = "Obtiene una inscripción por su ID."
    )

    // OBTENER UNO: devuelve una inscripcion según su ID
    @GetMapping("/{idInscripcion}")
    public ResponseEntity<EntityModel<Inscripcion>> obtenerUno(@PathVariable int idInscripcion) {
        
        // HATEOAS: Solo se ajustan las peticiones que solo devuelvan datos (GET)
        Inscripcion inscripcion = inscripcionServ.obtenerUno(idInscripcion);
        if (inscripcion == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(assembler.toModel(inscripcion));
    }

    // SWAGGER: documentar cada endpoint
    @Operation(
        summary = "Obtener por usuario",
        description = "Obtiene todas las inscripciones por el ID del usuario."
    )

    // OBTENER POR USUARIO: devuelve todas las inscripciones de un usuario
    @GetMapping("/allForUser/{idUsuario}")
    public CollectionModel<EntityModel<Inscripcion>> obtenerPorUsuario(@PathVariable int idUsuario) {
        
        // HATEOAS: Solo se ajustan las peticiones que solo devuelvan datos (GET)
        List<EntityModel<Inscripcion>> inscripciones = inscripcionServ.obtenerPorUsuario(idUsuario).stream()
            .map(assembler::toModel)
            .collect(Collectors.toList());

        return CollectionModel.of(inscripciones,
            linkTo(methodOn(InscripcionController.class).obtenerPorUsuario(idUsuario)).withSelfRel());
    }

    // SWAGGER: documentar cada endpoint
    @Operation(
        summary = "Obtener por curso",
        description = "Obtiene las inscripciones por el ID del curso."
    )

    // OBTENER POR CURSO: devuelve todas las inscripciones de un curso
    @GetMapping("/allForCourse/{idCurso}")
    public CollectionModel<EntityModel<Inscripcion>> obtenerPorCurso(@PathVariable int idCurso) {
        
        // HATEOAS: Solo se ajustan las peticiones que solo devuelvan datos (GET)
        List<EntityModel<Inscripcion>> inscripciones = inscripcionServ.obtenerPorCurso(idCurso).stream()
            .map(assembler::toModel)
            .collect(Collectors.toList());

        return CollectionModel.of(inscripciones,
            linkTo(methodOn(InscripcionController.class).obtenerPorCurso(idCurso)).withSelfRel());
    }

    // SWAGGER: documentar cada endpoint
    @Operation(
        summary = "Obtener todas las inscripciones",
        description = "Obtener todas las inscripciones registradas."
    )

    // OBTENER TODOS: devuelve todos las inscripciones en general
    @GetMapping("/all")
    public CollectionModel<EntityModel<Inscripcion>> obtenerTodos() {
        
        // HATEOAS: Solo se ajustan las peticiones que solo devuelvan datos (GET)
        List<EntityModel<Inscripcion>> inscripciones = inscripcionServ.obtenerTodos().stream()
            .map(assembler::toModel)
            .collect(Collectors.toList());

        return CollectionModel.of(inscripciones,
            linkTo(methodOn(InscripcionController.class).obtenerTodos()).withSelfRel());
    }

    // SWAGGER: documentar cada endpoint
    @Operation(
        summary = "Agregar una inscripcion",
        description = "Agrega una inscripcion, además de realizar el pago del mismo automáticamente siempre y cuando el usuario y el curso exista."
    )

    // AGREGAR: crea-agrega una inscripcion según los datos (datosCreate)
    @PostMapping("/add")
    public ResponseEntity<EntityModel<Inscripcion>> agregar(@Valid @RequestBody InscripcionCreate datosCreate) {
        Inscripcion inscripcion = inscripcionServ.agregar(datosCreate);
        return ResponseEntity.ok(assembler.toModel(inscripcion));
    }

        /*
        // AGREGAR: crea-agrega una inscripcion según los datos (datosCreate)
        @PostMapping("/add")
        public Inscripcion agregar(@Valid @RequestBody InscripcionCreate datosCreate) {
            return inscripcionServ.agregar(datosCreate);
        }
        */

}
