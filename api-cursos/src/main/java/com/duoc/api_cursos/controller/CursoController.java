package com.duoc.api_cursos.controller;

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
import com.duoc.api_cursos.model.entity.Curso;
import com.duoc.api_cursos.model.request.CursoCreate;
import com.duoc.api_cursos.model.request.CursoUpdate;
import com.duoc.api_cursos.service.CursoService;
import jakarta.validation.Valid;

/*------------------------------------------*/

@RestController
@RequestMapping("/courses")
public class CursoController {
    
    // Atributos
    @Autowired
    private CursoService cursoServ;

    // Obtener todos los cursos
    @GetMapping("/all")
    public List<Curso> obtenerTodos() {
        return cursoServ.obtenerTodos();
    }

    // Obtener todos los cursos activos
    @GetMapping("/allActives")
    public List<Curso> obtenerActivos() {
        return cursoServ.obtenerActivos();
    }

    // Agregar un curso
    @PostMapping("/add")
    public Curso agregar(@Valid @RequestBody CursoCreate datosCrear) {
        return cursoServ.agregar(datosCrear);
    }

    // Eliminar un curso
    @PutMapping("/remove/{id}")
    public String eliminar(@PathVariable int id) {
        cursoServ.eliminar(id);
        return "Curso eliminado!";
    }

    @PutMapping("/{id}")
    public String modificar(@Valid @RequestBody CursoUpdate datosModificar) {
        cursoServ.modificar(datosModificar);
        return "Curso modificado!";
    }

}
