package com.duoc.api_inscripciones.controller;

/*------------------------------------------*/

// Importaciones
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.duoc.api_inscripciones.assemblers.ProgresoModelAssembler;
import com.duoc.api_inscripciones.model.entity.Progreso;
import com.duoc.api_inscripciones.model.request.ProgresoUpdate;
import com.duoc.api_inscripciones.service.ProgresoService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;

/*------------------------------------------*/

@RestController
@RequestMapping("/progress")
public class ProgresoController {

    // Atributos
    @Autowired
    private ProgresoService progresoServ;

    // Atributos
    @Autowired
    private ProgresoModelAssembler assembler;

    // OBTENER UNO: devuelve a un usuario por su ID
    @GetMapping("/{idUsuario}")
    public ResponseEntity<EntityModel<Progreso>> obtenerUno(@PathVariable int idUsuario) {
        
        // HATEOAS: Solo se ajustan las peticiones que solo devuelvan datos (GET)
        Progreso progreso = progresoServ.obtenerUno(idUsuario);
        if (progreso == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(assembler.toModel(progreso));
    }

    // OBTENER SEGUN DATOS: devuelve el progreso de un usuario y su respectivo curso
    @GetMapping("/progress/user/{idUsuario}/course/{idCurso}")
    public ResponseEntity<EntityModel<Progreso>> obtenerSegunDatos(@PathVariable int idUsuario, int idCurso) {
        
        // HATEOAS: Solo se ajustan las peticiones que solo devuelvan datos (GET)
        Progreso progreso = progresoServ.obtenerSegunDatos(idUsuario, idCurso);
        if (progreso == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(assembler.toModel(progreso));
    }

    // ACTUALIZAR PROGRESO: modifica-actualiza el progreso de un usuario
    @PutMapping("/updateProgress")
    public ResponseEntity<EntityModel<Progreso>> actualizarProgreso(@Valid @RequestBody ProgresoUpdate datosModificar) {
        Progreso nuevoProgreso = progresoServ.actualizarProgreso(datosModificar);
        return ResponseEntity.ok(assembler.toModel(nuevoProgreso));
    }

}
