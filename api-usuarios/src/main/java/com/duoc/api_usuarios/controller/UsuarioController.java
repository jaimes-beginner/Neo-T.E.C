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

    // Obtener un usuario por su id 
    @GetMapping("/{id}")
    public Usuario obtenerUno(@PathVariable int id) {
        return usuarioServ.obtenerUno(id);
    }

    // Registrar un usuario
    @PostMapping("/auth/register")
    public Usuario registrar(@Valid @RequestBody UsuarioCreate datosCrear) {
        return usuarioServ.agregar(datosCrear);
    }

    // Eliminar a un usuario
    @PutMapping("/remove/{id}")
    public String eliminar(@PathVariable int id) {
        usuarioServ.eliminar(id);
        return "Usuario eliminado-desactivado!";
    }

    // Modificar un usuario
    @PutMapping("/{id}")
    public String modificar(@Valid @RequestBody UsuarioUpdate datosModificar) {
        usuarioServ.modificar(datosModificar);
        return "Usuario modificado!";
    }

}
