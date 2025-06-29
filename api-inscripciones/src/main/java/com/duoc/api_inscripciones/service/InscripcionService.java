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
import com.duoc.api_inscripciones.model.request.InscripcionCreate;
import com.duoc.api_inscripciones.model.request.UsuarioDTO;
import com.duoc.api_inscripciones.repository.InscripcionRepository;

/*------------------------------------------*/

@Service
public class InscripcionService {

    // Autowired
    @Autowired
    private InscripcionRepository inscripcionRepo;

    // Autowired
    @Autowired
    private WebClient usuarioWebClient;

    // Autowired
    @Autowired
    private WebClient cursoWebClient;


    
    // Obtener todos los inscripción
    public List<Inscripcion> obtenerTodos() {
        return inscripcionRepo.findAll();
    }



    // Obtener todas las inscripciones por el ID del usuario
    public List<Inscripcion> obtenerPorUsuario(int idUsuario) {
        return inscripcionRepo.findAllByIdUsuarioInscripcion(idUsuario);
    }



    // Obtener todas las inscripciones que tuvo un curso, según su ID
    public List<Inscripcion> obtenerPorCurso(int idCurso) {
        return inscripcionRepo.findAllByIdCursoInscripcion(idCurso);
    }



    // Obtener una inscripcion por su id 
    public Inscripcion obtenerUno(int id) {
        return inscripcionRepo.findById(id).orElse(null);
    }



    // Verificar si el usuario existe
    public Boolean existenciaUsuario(int idUsuario) {
        try {
            System.out.println("Verificando existencia del usuario con ID: " + idUsuario);
            
            UsuarioDTO usuario = usuarioWebClient.get()
                .uri("/users/{idUsuario}", idUsuario)
                .retrieve()
                .bodyToMono(UsuarioDTO.class)
                .block();

            System.out.println("Resultado de llamada al microservicio de usuarios: " + usuario);
            return usuario != null;

        } catch (WebClientResponseException.NotFound e) {
            System.out.println("Usuario no encontrado (404) para ID: " + idUsuario);
            return false;
        } catch (Exception e) {
            System.out.println("Error al consultar microservicio de usuarios: " + e.getMessage());
            throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "Error consultando al microservicio de usuarios");
        }
    }



    // Verificar si el curso existe
    public Boolean existenciaCurso(int idCurso) {
        try {
            CursoDTO curso = cursoWebClient.get()
                .uri("/courses/{idCurso}", idCurso).retrieve().bodyToMono(CursoDTO.class).block();

            return curso != null;
        } catch (WebClientResponseException.NotFound e) {
            return false;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "Error consultando al microservicio de cursos");
        }
    }



    // Inscribir a un usuario, verificando si en primer lugar existe el mismo y el curso
    public Inscripcion agregar(InscripcionCreate datosCrear) {
        Inscripcion inscripcion = new Inscripcion();

        // Verificar que no haya inscripciones duplicadas
        if(inscripcionRepo.existsByIdUsuarioInscripcionAndIdCursoInscripcion(datosCrear.getIdUsuario(),datosCrear.getIdCurso())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Ya estás inscrito");
        }

        // Verificando los datos del InscripcionCreate
        if(!existenciaUsuario(datosCrear.getIdUsuario())) {
            throw new IllegalArgumentException("Usuario no encontrado");
        }

        // Verificando los datos del InscripcionCreate
        if(!existenciaCurso(datosCrear.getIdCurso())) {
            throw new IllegalArgumentException("Curso no encontrado");
        }

        // Setteando los datos los cursos
        try {
            inscripcion.setFechaInscripcion(new Date());
            inscripcion.setEstadoInscripcion(true);
            inscripcion.setIdUsuarioInscripcion(datosCrear.getIdUsuario());
            inscripcion.setIdCursoInscripcion(datosCrear.getIdCurso());
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
