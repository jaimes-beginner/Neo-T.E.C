package com.duoc.api_contenidos.assemblers;

/*------------------------------------------*/

// Importaciones
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import com.duoc.api_contenidos.controller.ContenidoController;
import com.duoc.api_contenidos.model.entity.Contenido;

/*------------------------------------------*/

/* Haciendo el objeto CONTENIDO con enlaces HATEOAS, para que devuelva los 
datos corresponsientes además de enlaces a recursos que estén relacionados */

@Component
public class ContenidoModelAssembler implements RepresentationModelAssembler<Contenido, EntityModel<Contenido>> {

    @Override
    public EntityModel<Contenido> toModel(Contenido contenido) {
        return EntityModel.of(
            contenido,

            /* Todos los métodos que se encuentren aquí deben estar bien definidos
            además de que deben coincidir en su FIRMA, es decir: que estén exactamente iguales en nombre 
            del método, tipo de datos, etc. Para que methodOn pueda ejecutarlos correctamente */ 

            // Enlace a este mismo contenido
            linkTo(methodOn(ContenidoController.class).obtenerUno(contenido.getIdContenido())).withSelfRel(),

            // Link al listado de todos los contenidos
            linkTo(methodOn(ContenidoController.class).obtenerTodos()).withRel("todos")

        );
    }
}
