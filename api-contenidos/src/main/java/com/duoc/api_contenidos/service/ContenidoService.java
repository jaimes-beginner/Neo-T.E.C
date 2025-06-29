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
    
    // Atributos
    @Autowired
    private ContenidoRepository contenidoRepo;

    // Atributos
    @Autowired
    private WebClient cursosWebClient;



    // OBTENER TODOS: devuelve todos los contenidos
    public List<Contenido> obtenerTodos() {
        return contenidoRepo.findAll();
    }



    // OBTENER UNO: devuelve un contenido por su ID
    public Contenido obtenerUno(int id) {
        return contenidoRepo.findById(id).orElse(null);
    }



    // AGREGAR: agrega un nuevo contenido según los datos (datosCrear)
    public Contenido agregar(ContenidoCreate datosCrear) {
        Contenido contenido = new Contenido();
        try {
            // Verificar que este curso exista
            cursosWebClient.get().uri("/courses/" + datosCrear.getIdCursoContenido())
                            .retrieve().toBodilessEntity().block();

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Curso no encontrado");
        }

        try {
            // Setteando los datos del contenido de cursos
            contenido.setIdCursoContenido(datosCrear.getIdCursoContenido());
            contenido.setTipoContenido(datosCrear.getTipoContenido());
            contenido.setTituloContenido(datosCrear.getTituloContenido());
            contenido.setUrlContenido(datosCrear.getUrlContenido());
            return contenidoRepo.save(contenido);

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }



    // ELIMINAR: elimina un contenido por su ID
    public void eliminar(int id) {
        Contenido contenido = obtenerUno(id);
        if(contenido == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        contenidoRepo.deleteById(id);
    }



    // MODIFICAR: modifica un contenido según los datos (datosModificar)
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
