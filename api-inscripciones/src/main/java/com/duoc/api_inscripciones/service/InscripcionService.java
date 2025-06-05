package com.duoc.api_inscripciones.service;

/*------------------------------------------*/

// Importaciones
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import org.springframework.web.server.ResponseStatusException;
import com.duoc.api_inscripciones.model.entity.Inscripcion;
import com.duoc.api_inscripciones.model.request.CursoDTO;
import com.duoc.api_inscripciones.model.request.UsuarioDTO;
import com.duoc.api_inscripciones.repository.InscripcionRepository;

/*------------------------------------------*/

@Service
public class InscripcionService {

    // Autowired
    @Autowired
    private InscripcionRepository inscripcionRepo;

    // Elementos para el WebClient (comunicación entre microservicios)
    private final WebClient webClient;
    InscripcionService(WebClient webClient) {
        this.webClient = webClient;
    }



    // Obtener todos los inscripción
    public List<Inscripcion> obtenerTodos() {
        return inscripcionRepo.findAll();
    }

    // Obtener todas las inscripciones por el id del usuario
    public List<Inscripcion> obtenerPorUsuario(int idUsuario) {
        return inscripcionRepo.findAllByIdUsuarioInscripcion(idUsuario);
    }

    // Obtener una inscripcion por su id 
    public Inscripcion obtenerUno(int id) {
        return inscripcionRepo.findById(id).orElse(null);
    }

    // Verificar si el usuario existe, devolviendo true o false
    public Boolean existenciaUsuario(int idUsuario) {
        try {
            return Boolean.TRUE.equals(webClient.get().uri("http://localhost:8080/users/" + idUsuario)
            .retrieve().bodyToMono(UsuarioDTO.class).block() != null);
            
        }  catch (WebClientResponseException.NotFound e) {
            return false;
        }
    }

    // Verificar si el curso existe, devolviendo true o false
    public Boolean existenciaCurso(int idCurso) {
        try {
            return Boolean.TRUE.equals(webClient.get().uri("http://localhost:8081/users/" + idCurso)
            .retrieve().bodyToMono(CursoDTO.class).block() != null);
            
        }  catch (WebClientResponseException.NotFound e) {
            return false;
        }
    }

    // Inscribir a un usuario, verificando si en primer lugar existe el mismo y el curso
    public Inscripcion agregar(int idUsuario, int idCurso) {
        Inscripcion inscripcion = new Inscripcion();

        if(!existenciaUsuario(idUsuario)) {
            throw new IllegalArgumentException("Usuario no encontrado");
        }
        if(!existenciaCurso(idCurso)) {
            throw new IllegalArgumentException("Curso no encontrado");
        }

        try {
            inscripcion.setFechaInscripcion(new Date());
            inscripcion.setEstadoInscripcion(true);
            inscripcion.setIdUsuarioInscripcion(idUsuario);
            inscripcion.setIdCursoInscripcion(idCurso);
            return inscripcionRepo.save(inscripcion);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    // Eliminar una inscripción
    public void eliminar(int id) {
        Inscripcion inscripcion = obtenerUno(id);
        if(inscripcion == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        inscripcionRepo.delete(inscripcion);
    }

}
