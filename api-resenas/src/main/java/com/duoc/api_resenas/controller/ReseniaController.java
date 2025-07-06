package com.duoc.api_resenas.controller;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.duoc.api_resenas.assemblers.ReseniaModelAssembler;
import com.duoc.api_resenas.models.entities.Resenia;
import com.duoc.api_resenas.models.request.ReseniaCreate;
import com.duoc.api_resenas.service.ReseniaService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/*------------------------------------------*/

@RestController
@RequestMapping("/resenia")
public class ReseniaController {

    // Atributos
    @Autowired
    private ReseniaService reseniaServ;

    // Atributos
    @Autowired
    private ReseniaModelAssembler assembler;

    // OBTENER UNO: Obtener una resenia por su ID, con sus modificaciones para el HATEOAS
    @GetMapping("/{idResena}")
    public ResponseEntity<EntityModel<Resenia>> obtenerUno(@PathVariable int idResena) {
        Resenia resenia = reseniaServ.obtenerUno(idResena);
        if (resenia == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(assembler.toModel(resenia));
    }

    // OBTENER TODOS: Obtener todos las reseñas
    @GetMapping("/all")
    public CollectionModel<EntityModel<Resenia>> obtenerTodos() {
        List<EntityModel<Resenia>> resenia = reseniaServ.obtenerTodos().stream()
            .map(assembler::toModel)
            .collect(Collectors.toList());
        
        return CollectionModel.of(resenia,
            linkTo(methodOn(ReseniaController.class).obtenerTodos()).withSelfRel());
    }

    // CREAR RESENIA: Crear reseña
    @PostMapping("/add")
    public Resenia crearResenia(@RequestBody @Valid ReseniaCreate reCreate) {
        return reseniaServ.dejarResena(reCreate);
    }

}
