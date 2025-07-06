package com.duoc.api_inscripciones.service;

/*------------------------------------------*/

// Importaciones
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.duoc.api_inscripciones.model.entity.Progreso;
import com.duoc.api_inscripciones.model.request.ProgresoUpdate;
import com.duoc.api_inscripciones.repository.ProgresoRepository;

/*------------------------------------------*/

@Service
public class ProgresoService {
    
    // Atributos
    @Autowired
    private ProgresoRepository progresoRepo;

    // OBTENER UNO: Obtener un progreso según su ID
    public Progreso obtenerUno(int idProgreso) {
        return progresoRepo.findById(idProgreso).orElse(null);
    }

    // OBTENER SEGUN DATOS: Obtener el progreso de un alumno
    public Progreso obtenerSegunDatos(int idUsuario, int idCurso) {
        return progresoRepo.findByIdUsuarioProgresoAndIdCursoProgreso(idUsuario, idCurso).orElse(null);
    }

    // ACTUALIZAR PROGRESO: Actualizar el progreso de un usuario
    public Progreso actualizarProgreso(ProgresoUpdate datosModificar) {
        Progreso progreso = progresoRepo.findByIdUsuarioProgresoAndIdCursoProgreso(datosModificar.getIdUsuarioProgreso(), datosModificar.getIdCursoProgreso())
        
        // Si no hay un progreso, hacer lo siguiente:
        .orElseGet(() -> {
                Progreso nuevoProgreso = new Progreso();
                nuevoProgreso.setIdUsuarioProgreso(datosModificar.getIdUsuarioProgreso());
                nuevoProgreso.setIdCursoProgreso(datosModificar.getIdCursoProgreso());
                return nuevoProgreso;
        });    
        progreso.setPorcentajeProgreso(datosModificar.getPorcentajeProgreso());
        progreso.setUltimaActividadProgreso(new Date());
        return progresoRepo.save(progreso);
    }

}
