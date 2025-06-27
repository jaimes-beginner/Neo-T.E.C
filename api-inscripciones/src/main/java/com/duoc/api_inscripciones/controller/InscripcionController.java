package com.duoc.api_inscripciones.controller;

/*------------------------------------------*/

// Importaciones
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.duoc.api_inscripciones.model.entity.Inscripcion;
import com.duoc.api_inscripciones.service.InscripcionService;

/*------------------------------------------*/

@RestController
@RequestMapping("/inscriptions")
public class InscripcionController {
    
    // Atributos
    @Autowired
    private InscripcionService inscripcionServ;

    // Obtener todas las inscripciones
    @GetMapping("/all")
    public List<Inscripcion> obtenerTodos() {
        return inscripcionServ.obtenerTodos();
    }

    // Obtener inscripciones de un usuario por su id 
    @GetMapping("/{idUsuario}")
    public List<Inscripcion> obtenerPorUsuario(@PathVariable int idUsuario) {
        return inscripcionServ.obtenerPorUsuario(idUsuario);
    }

    // Inscribir un usuario a un curso
    @PostMapping("/add/{idUsuario}/{idCurso}")
    public Inscripcion agregar(@PathVariable int idUsuario, int idCurso) {
        return inscripcionServ.agregar(idUsuario, idCurso);
    }

}
