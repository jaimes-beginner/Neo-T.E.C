package com.duoc.api_contenidos.assemblers;

/*------------------------------------------*/

// Importaciones
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import com.duoc.api_contenidos.controller.EvaluacionController;
import com.duoc.api_contenidos.model.entity.Evaluacion;

/*------------------------------------------*/

/* Haciendo el objeto EVALUACION con enlaces HATEOAS, para que devuelva los 
datos corresponsientes además de enlaces a recursos que estén relacionados */


@Component
public class EvaluacionModelAssembler implements RepresentationModelAssembler<Evaluacion, EntityModel<Evaluacion>> {

    @Override
    public EntityModel<Evaluacion> toModel(Evaluacion evaluacion) {
        return EntityModel.of(
            evaluacion,

            /* Todos los métodos que se encuentren aquí deben estar bien definidos
            además de que deben coincidir en su FIRMA, es decir: que estén exactamente iguales en nombre 
            del método, tipo de datos, etc. Para que methodOn pueda ejecutarlos correctamente */ 

            // Enlace a esta evaluación específica
            linkTo(methodOn(EvaluacionController.class).obtenerUno(evaluacion.getIdEvaluacion())).withSelfRel(),

            // Enlace a todas las evaluaciones
            linkTo(methodOn(EvaluacionController.class).obtenerTodos()).withRel("todas"),

            // Enlace al contenido al que pertenece esta evaluación (por el ID de contenido)
            linkTo(methodOn(EvaluacionController.class).obtenerPorContenido(evaluacion.getContenido().getIdContenido())).withRel("contenido")

        );
    }
}
