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


    
    /* 
    @Operation(summary = "Crear y registrar a un usuario")
    // Permite varias respuestas del endpoint
    @ApiResponses(value = { 
        // Que pasa si la respuestas es existosa
        @ApiResponse(responseCode = "200", description = "El usuario se a creado exitosamente!",
            content = @Content(mediaType = "application/json", examples = @ExampleObject(value = "1"))),
        // Que pasa si la respuestas no es existosa
        @ApiResponse(responseCode = "400", description = "Parámetros inválidos")
    })
     */



    // REGISTRAR: registra-agrega a un nuevo usuario según los datos (datosCrear)
    @PostMapping("/auth/register")
    public Usuario registrar(@Valid @RequestBody UsuarioCreate datosCrear) {
        return usuarioServ.agregar(datosCrear);
    }



    // ELIMINAR: elimina a un usuario según su ID
    @PutMapping("/remove/{idUsuario}")
    public ResponseEntity<Void> eliminar(@PathVariable int idUsuario) {
        usuarioServ.eliminar(idUsuario);
        return ResponseEntity.noContent().build();
    }



    // MODIFICAR: modificar a un usuario según su ID, que se encuentra en los datos (datosModificar)
    @PutMapping("/modify")
    public Usuario modificar(@Valid @RequestBody UsuarioUpdate datosModificar) {
        return usuarioServ.modificar(datosModificar);
       
    }

}
