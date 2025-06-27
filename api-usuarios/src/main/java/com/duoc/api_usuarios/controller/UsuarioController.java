package com.duoc.api_usuarios.controller;

/*------------------------------------------*/

// Importaciones
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.duoc.api_usuarios.model.entity.Usuario;
import com.duoc.api_usuarios.model.request.UsuarioCreate;
import com.duoc.api_usuarios.model.request.UsuarioUpdate;
import com.duoc.api_usuarios.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

/*------------------------------------------*/


@RestController
@RequestMapping("/users")
public class UsuarioController {

    // Atributos
    @Autowired
    public UsuarioService usuarioServ;

    // Mostrar todos los usuarios 
    @GetMapping("/all")
    public List<Usuario> obtenerTodos() {
        return usuarioServ.obtenerTodos();
    }

    // Mostrar los usuarios activos
    @GetMapping("/allActives")
    public List<Usuario> obtenerActivos() {
        return usuarioServ.obtenerActivos();
    }


    @Operation(summary = "Crear y registrar a un usuario")
    // Permite varias respuestas del endpoint
    @ApiResponses(value = { 
        // Que pasa si la respuestas es existosa
        @ApiResponse(responseCode = "200", description = "El usuario se a creado exitosamente!",
            content = @Content(mediaType = "text/plain", examples = @ExampleObject(value = "1"))),
        // Que pasa si la respuestas no es existosa
        @ApiResponse(responseCode = "400", description = "Parámetros inválidos")
    })

    // Registrar un usuario
    @PostMapping("/auth/register")
    public Usuario registrar(@Valid @RequestBody UsuarioCreate datosCrear) {
        return usuarioServ.agregar(datosCrear);
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
    @PutMapping("/remove/{id}")
    public String eliminar(@PathVariable int id) {
        usuarioServ.eliminar(id);
        return "Usuario eliminado-desactivado!";
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
    @PutMapping("/modify/{id}")
    public String modificar(@Valid @RequestBody UsuarioUpdate datosModificar) {
        usuarioServ.modificar(datosModificar);
        return "Usuario modificado!";
    }

}
