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
import com.duoc.api_contenidos.model.entity.Contenido;
import com.duoc.api_contenidos.model.request.ContenidoCreate;
import com.duoc.api_contenidos.model.request.ContenidoUpdate;
import com.duoc.api_contenidos.model.request.CursoDTO;
import com.duoc.api_contenidos.service.ContenidoService;
import jakarta.validation.Valid;

/*------------------------------------------*/

@RestController
@RequestMapping("/contents")
public class ContenidoController {
    
    // Atributos
    @Autowired
    private ContenidoService contenidoServ;

    // Obtener todos los contenidos
    @GetMapping("/all")
    public List<Contenido> obtenerTodos() {
        return contenidoServ.obtenerTodos();
    }

    // Obtener todos los contenidos por el id del curso
    @GetMapping("/allByCourse/{idCurso}")
    public CursoDTO obtenerPorCurso(@PathVariable int idContenido) {
        return contenidoServ.obtenerPorCurso(idContenido);
    }

    // Obtener un contenido por su id 
    @GetMapping("/{id}")
    public Contenido obtenerUno(@PathVariable int id) {
        return contenidoServ.obtenerUno(id);
    }

    // Agregar un contenido
    @PostMapping("/add")
    public Contenido agregar(@Valid @RequestBody ContenidoCreate datosCrear) {
        return contenidoServ.agregar(datosCrear);
    }

    // Eliminar un contenido
    @DeleteMapping("/remove/{id}")
    public String eliminar(@PathVariable int id) {
        contenidoServ.eliminar(id);
        return "Pregunta eliminada!";
    }

    // Modificar un contenido
    @PutMapping("/{id}")
    public String modificar(@Valid @RequestBody ContenidoUpdate datosModificar) {
        contenidoServ.modificar(datosModificar);
        return "Pregunta modificada";
    }

}
