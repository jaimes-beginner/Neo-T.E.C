package com.duoc.api_inscripciones.service;

import java.util.Date;

/*------------------------------------------*/

// Importaciones
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.duoc.api_inscripciones.model.entity.Progreso;
import com.duoc.api_inscripciones.repository.ProgresoRepository;

/*------------------------------------------*/

@Service
public class ProgresoService {
    
    // Atributos
    @Autowired
    private ProgresoRepository progresoRepo;

    // Obtener el progreso de un alumno
    public Progreso obtener(int idUsuario, int idCurso) {
        return progresoRepo.findByIdUsuarioIdCuros(idUsuario, idCurso).orElse(null);
    }

    // Actualizar el progreso de un usuario
    public void actualizarProgreso(int idUsuario, int idCurso, double porcentajeProgresoNuevo) {
        Progreso progreso = progresoRepo.findByIdUsuarioIdCuros(idUsuario, idCurso)
        // Que hacer en caso de que no exista
        .orElseGet(() -> {
                Progreso nuevoProgreso = new Progreso();
                nuevoProgreso.setIdUsuarioProgreso(idUsuario);
                nuevoProgreso.setIdCursoProgreso(idCurso);
                return nuevoProgreso;
        });    
        progreso.setPorcentajeProgreso(porcentajeProgresoNuevo);
        progreso.setUltimaActividadProgreso(new Date());
        progresoRepo.save(progreso);
    }

}
