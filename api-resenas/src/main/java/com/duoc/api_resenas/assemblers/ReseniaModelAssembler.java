package com.duoc.api_resenas.assemblers;

/*------------------------------------------*/

// Importaciones
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import com.duoc.api_resenas.controller.ReseniaController;
import com.duoc.api_resenas.models.entities.Resenia;

/*------------------------------------------*/

/* Haciendo el objeto RESENIA con enlaces HATEOAS, para que devuelva los 
datos corresponsientes además de enlaces a recursos que estén relacionados */


@Component
public class ReseniaModelAssembler implements RepresentationModelAssembler<Resenia, EntityModel<Resenia>> {

    @Override
    public EntityModel<com.duoc.api_resenas.models.entities.Resenia> toModel(Resenia resenia) {
        return EntityModel.of(resenia,
        
            /* Todos los métodos que se encuentren aquí deben estar bien definidos
            además de que deben coincidir en su FIRMA, es decir: que estén exactamente iguales en nombre 
            del método, tipo de datos, etc. Para que methodOn pueda ejecutarlos correctamente */ 

            // Self link
            linkTo(methodOn(ReseniaController.class).obtenerUno(resenia.getIdResenia())).withSelfRel(),

            // Link al usuario que hizo la reseña
            Link.of("http://localhost:8080/users/" + resenia.getIdCursoResenia()).withRel("usuario"),

            // Link al curso que se está reseñando
            Link.of("http://localhost:8081/courses/" + resenia.getIdCursoResenia()).withRel("curso")

        );
    }
}