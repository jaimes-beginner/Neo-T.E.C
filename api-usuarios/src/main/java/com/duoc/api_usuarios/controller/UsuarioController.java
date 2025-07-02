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
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.MediaType;
import jakarta.validation.Valid;

/*------------------------------------------*/

@RestController
@RequestMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UsuarioController {

    // Atributos
    @Autowired
    public UsuarioService usuarioServ;

    // Atributos
    @Autowired
    public UsuarioModelAssembler assembler;




    // SWAGGER: documentar cada endpoint
    @Operation(
        summary = "Obtener a un usuario",
        description = "Se obtiene a un solo usuario dependiendo de su ID",
        responses = {
            @ApiResponse(responseCode = "200", description = "Vista generada exitosamente",
                content = @Content(schema = @Schema(implementation = Usuario.class)))
        }
    )

    // OBTENER UNO: devuelve a un usuario por su ID
    @GetMapping("/{idUsuario}")
    public ResponseEntity<EntityModel<Usuario>> obtenerUno(@PathVariable int idUsuario) {

        // HATEOAS: Solo se ajustan las peticiones que solo devuelvan datos (GET)
        Usuario usuario = usuarioServ.obtenerUno(idUsuario);
        if (usuario == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(assembler.toModel(usuario));
    }




    // SWAGGER: documentar cada endpoint
    @Operation(
        summary = "Obtener a todos los usuarios",
        description = "Se obtienen todos los usuarios en general, ya sean usuarios activos o no activos.",
        responses = {
            @ApiResponse(responseCode = "200", description = "Vista generada exitosamente",
                content = @Content(schema = @Schema(implementation = Usuario.class)))
        }
    )

    // OBTENER TODOS: devuelve a todos los usuarios en general, activos o no.
    @GetMapping("/all")
    public CollectionModel<EntityModel<Usuario>> obtenerTodos() {

        // HATEOAS: Solo se ajustan las peticiones que solo devuelvan datos (GET)
        List<EntityModel<Usuario>> usuarios = usuarioServ.obtenerTodos().stream()
            .map(assembler::toModel)
            .collect(Collectors.toList());

        return CollectionModel.of(usuarios,
            linkTo(methodOn(UsuarioController.class).obtenerTodos()).withSelfRel());
    }




    // SWAGGER: documentar cada endpoint
    @Operation(
        summary = "Obtener a todos los usuarios activos",
        description = "Se obtienen todos los usuarios que estén activos, es decir, usuarios que no se hayan eliminado/descartado.",
        responses = {
            @ApiResponse(responseCode = "200", description = "Vista generada exitosamente",
                content = @Content(schema = @Schema(implementation = Usuario.class)))
        }
    )

   // OBTENER ACTIVOS: devuelve a todos los usuarios activos
    @GetMapping("/allActives")
    public CollectionModel<EntityModel<Usuario>> obtenerActivos() {

        // HATEOAS: Solo se ajustan las peticiones que solo devuelvan datos (GET)
        List<EntityModel<Usuario>> usuarios = usuarioServ.obtenerActivos().stream()
            .map(assembler::toModel)
            .collect(Collectors.toList());

        return CollectionModel.of(usuarios,
            linkTo(methodOn(UsuarioController.class).obtenerActivos()).withSelfRel());
    }


    

    // SWAGGER: documentar cada endpoint
    @Operation(
        summary = "Registrar nuevo usuario",
        description = "Registra un nuevo usuario en el sistema, siempre y cuando este con exita previamente.",
        responses = {
            @ApiResponse(responseCode = "200", description = "Usuario registrado exitosamente",
                content = @Content(schema = @Schema(implementation = Usuario.class))),
            @ApiResponse(responseCode = "400", description = "Datos inválidos")
        }
    )

    // REGISTRAR: registra-agrega a un nuevo usuario según los datos (datosCrear)
    @PostMapping("/auth/register")
    public Usuario registrar(@Valid @RequestBody UsuarioCreate datosCrear) {
        return usuarioServ.agregar(datosCrear);
    }




    // SWAGGER: documentar cada endpoint
    @Operation(
        summary = "Eliminar a un usuario",
        description = "Aquí se 'elimina' a un usuario dependiendo de su ID, se cambia su estado de activo (true) a inactivo (false).",
        responses = {
            @ApiResponse(responseCode = "200", description = "Usuario eliminado exitosamente",
                content = @Content(schema = @Schema(implementation = Usuario.class)))
        }
    )

    // ELIMINAR: elimina a un usuario según su ID
    @PutMapping("/remove/{idUsuario}")
    public ResponseEntity<Void> eliminar(@PathVariable int idUsuario) {
        usuarioServ.eliminar(idUsuario);
        return ResponseEntity.noContent().build();
    }




    // SWAGGER: documentar cada endpoint
    @Operation(
        summary = "Modificar a un usuario",
        description = "Aquí se modifica a un usuario dependiendo de su ID, se puede cambiar cualquiera de sus datos que aparecen en el cuerpo.",
        responses = {
            @ApiResponse(responseCode = "200", description = "Usuario modificado exitosamente",
                content = @Content(schema = @Schema(implementation = Usuario.class)))
        }
    )

    // MODIFICAR: modificar a un usuario según su ID, que se encuentra en los datos (datosModificar)
    @PutMapping("/modify")
    public Usuario modificar(@Valid @RequestBody UsuarioUpdate datosModificar) {
        return usuarioServ.modificar(datosModificar);
       
    }

}
