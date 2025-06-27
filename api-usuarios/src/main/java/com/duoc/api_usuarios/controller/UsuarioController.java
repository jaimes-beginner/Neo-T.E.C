package com.duoc.api_usuarios.controller;

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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.duoc.api_usuarios.assemblers.UsuarioModelAssembler;
import com.duoc.api_usuarios.model.entity.Usuario;
import com.duoc.api_usuarios.model.request.UsuarioCreate;
import com.duoc.api_usuarios.model.request.UsuarioUpdate;
import com.duoc.api_usuarios.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.MediaType;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

/*------------------------------------------*/

@RestController
@RequestMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UsuarioController {

    // Atributos
    @Autowired
    public UsuarioService usuarioServ;
    @Autowired
    public UsuarioModelAssembler assembler;

    // Obtener a un usuario por su id 
    @GetMapping(value = "/{idUsuario}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EntityModel<Usuario>> obtenerUno(@PathVariable int idUsuario) {
        Usuario usuario = usuarioServ.obtenerUno(idUsuario);
        if (usuario == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(assembler.toModel(usuario));
    }

    // Obtener a todos los usuarios, HATEOAS: Devuelve todos los links disponibles para usar 
    @GetMapping("/all")
    public CollectionModel<EntityModel<Usuario>> obtenerTodos() {
        List<EntityModel<Usuario>> usuarios = usuarioServ.obtenerTodos().stream()
            .map(assembler::toModel)
            .collect(Collectors.toList());

        return CollectionModel.of(usuarios,
            linkTo(methodOn(UsuarioController.class).obtenerTodos()).withSelfRel());
    }

   // Obtener a todos los usuarios activos, HATEOAS: Devuelve todos los links disponibles para usar 
    @GetMapping("/allActives")
    public CollectionModel<EntityModel<Usuario>> obtenerActivos() {
        List<EntityModel<Usuario>> usuarios = usuarioServ.obtenerActivos().stream()
            .map(assembler::toModel)
            .collect(Collectors.toList());

        return CollectionModel.of(usuarios,
            linkTo(methodOn(UsuarioController.class).obtenerActivos()).withSelfRel());
    }



    @Operation(summary = "Crear y registrar a un usuario")
    // Permite varias respuestas del endpoint
    @ApiResponses(value = { 
        // Que pasa si la respuestas es existosa
        @ApiResponse(responseCode = "200", description = "El usuario se a creado exitosamente!",
            content = @Content(mediaType = "application/json", examples = @ExampleObject(value = "1"))),
        // Que pasa si la respuestas no es existosa
        @ApiResponse(responseCode = "400", description = "Parámetros inválidos")
    })

    // Registrar un usuario
    @PostMapping(value = "/auth/register", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EntityModel<Usuario>> registrar(@Valid @RequestBody UsuarioCreate datosCrear) {
        Usuario usuario = usuarioServ.agregar(datosCrear);
        return ResponseEntity.ok(assembler.toModel(usuario));
    }


    @Operation(summary = "Eliminar a un usuario por su ID")
    // Permite varias respuestas del endpoint
    @ApiResponses(value = { 
        // Que pasa si la respuestas es existosa
        @ApiResponse(responseCode = "200", description = "El usuario a sido eliminado/deshabilitado exitosamente!",
            content = @Content(mediaType = "text/plain", examples = @ExampleObject(value = "1"))),
        // Que pasa si la respuestas no es existosa
        @ApiResponse(responseCode = "400", description = "Parámetros inválidos")
    })

    // Eliminar a un usuario
    @PutMapping("/remove/{idUsuario}")
    public ResponseEntity<Void> eliminar(@PathVariable int idUsuario) {
        usuarioServ.eliminar(idUsuario);
        return ResponseEntity.noContent().build();
    }


    @Operation(summary = "Modificar a un usuario por su ID")
    // Permite varias respuestas del endpoint
    @ApiResponses(value = { 
        // Que pasa si la respuestas es existosa
        @ApiResponse(responseCode = "200", description = "El usuario a sido modificado/actualizado exitosamente!",
            content = @Content(mediaType = "text/plain", examples = @ExampleObject(value = "1"))),
        // Que pasa si la respuestas no es existosa
        @ApiResponse(responseCode = "400", description = "Parámetros inválidos")
    })

    // Modificar un usuario
    @PutMapping("/modify")
    public String modificar(@Valid @RequestBody UsuarioUpdate datosModificar) {
        usuarioServ.modificar(datosModificar);
        return "Usuario modificado!";
    }

}
