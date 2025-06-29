package com.duoc.api_inscripciones.assemblers;

/*------------------------------------------*/

// Importaciones
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import com.duoc.api_inscripciones.controller.InscripcionController;
import com.duoc.api_inscripciones.model.entity.Inscripcion;

/*------------------------------------------*/

/* Haciendo el objeto INSCRIPCION con enlaces HATEOAS, para que devuelva los 
datos corresponsientes además de enlaces a recursos que estén relacionados */

@Component
public class InscripcionModelAssembler implements RepresentationModelAssembler<Inscripcion, EntityModel<Inscripcion>> {

    @Override
    public EntityModel<Inscripcion> toModel(Inscripcion inscripcion) {
        return EntityModel.of(inscripcion,

            /* Todos los métodos que se encuentren aquí deben estar bien definidos
            además de que deben coincidir en su FIRMA, es decir: que estén exactamente iguales en nombre 
            del método, tipo de datos, etc. Para que methodOn pueda ejecutarlos correctamente */ 

            // Self link
            linkTo(methodOn(InscripcionController.class).obtenerUno(inscripcion.getIdInscripcion())).withSelfRel(),

            // Enlace a todos los progresos según el usuario
            linkTo(methodOn(InscripcionController.class).obtenerPorUsuario(inscripcion.getIdUsuarioInscripcion())).withRel("usuario")

        );
    }
}

