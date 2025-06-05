package com.duoc.api_inscripciones.controller;

/*------------------------------------------*/

// Importaciones
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.duoc.api_inscripciones.model.entity.Progreso;
import com.duoc.api_inscripciones.service.ProgresoService;

/*------------------------------------------*/

@RestController
@RequestMapping("/progress")
public class ProgresoController {

    // Atributos
    @Autowired
    private ProgresoService progresoServ;

    // Obtener el progreso de un usuario
    @GetMapping("/progress/user/{idUsuario}/course/{idCurso}")
    public Progreso obtenerProgresoUsuario(@PathVariable int idUsuario, int idCurso) {
        return progresoServ.obtener(idUsuario, idCurso);
    }

    // Actualizar el progreso de un usuario
    @PutMapping("/updateProgress")
    public String actualizarProgreso(int idUsuario, int idCurso, double porcentajeProgresoNuevo) {
        progresoServ.actualizarProgreso(idUsuario, idCurso, porcentajeProgresoNuevo);
        return "Progreso actualizado!";
    }

}
