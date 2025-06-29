package com.duoc.api_cursos.controller;

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
import com.duoc.api_cursos.assemblers.CursoModelAssembler;
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

    // Atributos
    @Autowired 
    private CursoModelAssembler assembler;



    // Obtener curso con las modificaciones para el Assembler
    @GetMapping("/{idCurso}")
    public ResponseEntity<EntityModel<Curso>> obtenerUno(@PathVariable int idCurso) {
        Curso curso = cursoServ.obtenerUno(idCurso);
        if (curso == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(assembler.toModel(curso));
    }
  


    // Obtener todos los cursos con las modificaciones para el Assembler
    @GetMapping("/all")
    public CollectionModel<EntityModel<Curso>> obtenerTodos() {
        List<EntityModel<Curso>> cursos = cursoServ.obtenerTodos().stream()
            .map(assembler::toModel)
            .collect(Collectors.toList());

        return CollectionModel.of(cursos,
            linkTo(methodOn(CursoController.class).obtenerTodos()).withSelfRel());
    }



    // Agregar un curso
    @PostMapping("/add")
    public ResponseEntity<EntityModel<Curso>> agregar(@Valid @RequestBody CursoCreate datosCrear) {
        Curso curso = cursoServ.agregar(datosCrear);
        return ResponseEntity.ok(assembler.toModel(curso));
    }



    // Eliminar un curso dependiendo de su ID
    @PutMapping("/remove/{id}")
    public String eliminar(@PathVariable int id) {
        cursoServ.eliminar(id);
        return "Curso eliminado!";
    }



    // Modificar un curso dependiendo de su ID
    @PutMapping("/modify/{id}")
    public String modificar(@Valid @RequestBody CursoUpdate datosModificar) {
        cursoServ.modificar(datosModificar);
        return "Curso modificado!";
    }

}
