package com.duoc.api_contenidos.service;

/*------------------------------------------*/

// Importaciones
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ResponseStatusException;
import com.duoc.api_contenidos.model.entity.Contenido;
import com.duoc.api_contenidos.model.request.ContenidoCreate;
import com.duoc.api_contenidos.model.request.ContenidoUpdate;
import com.duoc.api_contenidos.model.request.CursoDTO;
import com.duoc.api_contenidos.repository.ContenidoRepository;

/*------------------------------------------*/

@Service
public class ContenidoService {

    private final WebClient cursosWebClient;
    
    // Atributos
    @Autowired
    private ContenidoRepository contenidoRepo;

    ContenidoService(WebClient cursosWebClient) {
        this.cursosWebClient = cursosWebClient;
    }

    // Obtener todos los contenidos
    public List<Contenido> obtenerTodos() {
        return contenidoRepo.findAll();
    }

    // Obtener un contenido por su id 
    public Contenido obtenerUno(int id) {
        return contenidoRepo.findById(id).orElse(null);
    }

    // Obtener todos los contenidos por el id del curso
    public CursoDTO obtenerPorCurso(int idContenido) {
        Contenido contenido = obtenerUno(idContenido);
        if(contenido == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        int idCurso = contenido.getIdCursoContenido();

        // Devolverá el resultado de la llamada HTTP hacia el microservicio Cursos
        return cursosWebClient.get().uri("/courses/" + idCurso)
                .retrieve().bodyToMono(CursoDTO.class).block();
    }


    // Agregar un contenido
    public Contenido agregar(ContenidoCreate datosCrear) {
        Contenido contenido = new Contenido();
        try {
            contenido.setIdCursoContenido(datosCrear.getIdCursoContenido());
            contenido.setTipoContenido(datosCrear.getTipoContenido());
            contenido.setTituloContenido(datosCrear.getTituloContenido());
            contenido.setUrlContenido(datosCrear.getUrlContenido());
            return contenidoRepo.save(contenido);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    // Eliminar un contenido
    public void eliminar(int id) {
        Contenido contenido = obtenerUno(id);
        if(contenido == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        contenidoRepo.deleteById(id);
    }

    // Modificar un contenido
    public Contenido modificar(ContenidoUpdate datosModificar) {
        Contenido contenido = obtenerUno(datosModificar.getIdContenido());
        if(contenido == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        if(datosModificar.getIdCursoContenido() != null) {
            contenido.setIdCursoContenido(datosModificar.getIdCursoContenido());
        }
        if(datosModificar.getTipoContenido() != null) {
            contenido.setTipoContenido(datosModificar.getTipoContenido());
        }
        if(datosModificar.getTituloContenido() != null) {
            contenido.setTituloContenido(datosModificar.getTituloContenido());
        }
        if(datosModificar.getUrlContenido() != null) {
            contenido.setUrlContenido(datosModificar.getUrlContenido());
        }
        return contenidoRepo.save(contenido);
    }

}
