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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.duoc.api_contenidos.assemblers.ContenidoModelAssembler;
import com.duoc.api_contenidos.model.entity.Contenido;
import com.duoc.api_contenidos.model.request.ContenidoCreate;
import com.duoc.api_contenidos.model.request.ContenidoUpdate;
import com.duoc.api_contenidos.service.ContenidoService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

/*------------------------------------------*/

@RestController
@RequestMapping(value = "/contents", produces = MediaType.APPLICATION_JSON_VALUE)
public class ContenidoController {
    
    // Atributos
    @Autowired
    private ContenidoService contenidoServ;

    // Atributos
    @Autowired
    private ContenidoModelAssembler assembler;

    // SWAGGER: documentar cada endpoint
    @Operation(
        summary = "Obtener un contenido",
        description = "Obtiene un contenido por su ID."
    )

    // OBTENER UNO: Obtener un contenido por su
    @GetMapping("/{idContenido}")
    public ResponseEntity<EntityModel<Contenido>> obtenerUno(@PathVariable int idContenido) {
        Contenido contenido = contenidoServ.obtenerUno(idContenido);
        return ResponseEntity.ok(assembler.toModel(contenido));
    }

    // SWAGGER: documentar cada endpoint
    @Operation(
        summary = "Obtener todos los contenidos",
        description = "Obtiene todos los contenidos registrados."
    )

    // OBTENER TODOS: Obtener todos los contenidos
    @GetMapping("/all")
    public CollectionModel<EntityModel<Contenido>> obtenerTodos() {

        // HATEOAS: Solo se ajustan las peticiones que solo devuelvan datos (GET)
        List<EntityModel<Contenido>> contenidos = contenidoServ.obtenerTodos().stream()
            .map(assembler::toModel)
            .collect(Collectors.toList());

        return CollectionModel.of(contenidos,
            linkTo(methodOn(ContenidoController.class).obtenerTodos()).withSelfRel());
    }

    // SWAGGER: documentar cada endpoint
    @Operation(
        summary = "Agregar un contenido",
        description = "Crea un contenido."
    )

    // AGREGAR: Agregar un contenido, ATEOAS: método configurado para las pruebas acá
    @PostMapping("/add")
    public ResponseEntity<EntityModel<Contenido>> agregar(@Valid @RequestBody ContenidoCreate datosCrear) {
        Contenido contenido = contenidoServ.agregar(datosCrear);
        return ResponseEntity.ok(assembler.toModel(contenido));
    }

    // SWAGGER: documentar cada endpoint
    @Operation(
        summary = "Eliminar un contenido",
        description = "Elimina un contenido por su ID."
    )

    // ELIMINAR: Eliminar un contenido
    @DeleteMapping("/remove/{id}")
    public String eliminar(@PathVariable int id) {
        contenidoServ.eliminar(id);
        return "Contenido eliminado!";
    }

    // SWAGGER: documentar cada endpoint
    @Operation(
        summary = "Modificar un contenido",
        description = "Modifica un contendio siempre y cuando este exista."
    )

    // MODIFICAR: Modificar un contenido
    @PutMapping("/{id}")
    public String modificar(@Valid @RequestBody ContenidoUpdate datosModificar) {
        contenidoServ.modificar(datosModificar);
        return "Contenido modificado";
    }

}
