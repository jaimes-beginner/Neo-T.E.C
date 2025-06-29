package com.duoc.api_inscripciones.assemblers;

/*------------------------------------------*/

// Importaciones
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import com.duoc.api_inscripciones.model.entity.Progreso;
import com.duoc.api_inscripciones.controller.ProgresoController;
import org.springframework.hateoas.Link;

/*------------------------------------------*/

/* Haciendo el objeto PROGRESO con enlaces HATEOAS, para que devuelva los 
datos corresponsientes además de enlaces a recursos que estén relacionados */

@Component
public class ProgresoModelAssembler implements RepresentationModelAssembler<Progreso, EntityModel<Progreso>> {

    @Override
    public EntityModel<Progreso> toModel(Progreso progreso) {
        return EntityModel.of(
            progreso,

            /* Todos los métodos que se encuentren aquí deben estar bien definidos
            además de que deben coincidir en su FIRMA, es decir: que estén exactamente iguales en nombre 
            del método, tipo de datos, etc. Para que methodOn pueda ejecutarlos correctamente */ 

            // Enlace a este mismo recurso (self)
            linkTo(methodOn(ProgresoController.class).obtenerUno(progreso.getIdProgreso())).withSelfRel(),

            // Enlace a el progreso del usuario
            Link.of("http://localhost:8087/progress/user" + progreso.getIdUsuarioProgreso() + "/course/" + progreso.getIdCursoProgreso()).withRel("usuario"),

            // Enlace a todos los progresos 
            Link.of("http://localhost:8082/contents/all").withRel("contenidos")
            
        );
    }
}